package com.sang.nv.education.notification.domain.query;

import com.mbamc.common.enums.EventType;
import com.mbamc.common.query.PagingQuery;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
public class AutomaticEventSearchQuery extends PagingQuery {
    private String keyword;
    private List<EventType> eventTypes;
    private List<String> targetIds;
    private Instant notificationStartAt;
    private Instant notificationEndAt;
    private List<String> eventConfigurationIds;
    private List<EventStatus> statuses;
}
