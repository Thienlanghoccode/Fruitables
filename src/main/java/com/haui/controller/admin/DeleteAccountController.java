package com.haui.controller.admin;

import java.io.IOException;
import java.util.List;

import com.haui.entity.User;
import com.haui.service.UserService;
import com.haui.service.Impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/deleteaccount")
public class DeleteAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("Id");
		userService.delete(Integer.parseInt(id));
		List<User> users = userService.getAll();
		req.setAttribute("userlist", users);
		resp.sendRedirect(req.getContextPath() + "/admin/account");
	}

}
