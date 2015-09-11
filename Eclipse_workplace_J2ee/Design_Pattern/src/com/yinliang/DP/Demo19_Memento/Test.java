package com.yinliang.DP.Demo19_Memento;

public class Test {
	public static void main(String[] args) {
		Original original=new Original("egg");
		
		Storage storage=new Storage(original.createMemento());
		
		System.out.println("初始状态 为: "+original.getValue());
		
		original.setValue("niu");
		
		System.out.println("修改之后的状态为: "+original.getValue());
		
		original.restoreMemento(storage.getMemento());
		
		System.out.println("恢复后的状态为: "+original.getValue());
	}
}
