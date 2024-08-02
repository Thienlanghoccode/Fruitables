package com.haui.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haui.entity.User;
import com.haui.service.UserService;
import com.haui.service.Impl.UserServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/account")
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = userService.getAll();
		req.setAttribute("userlist", users);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/account.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Id = req.getParameter("Id");
		User user = userService.get(Integer.parseInt(Id));
		// Trả về thông tin người dùng dưới dạng JSON
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter out = resp.getWriter();
	    
	    ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        out.write(json);
        out.close();
	}

}
