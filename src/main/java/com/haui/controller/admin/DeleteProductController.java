package com.haui.controller.admin;

import java.io.IOException;
import java.util.List;

import com.haui.entity.Product;
import com.haui.service.CategoryService;
import com.haui.service.ProductService;
import com.haui.service.Impl.CategoryServiceImpl;
import com.haui.service.Impl.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/deleteproduct")
public class DeleteProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pId = req.getParameter("pId");
		productService.delete(Integer.parseInt(pId));
		List<Product> products = productService.getAll();
		req.setAttribute("products", products);
		resp.sendRedirect(req.getContextPath() + "/admin/productlist");
	}

}
