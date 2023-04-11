package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class BaseCreateOrUpdateRequest extends Request {
    @NotBlank(message = "NAME_REQUIRED")
    String name;
}
