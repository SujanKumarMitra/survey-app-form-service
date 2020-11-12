package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author skmitra
 * @since 2020-11-12
 */
public interface DefaultResponseGeneratorFactory {

	<F extends FormField> DefaultResponseGenerator<F> getGenerator(Class<? extends F> fieldType);

	<F extends FormField> void registerGenerator(Class<? extends F> fieldType,
			DefaultResponseGenerator<? extends F> generator);

}