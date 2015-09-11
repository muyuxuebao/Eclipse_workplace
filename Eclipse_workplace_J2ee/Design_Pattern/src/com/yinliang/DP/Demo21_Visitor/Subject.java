package com.yinliang.DP.Demo21_Visitor;

public interface Subject {

	public void accept(Visitor visitor);  
    public String getSubject();  
}
