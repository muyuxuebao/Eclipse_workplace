package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;

//使用@Service注解 ,将 UserService标注为一个服务层的Bean
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userName, String password) {
		int matchCount = this.userDao.getMatchCount(userName, password);
		return matchCount > 0;
	}

	public User findUserByUserName(String userName) {
		return this.userDao.findUserByUserName(userName);
	}

	public void loginSuccess(User user) {
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		this.loginLogDao.insertLoginLog(loginLog);
	}

}
