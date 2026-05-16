package com.fit.clients;

import com.fit.HttpClientServiceComponent;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientsConfiguration {
    private final HttpClientServiceComponent httpClientService;

    public ClientsConfiguration(HttpClientServiceComponent httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Bean
    EmployeeClient employeeClient(RestTemplateBuilder restTemplateBuilder) {
        return httpClientService.restServiceClient("employee-service", EmployeeClient.class);
    }

    @Bean
    BankAccountClient bankAccountClient(RestTemplateBuilder restTemplateBuilder) {
        return httpClientService.restServiceClient("bank-account-service", BankAccountClient.class);
    }

}
