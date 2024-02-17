package com.bookstore.entity;
// Generated 10-Feb-2024, 10:01:38 pm by Hibernate Tools 3.6.2.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_ALL;
import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_BY_EMAIL;
import static com.bookstore.controller.admin.AdminConstants.USERS_FIND_BY_EMAIL_NOT_USERID;
import static com.bookstore.controller.admin.AdminConstants.USERS_COUNT_ALL;;

@Entity
@Table(name = "users", catalog = "bookstoredb")
@NamedQueries({ 
			@NamedQuery(name = USERS_FIND_ALL, query = "SELECT u FROM Users u ORDER BY u.fullName"),
			@NamedQuery(name = USERS_COUNT_ALL, query = "SELECT count(*) FROM Users u"),
			@NamedQuery(name = USERS_FIND_BY_EMAIL, query = "SELECT u FROM Users u WHERE u.email = :email"),
			@NamedQuery(name = USERS_FIND_BY_EMAIL_NOT_USERID, query = "SELECT u FROM Users u WHERE u.email = :email and u.userId != :userId")
})
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	public Users() {
	}

	public Users(String email, String fullName, String password) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name", nullable = false, length = 30)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
