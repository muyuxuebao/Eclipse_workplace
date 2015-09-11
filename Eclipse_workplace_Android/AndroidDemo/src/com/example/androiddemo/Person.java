package com.example.androiddemo;

public class Person {
	int id;
	String name;
	String number;

	public Person(int id, String name, String number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}

	public Person() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
