package com.github.mitrakumarsujan.formservice.service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.exception.DuplicateKeyException;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formmodel.util.CollectorUtils;

@Service
public class ResponseIdentityMapper implements Function<FormResponse, Map<String,Response>> {
	
	@Autowired
	public CollectorUtils collectorUtils;

	@Override
//	@Cacheable
	public Map<String, Response> apply(FormResponse formResponse) throws DuplicateKeyException {
		Collection<Response> responses = formResponse.getResponses();
		return collectorUtils.toIdentityMap(responses, Response::getQuestionId);
	}

}