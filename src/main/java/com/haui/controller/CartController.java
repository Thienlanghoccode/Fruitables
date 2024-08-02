package com.haui.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.entity.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/cart")
public class CartController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("delete")) {
			String pID = req.getParameter("pID");

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
					TypeReference<Map<Integer, CartItem>> typeRef = new TypeReference<Map<Integer, CartItem>>() {
					};
					cartItemMap = mapper.readValue(cartItemJson, typeRef);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			cartItemMap.remove(Integer.parseInt(pID));
			
			// Chuyển đổi map thành JSON mới
		    String newCartItemJson = mapper.writeValueAsString(cartItemMap);

		    // Cập nhật lại cookie
		    Cookie updatedCookie = new Cookie("cartItem", URLEncoder.encode(newCartItemJson, "UTF-8"));
		    updatedCookie.setMaxAge(24 * 60 * 60); // Thời gian sống của cookie
		    updatedCookie.setPath("/");
		    resp.addCookie(updatedCookie);
		    
		    // Cập nhật giỏ hàng trong session
		    HttpSession session = req.getSession();
		    session.setAttribute("cart", cartItemMap);
		    
		}
	}
}
