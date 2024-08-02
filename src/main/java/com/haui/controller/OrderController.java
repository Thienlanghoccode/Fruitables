package com.haui.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.entity.CartItem;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
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
		req.setAttribute("cart", cartItemMap);
		RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/order.jsp");
		dispatcher.forward(req, resp);
	}
	

}
