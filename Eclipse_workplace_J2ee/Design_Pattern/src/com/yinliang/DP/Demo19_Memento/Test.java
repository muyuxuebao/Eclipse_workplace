package com.yinliang.DP.Demo19_Memento;

public class Test {
	public static void main(String[] args) {
		Original original=new Original("egg");
		
		Storage storage=new Storage(original.createMemento());
		
		System.out.println("��ʼ״̬ Ϊ: "+original.getValue());
		
		original.setValue("niu");
		
		System.out.println("�޸�֮���״̬Ϊ: "+original.getValue());
		
		original.restoreMemento(storage.getMemento());
		
		System.out.println("�ָ����״̬Ϊ: "+original.getValue());
	}
}
