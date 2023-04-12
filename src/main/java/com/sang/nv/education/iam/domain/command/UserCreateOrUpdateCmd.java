package com.sang.nv.education.iam.domain.command;

import com.sang.nv.education.iam.infrastructure.support.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateOrUpdateCmd {
    private String username;
    private String password;
    private String fullName;
    private String email;

    private String phoneNumber;

    private LocalDate dayOfBirth;

    private Boolean deleted;

    private UserType userType;
    private String avatarFileId;
    private String classId;
    private String code;
    private Boolean isRoot;
    private List<String> roleIds;

    private String avatarFileViewUrl;

}
