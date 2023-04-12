package com.sang.nv.education.common.web.security;


import com.sang.commonmodel.auth.UserAuthority;

public interface AuthorityService {

    UserAuthority getUserAuthority(String userId);

}
