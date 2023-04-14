package com.sang.nv.education.notification.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageData {
    private String eventId;
    private String effectType;
    private String title;
    private String body;
    private String content;
    private String description;
    private String contentType;
    private List<ReceiverData> receivers;
}
