package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin/")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String ADMIN_HOME_PAGE_URL = "index.jsp";
   
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_HOME_PAGE_URL);
		requestDispatcher.forward(request, response);
	}

}
