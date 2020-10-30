package com.github.mitrakumarsujan.formservice.service.validation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.ChoiceBasedResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.service.validation.validator.ChoiceBasedFormFieldValidator;
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

	@Autowired
	private FormFieldIdentityMapper formFieldMapper;

	@Autowired
	private ResponseIdentityMapper responseMapper;

	@Override
	public boolean validate(Form form, FormResponse formResponse) {
		Map<String, FormField> fieldMap = formFieldMapper.apply(form);
		Map<String, Response> responseMap = responseMapper.apply(formResponse);
		boolean res = areAllRequiredPresent(fieldMap, responseMap) && validateFields(fieldMap, responseMap);
		if (res) {
			formatResponse(fieldMap, responseMap);
		}
		return res;
	}

	private void formatResponse(Map<String, FormField> fieldMap, Map<String, Response> responseMap) {
		responseMap	.values()
					.parallelStream()
					.filter(this::isChoiceBasedResponse)
					.map(f -> (ChoiceBasedResponse) f)
					.forEach(response -> formatResponseFields(response, fieldMap));
	}

	private void formatResponseFields(ChoiceBasedResponse response, Map<String, FormField> fieldMap) {
		ChoiceBasedFormField formField = (ChoiceBasedFormField) fieldMap.get(response.getQuestionId());
		ChoiceBasedFormFieldValidator<ChoiceBasedFormField,ChoiceBasedResponse> validator = validatorFactory.getChoiceBasedValidator(formField.getClass(), ChoiceBasedResponse.class);
		validator.formatResponse(formField, response);
	}

	boolean areAllRequiredPresent(Map<String, FormField> fieldMap, Map<String, Response> responseMap) {
		return fieldMap	.values()
						.parallelStream()
						.filter(FormField::isRequired)
						.allMatch(field -> responseMap.containsKey(field.getId()));
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

	private boolean isChoiceBasedResponse(Response response) {

		return response instanceof ChoiceBasedResponse;
	}

}
