package com.github.mitrakumarsujan.formservice.service.validation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.util.CollectorUtils;

@Service
public class FormFieldIdentityMapper implements Function<Form, Map<String, FormField>> {
	@Autowired
	public CollectorUtils collectorUtils;

	@Override
//	@Cacheable
	public Map<String, FormField> apply(Form form) {
		// @formatter:off
		List<FormField> fields = form.getTemplate().getFields();
		// @formatter:on
		return collectorUtils.toIdentityMap(fields, FormField::getId);
	}
}