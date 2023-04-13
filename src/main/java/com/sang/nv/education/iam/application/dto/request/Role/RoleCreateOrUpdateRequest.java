package com.sang.nv.education.iam.application.dto.request.Role;

import com.sang.commonmodel.dto.request.Request;
import com.sang.commonmodel.validator.ValidateConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleCreateOrUpdateRequest extends Request {

//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "ROLE_CODE_FORMAT")
//    @Size(max = ValidateConstraint.LENGTH.CODE_MAX_LENGTH, message = "ROLE_CODE_LENGTH")
//    @NotBlank(message = "ROLE_CODE_REQUIRED")
//    private String code;

    @NotBlank(message = "ROLE_NAME_REQUIRED")
    @Size(max = ValidateConstraint.LENGTH.NAME_MAX_LENGTH, message = "ROLE_NAME_LENGTH")
    private String name;

    @Size(max = ValidateConstraint.LENGTH.DESC_MAX_LENGTH, message = "ROLE_DESCRIPTION_LENGTH")
    private String description;

    private Boolean isRoot;

    @Valid
    private List<RolePermissionCreateRequest> permissions;
}
