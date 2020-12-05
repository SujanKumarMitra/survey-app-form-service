package com.github.mitrakumarsujan.formservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

@ConfigurationProperties(prefix = "app")
public class ServiceEndpointsConfiguration {

    private final Map<String, String> serviceEndpoints;

    @ConstructorBinding
    public ServiceEndpointsConfiguration(Map<String, String> serviceEndpoints) {
        this.serviceEndpoints = Collections.unmodifiableMap(serviceEndpoints);
    }

    public String getEndpoint(String serviceId) {
        return serviceEndpoints.get(serviceId);
    }

    public String getDataStorageServiceEndpoint() {
        return getEndpoint("data-storage-service");
    }

}
