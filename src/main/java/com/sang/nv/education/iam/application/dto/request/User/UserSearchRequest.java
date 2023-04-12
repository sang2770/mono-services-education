package com.sang.nv.education.iam.application.dto.request.User;

import com.sang.commonmodel.dto.request.PagingRequest;
import com.sang.nv.education.iam.infrastructure.support.enums.UserStatus;
import com.sang.nv.education.iam.infrastructure.support.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest extends PagingRequest {

    private String keyword;
    private List<UserStatus> statuses;

    private List<String> roleIds;

    private List<UserType> userTypes;

    List<String> classIds;
    List<String> departmentIds;
    List<String> keyIds;
}
