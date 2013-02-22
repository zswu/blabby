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

	// ������д/����,����Ȩ�޲��ܸ�С���׳��쳣���ܸ���
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserServiceImpl();
		// �����û� ��ע����Ϣ
		User user = generateUser(request);
		// ����ҵ�񷽷�����ע��
		us.regist(user);
		// rquest.getContextPath()���ص��� ������
		// ҳ����ת�� ��½ҳ��
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
