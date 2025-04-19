package com.fit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Component
public class HttpClientServiceComponent {

    // This class is used to configure the HTTP client for the employee service
    private final HttpClientProperties httpClientProperties;
    private final RestTemplateBuilder restTemplateBuilder;

    public HttpClientServiceComponent(HttpClientProperties httpClientProperties, RestTemplateBuilder restTemplateBuilder) {
        this.httpClientProperties = httpClientProperties;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public <T> T restServiceClient(String serviceName, Class<T> clientClass) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder()
                .exchangeAdapter(createRestTemplateAdapter(serviceName))
                .build();
        return httpServiceProxyFactory.createClient(clientClass);
    }

    private RestTemplateAdapter createRestTemplateAdapter(String serviceName) {
        HttpClientProperties.HttpClientService httpClientService = httpClientProperties.getService(serviceName);
        return RestTemplateAdapter.create(restTemplateBuilder.rootUri(httpClientService.getUrl()).build());
    }
}
