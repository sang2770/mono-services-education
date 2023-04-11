package com.sang.nv.education.iam.application.dto.request.Client;


import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientCreateOrUpdateRequest extends Request {
    @NotBlank(message = "NAME_REQUIRED")
    private String name;
}
