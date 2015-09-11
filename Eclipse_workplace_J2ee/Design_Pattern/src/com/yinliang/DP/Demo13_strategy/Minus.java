package com.yinliang.DP.Demo13_strategy;

public class Minus extends AbstractCalculator implements ICalculator{

	@Override
	public int calculate(String exp) {
		// TODO Auto-generated method stub
		int[] arr= this.split(exp, "\\+");
		return arr[0]-arr[1];
	}

}
