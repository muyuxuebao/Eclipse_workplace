package com.yinliang.DP.Demo06_Adapter.e2;

public class Test {
	public static void main(String[] args) {
		Targetable targetable=new Wrapper(new Source());
		targetable.method1();
		targetable.method2();
	}
}
