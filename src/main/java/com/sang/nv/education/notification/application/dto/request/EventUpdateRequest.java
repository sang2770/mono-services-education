package com.sang.nv.education.notification.application.dto.request;

import com.mbamc.common.dto.request.Request;
import com.mbamc.common.validator.ValidateConstraint;
import com.mbamc.common.validator.ValidateUUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateRequest extends Request {

    @NotBlank(message = "TITLE_REQUIRED")
    @Size(max = ValidateConstraint.LENGTH.TITLE_MAX_LENGTH, message = "TITLE_MAX_LENGTH")
    private String title;

    @Valid
    @NotEmpty(message = "BUILDING_IDS_REQUIRED")
    private List<@ValidateUUID String> buildingIds;

    @Valid private List<@ValidateUUID String> floorIds;

    @Valid private List<@ValidateUUID String> organizationIds;

    @NotNull(message = "EXPECT_NOTIFICATION_AT_REQUIRED")
    private Instant expectedNotificationAt;

    @NotBlank(message = "CONTENT_REQUIRED")
    private String content;

    @Size(max = ValidateConstraint.LENGTH.NOTE_MAX_LENGTH, message = "NOTE_MAX_LENGTH")
    private String note;

    @Valid
    @Size(max = 5, message = "FILE_MAX_LENGTH")
    private List<@ValidateUUID String> fileIds;
}
