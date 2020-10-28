package com.github.mitrakumarsujan.formservice.service.validation.validator;

import com.github.mitrakumarsujan.formmodel.model.form.PatternBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface PatternBasedFormFieldValidator<F extends PatternBasedFormField> extends FormFieldValidator<F, Response> {}
