package com.sang.nv.education.notification.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateCommand {

    private String description;

    private String title;

    private String attachedLink;

    private String content;

    private Instant expectedNotificationAt;

    private String note;

    private List<String> buildingIds;

    private List<String> floorIds;

    private List<String> organizationIds;

    private List<String> fileIds;
}
