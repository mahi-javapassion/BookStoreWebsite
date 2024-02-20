package com.bookstore.dao;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_ALL;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_BY_NAME;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_BY_NAME_NOT_CATEGORY_ID;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category, Integer> implements GenericDAO<Category, Integer> {

	protected EntityManager entityManager;

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Category create(Category category) {
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		return super.update(category);
	}
	
	public Category get(Integer categoryId) {
		return super.get(Category.class, categoryId);
	}
	
	public void delete(Integer categoryId) {
		super.delete(Category.class, categoryId);
	}

	public List<Category> listAllByNamedQuery() {
		return super.listAllByNamedQuery(CATEGORY_FIND_ALL);
	}

	public List<Category> findByName(String name) {
		return super.findByNamedQuery(CATEGORY_FIND_BY_NAME, "name", name);
	}

	public List<Category> findByNameAndCategoryId(String name, Integer categoryId) {
		return super.findByNamedQuery(CATEGORY_FIND_BY_NAME_NOT_CATEGORY_ID, "name", name, "categoryId", categoryId);
	}
	
}
