package com.sang.nv.education.notification.infrastructure.persistence.entity;

import com.sang.commonmodel.entity.AuditableEntity;
import com.sang.commonmodel.validator.ValidateConstraint;
import com.sang.nv.education.notification.infrastructure.support.enums.ContentType;
import com.sang.nv.education.notification.infrastructure.support.enums.EventEffectType;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.notification.infrastructure.support.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(
        name = "event",
        indexes = {
            @Index(name = "event_issued_user_id_idx", columnList = "issued_user_id"),
            @Index(name = "event_event_type_idx", columnList = "event_type"),
            @Index(name = "event_deleted_idx", columnList = "deleted"),
        })
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class EventEntity extends AuditableEntity {
    @Id
    @Column(name = "id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH, nullable = false)
    private String id;

    @Column(name = "description", length = ValidateConstraint.LENGTH.DESC_MAX_LENGTH)
    private String description;

    @Column(name = "title", length = ValidateConstraint.LENGTH.TITLE_MAX_LENGTH, nullable = false)
    private String title;

    @Column(name = "event_type", length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private EventType eventType;


    @Column(name = "effect_type", length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private EventEffectType effectType;

    @Column(name = "status", length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "attached_link", length = ValidateConstraint.LENGTH.VALUE_MAX_LENGTH)
    private String attachedLink;

    @Column(name = "notification_at")
    private Instant notificationAt;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(
            name = "content_type",
            length = ValidateConstraint.LENGTH.ENUM_MAX_LENGTH,
            nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name = "note", length = ValidateConstraint.LENGTH.NOTE_MAX_LENGTH)
    private String note;

    @Column(name = "issued_user_id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private String issuedUserId;

    @Column(name = "sender_user_id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private String senderUserId;

    @Column(name = "expected_notification_at")
    private Instant expectedNotificationAt;

    @Column(name = "failure_causes", length = ValidateConstraint.LENGTH.NOTE_MAX_LENGTH)
    private String failureCauses;

    @Column(name = "event_configuration_id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private String eventConfigurationId;

    @Version
    @Column(name = "version")
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventEntity that = (EventEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
