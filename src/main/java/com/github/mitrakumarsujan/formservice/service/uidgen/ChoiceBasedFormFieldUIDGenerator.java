package com.github.mitrakumarsujan.formservice.service.uidgen;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class ChoiceBasedFormFieldUIDGenerator implements UIDGenerator<ChoiceBasedFormField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(ChoiceBasedFormField formField) {

		String uuid;
		synchronized (this) {
			uuid = UUID.randomUUID().toString();
		}
		String question = formField.getQuestion();
		boolean required = formField.isRequired();
		List<? extends OptionField> options = formField.getOptions();

		return hashFunction.toHash(uuid, question, required, options);
	}

}
