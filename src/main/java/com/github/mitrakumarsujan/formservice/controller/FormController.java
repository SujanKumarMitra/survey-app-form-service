package com.github.mitrakumarsujan.formservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{formUID}")
	public ResponseEntity<RestSuccessResponse<Form>> getForm
	(@PathVariable("formUID") String formUID) {

		Form form = formService.getForm(formUID);
		HttpStatus status = HttpStatus.FOUND;
		return getResponseEntity(form, status, status.getReasonPhrase());
	}

	@PostMapping
	public ResponseEntity<RestSuccessResponse<Form>> createForm
	(@RequestBody @Valid FormTemplate template,HttpServletRequest request) {
		Form createdForm = formService.createForm(template, request);
		return getResponseEntity(createdForm, HttpStatus.CREATED, getSuccessMessage());
	}

	private String getSuccessMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Form successfully created.");
		sb.append(" Use the uids when submitting responses.");
		sb.append(" Use the key for collecting responses.");
		return sb.toString();
	}

	private ResponseEntity<RestSuccessResponse<Form>> getResponseEntity(Form form, HttpStatus status, String message) {
		return getBuilder()	.withStatus(status)
							.withMessage(message)
							.withData(form)
							.build()
							.toResponseEntity();
	}

	private RestSuccessResponseBuilder<Form> getBuilder() {
		return responseBuilderFactory.getSingleDataBuilder(Form.class);
	}

}
