package com.github.mitrakumarsujan.formservice.controller.form;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormTemplate;
import com.github.mitrakumarsujan.formmodel.model.response.RestSuccessResponse;
import com.github.mitrakumarsujan.formmodel.model.response.success.RestSuccessResponseBuilder;
import com.github.mitrakumarsujan.formmodel.model.response.success.RestSuccessResponseBuilderFactory;
import com.github.mitrakumarsujan.formservice.service.form.FormService;


@RestController
@RequestMapping("/v1/form")
public class FormController {

	@Autowired
	private FormService formService;

	@Autowired
	private RestSuccessResponseBuilderFactory responseBuilderFactory;

	@PostMapping
	public ResponseEntity<RestSuccessResponse<Form>> createForm(@RequestBody @Valid FormTemplate template,
			HttpServletRequest request) {
		Form createdForm = formService.createForm(template, request);
		return getResponseEntity(createdForm);
	}

	private String getSuccessMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Form successfully created.");
		sb.append(" Use the uids when submitting responses.");
		sb.append(" Use the key for collecting responses.");
		return sb.toString();
	}

	private RestSuccessResponseBuilder<Form> getBuilder() {
		return responseBuilderFactory.getSingleDataBuilder(Form.class);
	}

	private ResponseEntity<RestSuccessResponse<Form>> getResponseEntity(Form createdForm) {
		return getBuilder()	.withStatus(HttpStatus.CREATED)
							.withMessage(getSuccessMessage())
							.withData(createdForm)
							.build()
							.toResponseEntity();
	}

}
