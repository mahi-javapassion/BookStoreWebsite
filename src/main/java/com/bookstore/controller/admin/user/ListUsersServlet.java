package com.bookstore.controller.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.service.UsersService;

import static com.bookstore.controller.admin.AdminConstants.USER_LIST;
import static com.bookstore.controller.admin.AdminConstants.USER_LIST_PAGE;

@WebServlet("/admin/list_users")
public class ListUsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public ListUsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersService usersService = new UsersService();
		
		List<Users> listUsers = usersService.listUsers();
		request.setAttribute(USER_LIST, listUsers);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_LIST_PAGE);
		requestDispatcher.forward(request, response);
	}
}
