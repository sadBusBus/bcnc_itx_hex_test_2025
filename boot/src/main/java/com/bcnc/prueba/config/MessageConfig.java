package com.bcnc.prueba.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MessageConfig implements WebMvcConfigurer {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        //messageSource.setBasename("classpath:messages/messages");
        messageSource.setBasename("messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
}
