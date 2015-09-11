package com.yinliang.DP.Demo10_Bridge;

public class Test {
	public static void main(String[] args) {
		Bridge bridge = new MyBridge();
		
		bridge.setSource(new SourceSub1());
		bridge.method();
		
		bridge.setSource(new SourceSub2());
		bridge.method();
	}
}
