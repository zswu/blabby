package com.ambow.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ambow.entity.Address;
import com.ambow.entity.User;
import com.ambow.service.UserService;
import com.ambow.util.Condition;
import com.ambow.util.JdbcUtil;
import com.ambow.util.Pagination;

import java.sql.*;

public class UserServiceImpl implements UserService {
	private static final String REGIST_SQL = "insert into user(email,password,nickName) values(?,?,?)";

	private static final String LOGIN_SQL = "select u.id,u.email,u.password,u.nickName,u.accessAddress,"
			+ "a.province,a.city "
			+ "from user u left join address a on u.id=a.userId "
			+ "where u.email=? and u.password=?";

	private static final String UPDATE_SQL1 = "update user set password=?,nickName=?,accessAddress=? where id=?";

	private static final String UPDATE_SQL2_1 = "update address set province=?,city=? where userId=?";

	private static final String UPDATE_SQL2_2 = "insert into address(province,city,userId) values(?,?,?)";

	private static final String ADDFRIEND_SQL = "insert into friend(userId,fuserId) values(?,?)";

	private static final String DELETEFRIEND_SQL = "delete from friend where userId=? and fuserId=?";

	private static final String FINDUSERBYID_SQL = "select u.id,u.email,u.password,u.nickName,u.accessAddress,a.province,a.city from user u left join address a on u.id=a.userId where u.id=?";

	private static final String FINDFRIEND_SQL = "select u.id,u.email,u.nickName from friend f join user u on f.fuserId=u.id  where f.userId=? limit ?,?";

	private static final String FINDUSERBYCONDITION_SQL = "select id,email,nickName from user where nickName like ? limit ?,?";

	public long regist(User user) {
		long id = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(REGIST_SQL);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickName());
			ps.executeUpdate();
			// id
			rs = ps.getGeneratedKeys();
			rs.next();
			id = rs.getLong(1);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return id;
	}

	public User login(String email, String pwd) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(LOGIN_SQL);
			ps.setString(1, email);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			user = dealUserRs(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return user;
	}

	private User dealUserRs(ResultSet rs) throws SQLException {
		User user = null;
		if (rs.next()) {
			user = new User();
			long id = rs.getLong(1);
			String email = rs.getString(2);
			String password = rs.getString(3);
			String nickName = rs.getString(4);
			String accessAddress = rs.getString(5);
			String province = rs.getString(6);
			String city = rs.getString(7);

			user.setId(id);
			user.setEmail(email);
			user.setPassword(password);
			user.setNickName(nickName);
			user.setAccessAddress(accessAddress);

			Address a = new Address();
			a.setProvince(province);
			a.setCity(city);
			user.setAddress(a);
		}
		return user;
	}

	/**
	 * user封装的属性:id,email,password,nickName,name,gender,accessAddress,bornDate,
	 * province,city
	 */
	public void update(User user) {
		Connection conn = null;
		// 更新用户信息
		PreparedStatement ps1 = null;
		// 查询
		PreparedStatement ps2 = null;
		// 更新address或者 插入 address
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(UPDATE_SQL1);
			ps2 = conn
					.prepareStatement("select id from address where userId=?");
			ps2.setLong(1, user.getId());
			rs = ps2.executeQuery();
			if (rs.next()) {
				// 有地址,更新操作
				ps3 = conn.prepareStatement(UPDATE_SQL2_1);
			} else {
				// 没有地址,插入操作
				ps3 = conn.prepareStatement(UPDATE_SQL2_2);
			}
			ps1.setString(1, user.getPassword());
			ps1.setString(2, user.getNickName());
			ps1.setString(3, user.getAccessAddress());
			ps1.setLong(4, user.getId());

			ps3.setString(1, user.getAddress().getProvince());
			ps3.setString(2, user.getAddress().getCity());
			ps3.setLong(3, user.getId());

			ps1.executeUpdate();
			ps3.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JdbcUtil.close(null, ps1, null);
			JdbcUtil.close(null, ps2, null);
			JdbcUtil.close(rs, ps3, conn);
		}
	}

	public void addFriend(long userId, long fuserId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(ADDFRIEND_SQL);
			ps.setLong(1, userId);
			ps.setLong(2, fuserId);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
	}

	public void deleteFriend(long userId, long fuserId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(DELETEFRIEND_SQL);
			ps.setLong(1, userId);
			ps.setLong(2, fuserId);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(null, ps, conn);
		}
	}

	public User findUserById(long id) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDUSERBYID_SQL);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			user = dealUserRs(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return user;
	}

	public List<User> findFriend(long userId, Condition condition) {

		Pagination pagination = condition.getPagination();
		// 代表 要查找第 current 页的信息
		int current = pagination.getCurrentPage();
		// 代表 每页 perPage条信息
		int perPage = pagination.getPerPage();
		int n = (current - 1) * perPage;
		int m = perPage;

		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDFRIEND_SQL);
			ps.setLong(1, userId);
			ps.setInt(2, n);
			ps.setInt(3, m);
			rs = ps.executeQuery();
			dealUserListRs(rs, users);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return users;
	}

	private void dealUserListRs(ResultSet rs, List<User> users)
			throws SQLException {
		while (rs.next()) {
			long id = rs.getLong(1);
			String email = rs.getString(2);
			String nickName = rs.getString(3);
			User user = new User();
			user.setId(id);
			user.setEmail(email);
			user.setNickName(nickName);
			users.add(user);
		}
	}

	public List<User> findUserByCondition(Condition condition) {
		String nick = condition.getNickName();
		if (nick == null)
			nick = "";
		Pagination pagination = condition.getPagination();
		// 代表 要查找第 current 页的信息
		int current = pagination.getCurrentPage();
		// 代表 每页 perPage条信息
		int perPage = pagination.getPerPage();
		int n = (current - 1) * perPage;
		int m = perPage;
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(FINDUSERBYCONDITION_SQL);
			ps.setString(1, "%" + nick + "%");
			ps.setInt(2, n);
			ps.setInt(3, m);
			rs = ps.executeQuery();
			dealUserListRs(rs, users);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return users;
	}

	public int getFriendMaxPage(long userId, Condition condition) {
		int maxPage = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn
					.prepareStatement("select count(*) from friend where userId=?");
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			rs.next();
			int totalRecords = rs.getInt(1);
			int perPage = condition.getPagination().getPerPage();
			maxPage = totalRecords % perPage == 0 ? totalRecords / perPage
					: totalRecords / perPage + 1;
			maxPage = maxPage < 1 ? 1 : maxPage;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return maxPage;
	}

	public int getUserMaxPage(Condition condition) {
		int maxPage = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn
					.prepareStatement("select count(*) from user where nickName like ?");
			String nick = condition.getNickName();
			if (nick == null)
				nick = "";
			ps.setString(1, "%" + nick + "%");
			rs = ps.executeQuery();
			rs.next();
			int totalRecords = rs.getInt(1);
			int perPage = condition.getPagination().getPerPage();
			maxPage = totalRecords % perPage == 0 ? totalRecords / perPage
					: totalRecords / perPage + 1;
			maxPage = maxPage < 1 ? 1 : maxPage;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return maxPage;
	}

}
