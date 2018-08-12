package com.cxp.config;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class HibernateEntityInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -6113332272486329219L;

	/*
     * entity - POJO对象
     * id - POJO对象的主键
     * state - POJO对象的每一个属性值所组成的集合(除了ID)
     * propertyNames - POJO对象的每一个属性名字组成的集合(除了ID)
     * types - POJO对象的每一个属性类型所对应的Hibernate类型组成的集合(除了ID)
     */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
			try {
				for (int i = 0; i < propertyNames.length; i++) {
					if("createDate".equals(propertyNames[i]) 
							|| "updateDatetime".equals(propertyNames[i])) {
							state[i] = new Date();
					}
				}
			} catch (Exception e) {
				return false;
			}
		
		return true;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		try {
			 for (int i = 0; i < propertyNames.length; i++) {   
	             if ("updateDatetime".equals(propertyNames[i])) {   
	            	 currentState[i] = new Date();   
	             }   
	         } 
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}


