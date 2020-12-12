package com.github.mitrakumarsujan.formservice.dao;

import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-31
 */
public interface FormResponseDao {
	
	FormResponse save(FormResponse response);
	
}
