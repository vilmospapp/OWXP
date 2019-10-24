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

import com.liferay.grow.gamification.model.Badge;
import com.liferay.grow.gamification.model.BadgeModel;
import com.liferay.grow.gamification.model.BadgeSoap;

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
 * The base model implementation for the Badge service. Represents a row in the &quot;gamification_Badge&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link BadgeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BadgeImpl}.
 * </p>
 *
 * @author Vilmos Papp
 * @see BadgeImpl
 * @see Badge
 * @see BadgeModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class BadgeModelImpl extends BaseModelImpl<Badge> implements BadgeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a badge model instance should use the {@link Badge} interface instead.
	 */
	public static final String TABLE_NAME = "gamification_Badge";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "badgeId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "badgeTypeId", Types.BIGINT },
			{ "toUserId", Types.BIGINT },
			{ "description", Types.VARCHAR },
			{ "delivered", Types.BOOLEAN },
			{ "deliveredAfter", Types.TIMESTAMP },
			{ "assignedDateId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("badgeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("badgeTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("toUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("delivered", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("deliveredAfter", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assignedDateId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table gamification_Badge (uuid_ VARCHAR(75) null,badgeId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,badgeTypeId LONG,toUserId LONG,description VARCHAR(255) null,delivered BOOLEAN,deliveredAfter DATE null,assignedDateId LONG)";
	public static final String TABLE_SQL_DROP = "drop table gamification_Badge";
	public static final String ORDER_BY_JPQL = " ORDER BY badge.assignedDateId DESC";
	public static final String ORDER_BY_SQL = " ORDER BY gamification_Badge.assignedDateId DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.grow.gamification.model.Badge"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.grow.gamification.model.Badge"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.grow.gamification.model.Badge"),
			true);
	public static final long BADGETYPEID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long TOUSERID_COLUMN_BITMASK = 8L;
	public static final long USERID_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;
	public static final long ASSIGNEDDATEID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Badge toModel(BadgeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Badge model = new BadgeImpl();

		model.setUuid(soapModel.getUuid());
		model.setBadgeId(soapModel.getBadgeId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setBadgeTypeId(soapModel.getBadgeTypeId());
		model.setToUserId(soapModel.getToUserId());
		model.setDescription(soapModel.getDescription());
		model.setDelivered(soapModel.isDelivered());
		model.setDeliveredAfter(soapModel.getDeliveredAfter());
		model.setAssignedDateId(soapModel.getAssignedDateId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Badge> toModels(BadgeSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Badge> models = new ArrayList<Badge>(soapModels.length);

		for (BadgeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.grow.gamification.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.grow.gamification.model.Badge"));

	public BadgeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _badgeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBadgeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _badgeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Badge.class;
	}

	@Override
	public String getModelClassName() {
		return Badge.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("badgeId", getBadgeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("badgeTypeId", getBadgeTypeId());
		attributes.put("toUserId", getToUserId());
		attributes.put("description", getDescription());
		attributes.put("delivered", isDelivered());
		attributes.put("deliveredAfter", getDeliveredAfter());
		attributes.put("assignedDateId", getAssignedDateId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long badgeId = (Long)attributes.get("badgeId");

		if (badgeId != null) {
			setBadgeId(badgeId);
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

		Long badgeTypeId = (Long)attributes.get("badgeTypeId");

		if (badgeTypeId != null) {
			setBadgeTypeId(badgeTypeId);
		}

		Long toUserId = (Long)attributes.get("toUserId");

		if (toUserId != null) {
			setToUserId(toUserId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean delivered = (Boolean)attributes.get("delivered");

		if (delivered != null) {
			setDelivered(delivered);
		}

		Date deliveredAfter = (Date)attributes.get("deliveredAfter");

		if (deliveredAfter != null) {
			setDeliveredAfter(deliveredAfter);
		}

		Long assignedDateId = (Long)attributes.get("assignedDateId");

		if (assignedDateId != null) {
			setAssignedDateId(assignedDateId);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getBadgeId() {
		return _badgeId;
	}

	@Override
	public void setBadgeId(long badgeId) {
		_badgeId = badgeId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

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

	public long getOriginalUserId() {
		return _originalUserId;
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
	public long getToUserId() {
		return _toUserId;
	}

	@Override
	public void setToUserId(long toUserId) {
		_columnBitmask |= TOUSERID_COLUMN_BITMASK;

		if (!_setOriginalToUserId) {
			_setOriginalToUserId = true;

			_originalToUserId = _toUserId;
		}

		_toUserId = toUserId;
	}

	@Override
	public String getToUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getToUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setToUserUuid(String toUserUuid) {
	}

	public long getOriginalToUserId() {
		return _originalToUserId;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public boolean getDelivered() {
		return _delivered;
	}

	@JSON
	@Override
	public boolean isDelivered() {
		return _delivered;
	}

	@Override
	public void setDelivered(boolean delivered) {
		_delivered = delivered;
	}

	@JSON
	@Override
	public Date getDeliveredAfter() {
		return _deliveredAfter;
	}

	@Override
	public void setDeliveredAfter(Date deliveredAfter) {
		_deliveredAfter = deliveredAfter;
	}

	@JSON
	@Override
	public long getAssignedDateId() {
		return _assignedDateId;
	}

	@Override
	public void setAssignedDateId(long assignedDateId) {
		_columnBitmask = -1L;

		_assignedDateId = assignedDateId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Badge.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Badge toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Badge)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		BadgeImpl badgeImpl = new BadgeImpl();

		badgeImpl.setUuid(getUuid());
		badgeImpl.setBadgeId(getBadgeId());
		badgeImpl.setGroupId(getGroupId());
		badgeImpl.setCompanyId(getCompanyId());
		badgeImpl.setUserId(getUserId());
		badgeImpl.setUserName(getUserName());
		badgeImpl.setCreateDate(getCreateDate());
		badgeImpl.setBadgeTypeId(getBadgeTypeId());
		badgeImpl.setToUserId(getToUserId());
		badgeImpl.setDescription(getDescription());
		badgeImpl.setDelivered(isDelivered());
		badgeImpl.setDeliveredAfter(getDeliveredAfter());
		badgeImpl.setAssignedDateId(getAssignedDateId());

		badgeImpl.resetOriginalValues();

		return badgeImpl;
	}

	@Override
	public int compareTo(Badge badge) {
		int value = 0;

		if (getAssignedDateId() < badge.getAssignedDateId()) {
			value = -1;
		}
		else if (getAssignedDateId() > badge.getAssignedDateId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Badge)) {
			return false;
		}

		Badge badge = (Badge)obj;

		long primaryKey = badge.getPrimaryKey();

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
		BadgeModelImpl badgeModelImpl = this;

		badgeModelImpl._originalUuid = badgeModelImpl._uuid;

		badgeModelImpl._originalGroupId = badgeModelImpl._groupId;

		badgeModelImpl._setOriginalGroupId = false;

		badgeModelImpl._originalCompanyId = badgeModelImpl._companyId;

		badgeModelImpl._setOriginalCompanyId = false;

		badgeModelImpl._originalUserId = badgeModelImpl._userId;

		badgeModelImpl._setOriginalUserId = false;

		badgeModelImpl._originalBadgeTypeId = badgeModelImpl._badgeTypeId;

		badgeModelImpl._setOriginalBadgeTypeId = false;

		badgeModelImpl._originalToUserId = badgeModelImpl._toUserId;

		badgeModelImpl._setOriginalToUserId = false;

		badgeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Badge> toCacheModel() {
		BadgeCacheModel badgeCacheModel = new BadgeCacheModel();

		badgeCacheModel.uuid = getUuid();

		String uuid = badgeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			badgeCacheModel.uuid = null;
		}

		badgeCacheModel.badgeId = getBadgeId();

		badgeCacheModel.groupId = getGroupId();

		badgeCacheModel.companyId = getCompanyId();

		badgeCacheModel.userId = getUserId();

		badgeCacheModel.userName = getUserName();

		String userName = badgeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			badgeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			badgeCacheModel.createDate = createDate.getTime();
		}
		else {
			badgeCacheModel.createDate = Long.MIN_VALUE;
		}

		badgeCacheModel.badgeTypeId = getBadgeTypeId();

		badgeCacheModel.toUserId = getToUserId();

		badgeCacheModel.description = getDescription();

		String description = badgeCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			badgeCacheModel.description = null;
		}

		badgeCacheModel.delivered = isDelivered();

		Date deliveredAfter = getDeliveredAfter();

		if (deliveredAfter != null) {
			badgeCacheModel.deliveredAfter = deliveredAfter.getTime();
		}
		else {
			badgeCacheModel.deliveredAfter = Long.MIN_VALUE;
		}

		badgeCacheModel.assignedDateId = getAssignedDateId();

		return badgeCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", badgeId=");
		sb.append(getBadgeId());
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
		sb.append(", badgeTypeId=");
		sb.append(getBadgeTypeId());
		sb.append(", toUserId=");
		sb.append(getToUserId());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", delivered=");
		sb.append(isDelivered());
		sb.append(", deliveredAfter=");
		sb.append(getDeliveredAfter());
		sb.append(", assignedDateId=");
		sb.append(getAssignedDateId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.grow.gamification.model.Badge");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>badgeId</column-name><column-value><![CDATA[");
		sb.append(getBadgeId());
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
			"<column><column-name>badgeTypeId</column-name><column-value><![CDATA[");
		sb.append(getBadgeTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toUserId</column-name><column-value><![CDATA[");
		sb.append(getToUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>delivered</column-name><column-value><![CDATA[");
		sb.append(isDelivered());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deliveredAfter</column-name><column-value><![CDATA[");
		sb.append(getDeliveredAfter());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assignedDateId</column-name><column-value><![CDATA[");
		sb.append(getAssignedDateId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Badge.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Badge.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _badgeId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private long _badgeTypeId;
	private long _originalBadgeTypeId;
	private boolean _setOriginalBadgeTypeId;
	private long _toUserId;
	private long _originalToUserId;
	private boolean _setOriginalToUserId;
	private String _description;
	private boolean _delivered;
	private Date _deliveredAfter;
	private long _assignedDateId;
	private long _columnBitmask;
	private Badge _escapedModel;
}