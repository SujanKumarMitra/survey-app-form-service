package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormIdGeneratorService {

	@Autowired
	private HashFunction hashFunction;

	public String generate(Form form) {
		String[] uids = form.getTemplate()
							.getFields()
							.parallelStream()
							.map(FormField::getId)
							.toArray(String[]::new);
		return hashFunction.toHash((Object[]) uids);
	}
}
