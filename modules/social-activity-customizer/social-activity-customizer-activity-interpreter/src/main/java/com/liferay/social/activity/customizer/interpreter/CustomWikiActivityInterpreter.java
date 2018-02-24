/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.social.activity.customizer.interpreter;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.model.WikiPageResource;
import com.liferay.wiki.service.WikiPageLocalService;
import com.liferay.wiki.service.WikiPageResourceLocalService;
import com.liferay.wiki.service.permission.WikiPagePermissionChecker;
import com.liferay.wiki.social.WikiActivityKeys;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Istvan Sajtos
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI,
		"service.ranking:Integer=100"
	},
	service = SocialActivityInterpreter.class
)
public class CustomWikiActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	public String getSelector() {
		return _SELECTOR;
	}

	@Override
	protected String addNoSuchEntryRedirect(
			String url, String className, long classPK,
			ServiceContext serviceContext)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			serviceContext.getLiferayPortletResponse();

		if (liferayPortletResponse == null) {
			return null;
		}

		Group group = _groupLocalService.fetchFriendlyURLGroup(
			serviceContext.getCompanyId(), "/guest");

		long plid = _layoutLocalService.getDefaultPlid(
			group.getGroupId(), true);

		WikiPage page = _wikiPageLocalService.getPage(classPK);

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			plid, WikiPortletKeys.WIKI, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/wiki/view");
		portletURL.setParameter("nodeId", String.valueOf(page.getNodeId()));
		portletURL.setParameter("title", page.getTitle());
		portletURL.setWindowState(WindowState.MAXIMIZED);

		String viewEntryURL = portletURL.toString();

		if (Validator.isNotNull(viewEntryURL)) {
			return viewEntryURL;
		}

		return _http.setParameter(url, "noSuchEntryRedirect", viewEntryURL);
	}

	protected String getAttachmentTitle(
			SocialActivity activity, WikiPageResource pageResource,
			ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if ((activityType == SocialActivityConstants.TYPE_ADD_ATTACHMENT) ||
			(activityType ==
				SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH) ||
			(activityType ==
				SocialActivityConstants.TYPE_RESTORE_ATTACHMENT_FROM_TRASH)) {

			String link = null;

			FileEntry fileEntry = null;

			try {
				long fileEntryId = GetterUtil.getLong(
					activity.getExtraDataValue("fileEntryId"));

				fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
					fileEntryId);
			}
			catch (NoSuchModelException nsme) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(nsme, nsme);
				}
			}

			String fileEntryTitle = activity.getExtraDataValue(
				"fileEntryTitle");

			if ((fileEntry != null) && !fileEntry.isInTrash()) {
				StringBundler sb = new StringBundler(9);

				sb.append(serviceContext.getPathMain());
				sb.append("/wiki/get_page_attachment?p_l_id=");
				sb.append(serviceContext.getPlid());
				sb.append("&nodeId=");
				sb.append(pageResource.getNodeId());
				sb.append("&title=");
				sb.append(_http.encodeURL(pageResource.getTitle()));
				sb.append("&fileName=");
				sb.append(fileEntryTitle);

				link = sb.toString();
			}

			return wrapLink(link, fileEntryTitle);
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		// Date

		Date createDate = DateUtil.newDate(activity.getCreateDate());

		// View count

		String className = WikiPage.class.getName();

		long classPK = activity.getClassPK();

		WikiPageResource pageResource =
			_wikiPageResourceLocalService.getWikiPageResource(classPK);

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			className, pageResource.getPrimaryKey());

		int viewCount = assetEntry.getViewCount();

		// Excerpt

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		AssetRenderer<?> assetRenderer = null;

		if (assetRendererFactory != null) {
			assetRenderer = assetRendererFactory.getAssetRenderer(classPK);
		}

		String summary = assetRenderer.getSearchSummary(
			serviceContext.getLocale());

		// Tags

		List<AssetTag> tags = assetEntry.getTags();

		return _getFormattedBody(
			createDate, viewCount, summary, tags, activity.getUserId(),
			serviceContext);
	}

	@Override
	protected String getPath(
		SocialActivity activity, ServiceContext serviceContext) {

		return "/wiki/find_page?pageResourcePrimKey=" + activity.getClassPK();
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String entryTitle = getEntryTitle(activity, serviceContext);

		String link = getLink(activity, serviceContext);

		Object[] titleArguments = getTitleArguments(
			null, activity, link, entryTitle, serviceContext);

		String titlePattern = "<h5><strong>{0}\t{1}</strong></h5>";

		return serviceContext.translate(titlePattern, titleArguments);
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		WikiPageResource pageResource =
			_wikiPageResourceLocalService.fetchWikiPageResource(
				activity.getClassPK());

		if (pageResource == null) {
			return null;
		}

		String activityText =
			"<font color=\"black\">" + _getActivityText(activity) + "</font>";

		title = wrapLink(link, title);

		return new Object[] {activityText, title};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if ((activityType == WikiActivityKeys.ADD_COMMENT) ||
			(activityType == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-add-comment";
			}
			else {
				return "activity-wiki-page-add-comment-in";
			}
		}
		else if (activityType == WikiActivityKeys.ADD_PAGE) {
			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-add-page";
			}
			else {
				return "activity-wiki-page-add-page-in";
			}
		}
		else if (activityType == SocialActivityConstants.TYPE_ADD_ATTACHMENT) {
			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-add-attachment";
			}
			else {
				return "activity-wiki-page-add-attachment-in";
			}
		}
		else if (activityType ==
					SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH) {

			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-remove-attachment";
			}
			else {
				return "activity-wiki-page-remove-attachment-in";
			}
		}
		else if (activityType ==
					SocialActivityConstants.
						TYPE_RESTORE_ATTACHMENT_FROM_TRASH) {

			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-restore-attachment";
			}
			else {
				return "activity-wiki-page-restore-attachment-in";
			}
		}
		else if (activityType == SocialActivityConstants.TYPE_MOVE_TO_TRASH) {
			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-move-to-trash";
			}
			else {
				return "activity-wiki-page-move-to-trash-in";
			}
		}
		else if (activityType ==
					SocialActivityConstants.TYPE_RESTORE_FROM_TRASH) {

			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-restore-from-trash";
			}
			else {
				return "activity-wiki-page-restore-from-trash-in";
			}
		}
		else if (activityType == WikiActivityKeys.UPDATE_PAGE) {
			if (Validator.isNull(groupName)) {
				return "activity-wiki-page-update-page";
			}
			else {
				return "activity-wiki-page-update-page-in";
			}
		}

		return null;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		if (!WikiPagePermissionChecker.contains(
				permissionChecker, activity.getClassPK(), ActionKeys.VIEW)) {

			return false;
		}

		int activityType = activity.getType();

		if (activityType == WikiActivityKeys.UPDATE_PAGE) {
			WikiPageResource pageResource =
				_wikiPageResourceLocalService.getPageResource(
					activity.getClassPK());

			double version = GetterUtil.getDouble(
				activity.getExtraDataValue("version"));

			WikiPage page = _wikiPageLocalService.getPage(
				pageResource.getNodeId(), pageResource.getTitle(), version);

			if (!page.isApproved() &&
				!WikiPagePermissionChecker.contains(
					permissionChecker, activity.getClassPK(),
					ActionKeys.UPDATE)) {

				return false;
			}
		}

		return true;
	}

	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.wiki.web)", unbind = "-"
	)
	protected void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			resourceBundleLoader,
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader());
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Reference(unbind = "-")
	protected void setWikiPageLocalService(
		WikiPageLocalService wikiPageLocalService) {

		_wikiPageLocalService = wikiPageLocalService;
	}

	@Reference(unbind = "-")
	protected void setWikiPageResourceLocalService(
		WikiPageResourceLocalService wikiPageResourceLocalService) {

		_wikiPageResourceLocalService = wikiPageResourceLocalService;
	}

	protected String wrapTagLink(String link, String text) {
		StringBundler sb = new StringBundler(5);

		sb.append("<a class=\"badge badge-default badge-sm\" href=\"");
		sb.append(link);
		sb.append("\">");
		sb.append(text);
		sb.append("</a>");

		return sb.toString();
	}

	private String _getActivityText(SocialActivity activity) {
		int activityType = activity.getType();

		String activityText = null;

		if ((activityType == WikiActivityKeys.ADD_COMMENT) ||
			(activityType == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			activityText = "Commented";
		}
		else if (activityType == WikiActivityKeys.ADD_PAGE) {
			activityText = "Created";
		}
		else if (activityType == SocialActivityConstants.TYPE_MOVE_TO_TRASH) {
			activityText = "Removed";
		}
		else if (activityType ==
					SocialActivityConstants.TYPE_RESTORE_FROM_TRASH) {

			activityText = "Restored";
		}
		else {
			activityText = "Updated";
		}

		return activityText;
	}

	private String _getFormattedBody(
			Date date, int viewCount, String summary, List<AssetTag> tags,
			long userId, ServiceContext serviceContext)
		throws PortalException {

		DateFormat df = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.SHORT, serviceContext.getLocale());

		String dateString = df.format(date);

		String tagRow = StringPool.BLANK;

		for (AssetTag tag : tags) {
			String url = _getTagSearchUrl(tag, userId, serviceContext);

			if (Validator.isNotNull(tagRow)) {
				tagRow = tagRow.concat(StringPool.SPACE);
			}

			tagRow = tagRow.concat(wrapTagLink(url, tag.getName()));
		}

		StringBundler sb = new StringBundler(19);

		sb.append("<h6 class=\"text-default\">");
		sb.append("<p style=\"text-align:left;\">");
		sb.append(dateString);
		sb.append("</h6>");
		sb.append("<h6 class=\"text-default\">");
		sb.append("<span style=\"float:right;\">");
		sb.append(String.valueOf(viewCount));
		sb.append(" views");
		sb.append("</span>");
		sb.append("</p>");
		sb.append("</h6>");
		sb.append("<h6 class=\"text-default\">");
		sb.append(StringUtil.shorten(summary, 200));
		sb.append("</h6>");
		sb.append("<h6 class=\"text-default\">");
		sb.append("<span class=\"taglib-asset-tags-summary\">");
		sb.append(tagRow);
		sb.append("</span>");
		sb.append("</h6>");

		return sb.toString();
	}

	private String _getTagSearchUrl(
			AssetTag tag, long userId, ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		String tagName = tag.getName();

		StringBundler sb = new StringBundler(22);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getCurrentURL());
		sb.append(
			"?p_p_id=com_liferay_portal_search_web_portlet_SearchPortlet");
		sb.append("&p_p_lifecycle=0");
		sb.append("&p_p_state=maximized");
		sb.append("&p_p_mode=view");
		sb.append(
			"&_com_liferay_portal_search_web_portlet_SearchPortlet_mvcPath=");
		sb.append("%2Fsearch.jsp");
		sb.append(
			"&_com_liferay_portal_search_web_portlet_SearchPortlet_cur=1");
		sb.append(
			"&_com_liferay_portal_search_web_portlet_SearchPortlet_keywords=");
		sb.append(tagName);
		sb.append("&_com_liferay_portal_search_web_portlet_SearchPortlet");
		sb.append("_assetTagNames=");
		sb.append(tagName);
		sb.append("&_com_liferay_portal_search_web_portlet_SearchPortlet");
		sb.append("_entryClassName=");
		sb.append(WikiPage.class.getName());
		sb.append(
			"&_com_liferay_portal_search_web_portlet_SearchPortlet_userName=");
		sb.append(HtmlUtil.escape(StringUtil.toLowerCase(user.getFullName())));
		sb.append(
			"&_com_liferay_portal_search_web_portlet_SearchPortlet_groupId=0");
		sb.append(
			"&_com_liferay_portal_search_web_portlet_SearchPortlet_scope=");
		sb.append("everything");

		return sb.toString();
	}

	private static final String[] _CLASS_NAMES = {WikiPage.class.getName()};

	private static final String _SELECTOR = "CUSTOM";

	private static final Log _log = LogFactoryUtil.getLog(
		CustomWikiActivityInterpreter.class);

	private AssetEntryLocalService _assetEntryLocalService;
	private GroupLocalService _groupLocalService;

	@Reference
	private Http _http;

	private LayoutLocalService _layoutLocalService;
	private ResourceBundleLoader _resourceBundleLoader;
	private UserLocalService _userLocalService;
	private WikiPageLocalService _wikiPageLocalService;
	private WikiPageResourceLocalService _wikiPageResourceLocalService;

}