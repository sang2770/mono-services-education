package com.sang.nv.education.notification.application.dto.request;

import com.mbamc.common.dto.request.PagingRequest;
import com.mbamc.common.validator.ValidateUUID;
import com.sang.nv.education.notification.infrastructure.support.enums.EventLevel;
import com.sang.nv.education.notification.infrastructure.support.enums.EventStatus;
import com.sang.nv.education.notification.infrastructure.support.enums.EventTargetType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSearchRequest extends PagingRequest {

    private String keyword;

    private List<EventStatus> statuses;

    private EventTargetType eventTargetType;

    private List<@ValidateUUID String> buildingIds;

    private List<@ValidateUUID String> floorIds;

    private List<@ValidateUUID String> organizationIds;

    private Instant expectedNotificationStartAt;

    private Instant expectedNotificationEndAt;

    private Instant notificationStartAt;

    private Instant notificationEndAt;

    private List<String> issuedUserIds;

    @ApiModelProperty(hidden = true)
    private EventLevel eventLevel;
}
