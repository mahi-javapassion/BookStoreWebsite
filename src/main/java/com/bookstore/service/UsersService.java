package com.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UsersService {

	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	
	private UserDAO userDAO;

	
	public UsersService() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO =  new UserDAO(entityManager);
	}

	public List<Users> listUsers(){
		return userDAO.listAllByNamedQuery();
	}
}
