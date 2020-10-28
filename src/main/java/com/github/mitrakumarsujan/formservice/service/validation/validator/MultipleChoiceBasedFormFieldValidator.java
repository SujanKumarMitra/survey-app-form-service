package com.github.mitrakumarsujan.formservice.service.validation.validator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.MultipleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface MultipleChoiceBasedFormFieldValidator<F extends FormField> extends FormFieldValidator<F, MultipleChoiceBasedResponse> {}