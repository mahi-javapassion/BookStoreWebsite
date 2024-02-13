package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_ALL;
import static com.bookstore.controller.admin.AdminConstants.USERS_COUNT_ALL;;

public class UserDAO extends JpaDAO<Users, Integer> implements GenericDAO<Users, Integer>{

	protected EntityManager entityManager;

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	public Users create(Users users) {
		return super.create(users);
	}

	public Users update(Users users) {
		return super.update(users);
	}

	public Users get(Integer userId) {
		return super.get(Users.class, userId);
	}
	
	public void delete(Integer userId) {
		super.delete(Users.class, userId);
	}
	
	public List<Users> listAllByNamedQuery() {
		return super.listAllByNamedQuery(USERS_FIND_ALL);
	}

	public List<Users> listAllByCriteria() {
		return super.listAllByCriteria(Users.class);
	}
	
	public Long countByNamedQuery() {
		return super.countByNamedQuery(USERS_COUNT_ALL);
	}

	public Long countByCriteria() {
		return super.countByCriteria(Users.class);
	}
}
