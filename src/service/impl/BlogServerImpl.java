package service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Comment;
import entity.MicroBlog;
import entity.User;
import service.BlogService;
import util.Condition;
import util.JdbcUtil;
import util.Pagination;


public class BlogServerImpl implements BlogService{
	
	private static final String PUBLICSHBLOG_SQL="insert into microblog(content,publishTime,userId) values(?,?,?)";
    private static final String GENERATECOMMENT_SQL="insert into comment(content,commentTime,userId,blogId)values(?,?,?,?)";
    //一周内的时间为：to_days(new()-publishTime)<7
	private static final String FINDNEWERBLOG_SQL="select id,content,publishTime from microblog where to_days(new()-publishTime)<7 and (userId=? or userId in (select fuserId from friend where userId=?))";
    private static final String FINDCOMMENTBYBLOGID_SQL="select id,content,commentTime from comment where blogId=?";
    private static final String FINDBLOG_SQL="select id,content,publishTime from microblog where userId=? ane content like ? limit ?,?";
    
	public List<MicroBlog> findBlog(long userId, Condition condition) {
		Pagination pagination = condition.getPagination();
		// 代表 要查找第 current 页的信息
		int current = pagination.getIntcurrentPage();
		// 代表 每页 perPage条信息
		int perPage = pagination.getPerPage();
		int n = (current - 1) * perPage;
		int m = perPage;
		
		List<MicroBlog> blogs=new ArrayList<MicroBlog>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(FINDBLOG_SQL);
			ps.setLong(1, userId);
			ps.setString(2, "%"+condition.getBlogKeyWord()+"%");
			ps.setInt(3, n);
			ps.setInt(4, m);
			rs=ps.executeQuery();
			dealBlogRs(rs,blogs);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
			try {
				JdbcUtil.close(rs, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return blogs;
	}
	
	


	
	
	
	public List<Comment> findCommentByBlogId(long blogId) {
		List<Comment> comments=new ArrayList<Comment>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(FINDCOMMENTBYBLOGID_SQL);
			ps.setLong(1, blogId);
			rs=ps.executeQuery();
			dealCommentRs(rs,comments);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				JdbcUtil.close(rs, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return comments;
	}
    
	private void dealCommentRs(ResultSet rs,List<Comment> comments) throws SQLException
	{
		while(rs.next())
		{
			long id=rs.getLong(1);
			String content=rs.getString(2);
			Date commentTime=rs.getDate(3);
			
			
			Comment comment=new Comment();
			comment.setId(id);
			comment.setContent(content);
			comment.setCommentTime(commentTime);
			
			comments.add(comment);
		}
	}
	
	
	
	
	
	public List<MicroBlog> findNewerBlog(long userId) {
		List<MicroBlog> blogs=new ArrayList<MicroBlog>();
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(FINDNEWERBLOG_SQL);
			ps.setLong(1, userId);
			ps.setLong(2, userId);
			rs=ps.executeQuery();
			dealBlogRs(rs,blogs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				JdbcUtil.close(rs, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return blogs;
	}

	private void dealBlogRs(ResultSet rs,List<MicroBlog> blogs) throws SQLException
	{
		while(rs.next())
		{
			long id=rs.getLong(1);
			String content=rs.getString(2);
			Date publishTime=rs.getDate(3);
			
			MicroBlog blog=new MicroBlog();
			blog.setContent(content);
			blog.setId(id);
			blog.setPublishTime(publishTime);
			
			blogs.add(blog);
		}
	}

	
	
	public void generateComment(Comment comment) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(GENERATECOMMENT_SQL);
			ps.setString(1, comment.getContent());
			ps.setDate(2, new java.sql.Date(comment.getCommentTime().getTime()));
			ps.setLong(3, comment.getUser().getId());
			ps.setLong(4, comment.getTargetBlog().getId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
		    try {
				conn.rollback();
			} catch (SQLException e1) {
            	e1.printStackTrace();
			}
			e.printStackTrace();
		}finally
		{
			try {
				JdbcUtil.close(null, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void publishBlog(MicroBlog blog) {
		User user=new User();
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(PUBLICSHBLOG_SQL);
			ps.setString(1, blog.getContent());
			ps.setDate(2, new java.sql.Date (blog.getPublishTime().getTime()));
			ps.setLong(3, user.getId());
			ps.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if(conn!=null)
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//抛出一个运行时异常，会导致程序直接终止
			throw new RuntimeException();
			
		}finally
		{
			try {
				JdbcUtil.close(null, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
