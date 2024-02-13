package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class UserDAOTest {

	private static EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager;
	private UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Inside setUpBeforeClass :- Creating EntityManagerFactory");
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Inside tearDownAfterClass: Closing entityManagerFactory");
		if (entityManagerFactory.isOpen())
			entityManagerFactory.close();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Inside setUp: Creating EntityManager");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Inside tearDown: Closing EntityManager");
		if (entityManager.isOpen())
			entityManager.close();
		userDAO = null;
	}

	@Test
	public void testCreateUsers() {
		Users dummyUser = createDummyUser();
		assertNotNull(dummyUser.getUserId());
		
		deleteDummyUser(dummyUser);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}

	@Test
	public void testUpdateUsers() {
		Users dummyUser = createDummyUser();
		dummyUser.setPassword("password123");
		userDAO.update(dummyUser);
		
		Users user2 = userDAO.get(Users.class, dummyUser.getUserId());
		assertTrue(dummyUser.getPassword().equals(user2.getPassword()));
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testGetUsersFound() {
		Users dummyUser = createDummyUser();
		
		Users user = userDAO.get(Users.class, dummyUser.getUserId());
		assertNotNull(user.getUserId());
		assertNotNull(user.getFullName());
		assertNotNull(user.getEmail());
		assertNotNull(user.getPassword());
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testGetUsersNotFound() {
		Users dummyUser = createDummyUser();
		
		Users user = userDAO.get(Users.class, Math.multiplyExact(dummyUser.getUserId(), -1));
		assertNull(user);
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testDeleteUsers() {
		Users dummyUser = createDummyUser();
		userDAO.delete(Users.class, dummyUser.getUserId());
		Users user2 = userDAO.get(Users.class, dummyUser.getUserId());
		assertNull(user2);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		userDAO.delete(Users.class, Integer.MAX_VALUE-1);
	}

	@Test
	public void testListAllByCriteria() {
		Users dummyUser = createDummyUser();
		List<Users> userList = userDAO.listAllByCriteria(Users.class);
		assertTrue(userList.size() >= 1);
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testListAllByNamedQuery() {
		Users dummyUser = createDummyUser();
		List<Users> userList = userDAO.listAllByNamedQuery("Users.findAll");
		assertTrue(userList.size() >= 1);
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testCountByNamedQuery() {
		Users dummyUser = createDummyUser();
		Long userCount = userDAO.countByNamedQuery();
		assertTrue(userCount >= 1);
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testCountByCriteria() {
		Users dummyUser = createDummyUser();
		Long userCount = userDAO.countByCriteria();
		assertTrue(userCount >= 1);
		
		deleteDummyUser(dummyUser);
	}

	private Users createDummyUser() {
		Users user1 = new Users();
		user1.setEmail("mahesh-test@java.net");
		user1.setFullName("Mahesh Yerudkar");
		user1.setPassword("password");
		return userDAO.create(user1);
	}

	private void deleteDummyUser(Users dummyUser) {
		userDAO.delete(Users.class, dummyUser.getUserId());
	}
}
