package com.bookstore.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_ALL;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_COUNT_ALL;


public class CategoryDAOTest extends BaseDAOTest {

	private static CategoryDAO categoryDAO;

	public CategoryDAOTest() {
	}

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
		System.out.println("CategoryDAOTest: Inside setUp: Creating EntityManager");
		super.setUp();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("CategoryDAOTest : Inside tearDown: Closing EntityManager");
		super.tearDown();
		categoryDAO = null;
	}

	@Test
	public void testCreateCategory() {
		Category dummyCategory = createDummyCategory("Dummy Category");
		assertNotNull(dummyCategory.getCategoryId());
		deleteDummyCategory(dummyCategory);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateCategoryFieldsNotSet() {
		Category category = new Category();
		category = categoryDAO.create(category);
	}
	
	@Test
	public void testUpdateUsers() {
		Category dummyCategory = createDummyCategory("Dummy Category");
		dummyCategory.setName("Dummy Category Update");
		categoryDAO.update(dummyCategory);

		Category category = categoryDAO.get(Category.class, dummyCategory.getCategoryId());
		assertTrue(category.getName().equals(dummyCategory.getName()));

		deleteDummyCategory(dummyCategory);
	}

	@Test
	public void testGetCategoryFound() {
		Category dummyCategory = createDummyCategory("Dummy Category");

		Category category = categoryDAO.get(Category.class, dummyCategory.getCategoryId());
		assertNotNull(category.getCategoryId());
		assertNotNull(category.getName());

		deleteDummyCategory(dummyCategory);
	}

	@Test
	public void testGetCategoryNotFound() {
		Category dummyCategory = createDummyCategory("Dummy Category");

		Category category = categoryDAO.get(Category.class, Math.multiplyExact(dummyCategory.getCategoryId(), -1));
		assertNull(category);

		deleteDummyCategory(dummyCategory);
	}

	@Test
	public void testDeleteCategory() {
		Category dummyCategory = createDummyCategory("Dummy Category");

		categoryDAO.delete(Category.class, dummyCategory.getCategoryId());
		
		Category category = categoryDAO.get(Category.class, dummyCategory.getCategoryId());
		assertNull(category);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistCategory() {
		categoryDAO.delete(Category.class, Integer.MAX_VALUE - 1);
	}

	@Test
	public void testListAllByNamedQuery() {
		Category dummyCategory = createDummyCategory("Dummy Category");

		List<Category> categoryList = categoryDAO.listAllByNamedQuery(CATEGORY_FIND_ALL);
		assertTrue(categoryList.size() >= 1);

		deleteDummyCategory(dummyCategory);
	}

	@Test
	public void testCountByNamedQuery() {
		Category dummyCategory = createDummyCategory("Dummy Category");

		Long categoryrCount = categoryDAO.countByNamedQuery(CATEGORY_COUNT_ALL);
		assertTrue(categoryrCount >= 1);

		deleteDummyCategory(dummyCategory);
	}

	@Test 
	public void testfindByNameFound() { 
		Category dummyCategory = createDummyCategory("Dummy Category");

		List<Category> categoryList = categoryDAO.findByName(dummyCategory.getName());
		assertTrue(CollectionUtils.isNotEmpty(categoryList)); 
		
		deleteDummyCategory(dummyCategory);
	}
	  
	@Test 
	public void testfindByNameNotFound() { 
		Category dummyCategory = createDummyCategory("Dummy Category");

		List<Category> categoryList = null;
		try {
			categoryList = categoryDAO.findByName("xyz" + dummyCategory.getName());
		}
		catch(Exception e) {}
		assertTrue(CollectionUtils.isEmpty(categoryList)); 
		
		deleteDummyCategory(dummyCategory);
	}

	@Test 
	public void testfindByNameAndCategoryIdFound() { 
		Category dummyCategory = createDummyCategory("Dummy Category");

		List<Category> categoryList = categoryDAO.findByNameAndCategoryId(dummyCategory.getName(), 0);
		assertTrue(CollectionUtils.isNotEmpty(categoryList)); 
		
		deleteDummyCategory(dummyCategory);
	}

	@Test 
	public void testfindByNameAndCategoryIdNotFound() { 
		Category dummyCategory = createDummyCategory("Dummy Category");

		List<Category> categoryList = null;
		try {
			categoryList = categoryDAO.findByNameAndCategoryId("xyz" + dummyCategory.getName(), dummyCategory.getCategoryId());
		}
		catch(Exception e) {}
		assertTrue(CollectionUtils.isEmpty(categoryList)); 
		
		deleteDummyCategory(dummyCategory);
	}

	private Category createDummyCategory(String name) {
		Category category = new Category();
		category.setName(name);
		category = categoryDAO.create(category);
		return category;
	}
	
	private void deleteDummyCategory(Category dummyCategory) {
		categoryDAO.delete(Category.class, dummyCategory.getCategoryId());
	}
}
