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
import com.github.mitrakumarsujan.formmodel.model.restresponse.RestSuccessResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.success.RestSuccessResponseBuilderFactoryImpl;
import com.github.mitrakumarsujan.formservice.service.FormService;

@RestController
@RequestMapping("/v1/form")
public class FormController {

	@Autowired
	private FormService formService;

	@Autowired
	private RestSuccessResponseBuilderFactoryImpl responseBuilderFactory;

	@GetMapping("/{formId}")
	public ResponseEntity<RestSuccessResponse<FormTemplate>> getFormTemplate(@PathVariable("formId") String formId) {

		Form form = formService.getForm(formId);
		HttpStatus status = HttpStatus.FOUND;

		return responseBuilderFactory	.getSingleDataBuilder(FormTemplate.class)
										.withStatus(status)
										.withData(form.getTemplate())
										.build()
										.toResponseEntity();
	}

	@PostMapping
	public ResponseEntity<RestSuccessResponse<Form>> createForm(
			@RequestBody @Valid FormTemplate template,
			HttpServletRequest request) {
		Form createdForm = formService.createForm(template, request);
		HttpStatus status = HttpStatus.CREATED;
		return responseBuilderFactory	.getSingleDataBuilder(Form.class)
										.withData(createdForm)
										.withStatus(status)
										.withMessage(getSuccessMessage())
										.build()
										.toResponseEntity();

	}

	private String getSuccessMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Form successfully created.");
		sb.append(" Use the uids when submitting responses.");
		sb.append(" Use the key for collecting responses.");
		return sb.toString();
	}

}
