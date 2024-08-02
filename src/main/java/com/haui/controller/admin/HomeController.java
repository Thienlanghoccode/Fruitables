package com.haui.controller.admin;

import java.io.IOException;
import java.util.List;

import com.haui.dto.CartDetails;
import com.haui.service.CartDetailsService;
import com.haui.service.Impl.CartDetailsServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin", name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CartDetailsService cartDetailsService = new CartDetailsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CartDetails> cartDetailList = cartDetailsService.getAll();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/index.jsp");
		req.setAttribute("cartDetailList", cartDetailList);
		dispatcher.forward(req, resp);
	}
}
