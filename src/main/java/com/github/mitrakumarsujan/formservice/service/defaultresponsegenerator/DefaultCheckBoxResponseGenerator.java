package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import static java.util.Collections.emptyList;

import com.github.mitrakumarsujan.formmodel.model.formresponse.ResponseConstants;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.CheckBoxField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.CheckBoxResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultCheckBoxResponseGenerator implements DefaultResponseGenerator<CheckBoxField> {

	@Override
	public Response generate(CheckBoxField field) {
		CheckBoxResponse response = new CheckBoxResponse();
		response.setAnswer(ResponseConstants.DEFAULT_VALUE);
		response.setQuestionId(field.getId());
		response.setOptionIds(emptyList());
		return response;
	}
}
