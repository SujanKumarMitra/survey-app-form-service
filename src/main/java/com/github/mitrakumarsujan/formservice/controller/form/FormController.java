package com.github.mitrakumarsujan.formservice.controller.form;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mitrakumarsujan.formservice.model.form.Form;
import com.github.mitrakumarsujan.formservice.model.form.FormTemplate;
import com.github.mitrakumarsujan.formservice.service.form.FormService;

@RestController
@RequestMapping("/v1/form")
public class FormController {

	@Autowired
	private FormService formService;

	@PostMapping
	public Form serialized(@RequestBody @Valid FormTemplate template, HttpServletRequest request) {
		return formService.createForm(template, request);
	}

}
