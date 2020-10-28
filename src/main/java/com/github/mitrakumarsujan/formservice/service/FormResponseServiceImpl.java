package com.github.mitrakumarsujan.formservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formservice.service.form.FormService;
import com.github.mitrakumarsujan.formservice.service.validation.FormResponseValidationService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Service
public class FormResponseServiceImpl implements FormResponseService {

	@Autowired
	private FormService formService;

	@Autowired
	private FormResponseValidationService responseValidationService;

	@Override
	public void submit(FormResponse response) {
		Form form = formService.getForm(response.getFormUID());
		boolean validate = responseValidationService.validate(form, response);
		System.out.println(validate);
	}

}
