package com.sang.nv.education.notification.infrastructure.persistence.repository.custom;

import com.sang.nv.education.notification.domain.query.EventSearchQuery;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositoryCustom {

    List<EventEntity> search(EventSearchQuery query);

    Long countEvent(EventSearchQuery query);
}
