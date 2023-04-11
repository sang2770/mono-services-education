package com.sang.nv.education.iam.application.dto.request.User;

import com.sang.commonmodel.dto.request.Request;
import com.sang.commonmodel.validator.ValidateConstraint;
import com.sang.nv.education.iaminfrastructure.support.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateRequest extends Request {
    @NotNull(message = "FULL_NAME_REQUIRED")

    private String fullName;

    @NotBlank(message = "EMAIL_REQUIRED")
    @Size(max = ValidateConstraint.LENGTH.EMAIL_MAX_LENGTH,
            message = "PASSWORD_LENGTH")
    private String email;

    private String phoneNumber;

    private LocalDate dayOfBirth;

    private Boolean deleted;

    private String classId;
    private String code;
    private List<String> roleIds;
    private String avatarFileId;
    private String repeatPassword;

    @NotNull(message = "USER_TYPE_REQUIRED")
    private UserType userType;

}
