package com.sang.nv.education.iam.web.rest;

import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.iamapplication.dto.request.Auth.LoginRequest;
import com.sang.nv.education.iamapplication.dto.request.Auth.LogoutRequest;
import com.sang.nv.education.iamapplication.dto.request.Auth.RefreshTokenRequest;
import com.sang.nv.education.iamapplication.dto.response.AuthToken;
import com.sang.nv.education.iamdomain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(tags = "Account Resource")
@RequestMapping("/api")
public interface AccountResource {
    @ApiOperation(value = "Get my profile")
    @GetMapping("/me/profile")
    Response<User> myProfile();

    @ApiOperation(value = "Login")
    @PostMapping("/authenticate")
    Response<AuthToken> authenticate(@RequestBody @Valid LoginRequest request);

    @ApiOperation(value = "Login")
    @PostMapping("/authenticate/client")
    Response<AuthToken> authenticateClient(@RequestBody @Valid LoginRequest request);


    @ApiOperation(value = "Refresh token")
    @PostMapping("/refresh-token")
    Response<AuthToken> refreshToken(@RequestBody @Valid RefreshTokenRequest request);

    @ApiOperation(value = "Who I am - Only for Dev")
    @GetMapping("/account")
    Response<String> me();

    @ApiOperation(value = "Get my authorities")
    @GetMapping("/me/authorities")
    Response<UserAuthority> myAuthorities();

    @ApiOperation(value = "Logout")
    @PostMapping("/logout")
    Response<Boolean> logout(@RequestBody(required = false) LogoutRequest request);

}
