package com.sang.nv.education.iam.application.dto.request.Classes;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class ClassesCreateOrUpdateRequest extends Request {
    @NotBlank(message = "NAME_REQUIRED")
    String name;
    @NotBlank(message = "KEY_REQUIRED")
    String keyId;
    @NotBlank(message = "DEPARTMENT_ID_REQUIRED")
    String departmentId;

//    @NotBlank(message = "CODE_REQUIRED")
//    String code;
}
