package com.github.mitrakumarsujan.formservice.model.form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public class ImmutableForm implements Form {

	private static final long serialVersionUID = 4560962341281647833L;

	private final Form delegetee;

	public ImmutableForm(Form delegatee) {
		this.delegetee = delegatee;
	}

	@Override
	public String getUID() {
		return delegetee.getUID();
	}

	@Override
	public String getKey() {
		return delegetee.getKey();
	}

	@Override
	public FormTemplate getTemplate() {
		return delegetee.getTemplate();
	}

}
