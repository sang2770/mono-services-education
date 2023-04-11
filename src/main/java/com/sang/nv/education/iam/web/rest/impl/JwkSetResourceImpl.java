package com.sang.nv.education.iam.web.rest.impl;


import com.sang.nv.education.iamapplication.config.TokenProvider;
import com.sang.nv.education.iampresentation.web.rest.JwkSetResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwkSetResourceImpl implements JwkSetResource {

    private final TokenProvider tokenProvider;

    public JwkSetResourceImpl(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public Map<String, Object> keys() {
        return this.tokenProvider.jwkSet().toJSONObject();
    }
}
