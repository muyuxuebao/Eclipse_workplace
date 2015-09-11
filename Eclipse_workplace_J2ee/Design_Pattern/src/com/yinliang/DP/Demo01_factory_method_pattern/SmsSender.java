package com.yinliang.DP.Demo01_factory_method_pattern;

public class SmsSender implements Sender{

	@Override
	public void Send() {
		// TODO Auto-generated method stub
		System.out.println("this is sms sender!");  
	}

}
