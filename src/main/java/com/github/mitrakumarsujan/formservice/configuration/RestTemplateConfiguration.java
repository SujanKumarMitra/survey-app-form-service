package com.github.mitrakumarsujan.formservice.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
@Configuration
public class RestTemplateConfiguration {

	@Bean
	@Qualifier("default-rest-template")
	public RestTemplate getDefaultRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Qualifier("load-balanced-rest-template")
	@LoadBalanced
	public RestTemplate getLoadBalancedRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
