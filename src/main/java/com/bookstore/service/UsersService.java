package com.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_ALL;

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
		return userDAO.listAllByNamedQuery(USERS_FIND_ALL);
	}

	public boolean isUserExists(String email) {
		try {
			List<Users> usersList = userDAO.findByEmail(email);
			return CollectionUtils.isNotEmpty(usersList);
		}
		catch(Exception exp) {
		}
		return false;
	}

	public boolean isUserExists(String email, Integer userId) {
		try {
			List<Users> usersList = userDAO.findByEmailAndUserId(email, userId);
			return CollectionUtils.isNotEmpty(usersList);
		}
		catch(Exception exp) {
		}
		return false;
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

	public void deleteUser(Integer userId){
		userDAO.delete(userId);
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
