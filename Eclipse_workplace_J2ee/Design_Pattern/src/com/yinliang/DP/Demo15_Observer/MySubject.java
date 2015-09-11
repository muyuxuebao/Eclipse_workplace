package com.yinliang.DP.Demo15_Observer;

public class MySubject extends AbstractSubject{

	@Override
	public void operation() {
		System.out.println("update self!");
		this.notifyObservers();
	}

}
