package com.ITS.management.bean;

import com.ITS.management.SystemConstant;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security Management Tool  
    * Comments: 1. stores a key-value cell.           
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-09-25                     
*/ 
public class KeyValueCell {

	private String infoName;	//key in cell
	private String infoValue;	//value in cell
	
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
