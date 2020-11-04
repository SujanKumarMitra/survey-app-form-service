package com.github.mitrakumarsujan.formservice.service.validation;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.ErrorInfo;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.ErrorInfoImpl;
import com.github.mitrakumarsujan.formservice.service.FormResponseRequest;
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

	private static final ValidationResult VALID_RESULT = new ValidationResultImpl(false, null, emptyList());

	private static final Logger LOGGER = LoggerFactory.getLogger(FormResponseValidationServiceImpl.class);

	@Override
	public ValidationResult validate(FormResponseRequest request) {
		Map<String, FormField> fieldMap = request.getFieldMap();
		Map<String, Response> responseMap = request.getResponseMap();
		
		String formId = request.getResponse().getFormId();
		LOGGER.info("starting validation of responses for formId '{}'", formId);
		
		LOGGER.info("checking for unknown fields");
		
		Collection<ErrorInfo> unknownFieldErrors = getUnknownFieldErrors(fieldMap, responseMap);

		if (!unknownFieldErrors.isEmpty()) {
			LOGGER.info("found unknown fields");
			return new ValidationResultImpl(true, "Some unknown fields are present", unknownFieldErrors);
		}
		LOGGER.info("no unknown fields present");
		
		LOGGER.info("checking form missing fields");
		Collection<ErrorInfo> missingFieldErrors = getMissingFieldErrors(fieldMap, responseMap);
		if (!missingFieldErrors.isEmpty()) {
			LOGGER.info("found missing fields");
			return new ValidationResultImpl(true, "Some required fields are missing", missingFieldErrors);
		}
		LOGGER.info("no missing fields present");
		
		LOGGER.info("applying validation rules");

		Collection<ErrorInfo> validationErrors = getValidationErrors(fieldMap, responseMap);
		if (!validationErrors.isEmpty()) {
			LOGGER.info("errors found during validation ");
			return new ValidationResultImpl(true, "Some fields are invalid", validationErrors);
		}
		LOGGER.info("response validation successful for formId '{}'",formId);
		return VALID_RESULT;
	}

	private Collection<ErrorInfo> getUnknownFieldErrors(Map<String, FormField> fieldMap,
			Map<String, Response> responseMap) {
		return responseMap	.values()
							.stream()
							.map(Response::getQuestionId)
							.filter(qId -> isUnknown(qId, fieldMap))
							.map(qId -> new ErrorInfoImpl("Unknown questionId '" + qId + "'"))
							.collect(toCollection(LinkedList::new));
	}

	private boolean isUnknown(String qId, Map<String, FormField> fieldMap) {
		return !fieldMap.containsKey(qId);
	}

	private Collection<ErrorInfo> getMissingFieldErrors(Map<String, FormField> fieldMap,
			Map<String, Response> responseMap) {
		return fieldMap	.values()
						.parallelStream()
						.filter(FormField::isRequired)
						.filter(field -> isMissing(field, responseMap))
						.map(FormField::getId)
						.map(id -> new ErrorInfoImpl("field with id '" + id + "' is missing"))
						.collect(toCollection(LinkedList::new));
	}

	private Collection<ErrorInfo> getValidationErrors(Map<String, FormField> fieldMap,
			Map<String, Response> responseMap) {
		return responseMap	.values()
							.parallelStream()
							.filter(response -> isInvalid(response, fieldMap))
							.map(response -> this.getErrorInfo(response, fieldMap))
							.collect(toCollection(LinkedList::new));
	}

	private boolean isMissing(FormField field, Map<String, Response> responseMap) {
		return !responseMap.containsKey(field.getId());
	}

	private boolean isInvalid(Response response, Map<String, FormField> fieldMap) {
		FormField formField = fieldMap.get(response.getQuestionId());
		FormFieldValidator<FormField, Response> validator = validatorFactory.getValidator(formField.getClass());

		return !validator.validate(formField, response);
	}

	private ErrorInfo getErrorInfo(Response response, Map<String, FormField> fieldMap) {
		String questionId = response.getQuestionId();
		FormField formField = fieldMap.get(questionId);
		FormFieldValidator<FormField, Response> validator = validatorFactory.getValidator(formField.getClass());
		String errorMessage = validator.getErrorMessage();

		return new ErrorInfoImpl(
				"Response invalid for formField with id '" + questionId + "'. Reason:: " + errorMessage);
	}

}
