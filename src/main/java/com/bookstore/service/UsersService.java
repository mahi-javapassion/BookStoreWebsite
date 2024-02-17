package com.bookstore.service;

import java.util.List;
import java.util.Objects;

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

	public Users createUser(String email, String fullName, String password){
		Users users = new Users(email, fullName, password);
		return createUser(users);
	}

	public Users createUser(Users users){
		return userDAO.create(users);
	}

	public Users updateUser(Users users){
		return userDAO.update(users);
	}

	public List<Users> listUsers(){
		return userDAO.listAllByNamedQuery();
	}
	
	
	public boolean isUserExists(String email) {
		try {
			Users users = userDAO.findByEmail(email);
			return Objects.nonNull(users);
		}
		catch(Exception exp) {
		}
		return false;
	}

	public boolean isUserExists(String email, Integer userId) {
		try {
			Users users = userDAO.findByEmailAndUserId(email, userId);
			return Objects.nonNull(users);
		}
		catch(Exception exp) {
		}
		return false;
	}

	public Users getUser(Integer userId) {
		try {
			return userDAO.get(userId);
		}
		catch(Exception exp) {
		}
		return null;
	}
}
