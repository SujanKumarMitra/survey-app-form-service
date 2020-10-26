package com.github.mitrakumarsujan.formservice.service.uidgen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.ChoiceTypeFormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class ChoiceTypeFormFieldUIDGenerator implements UIDGenerator<ChoiceTypeFormField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(ChoiceTypeFormField formField) {

		long time;
		synchronized (this) {
			time = System.currentTimeMillis();
		}
		String question = formField.getQuestion();
		boolean required = formField.isRequired();
		List<OptionField> options = formField.getOptions();

		return hashFunction.toHash(time, question, required, options);
	}

}
