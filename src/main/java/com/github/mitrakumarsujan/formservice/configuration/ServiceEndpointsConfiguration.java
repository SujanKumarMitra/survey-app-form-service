package com.github.mitrakumarsujan.formservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author skmitra
 * @since 09-12-2020
 */
@ConfigurationProperties(prefix = "app")
public class ServiceEndpointsConfiguration {

    private Map<String,String> serviceEndpoints;

    public Map<String, String> getServiceEndpoints() {
        return serviceEndpoints;
    }

    public void setServiceEndpoints(Map<String, String> serviceEndpoints) {
        this.serviceEndpoints = serviceEndpoints;
    }

    public String getEndpoint(String serviceId) {
        return this.serviceEndpoints.get(serviceId);
    }

    public String getDataStorageServiceEndpoint() {
        return getEndpoint("data-storage-service");
    }

}
