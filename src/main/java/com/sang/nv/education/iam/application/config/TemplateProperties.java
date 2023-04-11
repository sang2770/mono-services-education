package com.sang.nv.education.iam.application.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "template")
public class TemplateProperties {
    private Template user;
    private String folder;

    @Getter
    @Setter
    public static class Template {
        private String exportFileName;
        private String importFileName;
    }
}
