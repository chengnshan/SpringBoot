package com.cxp.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cxp.dao.BaseDao;
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	/**
	 * 通过构造方法指定DAO的具体泛型类
	 */
	public BaseDaoImpl() {
		// 当前对象的直接超类的 Type
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		// 参数化类型
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
		// 返回表示此类型实际类型参数的 Type 对象的数组
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		this.clazz = (Class<T>) actualTypeArguments[0];
		System.out.println("DAO的真实实现类是：" + clazz.getName());
	}

	/**
	 * 获取当前工作的Session
	 */
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public T load(Serializable id) {
		return (T) this.getSession().load(clazz, id);
	}

	@Override
	public T get(Serializable id) {
		return (T) this.getSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		Query createQuery = this.getSession().createQuery("from " + clazz.getSimpleName());
		List<T> list = createQuery.list();
		return list;
	}

	/**
	 * 1、persist把一个瞬时态的实例持久化，但是并"不保证"标识符(identifier主键对应的属性)被立刻填入到持久化实例中，
	 * 标识符的填入可能被推迟到flush的时间。
	 * 2、save, 把一个瞬态的实例持久化标识符及时的产生,它要返回标识符，所以它会立即执行Sql insert
	 */
	@Override
	public void persist(T entity) {
		this.getSession().persist(entity);
	}

	@Override
	public Serializable save(T entity) {
		return this.getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Serializable id) {
		this.getSession().delete(id);
	}

	@Override
	public void flush() {
		this.getSession().flush();
	}

	public T getOneByProperty(String propertyName,Object value) {
		Criteria criteria = this.getSession().createCriteria(clazz);
		Criteria add = criteria.add(Restrictions.eq(propertyName, value));
		return (T) add.uniqueResult();
	}
	
	public List<T> getListByProperty(String propertyName,Object value) {
		Criteria criteria = this.getSession().createCriteria(clazz);
		Criteria add = criteria.add(Restrictions.eq(propertyName, value));
		return (List<T>) add.list();
	}
}
