package com.sang.nv.education.exam.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class GroupQuestionCreateOrUpdateRequest extends BaseCreateOrUpdateRequest {
    @NotBlank(message = "SUBJECT_REQUIRED")
    String subjectId;
}
