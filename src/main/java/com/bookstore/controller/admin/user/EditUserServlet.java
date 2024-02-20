package com.bookstore.controller.admin.user;

import static com.bookstore.controller.admin.AdminConstants.USERS_NOT_FOUND_KEY;
import static com.bookstore.controller.admin.AdminConstants.USERS_NOT_FOUND_MSG;
import static com.bookstore.controller.admin.AdminConstants.USER_DATA;
import static com.bookstore.controller.admin.AdminConstants.USER_FORM_PAGE;

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

@WebServlet("/admin/edit_user")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersService usersService = new UsersService(); 
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		Users users = usersService.getUser(userId);
		if(Objects.isNull(users)) {
			request.setAttribute(USERS_NOT_FOUND_KEY, String.format(USERS_NOT_FOUND_MSG, userId));
		}
		else {
			super.log(String.format("User with user id %s is found", userId));
			request.setAttribute(USER_DATA, users);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_FORM_PAGE);
		requestDispatcher.forward(request, response);
	}
}
