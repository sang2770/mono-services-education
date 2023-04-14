package com.sang.nv.education.notification.application.dto.response;

import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UserDetailGetNotificationResponse {
    private String id;
    private String name;
    private String email;
    private List<String> roleNames;
    private Instant notificationAt;
    private Boolean wasSend;
    private EventStatus status;
    private String username;
}
