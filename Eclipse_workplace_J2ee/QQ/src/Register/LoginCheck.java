package Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginCheck {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String username;
	String userpassword;
	boolean flag = false;
	private String name;
	private String password;

	public LoginCheck(String name, String password) {
		this.name = name;
		this.password = password;
	}

	//验证用户名和密码 
	public boolean validate() {
		//		try {
		//			String url = "jdbc:mysql://localhost:3306/test";
		//			Class.forName("com.mysql.jdbc.Driver");
		//			conn = DriverManager.getConnection(url, "root", "123456");
		//			ps = conn.prepareStatement("select * from jdbctest");
		//			rs = ps.executeQuery();
		//			while (rs.next()) {
		//				username = rs.getString(2);
		//				userpassword = rs.getString(3);
		//				if (username.equals(name) && userpassword.equals(password)) {// 判断用户名和密码是否正确
		//					flag = true;
		//					break;
		//				}
		//			}
		//			rs = ps.executeQuery();
		//		} catch (ClassNotFoundException e) {
		//			System.out.println("找不到驱动类!");
		//		} catch (SQLException e) {
		//			System.out.println("连接 Mysql 数据库失败!");
		//		} finally {
		//			close();
		//		}
		return true;

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