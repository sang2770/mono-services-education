package com.sang.nv.education.iam.application.dto.request.User;

import com.sang.commonmodel.dto.request.Request;
import com.sang.commonmodel.validator.ValidateConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailForgotPasswordRequest extends Request {

    @NotBlank(message = "EMAIL_REQUIRED")
    @Size(max = ValidateConstraint.LENGTH.NAME_MAX_LENGTH, message = "EMAIL_LENGTH")
    private String email;
}
