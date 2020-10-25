package com.github.mitrakumarsujan.formservice.model.form;

import java.util.List;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
public interface ChoiceTypeFormField extends FormField {
	List<OptionField> getOptions();
}
