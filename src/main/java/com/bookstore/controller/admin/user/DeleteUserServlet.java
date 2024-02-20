package com.bookstore.controller.admin.user;

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

import static com.bookstore.controller.admin.AdminConstants.DELETE_USERS_SUCCESS_KEY;
import static com.bookstore.controller.admin.AdminConstants.DELETE_USERS_SUCCESS_MSG;
import static com.bookstore.controller.admin.AdminConstants.USERS_NOT_FOUND_KEY;
import static com.bookstore.controller.admin.AdminConstants.USERS_NOT_FOUND_MSG;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST_PAGE;
import static com.bookstore.controller.admin.AdminConstants.ADMIN_DELETE_KEY;
import static com.bookstore.controller.admin.AdminConstants.ADMIN_DELETE_MSG;


@WebServlet("/admin/delete_user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersService usersService = new UsersService();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_LIST_PAGE);
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		Users exsitingUser = usersService.getUser(userId);
		
		if(userId.intValue() == 194) {
			request.setAttribute(ADMIN_DELETE_KEY, ADMIN_DELETE_MSG);
		}		
		else if(Objects.isNull(exsitingUser)) {
			request.setAttribute(USERS_NOT_FOUND_KEY, String.format(USERS_NOT_FOUND_MSG, userId));
		}
		else {
			usersService.deleteUser(userId);
			super.log(String.format("User with User ID %s is deleted successfully", userId));
			request.setAttribute(DELETE_USERS_SUCCESS_KEY, String.format(DELETE_USERS_SUCCESS_MSG, userId));
		}
		request.setAttribute(USER_LIST, usersService.listUsers());
		requestDispatcher = request.getRequestDispatcher(USER_LIST_PAGE);
		requestDispatcher.forward(request, response);
	}

}
