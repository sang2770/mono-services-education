package com.sang.nv.education.notification.infrastructure.persistence.repository.impl;

import com.mbamc.common.persistence.support.SqlUtils;
import com.mbamc.common.util.StrUtils;
import com.sang.nv.education.notification.infrastructure.persistence.entity.NotificationTemplateEntity;
import com.sang.nv.education.notification.infrastructure.persistence.repository.custom.NotificationTemplateRepositoryCustom;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotificationTemplateEntityRepositoryImpl
        implements NotificationTemplateRepositoryCustom {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<NotificationTemplateEntity> search(NotificationTemplateSearchQuery searchQuery) {
        Map<String, Object> values = new HashMap<>();
        String sql =
                "FROM NotificationTemplateEntity  n "
                        + createWhereQuery(searchQuery, values)
                        + createOrderQuery(searchQuery);
        Query query = entityManager.createQuery(sql, NotificationTemplateEntity.class);
        values.forEach(query::setParameter);

        query.setFirstResult((searchQuery.getPageIndex() - 1) * searchQuery.getPageSize());
        query.setMaxResults(searchQuery.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long count(NotificationTemplateSearchQuery searchQuery) {
        Map<String, Object> values = new HashMap<>();
        Query query =
                entityManager.createQuery(
                        "Select count(*) FROM NotificationTemplateEntity n "
                                + createWhereQuery(searchQuery, values),
                        Long.class);
        values.forEach(query::setParameter);
        return (long) query.getSingleResult();
    }

    private String createWhereQuery(
            NotificationTemplateSearchQuery searchQuery, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder(" WHERE n.deleted = false ");
        if (StrUtils.isNotBlank(searchQuery.getKeyword())) {
            sql.append(" AND n.name like :keyword ");
            values.put("keyword", SqlUtils.encodeKeyword(searchQuery.getKeyword()));
        }

        if (!CollectionUtils.isEmpty(searchQuery.getStatuses())) {
            sql.append(" AND n.status in :statues ");
            values.put("statues", searchQuery.getStatuses());
        }

        if (Objects.nonNull(searchQuery.getStartCreatedAt())) {
            sql.append(" AND n.createdAt >= :startCreatedAt ");
            values.put("startCreatedAt", searchQuery.getStartCreatedAt());
        }

        if (Objects.nonNull(searchQuery.getEndCreatedAt())) {
            sql.append(" AND n.createdAt <= :endCreatedAt ");
            values.put("endCreatedAt", searchQuery.getEndCreatedAt());
        }

        if (Objects.nonNull(searchQuery.getStartLastModifiedAt())) {
            sql.append(" AND n.lastModifiedAt >= :startLastModifiedAt ");
            values.put("startLastModifiedAt", searchQuery.getStartLastModifiedAt());
        }

        if (Objects.nonNull(searchQuery.getEndLastModifiedAt())) {
            sql.append(" AND n.lastModifiedAt <= :endLastModifiedAt ");
            values.put("endLastModifiedAt", searchQuery.getEndLastModifiedAt());
        }

        return sql.toString();
    }

    private String createOrderQuery(NotificationTemplateSearchQuery searchQuery) {
        StringBuilder sql = new StringBuilder(" ");
        if (StringUtils.hasLength(searchQuery.getSortBy())) {
            sql.append(" order by n.").append(searchQuery.getSortBy().replace(".", " "));
        } else {
            sql.append(" order by n.lastModifiedAt desc ");
        }
        return sql.toString();
    }
}
