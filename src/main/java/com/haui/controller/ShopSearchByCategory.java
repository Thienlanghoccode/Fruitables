package com.haui.controller;

import java.io.IOException;
import java.util.List;

import com.haui.entity.Product;
import com.haui.service.ProductService;
import com.haui.service.Impl.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/shop/search")
public class ShopSearchByCategory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ProductService productService=new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cate_id=req.getParameter("cate_id");
		List<Product> productSeachByCategory =productService.seachByCategory(Integer.parseInt(cate_id));
		req.setAttribute("productSeachByCategory", productSeachByCategory);
		req.getRequestDispatcher("/views/user/shopSearchByCategory.jsp").forward(req, resp);
	}

}
