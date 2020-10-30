package com.github.mitrakumarsujan.formservice.service.validation.validator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.SingleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface SingleChoiceBasedFormFieldValidator<F extends FormField>
		extends ChoiceBasedFormFieldValidator<F, SingleChoiceBasedResponse> {}