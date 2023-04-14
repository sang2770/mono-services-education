package com.sang.nv.education.notification.infrastructure.persistence.repository.impl;

import com.mbamc.common.util.StrUtils;
import com.sang.nv.education.notification.domain.query.NotificationSearchQuery;
import com.sang.nv.education.notification.infrastructure.persistence.entity.NotificationEntity;
import com.sang.nv.education.notification.infrastructure.persistence.repository.custom.NotificationRepositoryCustom;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<NotificationEntity> search(NotificationSearchQuery params) {
        Map<String, Object> values = new HashMap<>();
        String sql =
                " SELECT e FROM NotificationEntity e "
                        + this.createWhereQuery(params, values)
                        + this.createOrderQuery(params);
        Query query = entityManager.createQuery(sql);
        values.forEach(query::setParameter);

        query.setFirstResult((params.getPageIndex() - 1) * params.getPageSize());
        query.setMaxResults(params.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long count(NotificationSearchQuery params) {
        Map<String, Object> values = new HashMap<>();
        Query query =
                entityManager.createQuery(
                        "SELECT count(e) FROM NotificationEntity e "
                                + this.createWhereQuery(params, values),
                        Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    private String createWhereQuery(NotificationSearchQuery params, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1 = 1 ");
        if (Objects.nonNull(params.getUserId()) && !StrUtils.isBlank(params.getUserId())) {
            sql.append(" and e.userId = :userId ");
            values.put("userId", params.getUserId());
        }
        sql.append(" and e.isSend = true ");
        sql.append(" and e.deleted = false ");
        return sql.toString();
    }

    public StringBuilder createOrderQuery(NotificationSearchQuery params) {
        StringBuilder sql = new StringBuilder(" ");
        if (StringUtils.hasLength(params.getSortBy())) {
            sql.append(" order by e.").append(params.getSortBy().replace(".", " "));
        } else {
            sql.append(" order by e.lastModifiedAt desc ");
        }
        return sql;
    }
}
