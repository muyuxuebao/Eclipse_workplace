package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
	private int loginLogId;

	private int userId;

	private String ip;

	private Date loginDate;

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public int getLoginLogId() {
		return this.loginLogId;
	}

	public void setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
