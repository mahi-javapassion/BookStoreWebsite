package com.bookstore.service;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_ALL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryService {

	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	
	private CategoryDAO categoryDAO;
	
	public CategoryService() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		categoryDAO =  new CategoryDAO(entityManager);
	}
	
	public List<Category> listCategory(){
		return categoryDAO.listAllByNamedQuery(CATEGORY_FIND_ALL);
	}
	
	public boolean isCategoryExists(String name) {
		try {
			List<Category> categoryList = categoryDAO.findByName(name);
			return CollectionUtils.isNotEmpty(categoryList);
		}
		catch(Exception exp) {
		}
		return false;
	}

	public boolean isCategoryExists(String name, Integer categoryId) {
		try {
			List<Category> categoryList = categoryDAO.findByNameAndCategoryId(name, categoryId);
			return CollectionUtils.isNotEmpty(categoryList);
		}
		catch(Exception exp) {
		}
		return false;
	}
	
	public Category createCategory(String name, String fullName, String password){
		Category category = new Category(name);
		return createCategory(category);
	}
	
	public Category createCategory(Category category){
		return categoryDAO.create(category);
	}

	public Category getCategory(Integer categoryId) {
		try {
			return categoryDAO.get(categoryId);
		}
		catch(Exception exp) {
		}
		return null;
	}

	public Category updateCategory(Category category){
		return categoryDAO.update(category);
	}

	public void deleteCategory(Integer categoryId){
		categoryDAO.delete(categoryId);
	}
}
