package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.service.UsersService;

import static com.bookstore.controller.admin.AdminConstants.USERS_EXISTS_KEY;
import static com.bookstore.controller.admin.AdminConstants.USERS_EXISTS_MSG;
import static com.bookstore.controller.admin.AdminConstants.USERS_NOT_FOUND_KEY;
import static com.bookstore.controller.admin.AdminConstants.USERS_NOT_FOUND_MSG;
import static com.bookstore.controller.admin.AdminConstants.USER_DATA;
import static com.bookstore.controller.admin.AdminConstants.USER_FORM_PAGE;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST_PAGE;
import static com.bookstore.controller.admin.AdminConstants.UPDATE_USERS_SUCCESS_KEY;
import static com.bookstore.controller.admin.AdminConstants.UPDATE_USERS_SUCCESS_MSG;

@WebServlet("/admin/update_user")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUserServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersService usersService = new UsersService(); 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_LIST_PAGE);

		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		Users userRequest = new Users(email, fullName, password);

		Users exsitingUser = usersService.getUser(Integer.parseInt(userId));
		
		if(Objects.isNull(exsitingUser)) {
			request.setAttribute(USERS_NOT_FOUND_KEY, String.format(USERS_NOT_FOUND_MSG, userId));
			requestDispatcher = request.getRequestDispatcher(USER_FORM_PAGE);
		}
		else {
			exsitingUser.setEmail(email);
			exsitingUser.setFullName(fullName);
			exsitingUser.setPassword(password);
			if(usersService.isUserExists(exsitingUser.getEmail(), exsitingUser.getUserId())) {
				request.setAttribute(USERS_EXISTS_KEY, String.format(USERS_EXISTS_MSG, userRequest.getEmail()));
				request.setAttribute(USER_DATA, exsitingUser);
				requestDispatcher = request.getRequestDispatcher(USER_FORM_PAGE);
			}	
			else {
				exsitingUser.setEmail(email);
				exsitingUser.setFullName(fullName);
				exsitingUser.setPassword(password);
				Users users = usersService.updateUser(exsitingUser);
				super.log(String.format("User with Full Name %s is updated", fullName));
				request.setAttribute(UPDATE_USERS_SUCCESS_KEY, String.format(UPDATE_USERS_SUCCESS_MSG, users.getUserId(), users.getEmail()));
				request.setAttribute(USER_LIST, usersService.listUsers());
				requestDispatcher = request.getRequestDispatcher(USER_LIST_PAGE);
			}
		}
		requestDispatcher.forward(request, response);
	}
}
