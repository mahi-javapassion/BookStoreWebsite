package com.bookstore.dao;

import java.util.List;

public interface GenericDAO<E, I> {

	public E create(E entity);

	public E update(E entity);

	public E get(Class<E> entityClass, I primaryKey);

	public void delete(Class<E> entityClass, I primaryKey);

	public List<E> listAll(Class<E> entityClass);

	public Long count(Class<E> entityClass);
}
