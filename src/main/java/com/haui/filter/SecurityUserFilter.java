package com.haui.filter;

import java.io.IOException;

import com.haui.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SecurityUserFilter
 */
@WebFilter(urlPatterns = { "/account", "/checkout", "/applyCoupon", "/finalOder", "/order", "/updateAccountServlet" })
public class SecurityUserFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public SecurityUserFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resq = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("currentUser");
		User user = (User) obj;
		if (obj != null) {
			chain.doFilter(request, response);
			return; //
		} else {
			resq.sendRedirect(req.getContextPath() + "/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
