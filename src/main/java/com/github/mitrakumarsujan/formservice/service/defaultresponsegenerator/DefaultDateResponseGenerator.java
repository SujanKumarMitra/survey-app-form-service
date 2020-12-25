package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import com.github.mitrakumarsujan.formmodel.model.formresponse.ResponseConstants;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.DateField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.DateResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultDateResponseGenerator implements DefaultResponseGenerator<DateField> {

	@Override
	public Response generate(DateField field) {
		DateResponse response = new DateResponse();
		response.setAnswer(ResponseConstants.DEFAULT_VALUE);
		response.setQuestionId(field.getId());
		return response;
	}
}
