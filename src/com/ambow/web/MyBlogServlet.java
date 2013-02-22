package com.ambow.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.entity.MicroBlog;
import com.ambow.entity.User;
import com.ambow.service.BlogService;
import com.ambow.service.UserService;
import com.ambow.service.impl.BlogServiceImpl;
import com.ambow.service.impl.UserServiceImpl;
import com.ambow.util.Condition;

/**
 * 用于显示 自己，或者他人的 '我的围脖' 界面
 * 
 * @author Administrator
 * 
 */
public class MyBlogServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserServiceImpl();
		BlogService bs = new BlogServiceImpl();
		String userIdStr = request.getParameter("userId");
		long userId = Long.parseLong(userIdStr);
		// 根据userId，查询对应用户的 个人信息
		User user = us.findUserById(userId);
		// 根据userId，查询对应用户的 所有围脖信息
		Condition condition = new Condition();
		List<MicroBlog> blogs = bs.findBlog(userId, condition);
		for (int i = 0; i < blogs.size(); i++) {
			MicroBlog blog = blogs.get(i);
			int commentNum = bs.getCommentNum(blog.getId());
			blog.setCommentNum(commentNum);
		}
		System.out.println("size:" + blogs.size());
		request.setAttribute("user", user);
		request.setAttribute("blogs", blogs);
		request.getRequestDispatcher("/myBlog.jsp").forward(request, response);
	}
}
