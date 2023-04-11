package com.sang.nv.education.iam.web.rest.impl;


import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.iamapplication.dto.request.Auth.LoginRequest;
import com.sang.nv.education.iamapplication.dto.request.Auth.LogoutRequest;
import com.sang.nv.education.iamapplication.dto.request.Auth.RefreshTokenRequest;
import com.sang.nv.education.iamapplication.dto.response.AuthToken;
import com.sang.nv.education.iamapplication.service.AccountService;
import com.sang.nv.education.iamdomain.User;
import com.sang.nv.education.iampresentation.web.rest.AccountResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResourceImpl implements AccountResource {
    private final AccountService accountService;

    public AccountResourceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Response<User> myProfile() {
        return Response.of(accountService.myProfile());
    }

    @Override
    public Response<AuthToken> authenticate(LoginRequest request) {
        AuthToken authToken = this.accountService.login(request);
        return Response.of(authToken);
    }

    @Override
    public Response<AuthToken> authenticateClient(LoginRequest request) {
        AuthToken authToken = this.accountService.loginClient(request);
        return Response.of(authToken);
    }

    @Override
    public Response<AuthToken> refreshToken(RefreshTokenRequest request) {
        return Response.of(this.accountService.refreshToken(request));
    }

    @Override
    public Response<String> me() {
        return Response.of(this.accountService.currentUser());
    }

    @Override
    public Response<UserAuthority> myAuthorities() {
        return Response.of(this.accountService.myAuthorities());
    }

    @Override
    public Response<Boolean> logout(LogoutRequest request) {
        this.accountService.logout(request);
        return Response.of(Boolean.TRUE);
    }
}
