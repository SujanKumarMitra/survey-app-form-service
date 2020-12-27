package com.github.mitrakumarsujan.formservice.service.idgenerator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author skmitra
 * @since 2020-11-12
 */
public interface FormFieldIdGeneratorFactory {

	<X extends FormField> IdGenerator<X> getGenerator(Class<? extends X> classType);

	<X extends FormField> void registerGenerator(Class<? extends X> fieldType, IdGenerator<? extends X> idGenerator);

}