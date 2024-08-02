package com.haui.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.constant.Path;
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

@WebServlet(urlPatterns = "/product")
public class ProductDetailController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("detail")) {
			String id = req.getParameter("id");
			Product product = productService.get(Integer.parseInt(id));
			List<Product> relatedProducts = productService.seachByCategory(product.getCategory().getId());
			req.setAttribute("product", product);
			req.setAttribute("relatedProducts", relatedProducts);
			RequestDispatcher dispatcher = req.getRequestDispatcher(Path.PRODUCT_DETAIL);
			dispatcher.forward(req, resp);
		}
		else if(action != null && action.equals("add")) {
			// Lấy giá trị của cookie "cartItem" nếu có
			String pId = req.getParameter("pId");
		    String quantity = req.getParameter("quantity");
		    Product product = productService.get(Integer.parseInt(pId));
		    CartItem cartItem = new CartItem();
		    cartItem.setQuantity(Integer.parseInt(quantity));
		    cartItem.setUnitPrice(product.getPrice());
		    cartItem.setProduct(product);

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

		    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
		    if (cartItemMap.containsKey(product.getId())) {
		        // Nếu có, cập nhật số lượng
		        CartItem existingCartItem = cartItemMap.get(product.getId());
		        existingCartItem.setQuantity(existingCartItem.getQuantity() + Integer.parseInt(quantity));
		    } else {
		        // Nếu không, thêm sản phẩm mới vào giỏ hàng
		        cartItemMap.put(product.getId(), cartItem);
		    }

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

		    // Sau khi thêm hoặc cập nhật cookie, điều hướng đến trang redirect.jsp
		    resp.sendRedirect("redirect.jsp");


		}
		else {
			 resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
		}
	}

}
