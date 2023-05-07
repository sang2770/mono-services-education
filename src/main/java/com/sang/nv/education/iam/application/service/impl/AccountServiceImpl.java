package com.sang.nv.education.iam.application.service.impl;


import com.sang.common.captcha.dto.CaptchaDTO;
import com.sang.common.captcha.service.CaptchaService;
import com.sang.common.captcha.service.LoginAttemptService;
import com.sang.common.captcha.utils.CaptchaConstants;
import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.error.enums.AuthenticationError;
import com.sang.commonmodel.error.enums.NotFoundError;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonutil.StrUtils;
import com.sang.nv.education.common.web.security.AuthorityService;
import com.sang.nv.education.common.web.support.SecurityUtils;
import com.sang.nv.education.iam.application.config.AuthenticationProperties;
import com.sang.nv.education.iam.application.config.TokenProvider;
import com.sang.nv.education.iam.application.dto.request.Auth.LoginRequest;
import com.sang.nv.education.iam.application.dto.request.Auth.LogoutRequest;
import com.sang.nv.education.iam.application.dto.request.Auth.RefreshTokenRequest;
import com.sang.nv.education.iam.application.dto.request.User.EmailForgotPasswordRequest;
import com.sang.nv.education.iam.application.dto.request.User.ForgotPasswordRequest;
import com.sang.nv.education.iam.application.dto.response.AuthToken;
import com.sang.nv.education.iam.application.mapper.IamAutoMapper;
import com.sang.nv.education.iam.application.service.AccountService;
import com.sang.nv.education.iam.application.service.AuthFailCacheService;
import com.sang.nv.education.iam.application.service.SendEmailService;
import com.sang.nv.education.iam.domain.User;
import com.sang.nv.education.iam.domain.repository.UserDomainRepository;
import com.sang.nv.education.iam.infrastructure.persistence.entity.UserEntity;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.UserEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.repository.UserEntityRepository;
import com.sang.nv.education.iam.infrastructure.support.enums.UserStatus;
import com.sang.nv.education.iam.infrastructure.support.enums.UserType;
import com.sang.nv.education.iam.infrastructure.support.exception.BadRequestError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String TITLE_KEY = "Khôi phục mật khẩu";
    private static final String TEMPLATE_NAME = "email/passwordResetEmail";
    private final UserDomainRepository userDomainRepository;
    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IamAutoMapper autoMapper;
    private final TokenProvider tokenProvider;
    private final AuthenticationProperties authenticationProperties;
    private final AuthenticationManager authenticationManager;
    private final AuthorityService authorityService;
    private final AuthFailCacheService authFailCacheService;
    private final SendEmailService sendEmailService;
    private final LoginAttemptService loginAttemptService;
    private final CaptchaService captchaService;

    @Override
    public AuthToken login(LoginRequest request) {
        return this.authenticateUser(request, true);
    }

    @Override
    public AuthToken loginClient(LoginRequest request) {
        return this.authenticateUser(request, false);
    }

    @Override
    public AuthToken refreshToken(RefreshTokenRequest request) {
        if (!this.tokenProvider.validateRefreshToken(request.getRefreshToken())) {
            throw new ResponseException(AuthenticationError.INVALID_REFRESH_TOKEN);
        }

        String userId = this.tokenProvider.getSubject(request.getRefreshToken());
        UserEntity userEntity = this.userEntityRepository.findById(userId).orElseThrow(() ->
                new ResponseException(NotFoundError.USER_NOT_FOUND));
        if (!UserStatus.ACTIVE.equals(userEntity.getStatus())) {
            authFailCacheService.checkLoginFail(userEntity.getUsername());
            throw new ResponseException(BadRequestError.LOGIN_FAIL_BLOCK_ACCOUNT);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity.getUsername(),
                "",
                new ArrayList<>());
        String accessToken = this.tokenProvider.createToken(authentication, userEntity.getId());
        String refreshToken = this.tokenProvider.createRefreshToken(userEntity.getId());

        long expiresIn = this.authenticationProperties.getAccessTokenExpiresIn().toSeconds();

        return AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(AuthToken.TOKEN_TYPE_BEARER)
                .expiresIn(expiresIn)
                .build();
    }

    @Override
    public User myProfile() {
        return currentAccount();
    }

    public String currentUserId() {
        Optional<String> currentUserLoginId = SecurityUtils.getCurrentUserLoginId();
        if (currentUserLoginId.isEmpty()) {
            throw new ResponseException(AuthenticationError.UNAUTHORISED);
        }
        return currentUserLoginId.get();
    }

    @Override
    public UserAuthority myAuthorities() {
        String me = currentUserId();
        return this.authorityService.getUserAuthority(me);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        // revoke device -> invalid token and refresh token
        Optional<String> optionalJwt = SecurityUtils.getCurrentUserJWT();
        if (optionalJwt.isPresent()) {
            String accessToken = optionalJwt.get();
            tokenProvider.invalidJwt(accessToken, logoutRequest.getRefreshToken());
        }
    }

    @Override
    public String currentUser() {
        Optional<String> currentUser = SecurityUtils.getCurrentUser();
        if (currentUser.isEmpty()) {
            throw new ResponseException(AuthenticationError.UNAUTHORISED);
        }
        return currentUser.get();
    }

    private User currentAccount() {
        String userId = currentUserId();
        User user = this.userDomainRepository.getById(userId);
//        if (Objects.nonNull(user.getAvatarFileId()))
//        {
//            Response<FileDTO> response = this.storageClient.findById(user.getAvatarFileId());
//            if (response.isSuccess() && Objects.nonNull(response.getData()))
//            {
//                user.enrichAvatarFileViewUrl(response.getData().getFilePath());
//            }
//        }
        return user;
    }

    private AuthToken authenticateUser(LoginRequest request, Boolean isManager) {
        log.warn("User {} start login", request.getUsername());

        // check account was locked
//        if (authFailCacheService.isBlockedUser(request.getUsername())) {
//            loginAttemptService.loginFailed(request.getUsername().toLowerCase());
//            log.warn("User {} is blocked", request.getUsername());
//            throw new ResponseException(BadRequestError.LOGIN_FAIL_BLOCK_ACCOUNT);
//        }

        if (loginAttemptService.isRequiredCaptcha(request.getUsername().toLowerCase())) {
            HttpServletRequest httpServletRequest =
                    ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                            .getRequest();

            String captcha = httpServletRequest.getHeader(CaptchaConstants.X_CAPTCHA_HEADER);
            String transactionId =
                    httpServletRequest.getHeader(CaptchaConstants.X_TRANSACTION_ID);
            log.info("Captcha: {} transaction id: {}", captcha, transactionId);
            if (StrUtils.isBlank(captcha)
                    || StrUtils.isBlank(transactionId)
                    || !passwordEncoder.matches(captcha, transactionId)
                    || !captchaService.validate(transactionId, captcha)) {
                Map<String, Object> params = captchaService.generateRequired();
                log.warn("zzz");
                throw new ResponseException(BadRequestError.INCORRECT_CAPTCHA, params);
            }
        }

        // check user
        Optional<UserEntity> optionalUserEntity = this.userEntityRepository.findByUsername(request.getUsername());
        if (optionalUserEntity.isEmpty()) {
            BadRequestError error = authFailCacheService.checkLoginFail(request.getUsername());
            log.warn("User login not found: {}", request.getUsername());
            if (error == null) {
                loginAttemptService.loginFailed(request.getUsername().toLowerCase());
                throw new BadCredentialsException("Bad credential!");
            } else {
                loginAttemptService.loginFailed(request.getUsername().toLowerCase());
                throw new ResponseException(error.getMessage(), error);
            }
        }


        UserEntity userEntity = optionalUserEntity.get();
        if (!UserStatus.ACTIVE.equals(userEntity.getStatus())) {
            log.warn("User login not activated: {}", request.getUsername());
            authFailCacheService.checkLoginFail(userEntity.getUsername());
            loginAttemptService.loginFailed(request.getUsername().toLowerCase());
            throw new ResponseException(BadRequestError.LOGIN_FAIL_BLOCK_ACCOUNT);
        }

        if (Boolean.TRUE.equals(Objects.equals(userEntity.getIsRoot(), false) && isManager) && !Objects.equals(userEntity.getUserType(), UserType.MANAGER)) {
            log.warn("User login not manager: {}", request.getUsername());
            authFailCacheService.checkLoginFail(userEntity.getUsername());
            loginAttemptService.loginFailed(request.getUsername().toLowerCase());
            throw new ResponseException(BadRequestError.USER_NOT_PERMISSION_FAIL_ACCOUNT);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername().toLowerCase(),
                request.getPassword(), new ArrayList<>());
        try {
            authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            log.warn("User login fail: {}", request.getUsername());
            authFailCacheService.checkLoginFail(userEntity.getUsername());
            loginAttemptService.loginFailed(request.getUsername().toLowerCase());
            throw new BadCredentialsException("Bad credential!");
        }

        String accessToken = this.tokenProvider.createToken(authentication, userEntity.getId());
        long expiresIn = this.authenticationProperties.getAccessTokenExpiresIn().toSeconds();
        String refreshToken = null;
        if (request.isRememberMe()) {
            refreshToken = this.tokenProvider.createRefreshToken(userEntity.getId());
        }
        log.warn("User {} login success", request.getUsername());

        authFailCacheService.resetLoginFail(request.getUsername());
        this.loginAttemptService.loginSucceeded(request.getUsername());
        return AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(AuthToken.TOKEN_TYPE_BEARER)
                .expiresIn(expiresIn)
                .build();
    }

    @Override
    public void forgotPassword(EmailForgotPasswordRequest request) throws MessagingException, javax.mail.MessagingException {
        Optional<UserEntity> userEntityByEmail =
                userEntityRepository.findByEmail(request.getEmail());
        if (userEntityByEmail.isEmpty()) {
            log.warn("Password reset requested for non existing mail '{}'", request.getEmail());
            throw new ResponseException(BadRequestError.EMAIL_NOT_EXISTED_IN_SYSTEM);
        }
        User user = userEntityMapper.toDomain(userEntityByEmail.get());
        String token = tokenProvider.createTokenSendEmail(user.getId(), request.getEmail());
        sendEmailService.send(user, TEMPLATE_NAME, TITLE_KEY, token);
    }

    @Override
    public void resetPassword(ForgotPasswordRequest request) {
        String userId = tokenProvider.validateEmailToken(request.getToken());
        if (Objects.isNull(userId)) {
            throw new ResponseException(BadRequestError.USER_INVALID);
        }
        User user = this.userDomainRepository.getById(userId);
        if (!Objects.equals(request.getPassword(), request.getRepeatPassword())) {
            throw new ResponseException(BadRequestError.REPEAT_PASSWORD_DOES_NOT_MATCH);
        }
        String encodedPassword = this.passwordEncoder.encode(request.getPassword());
        user.changePassword(encodedPassword);
        this.userDomainRepository.save(user);
    }

    @Override
    public CaptchaDTO refreshCaptcha() {
        return this.captchaService.generate();
    }
}
