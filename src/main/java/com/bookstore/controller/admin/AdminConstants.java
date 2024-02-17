package com.bookstore.controller.admin;

public class AdminConstants {

	private AdminConstants() {
	}
	
	public static final String ADMIN_HOME_PAGE_URL = "index.jsp";
	public static final String USER_LIST_PAGE = "users_list.jsp";

	public static final String USER_LIST = "listUsers";
	
	public static final String USERS_FIND_ALL = "Users.findAll";
	public static final String USERS_FIND_BY_EMAIL = "Users.findByEmail";
	public static final String USERS_COUNT_ALL = "Users.countAll";

	public static final String CREATE_USERS_SUCCESS_KEY = "CREATE_USERS_SUCCESS_KEY";
	public static final String CREATE_USERS_SUCCESS_MSG = "User with id : %s and email %s is saved successfully";

	public static final String USERS_EXISTS_KEY = "USERS_EXISTS_KEY";
	public static final String USERS_EXISTS_MSG = "User with email '%s' already exists";
}
