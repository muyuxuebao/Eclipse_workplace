package com.yinliang.DP.Demo16_Iterator;

public class Test {
	public static void main(String[] args) {
		Collection collection=new MyCollection();
		Iterator iterator=new MyIterator(collection);
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
