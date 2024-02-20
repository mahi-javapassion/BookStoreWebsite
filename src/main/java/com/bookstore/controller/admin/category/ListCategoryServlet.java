package com.bookstore.controller.admin.category;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST_PAGE;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Category;
import com.bookstore.service.CategoryService;


@WebServlet("/admin/list_category")
public class ListCategoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ListCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService = new CategoryService();
		
		List<Category> listCategory = categoryService.listCategory();
		request.setAttribute(CATEGORY_LIST, listCategory);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_LIST_PAGE);
		requestDispatcher.forward(request, response);
	}
}
