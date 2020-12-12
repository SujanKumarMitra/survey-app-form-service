package com.github.mitrakumarsujan.formservice.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
@Configuration
@EnableConfigurationProperties({ServiceEndpointsConfiguration.class})
public class ApplicationConfiguration {
}
