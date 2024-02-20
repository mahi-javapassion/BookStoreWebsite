package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_BY_EMAIL;
import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_BY_EMAIL_NOT_USERID;

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
	
	public List<Users> listAllByNamedQuery(String queryName) {
		return super.listAllByNamedQuery(queryName);
	}

	public List<Users> listAllByCriteria() {
		return super.listAllByCriteria(Users.class);
	}
	
	public Long countByNamedQuery(String queryName) {
		return super.countByNamedQuery(queryName);
	}

	public Long countByCriteria() {
		return super.countByCriteria(Users.class);
	}
	
	public List<Users> findByEmail(String email) {
		return super.findByNamedQuery(USERS_FIND_BY_EMAIL, "email", email);
	}

	public List<Users> findByEmailAndUserId(String email, Integer userId) {
		return super.findByNamedQuery(USERS_FIND_BY_EMAIL_NOT_USERID, "email", email, "userId", userId);
	}
}
