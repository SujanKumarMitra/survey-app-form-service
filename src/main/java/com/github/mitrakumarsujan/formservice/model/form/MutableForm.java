package com.github.mitrakumarsujan.formservice.model.form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public class MutableForm implements Form {

	private static final long serialVersionUID = 6928780678671932333L;

	private String uid;
	private String key;
	private FormTemplate template;

	@Override
	public String getUID() {
		return uid;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public FormTemplate getTemplate() {
		return template;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setTemplate(FormTemplate template) {
		this.template = template;
	}

}
