package com.sang.nv.education.notification.infrastructure.persistence.repository;

import com.sang.nv.education.notification.infrastructure.persistence.entity.EventEntity;
import com.sang.nv.education.notification.infrastructure.persistence.repository.custom.EventRepositoryCustom;
import com.sang.nv.education.notification.infrastructure.support.enums.EventLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, String>, EventRepositoryCustom {

    @Query("from EventEntity e where e.id in (:eventIds) and e.deleted = false ")
    List<EventEntity> findAllByIds(List<String> eventIds);

    @Query("from EventEntity e where e.id = :id and e.deleted = false ")
    Optional<EventEntity> findByIdActivated(String id);

    @Query(
            "select distinct(e.id) from EventEntity e where e.deleted = false and e.id in :ids and e.eventLevel = :eventLevel")
    List<String> findIdByIdsAndEventLevel(List<String> ids, EventLevel eventLevel);
}
