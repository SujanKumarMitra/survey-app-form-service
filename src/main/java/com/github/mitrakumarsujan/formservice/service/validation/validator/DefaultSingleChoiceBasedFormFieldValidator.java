package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.SingleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class DefaultSingleChoiceBasedFormFieldValidator
		implements SingleChoiceBasedFormFieldValidator<ChoiceBasedFormField> {

	@Override
	public boolean validate(ChoiceBasedFormField field, SingleChoiceBasedResponse response) {
		String uid = response.getOptionId();
		return field.getOptions()
					.parallelStream()
					.map(OptionField::getId)
					.anyMatch(optionUid -> optionUid.contentEquals(uid));
	}

	@Override
	public void formatResponse(ChoiceBasedFormField field, SingleChoiceBasedResponse response) {

		String optionId = response.getOptionId();

		field	.getOptions()
				.parallelStream()
				.filter(optionField -> isSelected(optionField, optionId))
				.map(OptionField::getText)
				.findFirst()
				.ifPresent(text -> response.setAnswer(text));

	}

	private boolean isSelected(OptionField field, String optionId) {
		return field.getId()
					.contentEquals(optionId);
	}

}
