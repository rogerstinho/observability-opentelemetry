package com.fit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "clients")
public class HttpClientProperties {

    private final Map<String, HttpClientService> services = new HashMap<>();

    public Map<String, HttpClientService> getServices() {
        return services;
    }

    public HttpClientService getService(String name) {
        return services.getOrDefault(name, null);
    }

    public static class HttpClientService {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
