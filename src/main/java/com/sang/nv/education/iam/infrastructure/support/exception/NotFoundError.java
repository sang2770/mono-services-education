package com.sang.nv.education.iam.infrastructure.support.exception;


import com.sang.commonmodel.error.ResponseError;

public enum NotFoundError implements ResponseError {

    USER_NOT_FOUND(40401001, "User not found: {0}"),
    EMPLOYEE_NOT_FOUND(40401001, "Employee not found: {0}"),
    ROLE_NOT_FOUND(40401002, "Role not found: {0}"),
    PERMISSION_NOT_FOUND(40401002, "Permission not found: {0}"),
    ORGANIZATION_NOT_EXITED(40401003, "Organization not exited");

    private final Integer code;
    private final String message;

    NotFoundError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return 404;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
