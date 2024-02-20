package com.bookstore.controller.admin.user;

import static com.bookstore.controller.admin.AdminConstants.CREATE_USERS_SUCCESS_KEY;
import static com.bookstore.controller.admin.AdminConstants.CREATE_USERS_SUCCESS_MSG;
import static com.bookstore.controller.admin.AdminConstants.USERS_EXISTS_KEY;
import static com.bookstore.controller.admin.AdminConstants.USERS_EXISTS_MSG;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST_PAGE;
import static com.bookstore.controller.admin.AdminConstants.USER_FORM_PAGE;
import static com.bookstore.controller.admin.AdminConstants.USER_DATA;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.service.UsersService;

@WebServlet("/admin/create_user")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersService usersService = new UsersService();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_LIST_PAGE);

		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		Users users = new Users(email, fullName, password);
		
		if(usersService.isUserExists(users.getEmail())) {
			request.setAttribute(USERS_EXISTS_KEY, String.format(USERS_EXISTS_MSG, email));
			request.setAttribute(USER_DATA, users);
			requestDispatcher = request.getRequestDispatcher(USER_FORM_PAGE);
		}
		else {
			usersService.createUser(users);
			super.log(String.format("User with Full Name %s is created", fullName));
			request.setAttribute(CREATE_USERS_SUCCESS_KEY, String.format(CREATE_USERS_SUCCESS_MSG, users.getUserId(), users.getEmail()));
			request.setAttribute(USER_LIST, usersService.listUsers());
		}
		requestDispatcher.forward(request, response);
	}
}
