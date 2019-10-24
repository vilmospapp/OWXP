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

package com.liferay.grow.gamification.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.grow.gamification.model.BadgeType;
import com.liferay.grow.gamification.model.BadgeTypeModel;
import com.liferay.grow.gamification.model.BadgeTypeSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the BadgeType service. Represents a row in the &quot;gamification_BadgeType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link BadgeTypeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BadgeTypeImpl}.
 * </p>
 *
 * @author Vilmos Papp
 * @see BadgeTypeImpl
 * @see BadgeType
 * @see BadgeTypeModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class BadgeTypeModelImpl extends BaseModelImpl<BadgeType>
	implements BadgeTypeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a badge type model instance should use the {@link BadgeType} interface instead.
	 */
	public static final String TABLE_NAME = "gamification_BadgeType";
	public static final Object[][] TABLE_COLUMNS = {
			{ "badgeTypeId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "type_", Types.VARCHAR },
			{ "assignableFrom", Types.TIMESTAMP },
			{ "assignableTo", Types.TIMESTAMP },
			{ "fileEntryId", Types.BIGINT },
			{ "system", Types.BOOLEAN },
			{ "templateHTML", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("badgeTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assignableFrom", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assignableTo", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("system", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("templateHTML", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table gamification_BadgeType (badgeTypeId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,type_ VARCHAR(75) null,assignableFrom DATE null,assignableTo DATE null,fileEntryId LONG,system BOOLEAN,templateHTML STRING null)";
	public static final String TABLE_SQL_DROP = "drop table gamification_BadgeType";
	public static final String ORDER_BY_JPQL = " ORDER BY badgeType.badgeTypeId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY gamification_BadgeType.badgeTypeId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.grow.gamification.model.BadgeType"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.grow.gamification.model.BadgeType"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.grow.gamification.model.BadgeType"),
			true);
	public static final long ASSIGNABLETO_COLUMN_BITMASK = 1L;
	public static final long BADGETYPEID_COLUMN_BITMASK = 2L;
	public static final long TYPE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static BadgeType toModel(BadgeTypeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		BadgeType model = new BadgeTypeImpl();

		model.setBadgeTypeId(soapModel.getBadgeTypeId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setType(soapModel.getType());
		model.setAssignableFrom(soapModel.getAssignableFrom());
		model.setAssignableTo(soapModel.getAssignableTo());
		model.setFileEntryId(soapModel.getFileEntryId());
		model.setSystem(soapModel.isSystem());
		model.setTemplateHTML(soapModel.getTemplateHTML());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<BadgeType> toModels(BadgeTypeSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<BadgeType> models = new ArrayList<BadgeType>(soapModels.length);

		for (BadgeTypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.grow.gamification.model.BadgeType"));

	public BadgeTypeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _badgeTypeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBadgeTypeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _badgeTypeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BadgeType.class;
	}

	@Override
	public String getModelClassName() {
		return BadgeType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("badgeTypeId", getBadgeTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("type", getType());
		attributes.put("assignableFrom", getAssignableFrom());
		attributes.put("assignableTo", getAssignableTo());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("system", isSystem());
		attributes.put("templateHTML", getTemplateHTML());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long badgeTypeId = (Long)attributes.get("badgeTypeId");

		if (badgeTypeId != null) {
			setBadgeTypeId(badgeTypeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date assignableFrom = (Date)attributes.get("assignableFrom");

		if (assignableFrom != null) {
			setAssignableFrom(assignableFrom);
		}

		Date assignableTo = (Date)attributes.get("assignableTo");

		if (assignableTo != null) {
			setAssignableTo(assignableTo);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean system = (Boolean)attributes.get("system");

		if (system != null) {
			setSystem(system);
		}

		String templateHTML = (String)attributes.get("templateHTML");

		if (templateHTML != null) {
			setTemplateHTML(templateHTML);
		}
	}

	@JSON
	@Override
	public long getBadgeTypeId() {
		return _badgeTypeId;
	}

	@Override
	public void setBadgeTypeId(long badgeTypeId) {
		_columnBitmask |= BADGETYPEID_COLUMN_BITMASK;

		if (!_setOriginalBadgeTypeId) {
			_setOriginalBadgeTypeId = true;

			_originalBadgeTypeId = _badgeTypeId;
		}

		_badgeTypeId = badgeTypeId;
	}

	public long getOriginalBadgeTypeId() {
		return _originalBadgeTypeId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	@JSON
	@Override
	public Date getAssignableFrom() {
		return _assignableFrom;
	}

	@Override
	public void setAssignableFrom(Date assignableFrom) {
		_assignableFrom = assignableFrom;
	}

	@JSON
	@Override
	public Date getAssignableTo() {
		return _assignableTo;
	}

	@Override
	public void setAssignableTo(Date assignableTo) {
		_columnBitmask |= ASSIGNABLETO_COLUMN_BITMASK;

		if (_originalAssignableTo == null) {
			_originalAssignableTo = _assignableTo;
		}

		_assignableTo = assignableTo;
	}

	public Date getOriginalAssignableTo() {
		return _originalAssignableTo;
	}

	@JSON
	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	@JSON
	@Override
	public boolean getSystem() {
		return _system;
	}

	@JSON
	@Override
	public boolean isSystem() {
		return _system;
	}

	@Override
	public void setSystem(boolean system) {
		_system = system;
	}

	@JSON
	@Override
	public String getTemplateHTML() {
		if (_templateHTML == null) {
			return "";
		}
		else {
			return _templateHTML;
		}
	}

	@Override
	public void setTemplateHTML(String templateHTML) {
		_templateHTML = templateHTML;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			BadgeType.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BadgeType toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (BadgeType)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		BadgeTypeImpl badgeTypeImpl = new BadgeTypeImpl();

		badgeTypeImpl.setBadgeTypeId(getBadgeTypeId());
		badgeTypeImpl.setGroupId(getGroupId());
		badgeTypeImpl.setCompanyId(getCompanyId());
		badgeTypeImpl.setUserId(getUserId());
		badgeTypeImpl.setUserName(getUserName());
		badgeTypeImpl.setCreateDate(getCreateDate());
		badgeTypeImpl.setModifiedDate(getModifiedDate());
		badgeTypeImpl.setType(getType());
		badgeTypeImpl.setAssignableFrom(getAssignableFrom());
		badgeTypeImpl.setAssignableTo(getAssignableTo());
		badgeTypeImpl.setFileEntryId(getFileEntryId());
		badgeTypeImpl.setSystem(isSystem());
		badgeTypeImpl.setTemplateHTML(getTemplateHTML());

		badgeTypeImpl.resetOriginalValues();

		return badgeTypeImpl;
	}

	@Override
	public int compareTo(BadgeType badgeType) {
		long primaryKey = badgeType.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BadgeType)) {
			return false;
		}

		BadgeType badgeType = (BadgeType)obj;

		long primaryKey = badgeType.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		BadgeTypeModelImpl badgeTypeModelImpl = this;

		badgeTypeModelImpl._originalBadgeTypeId = badgeTypeModelImpl._badgeTypeId;

		badgeTypeModelImpl._setOriginalBadgeTypeId = false;

		badgeTypeModelImpl._setModifiedDate = false;

		badgeTypeModelImpl._originalType = badgeTypeModelImpl._type;

		badgeTypeModelImpl._originalAssignableTo = badgeTypeModelImpl._assignableTo;

		badgeTypeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<BadgeType> toCacheModel() {
		BadgeTypeCacheModel badgeTypeCacheModel = new BadgeTypeCacheModel();

		badgeTypeCacheModel.badgeTypeId = getBadgeTypeId();

		badgeTypeCacheModel.groupId = getGroupId();

		badgeTypeCacheModel.companyId = getCompanyId();

		badgeTypeCacheModel.userId = getUserId();

		badgeTypeCacheModel.userName = getUserName();

		String userName = badgeTypeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			badgeTypeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			badgeTypeCacheModel.createDate = createDate.getTime();
		}
		else {
			badgeTypeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			badgeTypeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			badgeTypeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		badgeTypeCacheModel.type = getType();

		String type = badgeTypeCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			badgeTypeCacheModel.type = null;
		}

		Date assignableFrom = getAssignableFrom();

		if (assignableFrom != null) {
			badgeTypeCacheModel.assignableFrom = assignableFrom.getTime();
		}
		else {
			badgeTypeCacheModel.assignableFrom = Long.MIN_VALUE;
		}

		Date assignableTo = getAssignableTo();

		if (assignableTo != null) {
			badgeTypeCacheModel.assignableTo = assignableTo.getTime();
		}
		else {
			badgeTypeCacheModel.assignableTo = Long.MIN_VALUE;
		}

		badgeTypeCacheModel.fileEntryId = getFileEntryId();

		badgeTypeCacheModel.system = isSystem();

		badgeTypeCacheModel.templateHTML = getTemplateHTML();

		String templateHTML = badgeTypeCacheModel.templateHTML;

		if ((templateHTML != null) && (templateHTML.length() == 0)) {
			badgeTypeCacheModel.templateHTML = null;
		}

		return badgeTypeCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{badgeTypeId=");
		sb.append(getBadgeTypeId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", assignableFrom=");
		sb.append(getAssignableFrom());
		sb.append(", assignableTo=");
		sb.append(getAssignableTo());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append(", system=");
		sb.append(isSystem());
		sb.append(", templateHTML=");
		sb.append(getTemplateHTML());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.grow.gamification.model.BadgeType");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>badgeTypeId</column-name><column-value><![CDATA[");
		sb.append(getBadgeTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assignableFrom</column-name><column-value><![CDATA[");
		sb.append(getAssignableFrom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assignableTo</column-name><column-value><![CDATA[");
		sb.append(getAssignableTo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>system</column-name><column-value><![CDATA[");
		sb.append(isSystem());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>templateHTML</column-name><column-value><![CDATA[");
		sb.append(getTemplateHTML());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = BadgeType.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			BadgeType.class, ModelWrapper.class
		};
	private long _badgeTypeId;
	private long _originalBadgeTypeId;
	private boolean _setOriginalBadgeTypeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _type;
	private String _originalType;
	private Date _assignableFrom;
	private Date _assignableTo;
	private Date _originalAssignableTo;
	private long _fileEntryId;
	private boolean _system;
	private String _templateHTML;
	private long _columnBitmask;
	private BadgeType _escapedModel;
}