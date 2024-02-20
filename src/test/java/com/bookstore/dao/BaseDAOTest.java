package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {

	protected static EntityManagerFactory entityManagerFactory = null;
	protected static EntityManager entityManager;

	public BaseDAOTest() {
	}

	protected static void setUpBeforeClass() throws Exception {
		System.out.println("BaseDAOTest: Inside setUpBeforeClass :- Creating EntityManagerFactory");
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	}
	
	public static void tearDownAfterClass() throws Exception {
		System.out.println("BaseDAOTest: Inside tearDownAfterClass: Closing entityManagerFactory");
		if (entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}
	
	public void setUp() throws Exception {
		System.out.println("BaseDAOTest: Inside setUp: Creating EntityManager");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void tearDown() throws Exception {
		System.out.println("BaseDAOTest: Inside setUp: Creating EntityManager");
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}
