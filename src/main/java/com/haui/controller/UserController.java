package com.haui.controller;

import java.io.IOException;

import com.haui.constant.Path;
import com.haui.constant.SessionAttr;
import com.haui.entity.User;
import com.haui.service.UserService;
import com.haui.service.Impl.UserServiceImpl;
import com.haui.tools.sendEmail;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login", "/logout", "/register"})
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case  "/login":
			doGetLogin(req, resp);
			break;
		case "/register":
			doGetRegister(req, resp);
			break;
		case "/logout":
			doGetLogout(session, req, resp);
			break;
		}
	}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			String path = req.getServletPath();
			switch (path) {
			case "/login":
				doPostLogin(session, req, resp);
				break;
			case "/register":
				doPostRegister(session, req, resp);
				break;
			}
		}
		
		private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			RequestDispatcher dispatcher = req.getRequestDispatcher(Path.LOGIN);
			dispatcher.forward(req, resp);
		}
		
		private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			RequestDispatcher dispatcher = req.getRequestDispatcher(Path.REGISTER);
			dispatcher.forward(req, resp);
		}
		
		private void doGetLogout(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			session.removeAttribute(SessionAttr.CURRENT_USER);
			resp.sendRedirect("index");
		}
		
		
		private void doPostLogin(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User user = userService.login(username, password);
			if(user != null) {
				session.setAttribute(SessionAttr.CURRENT_USER, user);
				if(user.isAdmin() == false) {
					resp.sendRedirect("index");
				} else {
					resp.sendRedirect("admin");
				}
			}
			else {
				resp.sendRedirect("login");
			}
		}
		
		private void doPostRegister(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email = req.getParameter("email");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			if(userService.checkExistEmail(email)) {
				String alertMsg = "Username already exist!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Path.REGISTER).forward(req, resp);
				return;
			}
			User user =  userService.register(email, username, password);
			if(user != null) {
				sendEmail.sendMail(email, "Fruits Vegetable", "Welcome to Fruits Vegetable."
						+ " Wish you have a great experience on our website. Thanks !");
				session.setAttribute(SessionAttr.CURRENT_USER, user);
				resp.sendRedirect("login");
			}
			else {
				resp.sendRedirect("register");
			}
		}

}
