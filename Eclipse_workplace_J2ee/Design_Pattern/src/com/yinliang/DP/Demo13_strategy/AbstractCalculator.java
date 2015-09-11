package com.yinliang.DP.Demo13_strategy;

public abstract class AbstractCalculator {
	
	public int[] split(String exp, String opt) {
		String[] sarr = exp.split(opt);
		int[] iarr = new int[2];
		iarr[0] = Integer.parseInt(sarr[0]);
		iarr[1] = Integer.parseInt(sarr[1]);

		return iarr;
	}
}
