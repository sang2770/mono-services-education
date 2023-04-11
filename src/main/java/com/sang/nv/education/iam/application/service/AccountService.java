package com.sang.nv.education.iam.application.service;


import com.sang.commonmodel.auth.UserAuthority;
import com.sang.nv.education.iamapplication.dto.request.Auth.LoginRequest;
import com.sang.nv.education.iamapplication.dto.request.Auth.LogoutRequest;
import com.sang.nv.education.iamapplication.dto.request.Auth.RefreshTokenRequest;
import com.sang.nv.education.iamapplication.dto.response.AuthToken;
import com.sang.nv.education.iamdomain.User;

public interface AccountService {

    AuthToken login(LoginRequest request);
    AuthToken loginClient(LoginRequest request);

    AuthToken refreshToken(RefreshTokenRequest request);

    User myProfile();

    String currentUser();

    UserAuthority myAuthorities();

    void logout(LogoutRequest request);

}
