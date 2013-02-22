package com.ambow.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.entity.User;
import com.ambow.service.UserService;
import com.ambow.service.impl.UserServiceImpl;

public class RegistServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 方法重写/覆盖,访问权限不能更小，抛出异常不能更大
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserServiceImpl();
		// 接收用户 的注册信息
		User user = generateUser(request);
		// 调用业务方法进行注册
		us.regist(user);
		// rquest.getContextPath()返回的是 工程名
		// 页面跳转到 登陆页面
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	private User generateUser(HttpServletRequest request)
			throws UnsupportedEncodingException {
		User user = new User();
		request.setCharacterEncoding("utf-8");
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setNickName(request.getParameter("nickName"));
		return user;
	}
}
