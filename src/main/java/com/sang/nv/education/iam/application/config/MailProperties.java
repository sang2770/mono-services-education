package com.sang.nv.education.iam.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail")
@Data
public class MailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
}
