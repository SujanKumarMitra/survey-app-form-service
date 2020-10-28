package com.github.mitrakumarsujan.formservice.service.uidgen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormUIDGeneratorService {

	@Autowired
	private HashFunction hashFunction;

	public String generate(Form form, HttpServletRequest request) {
		Object[] uids = form.getTemplate()
							.getFields()
							.parallelStream()
							.map(FormField::getId)
							.map(s -> (Object) s)
							.toArray(Object[]::new);
		return hashFunction.toHash(uids);
	}
}
