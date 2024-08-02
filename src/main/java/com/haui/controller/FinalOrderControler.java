package com.haui.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.entity.Cart;
import com.haui.entity.CartItem;
import com.haui.entity.User;
import com.haui.service.CartItemService;
import com.haui.service.CartService;
import com.haui.service.UserService;
import com.haui.service.Impl.CartItemServiceImpl;
import com.haui.service.Impl.CartServiceImpl;
import com.haui.service.Impl.UserServiceImpl;
import com.haui.tools.RandomUserUID;
import com.haui.tools.sendEmail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/finalOder")
public class FinalOrderControler extends HttpServlet {

	
private static final long serialVersionUID = 1L;
	
	UserService userService = new UserServiceImpl();
	CartService cartService = new CartServiceImpl();
	CartItemService cartItemService = new CartItemServiceImpl();
	long time = System.currentTimeMillis();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("currentUser");
		User buyer = (User) obj;
		Cart cart = new Cart();
		cart.setBuyer(buyer);
		cart.setBuyDate(new java.sql.Date(time));
		cart.setId(RandomUserUID.getRandomID());
		
		cartService.insert(cart);
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
	    Object discountObj = session.getAttribute("discount");
	    long discount = 0;

	    if (discountObj instanceof Integer) {
	        discount = ((Integer) discountObj).longValue();
	    } else if (discountObj instanceof Long) {
	        discount = (Long) discountObj;
	    } 
	    if(cartItemMap != null) {
	    	for(CartItem cartItem : cartItemMap.values()) {
	    		cartItem.setUnitPrice(cartItem.getUnitPrice() * (100 - discount) / 100);;
	    		cartItem.setCart(cart);
	    		cartItem.setId(RandomUserUID.getRandomID());
				cartItemService.insert(cartItem);
	    	}
	    	sendEmail.sendMail(cart.getBuyer().getEmail(), "Fruits Vegetable", "Payment success. We will contact you soon ! ");
	    }
	    session.removeAttribute("cart");
	    session.removeAttribute("discount");
	    for (Cookie cookie : cookies) {
	    	cookie.setMaxAge(0);
	    	resp.addCookie(cookie);
        }
	    Cookie cookie = new Cookie("cartItem", null);
	    cookie.setMaxAge(0);
	    cookie.setPath("/");
	    resp.addCookie(cookie);
	    resp.sendRedirect("redirect.jsp");
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}

}
