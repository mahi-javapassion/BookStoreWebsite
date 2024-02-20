package com.bookstore.controller.admin.category;

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_NOT_FOUND_KEY;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_NOT_FOUND_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_DATA;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FORM_PAGE;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Category;
import com.bookstore.service.CategoryService;

@WebServlet("/admin/edit_category")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCategoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService = new CategoryService(); 
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		Category category = categoryService.getCategory(categoryId);
		if(Objects.isNull(category)) {
			request.setAttribute(CATEGORY_NOT_FOUND_KEY, String.format(CATEGORY_NOT_FOUND_MSG, categoryId));
		}
		else {
			super.log(String.format("Category with category id %s is found", categoryId));
			request.setAttribute(CATEGORY_DATA, category);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_FORM_PAGE);
		requestDispatcher.forward(request, response);
	}
}
