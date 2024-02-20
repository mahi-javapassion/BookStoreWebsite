package com.bookstore.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_ALL;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_BY_NAME;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FIND_BY_NAME_NOT_CATEGORY_ID;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_COUNT_ALL;

@Entity
@Table(name = "category", catalog = "bookstoredb")
@NamedQueries({
	@NamedQuery(name = CATEGORY_FIND_ALL, query = "SELECT c FROM Category c ORDER BY c.name"),
	@NamedQuery(name = CATEGORY_COUNT_ALL, query = "SELECT count(*) FROM Category c"),
	@NamedQuery(name = CATEGORY_FIND_BY_NAME, query = "SELECT c FROM Category c WHERE c.name = :name"),
	@NamedQuery(name = CATEGORY_FIND_BY_NAME_NOT_CATEGORY_ID, query = "SELECT c FROM Category c WHERE c.name = :name and c.categoryId != :categoryId")
})
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private String name;
	private Set<Book> books = new HashSet<Book>(0);

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
