package com.haui.controller;

import java.io.IOException;
import java.util.List;

import com.haui.entity.Category;
import com.haui.entity.Product;
import com.haui.service.CategoryService;
import com.haui.service.ProductService;
import com.haui.service.Impl.CategoryServiceImpl;
import com.haui.service.Impl.ProductServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/searchByName")
public class ShopSearchByName extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int PRODUCT_MAX_PAGE_SIZE = 9;

	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String txtSearch = req.getParameter("productName");
		List<Product> countProduct = productService.seachByName(txtSearch);
		List<Category> categories = categoryService.getAll();
		int maxPage = (int) Math.ceil(countProduct.size() / (double) PRODUCT_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		
		String pageNumber = req.getParameter("page");
		List<Product> productList;
		if(pageNumber == null || maxPage < Integer.valueOf(pageNumber)) {
			productList = productService.seachByName(txtSearch, 1, PRODUCT_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", 1);
		}
		else {
			productList = productService.seachByName(txtSearch, Integer.valueOf(pageNumber), PRODUCT_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		
		req.setAttribute("productList", productList);
		req.setAttribute("categories", categories);
		RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/shop.jsp");
		dispatcher.forward(req, resp);
	}

}
