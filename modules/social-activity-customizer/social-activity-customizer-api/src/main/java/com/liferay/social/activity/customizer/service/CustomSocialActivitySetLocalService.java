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

package com.liferay.social.activity.customizer.service;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.social.activity.customizer.model.CustomSocialActivitySet;
import com.liferay.social.kernel.model.SocialActivitySet;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for CustomSocialActivitySet. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CustomSocialActivitySetLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CustomSocialActivitySetLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomSocialActivitySetLocalServiceUtil} to access the custom social activity set local service. Add custom service methods to <code>com.liferay.social.activity.customizer.service.impl.CustomSocialActivitySetLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the custom social activity set to the database. Also notifies the appropriate model listeners.
	 *
	 * @param customSocialActivitySet the custom social activity set
	 * @return the custom social activity set that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CustomSocialActivitySet addCustomSocialActivitySet(
		CustomSocialActivitySet customSocialActivitySet);

	/**
	 * Creates a new custom social activity set with the primary key. Does not add the custom social activity set to the database.
	 *
	 * @param id the primary key for the new custom social activity set
	 * @return the new custom social activity set
	 */
	@Transactional(enabled = false)
	public CustomSocialActivitySet createCustomSocialActivitySet(long id);

	/**
	 * Deletes the custom social activity set from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customSocialActivitySet the custom social activity set
	 * @return the custom social activity set that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CustomSocialActivitySet deleteCustomSocialActivitySet(
		CustomSocialActivitySet customSocialActivitySet);

	/**
	 * Deletes the custom social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the custom social activity set
	 * @return the custom social activity set that was removed
	 * @throws PortalException if a custom social activity set with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CustomSocialActivitySet deleteCustomSocialActivitySet(long id)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.activity.customizer.model.impl.CustomSocialActivitySetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.activity.customizer.model.impl.CustomSocialActivitySetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomSocialActivitySet fetchCustomSocialActivitySet(long id);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the custom social activity set with the primary key.
	 *
	 * @param id the primary key of the custom social activity set
	 * @return the custom social activity set
	 * @throws PortalException if a custom social activity set with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomSocialActivitySet getCustomSocialActivitySet(long id)
		throws PortalException;

	/**
	 * Returns a range of all the custom social activity sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.activity.customizer.model.impl.CustomSocialActivitySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom social activity sets
	 * @param end the upper bound of the range of custom social activity sets (not inclusive)
	 * @return the range of custom social activity sets
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CustomSocialActivitySet> getCustomSocialActivitySets(
		int start, int end);

	/**
	 * Returns the number of custom social activity sets.
	 *
	 * @return the number of custom social activity sets
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomSocialActivitySetsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link CustomSocialActivitySetLocalServiceUtil} to access the custom social activity set local service.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivitySet> getUserViewableActivitySets(
		long userId, int[] types, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserViewableActivitySetsCount(long userId, int[] types);

	/**
	 * Updates the custom social activity set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param customSocialActivitySet the custom social activity set
	 * @return the custom social activity set that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CustomSocialActivitySet updateCustomSocialActivitySet(
		CustomSocialActivitySet customSocialActivitySet);

}