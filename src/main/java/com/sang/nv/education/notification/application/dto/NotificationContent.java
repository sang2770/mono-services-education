package com.sang.nv.education.notification.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationContent {
    private String dataType;
    private String dataId;
    private String buildingId;
}
