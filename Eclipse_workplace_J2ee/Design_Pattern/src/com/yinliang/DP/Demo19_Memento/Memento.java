package com.yinliang.DP.Demo19_Memento;

public class Memento {
	private String value;  
	  
    public Memento(String value) {  
        this.value = value;  
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}  
  
}
