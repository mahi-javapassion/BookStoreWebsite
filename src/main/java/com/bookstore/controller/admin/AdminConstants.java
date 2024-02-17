package com.bookstore.controller.admin;

public class AdminConstants {

	private AdminConstants() {
	}
	
	public static final String ADMIN_HOME_PAGE_URL = "index.jsp";
	public static final String USER_LIST_PAGE = "users_list.jsp";
	public static final String USER_FORM_PAGE = "user_form.jsp";


	public static final String USER_LIST = "listUsers";
	public static final String USER_DATA = "userData";
	
	public static final String USERS_FIND_ALL = "Users.findAll";
	public static final String USERS_FIND_BY_EMAIL = "Users.findByEmail";
	public static final String USERS_FIND_BY_EMAIL_NOT_USERID = "Users.findByEmailNotUserId";

	public static final String USERS_COUNT_ALL = "Users.countAll";

	public static final String CREATE_USERS_SUCCESS_KEY = "CREATE_USERS_SUCCESS_KEY";
	public static final String CREATE_USERS_SUCCESS_MSG = "User with id : %s and email %s is saved successfully";

	public static final String UPDATE_USERS_SUCCESS_KEY = "UPDATE_USERS_SUCCESS_KEY";
	public static final String UPDATE_USERS_SUCCESS_MSG = "User with id : %s and email %s is updated successfully";

	public static final String DELETE_USERS_SUCCESS_KEY = "DELETE_USERS_SUCCESS_KEY";
	public static final String DELETE_USERS_SUCCESS_MSG = "User with id : %s is delete successfully";

	public static final String USERS_EXISTS_KEY = "USERS_EXISTS_KEY";
	public static final String USERS_EXISTS_MSG = "User with email '%s' already exists";

	public static final String ADMIN_DELETE_KEY = "ADMIN_DELETE_KEY";
	public static final String ADMIN_DELETE_MSG = "The default admin user account cannot be deleted";

	public static final String USERS_NOT_FOUND_KEY = "USERS_NOT_FOUND_KEY";
	public static final String USERS_NOT_FOUND_MSG = "User with user id '%s' not found";

}
