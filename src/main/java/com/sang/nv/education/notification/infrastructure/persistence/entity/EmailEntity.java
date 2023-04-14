package com.sang.nv.education.notification.infrastructure.persistence.entity;

import com.mbamc.common.entity.AuditableEntity;
import com.mbamc.common.validator.ValidateConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "email")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity extends AuditableEntity {

    @Id
    @Column(name = "id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private String id;

    @Column(name = "user_id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH)
    private String userId;

    @Column(name = "event_id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH, nullable = false)
    private String eventId;

    @Column(name = "email", length = ValidateConstraint.LENGTH.EMAIL_MAX_LENGTH, nullable = false)
    private String email;

    @Column(name = "is_send")
    private Boolean isSend;

    @Column(name = "send_at")
    private Instant sendAt;

    @Column(name = "deleted")
    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailEntity that = (EmailEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
