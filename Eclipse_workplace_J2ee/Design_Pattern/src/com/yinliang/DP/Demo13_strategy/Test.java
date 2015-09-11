package com.yinliang.DP.Demo13_strategy;

public class Test {
	
	public static void main(String[] args) {
		String exp = "2+3";
		ICalculator calculator = new Plus();
		int result = calculator.calculate(exp);
		System.out.println(result);
	}
}
