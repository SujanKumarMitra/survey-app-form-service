package com.github.mitrakumarsujan.formservice.service.validation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.service.validation.validator.FormFieldValidator;
import com.github.mitrakumarsujan.formservice.service.validation.validator.FormFieldValidatorFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Service
public class FormResponseValidationServiceImpl implements FormResponseValidationService {

	@Autowired
	private FormFieldValidatorFactory validatorFactory;

	@Override
	public boolean validate(Form form, FormResponse formResponse) {
		Map<String, FormField> fieldMap = getFieldMap(form); // map of FormField::getUID, FormField
		Map<String, Response> responseMap = getResponseMap(formResponse); // map of Response::getQuestionUID, Response
		return areAllRequiredPresent(fieldMap, responseMap) && validateFields(fieldMap, responseMap);
	}

	private Map<String, Response> getResponseMap(FormResponse formResponse) {
		Collection<Response> responses = formResponse.getResponses();
		return toIdentityMap(responses, Response::getQuestionId);
	}

	private Map<String, FormField> getFieldMap(Form form) {
		// @formatter:off
		List<FormField> fields = form.getTemplate().getFields();
		// @formatter:on
		return toIdentityMap(fields, FormField::getId);
	}

	private boolean validateFields(Map<String, FormField> fieldMap, Map<String, Response> responseMap) {
		return responseMap	.values()
							.parallelStream()
							.allMatch(response -> this.validate(response, fieldMap));
	}

	private boolean validate(Response response, Map<String, FormField> fieldMap) {
		FormField formField = fieldMap.get(response.getQuestionId());
		FormFieldValidator<FormField, Response> validator = validatorFactory.getValidator(formField.getClass());

		return validator.validate(formField, response);
	}

	boolean areAllRequiredPresent(Map<String, FormField> fieldMap, Map<String, Response> responseMap) {
		return fieldMap	.values()
						.parallelStream()
						.filter(FormField::isRequired)
						.allMatch(field -> responseMap.containsKey(field.getId()));
	}

	private <K, V> Map<K, V> toIdentityMap(Collection<? extends V> collection,
			Function<? super V, ? extends K> keyMapper) {

		return collection	.parallelStream()
							.collect(Collectors.toUnmodifiableMap(keyMapper, Function.identity()));
	}

}
