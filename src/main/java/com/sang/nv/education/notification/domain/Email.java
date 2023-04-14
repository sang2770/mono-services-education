package com.sang.nv.education.notification.domain;

import com.sang.commonmodel.domain.AuditableDomain;
import com.sang.commonutil.IdUtils;
import com.sang.nv.education.iam.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Email extends AuditableDomain {

    private String id;
    private String userId;
    private String eventId;
    private Boolean deleted;
    private String email;
    private Boolean isSend;
    private Instant sendAt;

    public Email(User user, String eventId) {
        this.id = IdUtils.nextId();
        this.userId = user.getId();
        this.eventId = eventId;
        this.email = user.getEmail();
        this.deleted = false;
        this.isSend = false;
    }

    public Email(String email, String eventId) {
        this.id = IdUtils.nextId();
        this.eventId = eventId;
        this.email = email;
        this.deleted = false;
        this.isSend = false;
    }

    public void sent() {
        this.isSend = true;
        this.sendAt = Instant.now();
    }

    public void failed() {
        this.isSend = false;
        this.sendAt = null;
    }
}
