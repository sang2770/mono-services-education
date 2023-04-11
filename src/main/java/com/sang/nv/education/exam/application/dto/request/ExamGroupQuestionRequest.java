package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class ExamGroupQuestionRequest extends Request {
    @NotBlank(message = "GROUP_ID_REQUIRED")
    String groupQuestionId;

    @NotBlank(message = "NUMBER_QUESTION_REQUIRED")
    Integer numberQuestion;

}
