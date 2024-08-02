package com.haui.controller;

import java.io.File;
import java.io.IOException;

import com.haui.entity.User;
import com.haui.service.UserService;
import com.haui.service.Impl.UserServiceImpl;
import com.haui.tools.ExtractFileName;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/updateAccountServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UpdateAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	private static final String SAVE_DIR = "Upload";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lấy đường dẫn upload
		String appPath = req.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;

		// Tạo thư mục nếu chưa tồn tại
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		// Xử lý file upload
		String fileName = null;
		for (Part part : req.getParts()) {
			if (part.getName().equals("avatar") && part.getSize() > 0) {
				fileName = ExtractFileName.extractFileName(part);
				part.write(savePath + File.separator + fileName);
			}
		}
		
		// Lấy thông tin từ form
        int id = Integer.parseInt(req.getParameter("id"));
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String avatar = fileName != null ? SAVE_DIR + "/" + fileName : req.getParameter("avatar");
        
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setActive(true);
        
        userService.edit(user);
        User u = userService.get(id);
		HttpSession session = req.getSession(true);
		session.removeAttribute("currentUser");
		session.setAttribute("currentUser", u);
		resp.sendRedirect(req.getContextPath() + "/redirect.jsp");
	}

}
