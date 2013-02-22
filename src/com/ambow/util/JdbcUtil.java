package com.ambow.util;

import java.sql.*;

public class JdbcUtil {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://arlia.computing.dundee.ac.uk:3306/zhongshanwu";
		String username = "ZhongshanWu";
		String password = "ac31004";
		Connection conn = null;
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	/**
	 * 关闭数据库连接的一些对象
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 * @throws Exception
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
