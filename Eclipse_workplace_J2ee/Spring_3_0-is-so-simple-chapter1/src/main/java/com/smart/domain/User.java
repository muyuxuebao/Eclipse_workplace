package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private int userId;

	private String userName;

	private String password;

	private String lastIp;

	private Date lastVisit;

	public String getLastIp() {
		return this.lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return this.lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
