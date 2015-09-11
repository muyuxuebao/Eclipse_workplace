package com.yinliang.DP.Demo01_factory_method_pattern;


public class Test {
	public static void main(String[] args) {
		Sender sen=SendFactory.produce("mail");
		sen.Send();
	}
	
}
