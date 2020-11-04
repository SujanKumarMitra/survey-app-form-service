package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.SingleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class DefaultSingleChoiceResponseFormatter
		implements FormResponseFormatter<ChoiceBasedFormField, SingleChoiceBasedResponse> {
	@Override
	public void format(ChoiceBasedFormField field, SingleChoiceBasedResponse response) {

		String optionId = response.getOptionId();

		field	.getOptions()
				.parallelStream()
				.filter(optionField -> isSelected(optionField, optionId))
				.map(OptionField::getText)
				.findFirst()
				.ifPresent(response::setAnswer);

	}

	private boolean isSelected(OptionField field, String optionId) {
		return field.getId()
					.contentEquals(optionId);
	}
}
