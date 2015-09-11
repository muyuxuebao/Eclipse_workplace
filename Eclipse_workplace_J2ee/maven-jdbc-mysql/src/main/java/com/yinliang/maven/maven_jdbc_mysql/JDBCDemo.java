package com.yinliang.maven.maven_jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

	}

	public boolean openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ects?characterEncoding=UTF-8";
			Connection conn = DriverManager.getConnection(url, "root", "123456");

			String sql = "SELECT * FROM product";

			Statement stm = conn.createStatement();
			stm.execute(sql);

			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  ------  " + rs.getString(2));
			}

			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
