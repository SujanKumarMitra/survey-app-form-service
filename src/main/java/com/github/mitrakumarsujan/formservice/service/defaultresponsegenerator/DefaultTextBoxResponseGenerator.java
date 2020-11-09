package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextBoxField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TextBoxResponse;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultTextBoxResponseGenerator implements DefaultResponseGenerator<TextBoxField> {

	@Override
	public Response generate(TextBoxField field) {
		TextBoxResponse response = new TextBoxResponse();
		response.setAnswer("");
		response.setQuestionId(field.getId());
		return response;
	}
}
