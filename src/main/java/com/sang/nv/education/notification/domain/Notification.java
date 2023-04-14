package com.sang.nv.education.notification.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sang.commonmodel.domain.AuditableDomain;
import com.sang.commonutil.IdUtils;
import com.sang.nv.education.notification.application.dto.NotificationContent;
import com.sang.nv.education.notification.infrastructure.support.enums.ContentType;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.storage.domain.FileDomain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Notification extends AuditableDomain {
    private String id;
    private String userId;
    private String eventId;
    private Boolean isRead;
    private Instant readAt;
    private Boolean isSend;
    private Instant sendAt;
    private Boolean deleted;

    private String description;
    private String title;
    private EventStatus status;
    private String attachedLink;
    private String content;
    private NotificationContent contentJson;
    private ContentType contentType;
    private ArrayList<AttachFile> attachFiles;
    private Long version;

    public Notification(String userId, String eventId) {
        this.id = IdUtils.nextId();
        this.userId = userId;
        this.eventId = eventId;
        this.isRead = false;
        this.isSend = false;
        this.deleted = false;
    }

    public void read() {
        this.isRead = true;
        this.readAt = Instant.now();
    }

    public void unread() {
        this.isRead = false;
        this.readAt = null;
    }

    public void sent() {
        this.isSend = true;
        this.sendAt = Instant.now();
    }

    public void deleted() {
        this.deleted = true;
    }

//    public void enrichEvent(Event event) {
//        this.description = event.getDescription();
//        this.title = event.getTitle();
//        this.status = event.getStatus();
//        this.attachedLink = event.getAttachedLink();
//        this.content = event.getContent();
//        this.contentType = event.getContentType();
//        if (ContentType.JSON.equals(event.getContentType())) {
//            try {
//                this.contentJson =
//                        new ObjectMapper().readValue(event.getContent(), new TypeReference<>() {});
//            } catch (JsonProcessingException e) {
//            }
//        }
//    }

    public void enrichAttachFiles(List<FileDomain> files) {
        this.attachFiles = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(files)) {
//            files.forEach(
//                    file ->
//                            attachFiles.add(
//                                    new AttachFile(
//                                            file.getOriginalName(),
//                                            file.getViewUrl(),
//                                            file.getDownloadUrl())));
//        }
    }

    public void failed() {
        this.isSend = false;
        this.sendAt = null;
    }
}
