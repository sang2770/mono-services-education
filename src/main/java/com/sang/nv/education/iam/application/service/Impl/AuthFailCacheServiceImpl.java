package com.sang.nv.education.iam.application.service.Impl;


import com.sang.nv.education.iam.application.service.AuthFailCacheService;
import com.sang.nv.education.iam.infrastructure.support.exception.BadRequestError;
import org.springframework.stereotype.Service;

@Service
public class AuthFailCacheServiceImpl implements AuthFailCacheService {

    @Override
    public BadRequestError checkLoginFail(String username) {
        return null;
    }

    @Override
    public boolean isBlockedUser(String username) {
        return false;
    }

    @Override
    public void resetLoginFail(String username) {

    }
}
