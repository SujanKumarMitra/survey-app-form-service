package com.github.mitrakumarsujan.formservice.util;

import java.net.URI;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Extension of {@link RestTemplate} with deserialization feature of generic
 * POJOS
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
@Component
public class GenericRestTemplateFacade extends RestTemplate {

	public <T> ResponseEntity<T> exchange(RequestEntity<?> entity, Class<? extends T> responseEntityType,
			Class<?>... genericTypes) throws RestClientException {
		
		return exchange(entity, getTypeReference(responseEntityType, genericTypes));
	}

	public <T> ResponseEntity<T> exchange(URI uri, HttpMethod method, HttpEntity<?> requestEntity,
			Class<? extends T> responseEntityType, Class<?>... genericTypes) throws RestClientException {

		return exchange(uri, method, requestEntity, getTypeReference(responseEntityType, genericTypes));
	}

	public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<? extends T> responseEntityType, Class<?>... genericTypes) throws RestClientException {
		return exchange(url, method, requestEntity, getTypeReference(responseEntityType, genericTypes));
	}

	public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Map<String, ?> uriVariables, Class<? extends T> responseEntityType, Class<?>... genericTypes)
			throws RestClientException {
		return exchange(url, method, requestEntity, getTypeReference(responseEntityType, genericTypes), uriVariables);
	}

	public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Supplier<Object[]> uriVariables, Class<? extends T> responseEntityType, Class<?>... genericTypes)
			throws RestClientException {
		return exchange(url, method, requestEntity, getTypeReference(responseEntityType, genericTypes),
				uriVariables.get());
	}

	@Override
	public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
		return super.getForObject(url, responseType, uriVariables);
	}

	@Override
	public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		return super.getForObject(url, responseType, uriVariables);
	}

	@Override
	public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
		return super.getForObject(url, responseType);
	}

	@Override
	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		return super.getForEntity(url, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		return super.getForEntity(url, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
		return super.getForEntity(url, responseType);
	}

	@Override
	public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		return super.postForObject(url, request, responseType, uriVariables);
	}

	@Override
	public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		return super.postForObject(url, request, responseType, uriVariables);
	}

	@Override
	public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
		return super.postForObject(url, request, responseType);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType,
			Object... uriVariables) throws RestClientException {
		return super.postForEntity(url, request, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		return super.postForEntity(url, request, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType)
			throws RestClientException {
		return super.postForEntity(url, request, responseType);
	}

	@Override
	public <T> T patchForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		return super.patchForObject(url, request, responseType, uriVariables);
	}

	@Override
	public <T> T patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		return super.patchForObject(url, request, responseType, uriVariables);
	}

	@Override
	public <T> T patchForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
		return super.patchForObject(url, request, responseType);
	}

	private <T> ParameterizedTypeReference<T> getTypeReference(Class<? extends T> responseEntityType,
			Class<?>... genericTypes) {
		ResolvableType resolvableType = ResolvableType.forClassWithGenerics(responseEntityType, genericTypes);
		ParameterizedTypeReference<T> typeReference = ParameterizedTypeReference.forType(resolvableType.getType());
		return typeReference;
	}

}
