package com.ambow.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.entity.MicroBlog;
import com.ambow.entity.User;
import com.ambow.service.BlogService;
import com.ambow.service.impl.BlogServiceImpl;

/**
 * 获取最新的围脖列表
 * 
 * @author Administrator
 * 
 */
public class FindNewerBlog extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setContentType("gbk");
		BlogService bs = new BlogServiceImpl();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		List<MicroBlog> blogs = bs.findNewerBlog(user.getId());
		for (int i = 0; i < blogs.size(); i++) {
			MicroBlog blog = blogs.get(i);
			int commentNum = bs.getCommentNum(blog.getId());
			blog.setCommentNum(commentNum);
		}
		System.out.println(blogs.size());
		request.setAttribute("newerBlogs", blogs);
		request.getRequestDispatcher("/first.jsp").forward(request, response);
	}
}
