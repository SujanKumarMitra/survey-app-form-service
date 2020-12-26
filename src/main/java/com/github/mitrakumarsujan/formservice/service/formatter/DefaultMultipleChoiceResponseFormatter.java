package com.github.mitrakumarsujan.formservice.service.formatter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.MultipleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class DefaultMultipleChoiceResponseFormatter
		implements FormResponseFormatter<ChoiceBasedFormField, MultipleChoiceBasedResponse> {
	@Override
	public void format(ChoiceBasedFormField field, MultipleChoiceBasedResponse response) {

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
