package com.sang.nv.education.notification.infrastructure.persistence.repository.impl;

import com.mbamc.common.persistence.support.SqlUtils;
import com.mbamc.common.util.StrUtils;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventConfigurationEntity;
import com.sang.nv.education.notification.infrastructure.persistence.repository.custom.EventConfigurationRepositoryCustom;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EventConfigurationEntityRepositoryImpl implements EventConfigurationRepositoryCustom {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<EventConfigurationEntity> search(EventConfigurationSearchQuery searchQuery) {
        Map<String, Object> values = new HashMap<>();
        String sql =
                "FROM EventConfigurationEntity E "
                        + createWhereQuery(searchQuery, values)
                        + createOrderQuery(searchQuery);
        Query query = entityManager.createQuery(sql, EventConfigurationEntity.class);
        values.forEach(query::setParameter);

        query.setFirstResult((searchQuery.getPageIndex() - 1) * searchQuery.getPageSize());
        query.setMaxResults(searchQuery.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long count(EventConfigurationSearchQuery searchQuery) {
        Map<String, Object> values = new HashMap<>();
        Query query =
                entityManager.createQuery(
                        "SELECT count(E.id) FROM EventConfigurationEntity E "
                                + createWhereQuery(searchQuery, values),
                        Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    String createWhereQuery(EventConfigurationSearchQuery request, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder(" where E.deleted = false ");

        if (!StrUtils.isBlank(request.getKeyword())) {
            sql.append(
                    " and ( (E.name like :keyword)"
                            + " or (E.code like :keyword)"
                            + " or (E.description like :keyword) or (E.lastModifiedBy like :keyword) ) ");
            values.put("keyword", SqlUtils.encodeKeyword(request.getKeyword()));
        }

        if (Objects.nonNull(request.getStatuses())) {
            sql.append(" and E.status in :statues ");
            values.put("statues", request.getStatuses());
        }

        if (Objects.nonNull(request.getStartCreatedAt())) {
            sql.append(" and E.createdAt >= :startCreatedAt");
            values.put("startCreatedAt", request.getStartCreatedAt());
        }

        if (Objects.nonNull(request.getEndCreatedAt())) {
            sql.append(" and E.createdAt <= :endCreatedAt");
            values.put("endCreatedAt", request.getEndCreatedAt());
        }

        if (Objects.nonNull(request.getStartLastModifiedAt())) {
            sql.append(" and E.lastModifiedAt >= :startLastModifiedAt");
            values.put("startLastModifiedAt", request.getStartLastModifiedAt());
        }

        if (Objects.nonNull(request.getEndLastModifiedAt())) {
            sql.append(" and E.lastModifiedAt <= :endLastModifiedAt");
            values.put("endLastModifiedAt", request.getEndLastModifiedAt());
        }

        return sql.toString();
    }

    public StringBuilder createOrderQuery(EventConfigurationSearchQuery params) {
        StringBuilder sql = new StringBuilder(" ");
        if (StringUtils.hasLength(params.getSortBy())) {
            sql.append(" order by E.").append(params.getSortBy().replace(".", " "));
        } else {
            sql.append(" order by E.lastModifiedAt desc ");
        }
        return sql;
    }
}
