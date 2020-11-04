package com.github.mitrakumarsujan.formservice.service.formatter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.ChoiceBasedResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.service.FormResponseRequest;
import com.github.mitrakumarsujan.formservice.service.validation.validator.ChoiceBasedFormFieldValidator;
import com.github.mitrakumarsujan.formservice.service.validation.validator.FormFieldValidatorFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Service
public class FormResponseFormatterServiceImpl implements FormResponseFormatterService {

	@Autowired
	private FormFieldValidatorFactory validatorFactory;

	@Override
	public void formatResponses(FormResponseRequest request) {
		Map<String, FormField> fieldMap = request.getFieldMap();
		Map<String, Response> responseMap = request.getResponseMap();
		
		this.formatResponse(fieldMap, responseMap);
	}

	private void formatResponse(Map<String, FormField> fieldMap, Map<String, Response> responseMap) {
		responseMap	.values()
					.parallelStream()
					.filter(r -> r instanceof ChoiceBasedResponse)
					.map(r -> (ChoiceBasedResponse) r)
					.forEach(response -> formatResponseFields(response, fieldMap));
	}

	private void formatResponseFields(ChoiceBasedResponse response, Map<String, FormField> fieldMap) {
		ChoiceBasedFormField formField = (ChoiceBasedFormField) fieldMap.get(response.getQuestionId());
		ChoiceBasedFormFieldValidator<ChoiceBasedFormField, ChoiceBasedResponse> validator = validatorFactory.getChoiceBasedValidator(
				formField.getClass(), ChoiceBasedResponse.class);
		validator.formatResponse(formField, response);
	}

}
