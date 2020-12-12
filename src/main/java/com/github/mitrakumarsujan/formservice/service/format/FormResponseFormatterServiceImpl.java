package com.github.mitrakumarsujan.formservice.service.format;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.service.FormResponseRequest;
import com.github.mitrakumarsujan.formservice.service.format.formatter.FormResponseFormatter;
import com.github.mitrakumarsujan.formservice.service.format.formatter.ResponseFormatterFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Service
public class FormResponseFormatterServiceImpl implements FormResponseFormatterService {

	@Autowired
	private ResponseFormatterFactory formatterFactory;

	@Override
	public void formatResponses(FormResponseRequest request) {
		Map<String, Response> responseMap = request.getResponseMap();
		Map<String, FormField> fieldMap = request.getFieldMap();

		responseMap	.values()
					.stream()
					.forEach(response -> formatResponse(response, fieldMap));
	}

	private void formatResponse(Response response, Map<String, FormField> fieldMap) {
		FormField formField = fieldMap.get(response.getQuestionId());
		FormResponseFormatter<FormField, Response> formatter = formatterFactory.getFormatter(response.getClass());
		formatter.format(formField, response);
	}

}
