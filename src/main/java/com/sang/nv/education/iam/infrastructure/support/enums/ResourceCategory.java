package com.sang.nv.education.iam.infrastructure.support.enums;


import com.sang.commonmodel.enums.Scope;

import java.util.List;

public enum ResourceCategory {
    USER_MANAGEMENT("USER", "Quản lý người dùng", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE), 1),
    ROLE_MANAGEMENT("ROLE", "Quản lý vai trò", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE), 2),
    SUBJECT_MANAGEMENT("SUBJECT", "Quản lý môn học", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE, Scope.DELETE), 3),
    QUESTION_MANAGEMENT("QUESTION", "Quản lý câu hỏi", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE, Scope.DELETE), 4),
    GROUP_QUESTION_MANAGEMENT("GROUP_QUESTION", "Quản lý nhóm câu hỏi", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE, Scope.DELETE), 5),
    EXAM_MANAGEMENT("EXAM", "Quản lý đề thi", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE, Scope.DELETE), 6),
    ROOM_MANAGEMENT("ROOM", "Quản lý phòng thi", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE, Scope.DELETE), 7),
    CLASSES_MANAGEMENT("CATEGORY", "Quản lý lớp", List.of(Scope.CREATE, Scope.VIEW, Scope.UPDATE, Scope.DELETE), 8),
    DEPARTMENT_MANAGEMENT("DEPARTMENT", "Quản lý khoa", List.of(Scope.CREATE, Scope.UPDATE, Scope.VIEW, Scope.DELETE), 9),
    COURSE_MANAGEMENT("COURSE", "Quản lý khóa học", List.of(Scope.CREATE, Scope.UPDATE, Scope.VIEW, Scope.DELETE), 10),
    PERIOD_MANAGEMENT("PERIOD", "Quản lý kì thi", List.of(Scope.CREATE, Scope.UPDATE, Scope.VIEW, Scope.DELETE), 11),
    ;
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
