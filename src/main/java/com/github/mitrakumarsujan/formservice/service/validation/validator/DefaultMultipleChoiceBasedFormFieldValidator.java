package com.github.mitrakumarsujan.formservice.service.validation.validator;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.MultipleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class DefaultMultipleChoiceBasedFormFieldValidator
		implements MultipleChoiceBasedFormFieldValidator<ChoiceBasedFormField> {

	@Override
	public boolean validate(ChoiceBasedFormField field, MultipleChoiceBasedResponse response) {

		Set<String> uids = field.getOptions()
								.parallelStream()
								.map(OptionField::getId)
								.collect(Collectors.toSet());

		return response	.getOptionIds()
						.parallelStream()
						.allMatch(uids::contains);

	}

	@Override
	public void formatResponse(ChoiceBasedFormField field, MultipleChoiceBasedResponse response) {

		Set<String> optionIds = response.getOptionIds()
										.stream()
										.collect(Collectors.toSet());

		String answer = field	.getOptions()
								.stream()
								.filter(optionField -> isOptionSelected(optionIds, optionField))
								.map(OptionField::getText)
								.collect(Collectors.joining(",", "\"", "\""));

		response.setAnswer(answer);
	}

	private boolean isOptionSelected(Set<String> optionIds, OptionField optionField) {
		return optionIds.contains(optionField.getId());
	}
}
