package com.sang.nv.education.notification.domain.query;

import com.sang.commonmodel.query.PagingQuery;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.notification.infrastructure.support.enums.EventType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
public class EventSearchQuery extends PagingQuery {
    private List<EventStatus> statuses;
    private List<String> buildingIds;
    private List<String> floorIds;
    private List<String> organizationIds;
    private Instant expectedNotificationStartAt;
    private Instant expectedNotificationEndAt;
    private Instant notificationStartAt;
    private Instant notificationEndAt;
    private List<String> issuedUserIds;
    private String keyword;
    private List<EventType> eventTypes;
    private List<String> targetIds;
    private List<String> eventConfigurationIds;
}
