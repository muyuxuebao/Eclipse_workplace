package com.yinliang.DP.Demo09_Facade;

public class CPU implements Compent{

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		System.out.println("cpu startup!");  
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		System.out.println("cpu shutdown!");  
	}
	
}
