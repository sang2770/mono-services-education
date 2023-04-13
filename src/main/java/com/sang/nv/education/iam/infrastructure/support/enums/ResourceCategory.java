package com.sang.nv.education.iam.infrastructure.support.enums;


import com.sang.commonmodel.enums.Scope;

import java.util.List;

public enum ResourceCategory {
    USER_MANAGEMENT("USER", "Quản lý nguười dùng", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE), 1),
    ROLE_MANAGEMENT("ROLE", "Quản lý vai trò", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE), 2),
    DEPARTMENT_MANAGEMENT("DEPARTMENT", "Quản lý khoa", List.of(Scope.CREATE, Scope.UPDATE, Scope.VIEW, Scope.DELETE), 3);
    String resourceCode;
    String resourceName;
    Integer priority;
    List<Scope> scopes;

    ResourceCategory(String resourceCode, String resourceName, List<Scope> scopes, Integer priority) {
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.scopes = scopes;
        this.priority = priority;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public List<Scope> getScopes() {
        return scopes;
    }

    public Integer getPriority() {
        return priority;
    }
}
