package com.haui.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.entity.Category;
import com.haui.service.CategoryService;
import com.haui.service.Impl.CategoryServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/category")
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categoryList = categoryService.getAll();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/category.jsp");
		req.setAttribute("categoryList", categoryList);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Id = req.getParameter("Id");
		Category category = categoryService.get(Integer.parseInt(Id));
		// Trả về thông tin dưới dạng JSON
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter out = resp.getWriter();
	    
	    ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(category);
        out.write(json);
        out.close();
	}

}
