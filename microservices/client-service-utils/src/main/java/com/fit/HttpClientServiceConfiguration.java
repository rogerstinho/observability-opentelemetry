package com.fit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientServiceConfiguration {

    @Bean
    @ConditionalOnMissingBean
    RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

}
