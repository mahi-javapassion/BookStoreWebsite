package com.bookstore.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaDAO<E, I> implements GenericDAO<E, I> {

	protected EntityManager entityManager;
	
	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	@Override
	public E create(E entity) {
		entityManager.getTransaction().begin();
		
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		
		entityManager.getTransaction().commit();
		
		return entity;
	}

	@Override
	public E update(E entity) {
		entityManager.getTransaction().begin();
		
		entityManager.merge(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		
		entityManager.getTransaction().commit();
		
		return entity;
	}

	@Override
	public E get(Class<E> entityClass, I primaryKey) {
		E entity = entityManager.find(entityClass, primaryKey);
		if(Objects.nonNull(entity)) {
			entityManager.refresh(entity);
		}
		return entity;
	}

	@Override
	public void delete(Class<E> entityClass, I primaryKey) {
		entityManager.getTransaction().begin();
		E entityReference = entityManager.getReference(entityClass, primaryKey);
		entityManager.remove(entityReference);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<E> listAllByNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
	}

	@Override
	public List<E> listAllByCriteria(Class<E> entityClass) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> cr = cb.createQuery(entityClass);
		Root<E> root = cr.from(entityClass);
		cr.select(root);
		TypedQuery<E> query = entityManager.createQuery(cr);
		return query.getResultList();
	}

	@Override
	public Long countByNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (Long) query.getSingleResult();
	}
	
	@Override
	public Long countByCriteria(Class<E> entityClass) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<E> root = cr.from(entityClass);
		cr.select(cb.count(root));
		TypedQuery<Long> query = entityManager.createQuery(cr);
		List<Long> projectedResult = query.getResultList();
		if(Objects.nonNull(projectedResult)) return projectedResult.get(0);
		return 0L;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName, String paramName, Object paranValue) {
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paranValue);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName, String paramName1, Object paranValue1, String paramName2, Object paranValue2) {
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName1, paranValue1);
		query.setParameter(paramName2, paranValue2);
		return query.getResultList();
	}
}
