package com.sang.nv.education.notification.infrastructure.persistence.repository;

import com.mbamc.common.enums.TargetType;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventTargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTargetRepository extends JpaRepository<EventTargetEntity, String> {

    @Query(
            "from EventTargetEntity e where e.deleted = false and e.targetType = :targetType and e.eventId = :eventId")
    List<EventTargetEntity> findByTargetTypeAndEventId(TargetType targetType, String eventId);

    @Query("from EventTargetEntity e where e.deleted = false and e.eventId in :eventIds")
    List<EventTargetEntity> findByEventIds(List<String> eventIds);

    @Query("from EventTargetEntity e where e.deleted = false and e.eventId = :eventId")
    List<EventTargetEntity> findByEventId(String eventId);

    @Query(
            "select distinct(e.eventId) from EventTargetEntity e where e.deleted = false and e.targetId in :targetIds and e.targetType = :targetType")
    List<String> findEventIdsByTargetIdsAndTargetType(
            List<String> targetIds, TargetType targetType);
}
