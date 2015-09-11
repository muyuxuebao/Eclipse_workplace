package com.yinliang.DP.Demo21_Visitor;

public class MySubject implements Subject {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String getSubject() {
		return "love";
	}

}
