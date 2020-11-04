package com.github.mitrakumarsujan.formservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.dao.FormResponseDao;
import com.github.mitrakumarsujan.formservice.service.validation.FormResponseValidationService;
import com.github.mitrakumarsujan.formservice.service.validation.FormResponseValidationException;
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
		LOGGER.info("rearranging response fields for form '{}'", formId);

		rearrangeResponseFields(form, response);

		LOGGER.info("response fields rearranged for form '{}'", formId);
		formResponseDao.save(response);
	}

	private void rearrangeResponseFields(Form form, FormResponse response) {
		Map<String, Integer> indexedMap = getIndexedMap(form);

		List<Response> responses = response.getResponses();
		responses.sort(new ResponseComparator(indexedMap));
	}

	private Map<String, Integer> getIndexedMap(Form form) {
		MutableInteger index = new MutableInteger();
		return form	.getTemplate()
					.getFields()
					.stream()
					.map(FormField::getId)
					.collect(Collectors.toMap(Function.identity(), f -> index.getAndIncrement()));
	}

	private static class MutableInteger {
		Integer val;

		MutableInteger() {
			val = Integer.valueOf(0);
		}

		Integer getAndIncrement() {
			return val++;
		}
	}

	private static class ResponseComparator implements Comparator<Response> {

		Map<String, Integer> indexMap;

		ResponseComparator(Map<String, Integer> indexMap) {
			this.indexMap = indexMap;
		}

		@Override
		public int compare(Response o1, Response o2) {
			String o1QuestionId = o1.getQuestionId();
			String o2QuestionId = o2.getQuestionId();

			int index1 = indexMap.get(o1QuestionId);
			int index2 = indexMap.get(o2QuestionId);

			return Integer.compare(index1, index2);
		}

	}

}
