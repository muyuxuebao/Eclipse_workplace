package com.yinliang.DP.Demo02_abstract_factory_pattern;

public class Test {
	public static void main(String[] args) {
		Provider provider=new SendMailFactory();
		Sender sender=provider.produce();
		sender.Send();
	}
}
