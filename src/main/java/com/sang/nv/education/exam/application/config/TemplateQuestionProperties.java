package com.sang.nv.education.exam.application.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "question-template")
public class TemplateQuestionProperties {
    private Template question;
    private String folder;

    @Getter
    @Setter
    public static class Template {
        private String exportFileName;
        private String importFileName;
    }
}
