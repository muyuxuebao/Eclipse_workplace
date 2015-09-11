package com.yinliang.DP.Demo02_abstract_factory_pattern;

public class SmsSender implements Sender{

	@Override
	public void Send() {
		// TODO Auto-generated method stub
		System.out.println("this is sms sender!");  
	}

}
