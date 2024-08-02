package com.haui.controller.admin;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns = "/admin/productlist")
public class ProductListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	
	 //Lấy tất cả sản phẩm từ database và hiển thị
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productService.getAll();
		req.setAttribute("products", products);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/product-list.jsp");
		dispatcher.forward(req, resp);
	}

}
