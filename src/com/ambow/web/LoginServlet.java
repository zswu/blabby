package com.ambow.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.entity.User;
import com.ambow.service.UserService;
import com.ambow.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		UserService us = new UserServiceImpl();
		// 解析请求参数 email,password
		arg0.setCharacterEncoding("utf-8");
		String email = arg0.getParameter("email");
		String password = arg0.getParameter("password");
		// 调用业务方法进行登陆
		User user = us.login(email, password);
		// 判断登陆是否成功
		if (user != null) {
			// 登陆成功
			// 把用户信息放到session中，方便页面显示
			HttpSession session = arg0.getSession();
			session.setAttribute("userInfo", user);
			// 页面跳转到first.jsp
			arg1.sendRedirect(arg0.getContextPath() + "/FindNewerBlog");
			return;
		} else {
			// 登陆失败
			String msg = "登陆信息不正确";
			arg0.setAttribute("msg", msg);
			arg0.getRequestDispatcher("/login.jsp").forward(arg0, arg1);
			return;
		}
	}
}
