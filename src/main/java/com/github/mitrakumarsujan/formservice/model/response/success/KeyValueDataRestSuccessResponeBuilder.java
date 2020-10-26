package com.github.mitrakumarsujan.formservice.model.response.success;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;

import com.github.mitrakumarsujan.formservice.model.response.RestSuccessResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public class KeyValueDataRestSuccessResponeBuilder implements RestSuccessResponseBuilder<Map<String, Object>> {

	Map<String, Object> map;

	public KeyValueDataRestSuccessResponeBuilder() {
		this.map = new HashMap<>();
		HttpStatus ok = HttpStatus.OK;
		map.put("status", ok);
		map.put("message", ok.getReasonPhrase());
	}


	@Override
	public RestSuccessResponseBuilder<Map<String, Object>> withData(Map<String, Object> data) {
		map.putAll(data);
		return this;
	}

	public RestSuccessResponseBuilder<Map<String, Object>> withData(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public RestSuccessResponseBuilder<Map<String, Object>> withData(Entry<String, Object> entry) {
		return withData(entry.getKey(), entry.getValue());
	}

	@Override
	public RestSuccessResponseBuilder<Map<String, Object>> withStatus(HttpStatus status) {
		map.put("status", status);
		return this;
	}

	@Override
	public RestSuccessResponseBuilder<Map<String, Object>> withMessage(String message) {
		map.put("message", message);
		return this;
	}

	@Override
	public RestSuccessResponse<Map<String, Object>> build() {
		HttpStatus status = (HttpStatus) map.remove("status");
		int code = status.value();
		String message = (String) map.remove("message");
		return new RestSuccessResponse<Map<String, Object>>() {

			@Override
			public HttpStatus getStatus() {
				return status;
			}

			@Override
			public int getCode() {
				return code;
			}

			@Override
			public String getMessage() {
				return message;
			}

			@Override
			public Map<String, Object> getData() {
				return map;
			}

		};
	}

}
