package com.bookstore.dao;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users, Integer> implements GenericDAO<Users, Integer>{

	protected EntityManager entityManager;

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Users create(Users users) {
		return super.create(users);
	}

}
