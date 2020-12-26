package com.github.mitrakumarsujan.formservice.service.uidgenerator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author skmitra
 * @since 2020-11-12
 */
public interface FormFieldUIDGeneratorFactory {

	<X extends FormField> UIDGenerator<X> getGenerator(Class<? extends X> classType);

	<X extends FormField> void registerGenerator(Class<? extends X> fieldType, UIDGenerator<? extends X> uidGenerator);

}