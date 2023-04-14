package com.sang.nv.education.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sang.commonmodel.domain.AuditableDomain;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonutil.IdUtils;
import com.sang.commonutil.StrUtils;
import com.sang.nv.education.iam.domain.User;
import com.sang.nv.education.notification.domain.command.EventCreateCommand;
import com.sang.nv.education.notification.domain.command.EventUpdateCommand;
import com.sang.nv.education.notification.infrastructure.support.enums.EventEffectType;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.notification.infrastructure.support.enums.EventType;
import com.sang.nv.education.notification.infrastructure.support.exception.BadRequestError;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Event extends AuditableDomain {
    private String id;
    private String title;

    private EventType eventType;

    private EventEffectType effectType;

    private EventStatus status;

    private Boolean deleted;

    private String attachedLink;

    private String content;
    @JsonIgnore
    private List<Notification> notifications;

    @JsonIgnore
    private List<Email> emails;

    private List<String> fileIds;
    private List<EventFile> eventFiles;
    private String note;
    private String senderUserId;
    private String senderUsername;
    private String failureCauses;
    private Long version;

    public Event(EventCreateCommand command) {
        this.id = IdUtils.nextId();
        this.title = command.getTitle();
        this.content = command.getContent();
        this.deleted = false;
        this.fileIds = command.getFileIds();
        this.status = EventStatus.PENDING;
        this.attachedLink = command.getAttachedLink();
        this.effectType = command.getEffectType();
        this.note = command.getNote();
        if (!CollectionUtils.isEmpty(command.getFileIds())) {
            createNewEventFile(command.getFileIds());
        }
    }

    private void createNewEventFile(List<String> fileIds) {
        List<EventFile> tmpEventFiles = new ArrayList<>();
        fileIds.forEach(fileId -> tmpEventFiles.add(new EventFile(fileId, id)));
        this.eventFiles = tmpEventFiles;
    }

    public void update(EventUpdateCommand command) {
        // kiem tra trang thai hien tai cua event, chi cho phep update khi o trang thai waiting
        if (!EventStatus.PENDING.equals(this.status)) {
            throw new ResponseException(BadRequestError.EVENT_CAN_NOT_CHANGE);
        }
        this.attachedLink =
                !StrUtils.isBlank(command.getAttachedLink())
                        ? command.getAttachedLink()
                        : this.attachedLink;
        this.content =
                !StrUtils.isBlank(command.getContent()) ? command.getContent() : this.content;
        this.title = !StrUtils.isBlank(command.getTitle()) ? command.getTitle() : this.title;
        this.note = command.getNote();

        if (!Objects.isNull(command.getExpectedNotificationAt())
                && command.getExpectedNotificationAt().isBefore(Instant.now())) {
            throw new ResponseException(BadRequestError.EVENT_CAN_NOT_SEND_BEFORE_PRESENT_TIME);
        }

        this.eventFiles.forEach(EventFile::delete);
        if (!CollectionUtils.isEmpty(command.getFileIds())) {
            for (String fileId : command.getFileIds()) {
                Optional<EventFile> eventFileOptional =
                        this.eventFiles.stream()
                                .filter(ef -> ef.getFileId().equals(fileId))
                                .findFirst();
                if (eventFileOptional.isEmpty()) {
                    this.eventFiles.add(new EventFile(fileId, id));
                } else {
                    EventFile eventFile = eventFileOptional.get();
                    eventFile.unDelete();
                }
            }
        }
    }

    public void delete() {
        if (Objects.equals(this.status, EventStatus.DONE)
                || Objects.equals(this.status, EventStatus.IN_PROGRESS)) {
            throw new ResponseException(BadRequestError.EVENT_CAN_NOT_DELETE);
        }
        this.deleted = true;
        if (!CollectionUtils.isEmpty(this.eventFiles)) {
            this.eventFiles.forEach(EventFile::delete);
        }
    }

    public void sent() {
        this.status = EventStatus.DONE;
        if (!CollectionUtils.isEmpty(this.notifications)) {
            this.notifications.forEach(Notification::sent);
        }
        if (!CollectionUtils.isEmpty(this.emails)) {
            this.emails.forEach(Email::sent);
        }
    }

    public void retrySent(String currentUserId) {
        this.senderUserId = currentUserId;
        this.status = EventStatus.IN_PROGRESS;
    }

    public void preSent(String currentUserId, List<User> targets) {
        if (Objects.equals(this.eventType, EventType.NOTIFICATION)) {
            this.generateNotification(targets);
        }
        if (Objects.equals(this.eventType, EventType.EMAIL)) {
            this.generateEmail(targets);
        }
        this.senderUserId = currentUserId;
        this.status = EventStatus.IN_PROGRESS;
    }

    public void generateNotification(List<User> targets) {
        if (Objects.nonNull(targets)) {
            List<Notification> notificationList = new ArrayList<>();
            targets.forEach(
                    target -> {
                        Notification notification = new Notification(target.getId(), id);
                        notificationList.add(notification);
                    });
            this.notifications = notificationList;
        }
    }

    public void generateEmail(List<User> targets) {
        if (Objects.nonNull(targets)) {
            List<Email> emailList = new ArrayList<>();
            targets.forEach(target -> emailList.add(new Email(target, id)));
            this.emails = emailList;
        }
    }

    public void failed(String failureCauses) {
        this.status = EventStatus.FAILED;
        this.failureCauses = failureCauses;
    }

    public void cancel() {
        // kiem tra trang thai cua event, neu dang o trang thai WAITING thi cho phep huy, nguoc lai
        // thi khong
        if (!status.equals(EventStatus.PENDING)) {
            throw new ResponseException(BadRequestError.EVENT_CAN_NOT_CANCEL);
        }
        this.status = EventStatus.CANCELLED;
        if (this.eventFiles != null && !this.eventFiles.isEmpty()) {
            this.eventFiles.forEach(EventFile::delete);
        }

        if (this.notifications != null && !this.notifications.isEmpty()) {
            this.notifications.forEach(Notification::deleted);
        }
    }

    public void enrichEventFile(List<EventFile> eventFiles) {
        this.eventFiles = eventFiles;
    }

    public void enrichNotification(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void enrichEventFiles(List<EventFile> eventFiles) {
        this.eventFiles = eventFiles;
    }


    public void enrichSenderUserName(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void enrichEmail(List<Email> emails) {
        this.emails = emails;
    }

    public void failedEmail(String failureCauses) {
        this.status = EventStatus.FAILED;
        this.failureCauses = failureCauses;
        if (!CollectionUtils.isEmpty(this.emails)) {
            this.emails.forEach(Email::failed);
        }
    }

    public void failedNotification(String failureCauses) {
        this.status = EventStatus.FAILED;
        this.failureCauses = failureCauses;
        if (!CollectionUtils.isEmpty(this.notifications)) {
            this.notifications.forEach(Notification::failed);
        }
    }

    public void sentEmail() {
        if (!CollectionUtils.isEmpty(this.emails)) {
            List<Email> sentEmail =
                    this.emails.stream()
                            .filter(email -> !Objects.equals(Boolean.TRUE, email.getIsSend()))
                            .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sentEmail)) {
                sentEmail.forEach(Email::sent);
            }
        }
    }

    public void sentNotification() {
        if (!CollectionUtils.isEmpty(this.notifications)) {
            List<Notification> sentNotification =
                    this.notifications.stream()
                            .filter(
                                    notification ->
                                            !Objects.equals(Boolean.TRUE, notification.getIsSend()))
                            .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sentNotification)) {
                sentNotification.forEach(Notification::sent);
            }
        }
    }
}
