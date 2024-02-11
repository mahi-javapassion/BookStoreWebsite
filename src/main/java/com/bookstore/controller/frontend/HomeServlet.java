package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(description = "Servlet for frontend Home Page", urlPatterns = { "" })
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String HOME_PAGE_URL = "frontend/index.jsp";
       
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE_URL);
		requestDispatcher.forward(request, response);
	}
 
}
