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
		// ����������� email,password
		arg0.setCharacterEncoding("utf-8");
		String email = arg0.getParameter("email");
		String password = arg0.getParameter("password");
		// ����ҵ�񷽷����е�½
		User user = us.login(email, password);
		// �жϵ�½�Ƿ�ɹ�
		if (user != null) {
			// ��½�ɹ�
			// ���û���Ϣ�ŵ�session�У�����ҳ����ʾ
			HttpSession session = arg0.getSession();
			session.setAttribute("userInfo", user);
			// ҳ����ת��first.jsp
			arg1.sendRedirect(arg0.getContextPath() + "/FindNewerBlog");
			return;
		} else {
			// ��½ʧ��
			String msg = "��½��Ϣ����ȷ";
			arg0.setAttribute("msg", msg);
			arg0.getRequestDispatcher("/login.jsp").forward(arg0, arg1);
			return;
		}
	}
}
