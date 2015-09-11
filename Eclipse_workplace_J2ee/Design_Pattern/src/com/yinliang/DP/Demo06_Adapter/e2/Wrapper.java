package com.yinliang.DP.Demo06_Adapter.e2;

public class Wrapper implements Targetable {
	private Source source = null;

	public Wrapper(Source source) {
		this.source = source;
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		this.source.method1();
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("this is the targetable method!");

	}
}
