package com.haui.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.entity.CartItem;
import com.haui.entity.Product;
import com.haui.service.CategoryService;
import com.haui.service.ProductService;
import com.haui.service.Impl.CategoryServiceImpl;
import com.haui.service.Impl.ProductServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/index")
public class HomeController extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy giá trị của cookie "cartItem" nếu có
	    Cookie[] cookies = req.getCookies();
	    String cartItemJson = null;
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("cartItem")) {
	                cartItemJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
	                break;
	            }
	        }
	    }

	    // Kiểm tra và xử lý dữ liệu từ cookie
	    Map<Integer, CartItem> cartItemMap = new HashMap<>();
	    ObjectMapper mapper = new ObjectMapper();

	    if (cartItemJson != null && !cartItemJson.isEmpty()) {
	        try {
	            // Giải mã JSON thành một map các CartItem
	            TypeReference<Map<Integer, CartItem>> typeRef = new TypeReference<Map<Integer, CartItem>>() {};
	            cartItemMap = mapper.readValue(cartItemJson, typeRef);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    // Cập nhật giỏ hàng trong session
	    HttpSession session = req.getSession();
	    session.setAttribute("cart", cartItemMap);
	    
	    //Lấy tất cả sản phẩm từ database và hiển thị
		List<Product> products = productService.getAll();
		req.setAttribute("products", products);
		RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/index.jsp");
		dispatcher.forward(req, resp);
	}

}
