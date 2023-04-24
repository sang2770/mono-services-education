package com.sang.nv.education.iam.presentation.web.rest.impl;


import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.response.Response;
import com.sang.commonutil.StrUtils;
import com.sang.commonutil.StringPool;
import com.sang.nv.education.iam.application.dto.request.Auth.LoginRequest;
import com.sang.nv.education.iam.application.dto.request.Auth.LogoutRequest;
import com.sang.nv.education.iam.application.dto.request.Auth.RefreshTokenRequest;
import com.sang.nv.education.iam.application.dto.request.User.EmailForgotPasswordRequest;
import com.sang.nv.education.iam.application.dto.request.User.ForgotPasswordRequest;
import com.sang.nv.education.iam.application.dto.response.AuthToken;
import com.sang.nv.education.iam.application.service.AccountService;
import com.sang.nv.education.iam.domain.User;
import com.sang.nv.education.iam.infrastructure.support.util.Const;
import com.sang.nv.education.iam.presentation.web.rest.AccountResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountResourceImpl implements AccountResource {
    private final AccountService accountService;
    @Value("${app.iam.reset-password-deep-link}")
    private String urlResetPassword;

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

    @Override
    public Response<Boolean> initResetPassword(EmailForgotPasswordRequest request) throws MessagingException {
        this.accountService.forgotPassword(request);
        return Response.ok();
    }

    @Override
    public Response<Boolean> resetPassword(ForgotPasswordRequest request) {
        this.accountService.resetPassword(request);
        return Response.ok();
    }

    @Override
    public RedirectView redirect(String resetPasswordToken, HttpServletResponse response) {
        String url;
        if (!StrUtils.isBlank(urlResetPassword)) {
            url = String.format(urlResetPassword, resetPasswordToken);
        } else {
            url = Const.DEFAULT_LINK_RESET_PASSWORD;
        }
        response.setHeader(StringPool.LOCATION, url);
        response.setStatus(Const.REDIRECTION_CODE);
        return new RedirectView(url);
    }
}
