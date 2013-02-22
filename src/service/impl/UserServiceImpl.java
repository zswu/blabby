package service.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Address;
import entity.User;
import service.UserService;
import util.Condition;
import util.JdbcUtil;
import util.Pagination;

public class UserServiceImpl implements UserService{

	private static final String REGIST_SQL=" insert into user(email,password,nickName) values(?,?,?)";
	private static final String LOGIN_SQL="select u.id,u.email,u.password,u.nickName,u.accessAddress,a.province,a.city from user u left join address a on u.id=a.userId where u.password=? and u.email=? ";
	private static final String UPDATE_SQL="update user set password=?,nickName=?,accessAddress=? where id=?";
	private static final String UPDATE_SQL2_1="update address set province=?,city=? where userId=?";
	private static final String UPDATE_SQL2_2="insert into address(province,city,userId) values(?,?,?)";
	private static final String ADDFRIEND_SQL="insert into friend(userId,fuserId) values(?,?)";
	private static final String DELETEFRIEND_SQL="delete from friend where userId=? and fuserId=?";
	private static final String FINDUSERBYID_SQL = "select u.id,u.email,u.password,u.nickName,u.accessAddress,a.province,a.city from user u left join address a on u.id=a.userId where u.id=?";
	private static final String FINDFRIEND_SQL = "select u.id,u.email,u.nickName from friend f join user u on f.fuserId=u.id  where f.userId=? limit ?,?";
	private static final String FINDUSERBYCONDITION_SQL="select id,email,nickName from user where nickName like ? limit ?,?";
	
	
	public void addFriend(long userId, long fuserId) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(ADDFRIEND_SQL);
			ps.setLong(1, userId);
			ps.setLong(2,fuserId);
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

	public void deleteFriend(long userId, long fuserId) {
		
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(DELETEFRIEND_SQL);
			ps.setLong(1, userId);
			ps.setLong(2,fuserId);
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

	public List<User> findFriend(long userId, Condition condition) {
	
		Pagination pagination=condition.getPagination();
		int current=pagination.getIntcurrentPage();
		int perPage=pagination.getPerPage();
		int n=(current-1)*perPage;
		int m=perPage;
		
		List<User> users=new ArrayList<User>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(FINDFRIEND_SQL);
			ps.setLong(1, userId);
			ps.setInt(2, n);
			ps.setInt(3, m);
			rs=ps.executeQuery();
			dealListRs(rs,users);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	private void dealListRs(ResultSet rs,List<User> users) throws SQLException
	{
		while(rs.next())
		{
			long id=rs.getLong(1);
			String email=rs.getString(2);
			String nickName=rs.getString(3);
			User user=new User();
			user.setId(id);
			user.setEmail(email);
			user.setNickName(nickName);
			users.add(user);
		}
	}
/**
 * 模糊查找朋友
 */
	public List<User> findUserByCondition(Condition condition) {
		
		String nick = condition.getNickName();
		if (nick == null)
			nick = "";
		Pagination pagination = condition.getPagination();
		// 代表 要查找第 current 页的信息
		int current = pagination.getIntcurrentPage();
		// 代表 每页 perPage条信息
		int perPage = pagination.getPerPage();
		int n = (current - 1) * perPage;
		int m = perPage;
		
		List<User> users=new ArrayList<User>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(FINDUSERBYCONDITION_SQL);
			ps.setString(1, "%"+nick+"%");
			ps.setInt(2, n);
			ps.setInt(3, m);
			rs=ps.executeQuery();
			dealListRs(rs,users);
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
		
		return users;
	}
/**
 * 查找朋友
 */
	public User findUserById(long id) {
		User user=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(FINDUSERBYID_SQL);
			ps.setLong(1, id);
			rs=ps.executeQuery();
			dealLoginRs(rs,user);
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
		return user;
	}
	


	/**
	 * 登录
	 */
	public User login(String email, String password) {
	
		User user=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(LOGIN_SQL);
			ps.setString(1, email);
			ps.setString(2, password);
			rs=ps.executeQuery();
			dealLoginRs(rs,user);
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
		return user;
	}
	
	private void dealLoginRs(ResultSet rs,User user) throws SQLException
	{
		if(rs.next())
		{
			user=new User();
			long id=rs.getLong(1);
			String email=rs.getString(2);
			String password=rs.getString(3);
			String nickName=rs.getString(4);
			String accessAddress=rs.getString(5);
			String province=rs.getString(6);
			String city=rs.getString(7);
			user.setId(id);
			user.setEmail(email);
			user.setPassword(password);
			user.setNickName(nickName);
			user.setAccessAddress(accessAddress);
			Address address=new Address();
			address.setProvice(province);
			address.setCity(city);
			user.setAddress(address);
		}
	}

	
	/**
	 * 注册
	 */
	public long regist(User user) {
		
		long id = -1;
	     
	    Connection conn=null;
	    PreparedStatement ps=null;
	    ResultSet rs=null;
	    
	    try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(REGIST_SQL);
			ps.setString(1, user.getEmail());
			ps.setString(2,user.getPassword());
			ps.setString(3, user.getNickName());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			rs.next();
			id=rs.getLong(1);
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
				JdbcUtil.close(rs, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * 修改个人信息
	 */
	public void update(User user) {
	   
	    Connection conn=null;
	    PreparedStatement ps1=null;
	    PreparedStatement ps2=null;
	    PreparedStatement ps3=null;
	    ResultSet rs=null;
	    
	    try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps1=conn.prepareStatement(UPDATE_SQL);
			ps2=conn.prepareStatement("select id from address where userId=?");
			ps2.setLong(1, user.getId());
			rs=ps2.executeQuery();
			if(rs.next())
			{
				//地址存在,更新操作
				ps3=conn.prepareStatement(UPDATE_SQL2_1);
			}else
			{
                //地址不存在，插入操作
				ps3=conn.prepareStatement(UPDATE_SQL2_2);
			}
			
			ps3.setString(1, user.getAddress().getProvince());
			ps3.setString(2, user.getAddress().getCity());
			ps3.setLong(3,user.getId());
			
			ps1.setString(1,user.getPassword());
			ps1.setString(2, user.getNickName());
			ps1.setString(3, user.getAccessAddress());
			ps1.setLong(4, user.getId());
			
			ps3.executeUpdate();
			ps1.executeUpdate();
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
				JdbcUtil.close(null, ps1, null);
				JdbcUtil.close(null,ps2,null);
				JdbcUtil.close(rs,ps3,conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    }

}
