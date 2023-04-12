package com.sang.nv.education.common.web.security.impl;

import com.sang.nv.education.common.web.security.TokenCacheService;
import org.springframework.stereotype.Service;

@Service
public class TokenCacheServiceImpl implements TokenCacheService {
    @Override
    public boolean isExisted(String cacheName, String token) {
        return false;
    }

    @Override
    public void invalidToken(String token) {

    }

    @Override
    public void invalidRefreshToken(String refreshToken) {

    }

    @Override
    public boolean isInvalidToken(String token) {
        return TokenCacheService.super.isInvalidToken(token);
    }

    @Override
    public boolean isInvalidRefreshToken(String refreshToken) {
        return TokenCacheService.super.isInvalidRefreshToken(refreshToken);
    }
}
