package com.yinliang.DP.Demo08_Proxy;

public class Proxy implements Sourceable {
	private Source source = null;

	public Proxy() {
		// TODO Auto-generated constructor stub
		this.source = new Source();
	}

	@Override
	public void method() {
		// TODO Auto-generated method stub
		before();
		source.method();
		atfer();
	}

	private void atfer() {
		System.out.println("after proxy!");
	}

	private void before() {
		System.out.println("before proxy!");
	}

}
