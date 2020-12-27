package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class HashFunctionImpl implements HashFunction {
	@Override
	public String toHash(Object o) {
		return Integer.toHexString(ObjectUtils.nullSafeHashCode(o));
	}

	@Override
	public String toHash(Object... o) {
		return Integer.toHexString(ObjectUtils.nullSafeHashCode(o));
	}

}
