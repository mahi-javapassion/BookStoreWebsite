package com.bookstore.controller.admin.category;

import static com.bookstore.controller.admin.AdminConstants.DELETE_CATEGORY_SUCCESS_KEY;
import static com.bookstore.controller.admin.AdminConstants.DELETE_CATEGORY_SUCCESS_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_NOT_FOUND_KEY;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_NOT_FOUND_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST_PAGE;

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

@WebServlet("/admin/delete_category")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService = new CategoryService();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_LIST_PAGE);
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		Category exisitingCategory = categoryService.getCategory(categoryId);
		
		if(Objects.isNull(exisitingCategory)) {
			request.setAttribute(CATEGORY_NOT_FOUND_KEY, String.format(CATEGORY_NOT_FOUND_MSG, categoryId));
		}
		else {
			categoryService.deleteCategory(categoryId);
			super.log(String.format("categoryId with Category ID %s is deleted successfully", categoryId));
			request.setAttribute(DELETE_CATEGORY_SUCCESS_KEY, String.format(DELETE_CATEGORY_SUCCESS_MSG, categoryId));
		}
		request.setAttribute(CATEGORY_LIST, categoryService.listCategory());
		requestDispatcher = request.getRequestDispatcher(CATEGORY_LIST_PAGE);
		requestDispatcher.forward(request, response);
	}
}
