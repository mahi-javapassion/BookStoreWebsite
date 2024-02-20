package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_ALL;
import static com.bookstore.controller.admin.AdminConstants.USERS_COUNT_ALL;;

public class UserDAOTest extends BaseDAOTest {

	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("UserDAOTest: Inside setUp: Creating EntityManager");
		super.setUp();
		userDAO = new UserDAO(entityManager);
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("UserDAOTest : Inside tearDown: Closing EntityManager");
		super.tearDown();
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
		userDAO.delete(Users.class, Integer.MAX_VALUE - 1);
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
		List<Users> userList = userDAO.listAllByNamedQuery(USERS_FIND_ALL);
		assertTrue(userList.size() >= 1);

		deleteDummyUser(dummyUser);
	}

	@Test
	public void testCountByNamedQuery() {
		Users dummyUser = createDummyUser();
		Long userCount = userDAO.countByNamedQuery(USERS_COUNT_ALL);
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

	
	@Test 
	public void testFindByEmailFound() { 
	  Users dummyUser =  createDummyUser("xyz123@gmail.com"); 
	  List<Users> usersList = userDAO.findByEmail(dummyUser.getEmail());
	  assertTrue(CollectionUtils.isNotEmpty(usersList)); 
	  deleteDummyUser(dummyUser); 
	}
	  
		
	@Test 
	public void testFindByEmailNotFound() { 
		Users dummyUser = createDummyUser("xyz1234@gmail.com");
		List<Users> usersList = null;
		try {
			usersList = userDAO.findByEmail("xyz" + dummyUser.getEmail());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(CollectionUtils.isEmpty(usersList)); 
		deleteDummyUser(dummyUser); 
	}
	  
	@Test 
	public void testFindByEmailAndUserIdFound() { 
		Users dummyUser = createDummyUser("xyz1234@gmail.com");

		List<Users> usersList = userDAO.findByEmailAndUserId(dummyUser.getEmail(), 0);
		
		assertTrue(CollectionUtils.isNotEmpty(usersList)); 
		
		deleteDummyUser(dummyUser);
	}

	@Test 
	public void testFindByEmailAndUserIdNotFound() { 
		Users dummyUser = createDummyUser("xyz1234@gmail.com");

		List<Users> usersList = null;
		try {
			usersList = userDAO.findByEmailAndUserId("xyz" + dummyUser.getEmail(), dummyUser.getUserId());
		}
		catch(Exception e) {}
		assertTrue(CollectionUtils.isEmpty(usersList)); 
		
		deleteDummyUser(dummyUser);
	}

	
	
	private Users createDummyUser(String email) {
		Users user1 = new Users();
		user1.setEmail(email);
		user1.setFullName("Mahesh Yerudkar");
		user1.setPassword("password");
		return userDAO.create(user1);
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
