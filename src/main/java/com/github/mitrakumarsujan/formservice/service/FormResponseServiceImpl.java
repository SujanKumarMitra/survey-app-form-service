package com.github.mitrakumarsujan.formservice.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.dao.FormResponseDao;
import com.github.mitrakumarsujan.formservice.service.format.FormResponseFormatterService;
import com.github.mitrakumarsujan.formservice.service.validation.FormResponseValidationException;
import com.github.mitrakumarsujan.formservice.service.validation.FormResponseValidationService;
import com.github.mitrakumarsujan.formservice.service.validation.ValidationResult;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Service
public class FormResponseServiceImpl implements FormResponseService {

	@Autowired
	private FormService formService;

	@Autowired
	private FormResponseDao formResponseDao;

	@Autowired
	private FormFieldIdentityMapper fieldMapper;

	@Autowired
	private ResponseIdentityMapper responseMapper;

	@Autowired
	private FormResponseValidationService responseValidationService;

	@Autowired
	private FormResponseFormatterService responseFormatterService;

	@Autowired
	private ResponseFieldRearrangerService fieldRearrangerService;
	
	@Autowired
	private AbsentResponseFieldDefaultResponseInsertionService defaultResponseInsertionService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormResponseServiceImpl.class);

	@Override
	public void submit(FormResponse response) {
		String formId = response.getFormId();
		Form form = formService.getForm(formId);

		Map<String, FormField> fieldMap = fieldMapper.apply(form);
		Map<String, Response> responseMap = responseMapper.apply(response);

		FormResponseRequest request = new FormResponseRequestImpl(response, form, fieldMap, responseMap);

		LOGGER.info("validating responses for form '{}'", formId);
		ValidationResult result = responseValidationService.validate(request);
		if (result.hasErrors()) {
			throw new FormResponseValidationException(result);
		}
		LOGGER.info("responses validated for form '{}'", formId);
		
		LOGGER.info("inserting absent responses for form '{}'", formId);
		
		defaultResponseInsertionService.insertAbsentFieldsWithDefaultResponses(request);
		
		LOGGER.info("absent responses inserted for form '{}'", formId);
		
		LOGGER.info("formatting response fields for form '{}'", formId);

		responseFormatterService.formatResponses(request);

		LOGGER.info("formatting complete for form '{}'", formId);

		LOGGER.info("rearranging response fields for form '{}'", formId);

		fieldRearrangerService.rearrange(request);

		LOGGER.info("response fields rearranged for form '{}'", formId);
		formResponseDao.save(response);
	}

}
