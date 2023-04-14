package com.sang.nv.education.notification.infrastructure.persistence.repository.impl;

import com.mbamc.common.persistence.support.SqlUtils;
import com.mbamc.common.util.StrUtils;
import com.sang.nv.education.notification.domain.query.EventSearchQuery;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventEntity;
import com.sang.nv.education.notification.infrastructure.persistence.repository.custom.EventRepositoryCustom;
import com.sang.nv.education.notification.infrastructure.support.enums.ContentType;
import com.sang.nv.education.notification.infrastructure.support.enums.EventLevel;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.notification.infrastructure.support.enums.EventTargetType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EventRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<EventEntity> search(EventSearchQuery request) {
        Map<String, Object> values = new HashMap<>();
        String sql =
                "FROM EventEntity E "
                        + createWhereQuery(request, values)
                        + createOrderQuery(request);
        Query query = entityManager.createQuery(sql, EventEntity.class);
        values.forEach(query::setParameter);

        query.setFirstResult((request.getPageIndex() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long countEvent(EventSearchQuery request) {
        Map<String, Object> values = new HashMap<>();
        Query query =
                entityManager.createQuery(
                        "SELECT count(E.id) FROM EventEntity E "
                                + createWhereQuery(request, values),
                        Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    String createWhereQuery(EventSearchQuery request, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder(" where 1 = 1 ");

        if (!CollectionUtils.isEmpty(request.getStatuses())) {
            sql.append(" and E.status IN :statuses ");
            values.put("statuses", request.getStatuses());
        }

        if (Objects.nonNull(request.getEventTargetType())) {
            sql.append(" and E.eventTargetType = :eventTargetType ");
            values.put("eventTargetType", request.getEventTargetType());
        }

        if (Objects.nonNull(request.getEventLevel())) {
            // Trung tam co the nhin thay event do ban quan ly tao o trang thay DONE
            if (Objects.equals(request.getEventLevel(), EventLevel.CENTER)
                    && Objects.equals(request.getEventTargetType(), EventTargetType.CUSTOMER)) {
                sql.append(
                        " and (E.eventLevel = :eventLevelCenter or (E.eventLevel = :eventLevelManager and E.status = :statusDone))");
                values.put("eventLevelCenter", EventLevel.CENTER);
                values.put("eventLevelManager", EventLevel.BUILDING);
                values.put("statusDone", EventStatus.DONE);
            } else {
                sql.append(" and E.eventLevel = :eventLevel ");
                values.put("eventLevel", request.getEventLevel());
            }
        }

        if (!StrUtils.isBlank(request.getKeyword())) {
            sql.append(
                    " and ( (E.title like :keyword)"
                            + " or (E.note like :keyword)"
                            + " or (E.description like :keyword) ) ");
            values.put("keyword", SqlUtils.encodeKeyword(request.getKeyword()));
        }
        if (!CollectionUtils.isEmpty(request.getBuildingIds())) {
            sql.append(
                    " and E.id in (select distinct(ET.eventId) from EventTargetEntity ET where 1=1 and ET.targetId in :targetBuildingIds ) ");
            values.put("targetBuildingIds", request.getBuildingIds());
        }
        if (!CollectionUtils.isEmpty(request.getFloorIds())) {
            sql.append(
                    " and E.id in (select distinct(ET.eventId) from EventTargetEntity ET where 1=1 and ET.targetId in :targetFloorIds ) ");
            values.put("targetFloorIds", request.getFloorIds());
        }
        if (!CollectionUtils.isEmpty(request.getOrganizationIds())) {
            sql.append(
                    " and E.id in (select distinct(ET.eventId) from EventTargetEntity ET where 1=1 and ET.targetId in :targetOrgIds ) ");
            values.put("targetOrgIds", request.getOrganizationIds());
        }
        if (Objects.nonNull(request.getExpectedNotificationStartAt())) {
            sql.append(" and E.expectedNotificationAt >= :expectedStartDate ");
            values.put("expectedStartDate", request.getExpectedNotificationStartAt());
        }

        if (Objects.nonNull(request.getExpectedNotificationEndAt())) {
            sql.append(" and E.expectedNotificationAt <= :expectedEndDate ");
            values.put("expectedEndDate", request.getExpectedNotificationEndAt());
        }

        if (Objects.nonNull(request.getNotificationStartAt())) {
            sql.append(" and E.notificationAt >= :notificationStartAt ");
            values.put("notificationStartAt", request.getNotificationStartAt());
        }

        if (Objects.nonNull(request.getNotificationEndAt())) {
            sql.append(" and E.notificationAt <= :notificationEndAt ");
            values.put("notificationEndAt", request.getNotificationEndAt());
        }

        if (!CollectionUtils.isEmpty(request.getIssuedUserIds())) {
            sql.append(" and E.issuedUserId IN :issuedUserIds ");
            values.put("issuedUserIds", request.getIssuedUserIds());
        }

        if (!CollectionUtils.isEmpty(request.getEventConfigurationIds())) {
            sql.append(" AND E.eventConfigurationId in :eventConfigurationIds ");
            values.put("eventConfigurationIds", request.getEventConfigurationIds());
        }

        if (!CollectionUtils.isEmpty(request.getEventTypes())) {
            sql.append(" AND E.eventType in :eventTypes ");
            values.put("eventTypes", request.getEventTypes());
        }

        sql.append(" and E.deleted = false ");

        sql.append(" and E.contentType = : contentType ");
        values.put("contentType", ContentType.HTML);

        return sql.toString();
    }

    public StringBuilder createOrderQuery(EventSearchQuery params) {
        StringBuilder sql = new StringBuilder(" ");
        if (StringUtils.hasLength(params.getSortBy())) {
            sql.append(" order by E.").append(params.getSortBy().replace(".", " "));
        } else {
            sql.append(" order by E.lastModifiedAt desc ");
        }
        return sql;
    }
}
