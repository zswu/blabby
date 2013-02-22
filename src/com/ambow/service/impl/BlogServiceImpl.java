package com.ambow.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ambow.entity.Comment;
import com.ambow.entity.MicroBlog;
import com.ambow.entity.User;
import com.ambow.service.BlogService;
import com.ambow.util.*;

import java.sql.*;

public class BlogServiceImpl implements BlogService {
	private static final String PUBLISHBLOG_SQL = "insert into microBlog(content,publishTime,userId) values(?,?,?)";

	private static final String GENERATECOMMENT_SQL = "insert into comment(content,commentTime,userId,blogId) values(?,?,?,?)";

	private static final String FINDNEWERBLOG_SQL = "select m.id,content,publishTime,u.id,u.nickName,u.email  from microBlog m join user u on m.userId=u.id where (to_days(now()) - to_days(publishTime))<7 and (userId=? or userId in (select fuserId from friend where userId=?)) order by publishTime desc";

	private static final String FINDCOMMENTBYBLOGID_SQL = "select c.id,c.content,c.commentTime,u.id,u.nickName,u.email from comment c join user u on c.userId=u.id where blogId=? order by commentTime desc";

	private static final String FINDBLOG_SQL = "select m.id,content,publishTime,u.id,u.nickName,u.email  from microBlog m join user u on m.userId=u.id where userId=? and content like ? limit ?,?";

	private static final String FINDBLOGMAXPAGE_SQL = "select count(*) from microBlog where userId=? and content like ?";

	private static final String GETCOMMENTNUM_SQL = "select count(*) from comment where blogId=?";

	/**
	 * 发布围脖消息，往microBlog中插入一条记录
	 */
	public void publishBlog(MicroBlog blog) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(PUBLISHBLOG_SQL);
			ps.setString(1, blog.getContent());
			// datetime类型需要使用ps.setTimestamp();
			ps.setTimestamp(2, new Timestamp(blog.getPublishTime().getTime()));
			ps.setLong(3, blog.getUser().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出一个运行时异常，会导致程序直接终止
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
	}

	/**
	 * 发布评论，往comment表中插入一条记录
	 */
	public void generateComment(Comment comment) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(GENERATECOMMENT_SQL);
			ps.setString(1, comment.getContent());
			ps.setTimestamp(2,
					new Timestamp(comment.getCommentTime().getTime()));
			ps.setLong(3, comment.getUser().getId());
			ps.setLong(4, comment.getTargetBlog().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出一个运行时异常，会导致程序直接终止
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
	}

	public List<MicroBlog> findNewerBlog(long userId) {
		List<MicroBlog> blogs = new ArrayList<MicroBlog>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDNEWERBLOG_SQL);
			ps.setLong(1, userId);
			ps.setLong(2, userId);
			rs = ps.executeQuery();
			dealBlogsRs(rs, blogs);
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出一个运行时异常，会导致程序直接终止
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
		return blogs;
	}

	private void dealBlogsRs(ResultSet rs, List<MicroBlog> blogs)
			throws SQLException {
		while (rs.next()) {
			long id = rs.getLong(1);
			String content = rs.getString(2);
			Date publishTime = new java.sql.Date(rs.getTimestamp(3).getTime());
			long userId = rs.getLong(4);
			String nickName = rs.getString(5);
			String email = rs.getString(6);
			MicroBlog blog = new MicroBlog();
			blog.setId(id);
			blog.setContent(content);
			blog.setPublishTime(publishTime);

			User user = new User();
			user.setId(userId);
			user.setNickName(nickName);
			user.setEmail(email);
			blog.setUser(user);
			blogs.add(blog);
		}
	}

	public List<Comment> findCommentByBlogId(long blogId) {
		List<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDCOMMENTBYBLOGID_SQL);
			ps.setLong(1, blogId);
			rs = ps.executeQuery();
			dealCommentsRs(rs, comments);
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出一个运行时异常，会导致程序直接终止
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
		return comments;
	}

	private void dealCommentsRs(ResultSet rs, List<Comment> comments)
			throws SQLException {
		while (rs.next()) {
			long id = rs.getLong(1);
			String content = rs.getString(2);
			Date commentTime = new Date(rs.getTimestamp(3).getTime());
			long userId = rs.getLong(4);
			String nickName = rs.getString(5);
			String email = rs.getString(6);
			Comment c = new Comment();
			c.setId(id);
			c.setContent(content);
			c.setCommentTime(commentTime);
			User user = new User();
			user.setId(userId);
			user.setEmail(email);
			user.setNickName(nickName);
			c.setUser(user);
			comments.add(c);
		}
	}

	public List<MicroBlog> findBlog(long userId, Condition condition) {
		Pagination pagination = condition.getPagination();
		int currentPage = pagination.getCurrentPage();
		int perPage = pagination.getPerPage();
		int n = (currentPage - 1) * perPage;
		int m = perPage;
		List<MicroBlog> blogs = new ArrayList<MicroBlog>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDBLOG_SQL);
			ps.setLong(1, userId);
			ps.setString(2, "%" + condition.getBlogKeyWord() + "%");
			ps.setInt(3, n);
			ps.setInt(4, m);
			rs = ps.executeQuery();
			dealBlogsRs(rs, blogs);
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出一个运行时异常，会导致程序直接终止
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
		return blogs;
	}

	public int findBlogMaxPage(long userId, Condition condition) {
		int maxPage = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDBLOGMAXPAGE_SQL);
			ps.setLong(1, userId);
			ps.setString(2, "%" + condition.getBlogKeyWord() + "%");
			rs = ps.executeQuery();
			rs.next();
			int totalRecords = rs.getInt(1);
			int perPage = condition.getPagination().getPerPage();
			maxPage = totalRecords % perPage == 0 ? totalRecords / perPage
					: totalRecords / perPage + 1;
			maxPage = maxPage < 1 ? 1 : maxPage;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return maxPage;
	}

	public int getCommentNum(long blogId) {
		int num = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(GETCOMMENTNUM_SQL);
			ps.setLong(1, blogId);
			rs = ps.executeQuery();
			rs.next();
			num = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return num;
	}
}
