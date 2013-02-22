package com.ambow.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.entity.Comment;
import com.ambow.entity.MicroBlog;
import com.ambow.entity.User;
import com.ambow.service.BlogService;
import com.ambow.service.impl.BlogServiceImpl;

public class PublishComment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 从请求中获取评论信息
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		String blogIdStr = request.getParameter("blogId");
		long blogId = Long.parseLong(blogIdStr);
		HttpSession session = request.getSession();
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCommentTime(new Date());
		comment.setUser((User) session.getAttribute("userInfo"));
		MicroBlog blog = new MicroBlog();
		blog.setId(blogId);
		comment.setTargetBlog(blog);
		// 调用业务方法发布评论
		BlogService bs = new BlogServiceImpl();
		bs.generateComment(comment);
		// 页面跳转
		response.sendRedirect(request.getContextPath()
				+ "/FindCommentsList?blogId=" + blogId);
	}
}
