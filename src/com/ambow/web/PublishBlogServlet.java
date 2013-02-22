package com.ambow.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.entity.MicroBlog;
import com.ambow.entity.User;
import com.ambow.service.BlogService;
import com.ambow.service.impl.BlogServiceImpl;

public class PublishBlogServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BlogService bs = new BlogServiceImpl();
		// 获取要发布的围脖信息
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		MicroBlog blog = new MicroBlog();
		blog.setContent(content);
		blog.setPublishTime(new Date());
		blog.setUser((User) session.getAttribute("userInfo"));
		// 调用业务方法 进行发布
		bs.publishBlog(blog);
		// 页面跳转
		response.sendRedirect(request.getContextPath() + "/FindNewerBlog");
	}
}
