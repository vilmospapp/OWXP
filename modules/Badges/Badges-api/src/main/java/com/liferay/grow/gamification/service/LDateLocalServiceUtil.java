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

package com.liferay.grow.gamification.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LDate. This utility wraps
 * {@link com.liferay.grow.gamification.service.impl.LDateLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Vilmos Papp
 * @see LDateLocalService
 * @see com.liferay.grow.gamification.service.base.LDateLocalServiceBaseImpl
 * @see com.liferay.grow.gamification.service.impl.LDateLocalServiceImpl
 * @generated
 */
@ProviderType
public class LDateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.grow.gamification.service.impl.LDateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l date to the database. Also notifies the appropriate model listeners.
	*
	* @param lDate the l date
	* @return the l date that was added
	*/
	public static com.liferay.grow.gamification.model.LDate addLDate(
		com.liferay.grow.gamification.model.LDate lDate) {
		return getService().addLDate(lDate);
	}

	/**
	* Creates a new l date with the primary key. Does not add the l date to the database.
	*
	* @param dateId the primary key for the new l date
	* @return the new l date
	*/
	public static com.liferay.grow.gamification.model.LDate createLDate(
		long dateId) {
		return getService().createLDate(dateId);
	}

	/**
	* Deletes the l date from the database. Also notifies the appropriate model listeners.
	*
	* @param lDate the l date
	* @return the l date that was removed
	*/
	public static com.liferay.grow.gamification.model.LDate deleteLDate(
		com.liferay.grow.gamification.model.LDate lDate) {
		return getService().deleteLDate(lDate);
	}

	/**
	* Deletes the l date with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dateId the primary key of the l date
	* @return the l date that was removed
	* @throws PortalException if a l date with the primary key could not be found
	*/
	public static com.liferay.grow.gamification.model.LDate deleteLDate(
		long dateId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLDate(dateId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.grow.gamification.model.impl.LDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.grow.gamification.model.impl.LDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.grow.gamification.model.LDate fetchLDate(
		long dateId) {
		return getService().fetchLDate(dateId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static long getDateId(int year, int month, int day)
		throws com.liferay.grow.gamification.exception.NoSuchLDateException {
		return getService().getDateId(year, month, day);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the l date with the primary key.
	*
	* @param dateId the primary key of the l date
	* @return the l date
	* @throws PortalException if a l date with the primary key could not be found
	*/
	public static com.liferay.grow.gamification.model.LDate getLDate(
		long dateId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLDate(dateId);
	}

	/**
	* Returns a range of all the l dates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.grow.gamification.model.impl.LDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l dates
	* @param end the upper bound of the range of l dates (not inclusive)
	* @return the range of l dates
	*/
	public static java.util.List<com.liferay.grow.gamification.model.LDate> getLDates(
		int start, int end) {
		return getService().getLDates(start, end);
	}

	/**
	* Returns the number of l dates.
	*
	* @return the number of l dates
	*/
	public static int getLDatesCount() {
		return getService().getLDatesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the l date in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lDate the l date
	* @return the l date that was updated
	*/
	public static com.liferay.grow.gamification.model.LDate updateLDate(
		com.liferay.grow.gamification.model.LDate lDate) {
		return getService().updateLDate(lDate);
	}

	public static LDateLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LDateLocalService, LDateLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LDateLocalService.class);

		ServiceTracker<LDateLocalService, LDateLocalService> serviceTracker = new ServiceTracker<LDateLocalService, LDateLocalService>(bundle.getBundleContext(),
				LDateLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}