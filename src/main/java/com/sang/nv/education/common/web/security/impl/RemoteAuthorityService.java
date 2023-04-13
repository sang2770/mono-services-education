package com.sang.nv.education.common.web.security.impl;


import com.sang.commonmodel.auth.UserAuthority;
import com.sang.nv.education.common.web.security.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoteAuthorityService implements AuthorityService {

    private final AuthorityService authorityService;
    @Cacheable(cacheNames = "user-authority", key = "#userId",
            condition = "#userId != null", unless = "#userId == null || #result == null")
    @Override
    public UserAuthority getUserAuthority(String userId) {
        return this.authorityService.getUserAuthority(userId);
    }

}
