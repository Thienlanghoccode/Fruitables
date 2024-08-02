package com.haui.controller.admin;

import java.io.IOException;

import com.haui.service.CategoryService;
import com.haui.service.Impl.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/deletecategory")
public class DeleteCategory extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("Id");
		categoryService.delete(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath() + "/admin/category");
	}

}
