package com.sang.nv.education.common.web.config;

import com.sang.commonutil.Constants;
import com.sang.nv.education.common.web.support.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUser().orElse(Constants.ANONYMOUS_ACCOUNT));
    }
}
