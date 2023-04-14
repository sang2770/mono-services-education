package com.sang.nv.education.notification.infrastructure.persistence.repository;

import com.sang.nv.education.notification.infrastructure.persistence.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, String> {

    @Query("From EmailEntity e where e.deleted = false and e.eventId in :eventIds")
    List<EmailEntity> findAllByEventIds(List<String> eventIds);
}
