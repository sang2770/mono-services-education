package com.sang.nv.education.storage.infrastructure.support;

import com.sang.commonmodel.error.ResponseError;
import lombok.Getter;

@Getter
public enum BadRequestError implements ResponseError {
    FILE_NOT_FOUND(404, "File not found!"),
    FILE_INVALID(400, "File invalid!")

    ;

    private final Integer code;
    private final String message;

    BadRequestError(Integer code, String message) {
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
        return 400;
    }
}
