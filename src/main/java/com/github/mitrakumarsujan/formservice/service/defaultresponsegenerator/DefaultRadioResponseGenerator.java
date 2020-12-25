package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import com.github.mitrakumarsujan.formmodel.model.formresponse.ResponseConstants;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.RadioResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultRadioResponseGenerator implements DefaultResponseGenerator<RadioField> {

	@Override
	public Response generate(RadioField field) {
		RadioResponse response = new RadioResponse();
		response.setAnswer(ResponseConstants.DEFAULT_VALUE);
		response.setQuestionId(field.getId());
		response.setOptionId("");
		return response;
	}
}
