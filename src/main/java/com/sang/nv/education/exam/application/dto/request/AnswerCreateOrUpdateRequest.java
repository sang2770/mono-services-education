package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class AnswerCreateOrUpdateRequest extends Request {
    @NotBlank(message = "CONTENT_REQUIRED")
    String content;
    @NotBlank(message = "STATUS_REQUIRED")
    Boolean status;

}
