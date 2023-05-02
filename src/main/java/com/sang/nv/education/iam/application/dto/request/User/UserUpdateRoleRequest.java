package com.sang.nv.education.iam.application.dto.request.User;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateRoleRequest extends Request {
    private List<String> roleIds;
}
