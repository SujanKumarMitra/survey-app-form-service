package com.github.mitrakumarsujan.formservice.service.formatter;

import com.github.mitrakumarsujan.formservice.service.FormResponseRequest;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
public interface FormResponseFormatterService {
	
	void formatResponses(FormResponseRequest request);
}
