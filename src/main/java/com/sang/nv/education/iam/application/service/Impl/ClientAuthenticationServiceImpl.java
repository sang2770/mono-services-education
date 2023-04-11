package com.sang.nv.education.iam.application.service.Impl;


import com.sang.commonclient.config.security.ClientAuthentication;
import com.sang.commonmodel.dto.response.iam.ClientToken;
import com.sang.nv.education.iamapplication.config.CustomAuthenticateClientProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Primary
public class ClientAuthenticationServiceImpl implements ClientAuthentication {
    private final CustomAuthenticateClientProvider customAuthenticateClientProvider;

    public ClientAuthenticationServiceImpl(CustomAuthenticateClientProvider customAuthenticateClientProvider) {
        this.customAuthenticateClientProvider = customAuthenticateClientProvider;
    }

    @Cacheable(cacheNames = "client-token", key = "#clientId",
            condition = "#clientId != null", unless = "#clientId == null || #result == null")
    @Override
    public ClientToken getClientToken(String clientId, String clientSecret) {

        log.info("Client {} login", clientId);
        return customAuthenticateClientProvider.authenticationClient(clientId, clientSecret);
    }
}
