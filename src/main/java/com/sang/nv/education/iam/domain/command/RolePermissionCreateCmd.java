package com.sang.nv.education.iam.domain.command;

import com.sang.commonmodel.enums.Scope;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionCreateCmd {

    private String resourceCode;
    private List<Scope> scopes;
}
