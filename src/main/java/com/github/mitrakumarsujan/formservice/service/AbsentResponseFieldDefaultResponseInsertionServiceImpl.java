package com.github.mitrakumarsujan.formservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator.DefaultResponseGenerator;
import com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator.DefaultResponseGeneratorFactory;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Service
public class AbsentResponseFieldDefaultResponseInsertionServiceImpl
		implements AbsentResponseFieldDefaultResponseInsertionService {

	@Autowired
	private DefaultResponseGeneratorFactory generatorFactory;

	@Override
	public void insertAbsentFieldsWithDefaultResponses(FormResponseRequest request) {
		Map<String, FormField> fieldMap = request.getFieldMap();
		Map<String, Response> responseMap = request.getResponseMap();

		fieldMap.values()
				.parallelStream()
				.filter(field -> isAbsent(field, responseMap))
				.map(this::getDefaultResponse)
				.forEachOrdered(response -> addResponse(response, request));
	}

	private void addResponse(Response response, FormResponseRequest request) {
		FormResponse formResponse = request.getResponse();
		Map<String, Response> responseMap = request.getResponseMap();
		List<Response> responses = formResponse.getResponses();
		responses.add(response);
		responseMap.put(response.getQuestionId(), response);
	}

	private Response getDefaultResponse(FormField field) {
		DefaultResponseGenerator<FormField> generator = generatorFactory.getGenerator(field.getClass());
		return generator.generate(field);
	}

	private boolean isAbsent(FormField field, Map<String, Response> responseMap) {
		String id = field.getId();
		return !responseMap.containsKey(id);
	}

}
