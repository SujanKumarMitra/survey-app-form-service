package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import com.github.mitrakumarsujan.formmodel.model.formresponse.ResponseConstants;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TextFieldResponse;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultTextFieldResponseGenerator implements DefaultResponseGenerator<TextField> {

	@Override
	public Response generate(TextField field) {
		TextFieldResponse response = new TextFieldResponse();
		response.setAnswer(ResponseConstants.DEFAULT_VALUE);
		response.setQuestionId(field.getId());
		return response;
	}
}
