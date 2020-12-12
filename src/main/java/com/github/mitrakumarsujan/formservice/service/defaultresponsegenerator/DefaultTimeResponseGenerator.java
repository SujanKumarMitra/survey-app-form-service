package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TimeField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TimeResponse;

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Component
public class DefaultTimeResponseGenerator implements DefaultResponseGenerator<TimeField> {

	@Override
	public Response generate(TimeField field) {
		TimeResponse response = new TimeResponse();
		response.setAnswer("");
		response.setQuestionId(field.getId());
		return response;
	}
}
