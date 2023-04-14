package com.sang.nv.education.notification.application.dto.response;

import com.sang.nv.education.notification.infrastructure.support.enums.ContentType;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.notification.infrastructure.support.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class EventDTO {
    private String id;

    private String title;

    private EventType eventType;

    private EventStatus status;


    private Boolean deleted;

    private String attachedLink;

    private String content;

    private ContentType contentType;

    private String note;

    private String issuedUserId;

    private String issuedUserName;

    private String senderUserId;

    private String senderUsername;

    private Instant expectedNotificationAt; // ngày dự kiến gửi

    private Instant notificationAt; // ngày gửi thực tế

    private String eventConfigurationId;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
