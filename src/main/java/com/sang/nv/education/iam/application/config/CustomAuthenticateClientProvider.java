package com.sang.nv.education.iam.application.config;


import com.sang.commonmodel.dto.response.iam.ClientToken;
import com.sang.commonmodel.error.enums.AuthenticationError;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.nv.education.iaminfrastructure.persistence.entity.ClientEntity;
import com.sang.nv.education.iaminfrastructure.persistence.repository.ClientEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthenticateClientProvider {

    private final ClientEntityRepository clientEntityRepository;
    private final TokenProvider tokenProvider;

    public CustomAuthenticateClientProvider(ClientEntityRepository clientEntityRepository, TokenProvider tokenProvider) {
        this.clientEntityRepository = clientEntityRepository;
        this.tokenProvider = tokenProvider;
    }

    public ClientToken authenticationClient(String clientId, String clientSecret) {
        ClientEntity clientEntity = clientEntityRepository.findByClientIdAndSecret(clientId, clientSecret).orElseThrow(() ->
                new ResponseException(AuthenticationError.UNAUTHORISED));
        log.info("Client {} login success", clientEntity.getId());
        String token = tokenProvider.createClientToken(clientId);
        return ClientToken.builder()
                .token(token)
                .build();
    }
}
