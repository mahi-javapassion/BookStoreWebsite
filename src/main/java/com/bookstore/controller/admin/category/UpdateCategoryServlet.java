package com.bookstore.controller.admin.category;

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

import static com.bookstore.controller.admin.AdminConstants.CATEGORY_EXISTS_KEY;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_EXISTS_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_NOT_FOUND_KEY;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_NOT_FOUND_MSG;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_DATA;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_FORM_PAGE;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST;
import static com.bookstore.controller.admin.AdminConstants.CATEGORY_LIST_PAGE;
import static com.bookstore.controller.admin.AdminConstants.UPDATE_CATEGORY_SUCCESS_KEY;
import static com.bookstore.controller.admin.AdminConstants.UPDATE_CATEGORY_SUCCESS_MSG;

@WebServlet("/admin/update_category")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCategoryServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService = new CategoryService(); 
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_LIST_PAGE);

		String categoryId = request.getParameter("categoryId");
		String name = request.getParameter("name");
		
		Category exisitingCategory = categoryService.getCategory(Integer.parseInt(categoryId));
		
		if(Objects.isNull(exisitingCategory)) {
			request.setAttribute(CATEGORY_NOT_FOUND_KEY, String.format(CATEGORY_NOT_FOUND_MSG, categoryId));
			requestDispatcher = request.getRequestDispatcher(CATEGORY_FORM_PAGE);
		}
		else {
			if(categoryService.isCategoryExists(name, exisitingCategory.getCategoryId())) {
				exisitingCategory.setName(name);
				request.setAttribute(CATEGORY_EXISTS_KEY, String.format(CATEGORY_EXISTS_MSG, exisitingCategory.getName()));
				request.setAttribute(CATEGORY_DATA, exisitingCategory);
				requestDispatcher = request.getRequestDispatcher(CATEGORY_FORM_PAGE);
			}	
			else {
				exisitingCategory.setName(name);
				Category category = categoryService.updateCategory(exisitingCategory);
				super.log(String.format("Category with Name %s is updated", name));
				request.setAttribute(UPDATE_CATEGORY_SUCCESS_KEY, String.format(UPDATE_CATEGORY_SUCCESS_MSG, 
						category.getCategoryId(), category.getName()));
				request.setAttribute(CATEGORY_LIST, categoryService.listCategory());
				requestDispatcher = request.getRequestDispatcher(CATEGORY_LIST_PAGE);
			}
		}
		requestDispatcher.forward(request, response);
	}
}
