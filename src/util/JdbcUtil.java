package util;

import java.sql.*;

public class JdbcUtil {
	// �ھ�̬������ ��������
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����һ�����ݿ� ����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() 
			throws SQLException {
		String url = "jdbc:mysql://arlia.computing.dundee.ac.uk:3306/zhongshanwu";
		String username = "ZhongshanWu";
		String password = "ac31004";
		Connection conn = null;
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	/**
	 * �ر����ݿ����ӵ�һЩ����
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 * @throws Exception
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn)
			throws Exception {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}
}

