package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smart.domain.LoginLog;

//使用了@Repository注解后, Spring将在初始化的时候这个class的一个对象放入Spring容器中
@Repository
public class LoginLogDao {
	// 使用了 @Autowired表示 jdbcTemplate 是Spring容器的中的一个对象, Spring将自动初始化这个对象.
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertLoginLog(LoginLog loginLog) {
		String sqlStr = "INSERT INTO t_login_log(user_id,ip,login_datetime) " + "VALUES(?,?,?)";
		Object[] args = { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() };
		this.jdbcTemplate.update(sqlStr, args);
	}
}