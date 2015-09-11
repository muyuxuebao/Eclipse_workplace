package com.yinliang.netty_3_5.Object_Transmit;

import java.io.Serializable;

public class Command implements Serializable {
	private static final long serialVersionUID = 7590999461767050471L;
	private String actionName;

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
