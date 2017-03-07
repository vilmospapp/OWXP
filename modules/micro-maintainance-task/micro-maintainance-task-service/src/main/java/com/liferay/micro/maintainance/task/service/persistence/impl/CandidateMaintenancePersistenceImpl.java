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

package com.liferay.micro.maintainance.task.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.micro.maintainance.task.exception.NoSuchCandidateMaintenanceException;
import com.liferay.micro.maintainance.task.model.CandidateMaintenance;
import com.liferay.micro.maintainance.task.model.impl.CandidateMaintenanceImpl;
import com.liferay.micro.maintainance.task.model.impl.CandidateMaintenanceModelImpl;
import com.liferay.micro.maintainance.task.service.persistence.CandidateMaintenancePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the candidate maintenance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CandidateMaintenancePersistence
 * @see com.liferay.micro.maintainance.task.service.persistence.CandidateMaintenanceUtil
 * @generated
 */
@ProviderType
public class CandidateMaintenancePersistenceImpl extends BasePersistenceImpl<CandidateMaintenance>
	implements CandidateMaintenancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CandidateMaintenanceUtil} to access the candidate maintenance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CandidateMaintenanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CandidateMaintenanceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the candidate maintenances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the candidate maintenances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @return the range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByUuid(String uuid, int start,
		int end, OrderByComparator<CandidateMaintenance> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByUuid(String uuid, int start,
		int end, OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CandidateMaintenance> list = null;

		if (retrieveFromCache) {
			list = (List<CandidateMaintenance>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CandidateMaintenance candidateMaintenance : list) {
					if (!Objects.equals(uuid, candidateMaintenance.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first candidate maintenance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByUuid_First(String uuid,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByUuid_First(uuid,
				orderByComparator);

		if (candidateMaintenance != null) {
			return candidateMaintenance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCandidateMaintenanceException(msg.toString());
	}

	/**
	 * Returns the first candidate maintenance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByUuid_First(String uuid,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		List<CandidateMaintenance> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last candidate maintenance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByUuid_Last(String uuid,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByUuid_Last(uuid,
				orderByComparator);

		if (candidateMaintenance != null) {
			return candidateMaintenance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCandidateMaintenanceException(msg.toString());
	}

	/**
	 * Returns the last candidate maintenance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByUuid_Last(String uuid,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CandidateMaintenance> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the candidate maintenances before and after the current candidate maintenance in the ordered set where uuid = &#63;.
	 *
	 * @param candidateMaintenanceId the primary key of the current candidate maintenance
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance[] findByUuid_PrevAndNext(
		long candidateMaintenanceId, String uuid,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = findByPrimaryKey(candidateMaintenanceId);

		Session session = null;

		try {
			session = openSession();

			CandidateMaintenance[] array = new CandidateMaintenanceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, candidateMaintenance,
					uuid, orderByComparator, true);

			array[1] = candidateMaintenance;

			array[2] = getByUuid_PrevAndNext(session, candidateMaintenance,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CandidateMaintenance getByUuid_PrevAndNext(Session session,
		CandidateMaintenance candidateMaintenance, String uuid,
		OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(candidateMaintenance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CandidateMaintenance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the candidate maintenances where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CandidateMaintenance candidateMaintenance : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(candidateMaintenance);
		}
	}

	/**
	 * Returns the number of candidate maintenances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching candidate maintenances
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CANDIDATEMAINTENANCE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "candidateMaintenance.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "candidateMaintenance.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(candidateMaintenance.uuid IS NULL OR candidateMaintenance.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CANDIDATEIDS =
		new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCandidateIds",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CANDIDATEIDS =
		new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCandidateIds",
			new String[] { Long.class.getName() },
			CandidateMaintenanceModelImpl.CANDIDATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CANDIDATEIDS = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCandidateIds",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the candidate maintenances where candidateId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @return the matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByCandidateIds(long candidateId) {
		return findByCandidateIds(candidateId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the candidate maintenances where candidateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param candidateId the candidate ID
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @return the range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByCandidateIds(long candidateId,
		int start, int end) {
		return findByCandidateIds(candidateId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances where candidateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param candidateId the candidate ID
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByCandidateIds(long candidateId,
		int start, int end,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		return findByCandidateIds(candidateId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances where candidateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param candidateId the candidate ID
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByCandidateIds(long candidateId,
		int start, int end,
		OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CANDIDATEIDS;
			finderArgs = new Object[] { candidateId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CANDIDATEIDS;
			finderArgs = new Object[] { candidateId, start, end, orderByComparator };
		}

		List<CandidateMaintenance> list = null;

		if (retrieveFromCache) {
			list = (List<CandidateMaintenance>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CandidateMaintenance candidateMaintenance : list) {
					if ((candidateId != candidateMaintenance.getCandidateId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

			query.append(_FINDER_COLUMN_CANDIDATEIDS_CANDIDATEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(candidateId);

				if (!pagination) {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first candidate maintenance in the ordered set where candidateId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByCandidateIds_First(long candidateId,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByCandidateIds_First(candidateId,
				orderByComparator);

		if (candidateMaintenance != null) {
			return candidateMaintenance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("candidateId=");
		msg.append(candidateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCandidateMaintenanceException(msg.toString());
	}

	/**
	 * Returns the first candidate maintenance in the ordered set where candidateId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByCandidateIds_First(long candidateId,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		List<CandidateMaintenance> list = findByCandidateIds(candidateId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last candidate maintenance in the ordered set where candidateId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByCandidateIds_Last(long candidateId,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByCandidateIds_Last(candidateId,
				orderByComparator);

		if (candidateMaintenance != null) {
			return candidateMaintenance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("candidateId=");
		msg.append(candidateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCandidateMaintenanceException(msg.toString());
	}

	/**
	 * Returns the last candidate maintenance in the ordered set where candidateId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByCandidateIds_Last(long candidateId,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		int count = countByCandidateIds(candidateId);

		if (count == 0) {
			return null;
		}

		List<CandidateMaintenance> list = findByCandidateIds(candidateId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the candidate maintenances before and after the current candidate maintenance in the ordered set where candidateId = &#63;.
	 *
	 * @param candidateMaintenanceId the primary key of the current candidate maintenance
	 * @param candidateId the candidate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance[] findByCandidateIds_PrevAndNext(
		long candidateMaintenanceId, long candidateId,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = findByPrimaryKey(candidateMaintenanceId);

		Session session = null;

		try {
			session = openSession();

			CandidateMaintenance[] array = new CandidateMaintenanceImpl[3];

			array[0] = getByCandidateIds_PrevAndNext(session,
					candidateMaintenance, candidateId, orderByComparator, true);

			array[1] = candidateMaintenance;

			array[2] = getByCandidateIds_PrevAndNext(session,
					candidateMaintenance, candidateId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CandidateMaintenance getByCandidateIds_PrevAndNext(
		Session session, CandidateMaintenance candidateMaintenance,
		long candidateId,
		OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

		query.append(_FINDER_COLUMN_CANDIDATEIDS_CANDIDATEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(candidateId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(candidateMaintenance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CandidateMaintenance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the candidate maintenances where candidateId = &#63; from the database.
	 *
	 * @param candidateId the candidate ID
	 */
	@Override
	public void removeByCandidateIds(long candidateId) {
		for (CandidateMaintenance candidateMaintenance : findByCandidateIds(
				candidateId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(candidateMaintenance);
		}
	}

	/**
	 * Returns the number of candidate maintenances where candidateId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @return the number of matching candidate maintenances
	 */
	@Override
	public int countByCandidateIds(long candidateId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CANDIDATEIDS;

		Object[] finderArgs = new Object[] { candidateId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CANDIDATEMAINTENANCE_WHERE);

			query.append(_FINDER_COLUMN_CANDIDATEIDS_CANDIDATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(candidateId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CANDIDATEIDS_CANDIDATEID_2 = "candidateMaintenance.candidateId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TASKIDS = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTaskIds",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKIDS =
		new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTaskIds",
			new String[] { Long.class.getName() },
			CandidateMaintenanceModelImpl.TASKID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TASKIDS = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTaskIds",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the candidate maintenances where taskId = &#63;.
	 *
	 * @param taskId the task ID
	 * @return the matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByTaskIds(long taskId) {
		return findByTaskIds(taskId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the candidate maintenances where taskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskId the task ID
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @return the range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByTaskIds(long taskId, int start,
		int end) {
		return findByTaskIds(taskId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances where taskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskId the task ID
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByTaskIds(long taskId, int start,
		int end, OrderByComparator<CandidateMaintenance> orderByComparator) {
		return findByTaskIds(taskId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances where taskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskId the task ID
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findByTaskIds(long taskId, int start,
		int end, OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKIDS;
			finderArgs = new Object[] { taskId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TASKIDS;
			finderArgs = new Object[] { taskId, start, end, orderByComparator };
		}

		List<CandidateMaintenance> list = null;

		if (retrieveFromCache) {
			list = (List<CandidateMaintenance>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CandidateMaintenance candidateMaintenance : list) {
					if ((taskId != candidateMaintenance.getTaskId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

			query.append(_FINDER_COLUMN_TASKIDS_TASKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(taskId);

				if (!pagination) {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first candidate maintenance in the ordered set where taskId = &#63;.
	 *
	 * @param taskId the task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByTaskIds_First(long taskId,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByTaskIds_First(taskId,
				orderByComparator);

		if (candidateMaintenance != null) {
			return candidateMaintenance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("taskId=");
		msg.append(taskId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCandidateMaintenanceException(msg.toString());
	}

	/**
	 * Returns the first candidate maintenance in the ordered set where taskId = &#63;.
	 *
	 * @param taskId the task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByTaskIds_First(long taskId,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		List<CandidateMaintenance> list = findByTaskIds(taskId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last candidate maintenance in the ordered set where taskId = &#63;.
	 *
	 * @param taskId the task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByTaskIds_Last(long taskId,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByTaskIds_Last(taskId,
				orderByComparator);

		if (candidateMaintenance != null) {
			return candidateMaintenance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("taskId=");
		msg.append(taskId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCandidateMaintenanceException(msg.toString());
	}

	/**
	 * Returns the last candidate maintenance in the ordered set where taskId = &#63;.
	 *
	 * @param taskId the task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByTaskIds_Last(long taskId,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		int count = countByTaskIds(taskId);

		if (count == 0) {
			return null;
		}

		List<CandidateMaintenance> list = findByTaskIds(taskId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the candidate maintenances before and after the current candidate maintenance in the ordered set where taskId = &#63;.
	 *
	 * @param candidateMaintenanceId the primary key of the current candidate maintenance
	 * @param taskId the task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance[] findByTaskIds_PrevAndNext(
		long candidateMaintenanceId, long taskId,
		OrderByComparator<CandidateMaintenance> orderByComparator)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = findByPrimaryKey(candidateMaintenanceId);

		Session session = null;

		try {
			session = openSession();

			CandidateMaintenance[] array = new CandidateMaintenanceImpl[3];

			array[0] = getByTaskIds_PrevAndNext(session, candidateMaintenance,
					taskId, orderByComparator, true);

			array[1] = candidateMaintenance;

			array[2] = getByTaskIds_PrevAndNext(session, candidateMaintenance,
					taskId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CandidateMaintenance getByTaskIds_PrevAndNext(Session session,
		CandidateMaintenance candidateMaintenance, long taskId,
		OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

		query.append(_FINDER_COLUMN_TASKIDS_TASKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(taskId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(candidateMaintenance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CandidateMaintenance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the candidate maintenances where taskId = &#63; from the database.
	 *
	 * @param taskId the task ID
	 */
	@Override
	public void removeByTaskIds(long taskId) {
		for (CandidateMaintenance candidateMaintenance : findByTaskIds(taskId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(candidateMaintenance);
		}
	}

	/**
	 * Returns the number of candidate maintenances where taskId = &#63;.
	 *
	 * @param taskId the task ID
	 * @return the number of matching candidate maintenances
	 */
	@Override
	public int countByTaskIds(long taskId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TASKIDS;

		Object[] finderArgs = new Object[] { taskId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CANDIDATEMAINTENANCE_WHERE);

			query.append(_FINDER_COLUMN_TASKIDS_TASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(taskId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TASKIDS_TASKID_2 = "candidateMaintenance.taskId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_T = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED,
			CandidateMaintenanceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_T",
			new String[] { Long.class.getName(), Long.class.getName() },
			CandidateMaintenanceModelImpl.CANDIDATEID_COLUMN_BITMASK |
			CandidateMaintenanceModelImpl.TASKID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T = new FinderPath(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the candidate maintenance where candidateId = &#63; and taskId = &#63; or throws a {@link NoSuchCandidateMaintenanceException} if it could not be found.
	 *
	 * @param candidateId the candidate ID
	 * @param taskId the task ID
	 * @return the matching candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance findByC_T(long candidateId, long taskId)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByC_T(candidateId,
				taskId);

		if (candidateMaintenance == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("candidateId=");
			msg.append(candidateId);

			msg.append(", taskId=");
			msg.append(taskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCandidateMaintenanceException(msg.toString());
		}

		return candidateMaintenance;
	}

	/**
	 * Returns the candidate maintenance where candidateId = &#63; and taskId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param candidateId the candidate ID
	 * @param taskId the task ID
	 * @return the matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByC_T(long candidateId, long taskId) {
		return fetchByC_T(candidateId, taskId, true);
	}

	/**
	 * Returns the candidate maintenance where candidateId = &#63; and taskId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param candidateId the candidate ID
	 * @param taskId the task ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching candidate maintenance, or <code>null</code> if a matching candidate maintenance could not be found
	 */
	@Override
	public CandidateMaintenance fetchByC_T(long candidateId, long taskId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { candidateId, taskId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_T,
					finderArgs, this);
		}

		if (result instanceof CandidateMaintenance) {
			CandidateMaintenance candidateMaintenance = (CandidateMaintenance)result;

			if ((candidateId != candidateMaintenance.getCandidateId()) ||
					(taskId != candidateMaintenance.getTaskId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE);

			query.append(_FINDER_COLUMN_C_T_CANDIDATEID_2);

			query.append(_FINDER_COLUMN_C_T_TASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(candidateId);

				qPos.add(taskId);

				List<CandidateMaintenance> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_T, finderArgs,
						list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CandidateMaintenancePersistenceImpl.fetchByC_T(long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					CandidateMaintenance candidateMaintenance = list.get(0);

					result = candidateMaintenance;

					cacheResult(candidateMaintenance);

					if ((candidateMaintenance.getCandidateId() != candidateId) ||
							(candidateMaintenance.getTaskId() != taskId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_T,
							finderArgs, candidateMaintenance);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_T, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CandidateMaintenance)result;
		}
	}

	/**
	 * Removes the candidate maintenance where candidateId = &#63; and taskId = &#63; from the database.
	 *
	 * @param candidateId the candidate ID
	 * @param taskId the task ID
	 * @return the candidate maintenance that was removed
	 */
	@Override
	public CandidateMaintenance removeByC_T(long candidateId, long taskId)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = findByC_T(candidateId,
				taskId);

		return remove(candidateMaintenance);
	}

	/**
	 * Returns the number of candidate maintenances where candidateId = &#63; and taskId = &#63;.
	 *
	 * @param candidateId the candidate ID
	 * @param taskId the task ID
	 * @return the number of matching candidate maintenances
	 */
	@Override
	public int countByC_T(long candidateId, long taskId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_T;

		Object[] finderArgs = new Object[] { candidateId, taskId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CANDIDATEMAINTENANCE_WHERE);

			query.append(_FINDER_COLUMN_C_T_CANDIDATEID_2);

			query.append(_FINDER_COLUMN_C_T_TASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(candidateId);

				qPos.add(taskId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_T_CANDIDATEID_2 = "candidateMaintenance.candidateId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_TASKID_2 = "candidateMaintenance.taskId = ?";

	public CandidateMaintenancePersistenceImpl() {
		setModelClass(CandidateMaintenance.class);
	}

	/**
	 * Caches the candidate maintenance in the entity cache if it is enabled.
	 *
	 * @param candidateMaintenance the candidate maintenance
	 */
	@Override
	public void cacheResult(CandidateMaintenance candidateMaintenance) {
		entityCache.putResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			candidateMaintenance.getPrimaryKey(), candidateMaintenance);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_T,
			new Object[] {
				candidateMaintenance.getCandidateId(),
				candidateMaintenance.getTaskId()
			}, candidateMaintenance);

		candidateMaintenance.resetOriginalValues();
	}

	/**
	 * Caches the candidate maintenances in the entity cache if it is enabled.
	 *
	 * @param candidateMaintenances the candidate maintenances
	 */
	@Override
	public void cacheResult(List<CandidateMaintenance> candidateMaintenances) {
		for (CandidateMaintenance candidateMaintenance : candidateMaintenances) {
			if (entityCache.getResult(
						CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
						CandidateMaintenanceImpl.class,
						candidateMaintenance.getPrimaryKey()) == null) {
				cacheResult(candidateMaintenance);
			}
			else {
				candidateMaintenance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all candidate maintenances.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CandidateMaintenanceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the candidate maintenance.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CandidateMaintenance candidateMaintenance) {
		entityCache.removeResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceImpl.class, candidateMaintenance.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CandidateMaintenanceModelImpl)candidateMaintenance);
	}

	@Override
	public void clearCache(List<CandidateMaintenance> candidateMaintenances) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CandidateMaintenance candidateMaintenance : candidateMaintenances) {
			entityCache.removeResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
				CandidateMaintenanceImpl.class,
				candidateMaintenance.getPrimaryKey());

			clearUniqueFindersCache((CandidateMaintenanceModelImpl)candidateMaintenance);
		}
	}

	protected void cacheUniqueFindersCache(
		CandidateMaintenanceModelImpl candidateMaintenanceModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					candidateMaintenanceModelImpl.getCandidateId(),
					candidateMaintenanceModelImpl.getTaskId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_C_T, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_C_T, args,
				candidateMaintenanceModelImpl);
		}
		else {
			if ((candidateMaintenanceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						candidateMaintenanceModelImpl.getCandidateId(),
						candidateMaintenanceModelImpl.getTaskId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_C_T, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_C_T, args,
					candidateMaintenanceModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		CandidateMaintenanceModelImpl candidateMaintenanceModelImpl) {
		Object[] args = new Object[] {
				candidateMaintenanceModelImpl.getCandidateId(),
				candidateMaintenanceModelImpl.getTaskId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_C_T, args);

		if ((candidateMaintenanceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					candidateMaintenanceModelImpl.getOriginalCandidateId(),
					candidateMaintenanceModelImpl.getOriginalTaskId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_T, args);
		}
	}

	/**
	 * Creates a new candidate maintenance with the primary key. Does not add the candidate maintenance to the database.
	 *
	 * @param candidateMaintenanceId the primary key for the new candidate maintenance
	 * @return the new candidate maintenance
	 */
	@Override
	public CandidateMaintenance create(long candidateMaintenanceId) {
		CandidateMaintenance candidateMaintenance = new CandidateMaintenanceImpl();

		candidateMaintenance.setNew(true);
		candidateMaintenance.setPrimaryKey(candidateMaintenanceId);

		String uuid = PortalUUIDUtil.generate();

		candidateMaintenance.setUuid(uuid);

		return candidateMaintenance;
	}

	/**
	 * Removes the candidate maintenance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param candidateMaintenanceId the primary key of the candidate maintenance
	 * @return the candidate maintenance that was removed
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance remove(long candidateMaintenanceId)
		throws NoSuchCandidateMaintenanceException {
		return remove((Serializable)candidateMaintenanceId);
	}

	/**
	 * Removes the candidate maintenance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the candidate maintenance
	 * @return the candidate maintenance that was removed
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance remove(Serializable primaryKey)
		throws NoSuchCandidateMaintenanceException {
		Session session = null;

		try {
			session = openSession();

			CandidateMaintenance candidateMaintenance = (CandidateMaintenance)session.get(CandidateMaintenanceImpl.class,
					primaryKey);

			if (candidateMaintenance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCandidateMaintenanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(candidateMaintenance);
		}
		catch (NoSuchCandidateMaintenanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CandidateMaintenance removeImpl(
		CandidateMaintenance candidateMaintenance) {
		candidateMaintenance = toUnwrappedModel(candidateMaintenance);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(candidateMaintenance)) {
				candidateMaintenance = (CandidateMaintenance)session.get(CandidateMaintenanceImpl.class,
						candidateMaintenance.getPrimaryKeyObj());
			}

			if (candidateMaintenance != null) {
				session.delete(candidateMaintenance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (candidateMaintenance != null) {
			clearCache(candidateMaintenance);
		}

		return candidateMaintenance;
	}

	@Override
	public CandidateMaintenance updateImpl(
		CandidateMaintenance candidateMaintenance) {
		candidateMaintenance = toUnwrappedModel(candidateMaintenance);

		boolean isNew = candidateMaintenance.isNew();

		CandidateMaintenanceModelImpl candidateMaintenanceModelImpl = (CandidateMaintenanceModelImpl)candidateMaintenance;

		if (Validator.isNull(candidateMaintenance.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			candidateMaintenance.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (candidateMaintenance.isNew()) {
				session.save(candidateMaintenance);

				candidateMaintenance.setNew(false);
			}
			else {
				candidateMaintenance = (CandidateMaintenance)session.merge(candidateMaintenance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CandidateMaintenanceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((candidateMaintenanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						candidateMaintenanceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { candidateMaintenanceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((candidateMaintenanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CANDIDATEIDS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						candidateMaintenanceModelImpl.getOriginalCandidateId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CANDIDATEIDS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CANDIDATEIDS,
					args);

				args = new Object[] {
						candidateMaintenanceModelImpl.getCandidateId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CANDIDATEIDS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CANDIDATEIDS,
					args);
			}

			if ((candidateMaintenanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKIDS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						candidateMaintenanceModelImpl.getOriginalTaskId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TASKIDS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKIDS,
					args);

				args = new Object[] { candidateMaintenanceModelImpl.getTaskId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TASKIDS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TASKIDS,
					args);
			}
		}

		entityCache.putResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
			CandidateMaintenanceImpl.class,
			candidateMaintenance.getPrimaryKey(), candidateMaintenance, false);

		clearUniqueFindersCache(candidateMaintenanceModelImpl);
		cacheUniqueFindersCache(candidateMaintenanceModelImpl, isNew);

		candidateMaintenance.resetOriginalValues();

		return candidateMaintenance;
	}

	protected CandidateMaintenance toUnwrappedModel(
		CandidateMaintenance candidateMaintenance) {
		if (candidateMaintenance instanceof CandidateMaintenanceImpl) {
			return candidateMaintenance;
		}

		CandidateMaintenanceImpl candidateMaintenanceImpl = new CandidateMaintenanceImpl();

		candidateMaintenanceImpl.setNew(candidateMaintenance.isNew());
		candidateMaintenanceImpl.setPrimaryKey(candidateMaintenance.getPrimaryKey());

		candidateMaintenanceImpl.setUuid(candidateMaintenance.getUuid());
		candidateMaintenanceImpl.setCandidateMaintenanceId(candidateMaintenance.getCandidateMaintenanceId());
		candidateMaintenanceImpl.setCandidateId(candidateMaintenance.getCandidateId());
		candidateMaintenanceImpl.setTaskId(candidateMaintenance.getTaskId());
		candidateMaintenanceImpl.setCreateDate(candidateMaintenance.getCreateDate());

		return candidateMaintenanceImpl;
	}

	/**
	 * Returns the candidate maintenance with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the candidate maintenance
	 * @return the candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCandidateMaintenanceException {
		CandidateMaintenance candidateMaintenance = fetchByPrimaryKey(primaryKey);

		if (candidateMaintenance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCandidateMaintenanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return candidateMaintenance;
	}

	/**
	 * Returns the candidate maintenance with the primary key or throws a {@link NoSuchCandidateMaintenanceException} if it could not be found.
	 *
	 * @param candidateMaintenanceId the primary key of the candidate maintenance
	 * @return the candidate maintenance
	 * @throws NoSuchCandidateMaintenanceException if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance findByPrimaryKey(long candidateMaintenanceId)
		throws NoSuchCandidateMaintenanceException {
		return findByPrimaryKey((Serializable)candidateMaintenanceId);
	}

	/**
	 * Returns the candidate maintenance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the candidate maintenance
	 * @return the candidate maintenance, or <code>null</code> if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
				CandidateMaintenanceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CandidateMaintenance candidateMaintenance = (CandidateMaintenance)serializable;

		if (candidateMaintenance == null) {
			Session session = null;

			try {
				session = openSession();

				candidateMaintenance = (CandidateMaintenance)session.get(CandidateMaintenanceImpl.class,
						primaryKey);

				if (candidateMaintenance != null) {
					cacheResult(candidateMaintenance);
				}
				else {
					entityCache.putResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
						CandidateMaintenanceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
					CandidateMaintenanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return candidateMaintenance;
	}

	/**
	 * Returns the candidate maintenance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param candidateMaintenanceId the primary key of the candidate maintenance
	 * @return the candidate maintenance, or <code>null</code> if a candidate maintenance with the primary key could not be found
	 */
	@Override
	public CandidateMaintenance fetchByPrimaryKey(long candidateMaintenanceId) {
		return fetchByPrimaryKey((Serializable)candidateMaintenanceId);
	}

	@Override
	public Map<Serializable, CandidateMaintenance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CandidateMaintenance> map = new HashMap<Serializable, CandidateMaintenance>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CandidateMaintenance candidateMaintenance = fetchByPrimaryKey(primaryKey);

			if (candidateMaintenance != null) {
				map.put(primaryKey, candidateMaintenance);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
					CandidateMaintenanceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CandidateMaintenance)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CANDIDATEMAINTENANCE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CandidateMaintenance candidateMaintenance : (List<CandidateMaintenance>)q.list()) {
				map.put(candidateMaintenance.getPrimaryKeyObj(),
					candidateMaintenance);

				cacheResult(candidateMaintenance);

				uncachedPrimaryKeys.remove(candidateMaintenance.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CandidateMaintenanceModelImpl.ENTITY_CACHE_ENABLED,
					CandidateMaintenanceImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the candidate maintenances.
	 *
	 * @return the candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the candidate maintenances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @return the range of candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findAll(int start, int end,
		OrderByComparator<CandidateMaintenance> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the candidate maintenances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CandidateMaintenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of candidate maintenances
	 * @param end the upper bound of the range of candidate maintenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of candidate maintenances
	 */
	@Override
	public List<CandidateMaintenance> findAll(int start, int end,
		OrderByComparator<CandidateMaintenance> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CandidateMaintenance> list = null;

		if (retrieveFromCache) {
			list = (List<CandidateMaintenance>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CANDIDATEMAINTENANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CANDIDATEMAINTENANCE;

				if (pagination) {
					sql = sql.concat(CandidateMaintenanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CandidateMaintenance>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the candidate maintenances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CandidateMaintenance candidateMaintenance : findAll()) {
			remove(candidateMaintenance);
		}
	}

	/**
	 * Returns the number of candidate maintenances.
	 *
	 * @return the number of candidate maintenances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CANDIDATEMAINTENANCE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CandidateMaintenanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the candidate maintenance persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CandidateMaintenanceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CANDIDATEMAINTENANCE = "SELECT candidateMaintenance FROM CandidateMaintenance candidateMaintenance";
	private static final String _SQL_SELECT_CANDIDATEMAINTENANCE_WHERE_PKS_IN = "SELECT candidateMaintenance FROM CandidateMaintenance candidateMaintenance WHERE candidateMaintenanceId IN (";
	private static final String _SQL_SELECT_CANDIDATEMAINTENANCE_WHERE = "SELECT candidateMaintenance FROM CandidateMaintenance candidateMaintenance WHERE ";
	private static final String _SQL_COUNT_CANDIDATEMAINTENANCE = "SELECT COUNT(candidateMaintenance) FROM CandidateMaintenance candidateMaintenance";
	private static final String _SQL_COUNT_CANDIDATEMAINTENANCE_WHERE = "SELECT COUNT(candidateMaintenance) FROM CandidateMaintenance candidateMaintenance WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "candidateMaintenance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CandidateMaintenance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CandidateMaintenance exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CandidateMaintenancePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}