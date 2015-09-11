package com.yinliang.ects.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	User user;
	Map<Product, Integer> productsMap=new HashMap<Product, Integer>();
	double money=0;
	int items=0;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void addItems(Product product, int number){
		money+=(number*product.getBasePrice());
		items+=number;
		
		if(productsMap.containsKey(product)){
			int value=productsMap.get(product);
			value+=number;
			productsMap.put(product, value);
		}else{
			productsMap.put(product, number);
		}
	}
	public void removeAll(){
		productsMap.clear();
		items=0;
		money=0;
	}
	public void removeItems(Product product){
		int value=productsMap.get(product);
		items-=value;
		money-=(value*product.getBasePrice());
		
		
		productsMap.remove(product);
		System.out.println(productsMap.size());
	}
	
	public Map<Product, Integer> getProductsMap() {
		return productsMap;
	}
	public void setProductsMap(Map<Product, Integer> productsMap) {
		this.productsMap = productsMap;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
}
