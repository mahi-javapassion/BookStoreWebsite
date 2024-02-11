package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	public void testGetUsers() {
		Users dummyUser = createDummyUser();
		
		Users user1 = userDAO.get(Users.class, dummyUser.getUserId());
		assertNotNull(user1.getUserId());
		assertNotNull(user1.getFullName());
		assertNotNull(user1.getEmail());
		assertNotNull(user1.getPassword());
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testDeleteUsers() {
		Users dummyUser = createDummyUser();
		userDAO.delete(Users.class, dummyUser.getUserId());
		Users user2 = userDAO.get(Users.class, dummyUser.getUserId());
		assertNull(user2);
	}

	@Test
	public void testListAll() {
		Users dummyUser = createDummyUser();

		List<Users> userList = userDAO.listAll(Users.class);
		assertTrue(userList.size() >= 1);
		
		deleteDummyUser(dummyUser);
	}

	@Test
	public void testCount() {
		Users dummyUser = createDummyUser();
		Long userCount = userDAO.count(Users.class);
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
