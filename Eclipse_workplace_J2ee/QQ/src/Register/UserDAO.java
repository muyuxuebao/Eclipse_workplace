package Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	static Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;
	boolean flag = false;

	public boolean addUser(String sql) {
		try {
			System.out.println("DAO: " + sql);
			String url = "jdbc:mysql://localhost:3306/test";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "123456");
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			flag = true;
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动类!");
		} catch (SQLException e) {
			System.out.println("连接 Mysql 数据库失败!");
		} finally {
			close();
		}
		return flag;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			System.out.println("数据库 close 异常");
		}
	}
}