package com.ambow.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.entity.Comment;
import com.ambow.service.BlogService;
import com.ambow.service.impl.BlogServiceImpl;

/**
 * ����Χ����Ϣ��id���������б�
 * 
 * @author Administrator
 * 
 */
public class FindCommentsList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BlogService bs = new BlogServiceImpl();
		// �������л�ȡ Χ��id
		String blogIdStr = request.getParameter("blogId");
		long blogId = Long.parseLong(blogIdStr);
		List<Comment> comments = bs.findCommentByBlogId(blogId);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/commentsList.jsp").forward(request,
				response);
	}
}
