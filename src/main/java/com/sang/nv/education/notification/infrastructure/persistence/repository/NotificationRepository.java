package com.sang.nv.education.notification.infrastructure.persistence.repository;

import com.sang.nv.education.notification.infrastructure.persistence.repository.custom.NotificationRepositoryCustom;
import com.sang.nv.education.notification.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository
        extends JpaRepository<NotificationEntity, String>, NotificationRepositoryCustom {

    @Query(
            "from NotificationEntity e where e.deleted = false and e.isSend = true and e.userId = :userId")
    List<NotificationEntity> findAllByUserId(String userId);

    @Query(
            "from NotificationEntity e where e.deleted = false and e.isSend = true and e.userId = :userId and e.eventId in :eventIds")
    List<NotificationEntity> findAllByUserIdAndEventIds(String userId, List<String> eventIds);

    default Optional<NotificationEntity> findByUserIdAndEventId(String userId, String eventId) {
        return this.findAllByUserIdAndEventId(userId, eventId).stream().findFirst();
    }

    @Query(
            "from NotificationEntity e where e.deleted = false and e.isSend = true and e.userId = :userId and e.eventId = :eventId")
    List<NotificationEntity> findAllByUserIdAndEventId(String userId, String eventId);

    @Query(
            "from NotificationEntity e where e.deleted = false and e.isSend = true and e.id in :ids and e.userId = :userId")
    List<NotificationEntity> findAllByIdsAndUserId(List<String> ids, String userId);

    @Query(
            "from NotificationEntity e where e.deleted = false and e.isSend = true and e.id = :id and e.userId = :userId")
    Optional<NotificationEntity> findByIdAndUserId(String id, String userId);

    @Query("from NotificationEntity n where n.eventId = :id")
    List<NotificationEntity> findAllByEventId(String id);

    @Query("from NotificationEntity n where n.eventId in :ids")
    List<NotificationEntity> findAllByEventIds(List<String> ids);

    @Query(
            " SELECT COUNT (e) from NotificationEntity e where e.deleted = false and e.isSend = true and e.isRead = false "
                    + "and e.userId = :userId")
    Long countUnreadNotification(String userId);

    @Query(
            "from NotificationEntity e where e.deleted = false and e.isSend = true and e.isRead = false and e.userId = :userId")
    List<NotificationEntity> findAllUnreadNotiByUserId(String userId);
}
