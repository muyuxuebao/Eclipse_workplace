package com.yinliang.DP.Demo10_Bridge;

public class MyBridge extends Bridge {
	public void method() {
		this.getSource().method();
	}
}
