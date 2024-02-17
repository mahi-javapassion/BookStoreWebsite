package com.bookstore.dao;

import java.util.List;

public interface GenericDAO<E, I> {

	public E create(E entity);

	public E update(E entity);

	public E get(Class<E> entityClass, I primaryKey);

	public void delete(Class<E> entityClass, I primaryKey);

	public List<E> listAllByCriteria(Class<E> entityClass);
	
	public List<E> listAllByNamedQuery(String queryName);
	
	public E findOneByNamedQuery(String queryName, String paramName, Object paranValue);

	public Long countByCriteria(Class<E> entityClass);
	
	public Long countByNamedQuery(String queryName);
}
