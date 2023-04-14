package com.sang.nv.education.notification.infrastructure.persistence.repository.custom;

import com.sang.nv.education.notification.domain.query.NotificationSearchQuery;
import com.sang.nv.education.notification.infrastructure.persistence.entity.NotificationEntity;

import java.util.List;

public interface NotificationRepositoryCustom {

    List<NotificationEntity> search(NotificationSearchQuery params);

    Long count(NotificationSearchQuery params);
}
