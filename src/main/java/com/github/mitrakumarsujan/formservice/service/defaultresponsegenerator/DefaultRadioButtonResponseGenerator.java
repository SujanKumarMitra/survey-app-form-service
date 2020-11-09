package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioButtonField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.RadioButtonResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultRadioButtonResponseGenerator implements DefaultResponseGenerator<RadioButtonField> {

	@Override
	public Response generate(RadioButtonField field) {
		RadioButtonResponse response = new RadioButtonResponse();
		response.setAnswer("");
		response.setQuestionId(field.getId());
		response.setOptionId("");
		return response;
	}
}
