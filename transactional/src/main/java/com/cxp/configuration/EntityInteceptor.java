package com.cxp.configuration;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class EntityInteceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -7540390424845728537L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		return super.onSave(entity, id, state, propertyNames, types);
	}
}
