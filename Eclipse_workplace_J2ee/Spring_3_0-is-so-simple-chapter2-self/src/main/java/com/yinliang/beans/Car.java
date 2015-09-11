package com.yinliang.beans;

import org.springframework.beans.factory.BeanFactory;

public class Car {
	private String brand;

	private String color;
	private int maxSpeed;
	private String name;
	private BeanFactory beanFactory;
	private String beanName;

	public void myInit() {
		System.out.println("调用myInit()，将maxSpeed设置为240。");
		this.maxSpeed = 240;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMaxSpeed() {
		return this.maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BeanFactory getBeanFactory() {
		return this.beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public String getBeanName() {
		return this.beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
}
