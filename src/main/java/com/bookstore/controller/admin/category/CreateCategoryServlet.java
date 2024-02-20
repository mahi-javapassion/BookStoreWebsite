package com.bookstore.controller.admin.category;

import static com.bookstore.controller.admin.AdminConstants.CREATE_CATEGORY_SUCCESS_KEY;
import static com.bookstore.controller.admin.AdminConstants.CREATE_CATEGORY_SUCCESS_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_EXISTS_KEY;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_EXISTS_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST_PAGE;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FORM_PAGE;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_DATA;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Category;
import com.bookstore.service.CategoryService;

@WebServlet("/admin/create_category")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryService categoryService = new CategoryService();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_LIST_PAGE);

		String categoryName = request.getParameter("name");
		Category category = new Category(categoryName);
		
		if(categoryService.isCategoryExists(category.getName())) {
			request.setAttribute(CATEGORY_EXISTS_KEY, String.format(CATEGORY_EXISTS_MSG, categoryName));
			request.setAttribute(CATEGORY_DATA, category);
			requestDispatcher = request.getRequestDispatcher(CATEGORY_FORM_PAGE);
		}
		else {
			categoryService.createCategory(category);
			super.log(String.format("Category with Name %s is created", categoryName));
			request.setAttribute(CREATE_CATEGORY_SUCCESS_KEY, String.format(CREATE_CATEGORY_SUCCESS_MSG, category.getCategoryId(), category.getName()));
			request.setAttribute(CATEGORY_LIST, categoryService.listCategory());
		}
		requestDispatcher.forward(request, response);
	}
}
