package com.yinliang.DP.Demo06_Adapter.e1;

public class Test {
	public static void main(String[] args) {
		Targetable targetable=new Adapter();
		targetable.method1();
		targetable.method2();
	}
}
