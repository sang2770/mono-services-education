package com.sang.nv.education.iam.application.service;


import com.sang.nv.education.iaminfrastructure.support.exception.BadRequestError;

public interface AuthFailCacheService {
    String LOGIN_FAIL_COUNT_CACHE = "auth-login-fail-count";
    String BLOCKED_USER_LOGIN_CACHE = "auth-blocked-user";

    Integer REACH_MAX_LOGIN_FAIL_COUNT = 5;
    Integer REACH_WARNING_BEFORE_BLOCK_LOGIN_FAIL_COUNT = 3;

    BadRequestError checkLoginFail(String username);

    boolean isBlockedUser(String username);

    void resetLoginFail(String username);
}
