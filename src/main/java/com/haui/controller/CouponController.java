package com.haui.controller;

import java.io.IOException;

import com.haui.constant.Path;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/applyCoupon")
public class CouponController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String couponCode = req.getParameter("couponCode");
		
		if(couponCode.equalsIgnoreCase("yenthan") && couponCode != null) {
			// Lưu vào session
	        session.setAttribute("discount", 15);
		} else {
			session.setAttribute("discount", 0);
		}
        // Chuyển hướng về trang hiện tại hoặc trang xác nhận
        RequestDispatcher dispatcher = req.getRequestDispatcher(Path.CHECKOUT);
        dispatcher.forward(req, resp);
	}
}
