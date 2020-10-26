package com.github.mitrakumarsujan.formservice.model.response.success;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class RestSuccessResponseBuilderFactory {

	public <T> RestSuccessResponseBuilder<T> getSingleDataBuilder(Class<? extends T> classType) {
		return new SingleDataRestSuccessResponeBuilder<>();
	}

	public RestSuccessResponseBuilder<Map<String, Object>> getKeyValueDataBuilder() {
		return new KeyValueDataRestSuccessResponeBuilder();
	}
}
