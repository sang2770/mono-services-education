package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class GroupQuestionRandomRequest extends Request {
    @NotBlank(message = "GROUP_ID_REQUIRED")
    String groupId;
    Integer numberLowQuestion;
    Integer numberMediumQuestion;
    Integer numberHighQuestion;
}
