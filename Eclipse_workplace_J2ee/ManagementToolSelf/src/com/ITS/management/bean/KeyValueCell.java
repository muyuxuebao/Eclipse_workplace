package com.ITS.management.bean;

import com.ITS.management.SystemConstant;

public class KeyValueCell {

	private String infoName;
	private String infoValue;
	
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getInfoValue() {
		return infoValue;
	}
	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}
	
	public KeyValueCell(String infoName, String infoValue) {
		super();
		this.infoName = infoName;
		this.infoValue = infoValue;
	}
	
	public String getContent()
	{
		return infoName + SystemConstant.KEY_VALUE_DIVIDER + infoValue;
	}
	
}
