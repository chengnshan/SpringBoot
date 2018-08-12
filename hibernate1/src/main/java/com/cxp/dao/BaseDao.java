package com.cxp.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	T load(Serializable id);

	T get(Serializable id);

	List<T> findAll();

	void persist(T entity);

	Serializable save(T entity);

	void saveOrUpdate(T entity);

	void delete(Serializable id);

	void flush();
	
	public T getOneByProperty(String propertyName,Object value) ;
	
	public List<T> getListByProperty(String propertyName,Object value);

}
