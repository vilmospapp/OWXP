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

package com.liferay.grow.gamification.service.base;

import com.liferay.grow.gamification.model.Badge;
import com.liferay.grow.gamification.service.BadgeService;
import com.liferay.grow.gamification.service.persistence.BadgePersistence;
import com.liferay.grow.gamification.service.persistence.BadgeTypePersistence;
import com.liferay.grow.gamification.service.persistence.LDatePersistence;
import com.liferay.grow.gamification.service.persistence.SubscriberPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the badge remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.grow.gamification.service.impl.BadgeServiceImpl}.
 * </p>
 *
 * @author Vilmos Papp
 * @see com.liferay.grow.gamification.service.impl.BadgeServiceImpl
 * @see com.liferay.grow.gamification.service.BadgeServiceUtil
 * @generated
 */
public abstract class BadgeServiceBaseImpl extends BaseServiceImpl
	implements BadgeService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.grow.gamification.service.BadgeServiceUtil} to access the badge remote service.
	 */

	/**
	 * Returns the badge local service.
	 *
	 * @return the badge local service
	 */
	public com.liferay.grow.gamification.service.BadgeLocalService getBadgeLocalService() {
		return badgeLocalService;
	}

	/**
	 * Sets the badge local service.
	 *
	 * @param badgeLocalService the badge local service
	 */
	public void setBadgeLocalService(
		com.liferay.grow.gamification.service.BadgeLocalService badgeLocalService) {
		this.badgeLocalService = badgeLocalService;
	}

	/**
	 * Returns the badge remote service.
	 *
	 * @return the badge remote service
	 */
	public BadgeService getBadgeService() {
		return badgeService;
	}

	/**
	 * Sets the badge remote service.
	 *
	 * @param badgeService the badge remote service
	 */
	public void setBadgeService(BadgeService badgeService) {
		this.badgeService = badgeService;
	}

	/**
	 * Returns the badge persistence.
	 *
	 * @return the badge persistence
	 */
	public BadgePersistence getBadgePersistence() {
		return badgePersistence;
	}

	/**
	 * Sets the badge persistence.
	 *
	 * @param badgePersistence the badge persistence
	 */
	public void setBadgePersistence(BadgePersistence badgePersistence) {
		this.badgePersistence = badgePersistence;
	}

	/**
	 * Returns the badge type local service.
	 *
	 * @return the badge type local service
	 */
	public com.liferay.grow.gamification.service.BadgeTypeLocalService getBadgeTypeLocalService() {
		return badgeTypeLocalService;
	}

	/**
	 * Sets the badge type local service.
	 *
	 * @param badgeTypeLocalService the badge type local service
	 */
	public void setBadgeTypeLocalService(
		com.liferay.grow.gamification.service.BadgeTypeLocalService badgeTypeLocalService) {
		this.badgeTypeLocalService = badgeTypeLocalService;
	}

	/**
	 * Returns the badge type remote service.
	 *
	 * @return the badge type remote service
	 */
	public com.liferay.grow.gamification.service.BadgeTypeService getBadgeTypeService() {
		return badgeTypeService;
	}

	/**
	 * Sets the badge type remote service.
	 *
	 * @param badgeTypeService the badge type remote service
	 */
	public void setBadgeTypeService(
		com.liferay.grow.gamification.service.BadgeTypeService badgeTypeService) {
		this.badgeTypeService = badgeTypeService;
	}

	/**
	 * Returns the badge type persistence.
	 *
	 * @return the badge type persistence
	 */
	public BadgeTypePersistence getBadgeTypePersistence() {
		return badgeTypePersistence;
	}

	/**
	 * Sets the badge type persistence.
	 *
	 * @param badgeTypePersistence the badge type persistence
	 */
	public void setBadgeTypePersistence(
		BadgeTypePersistence badgeTypePersistence) {
		this.badgeTypePersistence = badgeTypePersistence;
	}

	/**
	 * Returns the l date local service.
	 *
	 * @return the l date local service
	 */
	public com.liferay.grow.gamification.service.LDateLocalService getLDateLocalService() {
		return lDateLocalService;
	}

	/**
	 * Sets the l date local service.
	 *
	 * @param lDateLocalService the l date local service
	 */
	public void setLDateLocalService(
		com.liferay.grow.gamification.service.LDateLocalService lDateLocalService) {
		this.lDateLocalService = lDateLocalService;
	}

	/**
	 * Returns the l date remote service.
	 *
	 * @return the l date remote service
	 */
	public com.liferay.grow.gamification.service.LDateService getLDateService() {
		return lDateService;
	}

	/**
	 * Sets the l date remote service.
	 *
	 * @param lDateService the l date remote service
	 */
	public void setLDateService(
		com.liferay.grow.gamification.service.LDateService lDateService) {
		this.lDateService = lDateService;
	}

	/**
	 * Returns the l date persistence.
	 *
	 * @return the l date persistence
	 */
	public LDatePersistence getLDatePersistence() {
		return lDatePersistence;
	}

	/**
	 * Sets the l date persistence.
	 *
	 * @param lDatePersistence the l date persistence
	 */
	public void setLDatePersistence(LDatePersistence lDatePersistence) {
		this.lDatePersistence = lDatePersistence;
	}

	/**
	 * Returns the subscriber local service.
	 *
	 * @return the subscriber local service
	 */
	public com.liferay.grow.gamification.service.SubscriberLocalService getSubscriberLocalService() {
		return subscriberLocalService;
	}

	/**
	 * Sets the subscriber local service.
	 *
	 * @param subscriberLocalService the subscriber local service
	 */
	public void setSubscriberLocalService(
		com.liferay.grow.gamification.service.SubscriberLocalService subscriberLocalService) {
		this.subscriberLocalService = subscriberLocalService;
	}

	/**
	 * Returns the subscriber remote service.
	 *
	 * @return the subscriber remote service
	 */
	public com.liferay.grow.gamification.service.SubscriberService getSubscriberService() {
		return subscriberService;
	}

	/**
	 * Sets the subscriber remote service.
	 *
	 * @param subscriberService the subscriber remote service
	 */
	public void setSubscriberService(
		com.liferay.grow.gamification.service.SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	/**
	 * Returns the subscriber persistence.
	 *
	 * @return the subscriber persistence
	 */
	public SubscriberPersistence getSubscriberPersistence() {
		return subscriberPersistence;
	}

	/**
	 * Sets the subscriber persistence.
	 *
	 * @param subscriberPersistence the subscriber persistence
	 */
	public void setSubscriberPersistence(
		SubscriberPersistence subscriberPersistence) {
		this.subscriberPersistence = subscriberPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BadgeService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Badge.class;
	}

	protected String getModelClassName() {
		return Badge.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = badgePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.grow.gamification.service.BadgeLocalService.class)
	protected com.liferay.grow.gamification.service.BadgeLocalService badgeLocalService;
	@BeanReference(type = BadgeService.class)
	protected BadgeService badgeService;
	@BeanReference(type = BadgePersistence.class)
	protected BadgePersistence badgePersistence;
	@BeanReference(type = com.liferay.grow.gamification.service.BadgeTypeLocalService.class)
	protected com.liferay.grow.gamification.service.BadgeTypeLocalService badgeTypeLocalService;
	@BeanReference(type = com.liferay.grow.gamification.service.BadgeTypeService.class)
	protected com.liferay.grow.gamification.service.BadgeTypeService badgeTypeService;
	@BeanReference(type = BadgeTypePersistence.class)
	protected BadgeTypePersistence badgeTypePersistence;
	@BeanReference(type = com.liferay.grow.gamification.service.LDateLocalService.class)
	protected com.liferay.grow.gamification.service.LDateLocalService lDateLocalService;
	@BeanReference(type = com.liferay.grow.gamification.service.LDateService.class)
	protected com.liferay.grow.gamification.service.LDateService lDateService;
	@BeanReference(type = LDatePersistence.class)
	protected LDatePersistence lDatePersistence;
	@BeanReference(type = com.liferay.grow.gamification.service.SubscriberLocalService.class)
	protected com.liferay.grow.gamification.service.SubscriberLocalService subscriberLocalService;
	@BeanReference(type = com.liferay.grow.gamification.service.SubscriberService.class)
	protected com.liferay.grow.gamification.service.SubscriberService subscriberService;
	@BeanReference(type = SubscriberPersistence.class)
	protected SubscriberPersistence subscriberPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}