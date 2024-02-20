package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CategoryService;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST;


@WebServlet(description = "Servlet for frontend Home Page", urlPatterns = { "" })
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String HOME_PAGE_URL = "frontend/index.jsp";

	CategoryService categoryService = new CategoryService();

    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE_URL);

		request.setAttribute(CATEGORY_LIST, categoryService.listCategory());
		
		requestDispatcher.forward(request, response);
	}
 
}
