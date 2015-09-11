package com.ITS.management.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ITS.management.util.DataForDB;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security Management Tool  
    * Comments: 1. records each ukey and the certificate inside.                   
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-09-25                     
*/ 
public class KeyUserMapInfo implements DataForDB{

	private String hid;		//worldwide unique ukey id
	private String userName;	//owner of this ukey
	private String certName;	//certname inside this ukey
	
	//start and end field have not used
	private String start;	//validity start date
	private String end;		//expire end date
	
	/** 
	 * FunName: KeyUserMapInfo 
	 	* Description: Constructing methods, supporting three modes: none args, only useful args, full args, arraylist args.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-09-25
	 */ 
	public KeyUserMapInfo() {
		super();
	}

	public KeyUserMapInfo(String hid, String userName, String certName) {
		super();
		this.hid = hid;
		this.userName = userName;
		this.certName = certName;
	}

	public KeyUserMapInfo(String hid, String userName, String certName,
			String start, String end) {
		super();
		this.hid = hid;
		this.userName = userName;
		this.certName = certName;
		this.start = start;
		this.end = end;
	}
	
	public KeyUserMapInfo(ArrayList<KeyValueCell> list)
	{
		//add all info into a map
		HashMap<String, String> map = new HashMap<String, String>();
		for(KeyValueCell cell: list)
		{
			map.put(cell.getInfoName(), cell.getInfoValue());
		}
		
		//get info from map and initiate this object
		this.hid = map.get("hid");
		this.userName = map.get("userName");
		this.certName = map.get("certName");
	}

	public String getHid() {
		return hid;
	}

	public String getuserName() {
		return userName;
	}

	public String getcertName() {
		return certName;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
	
	@Override
	public String getInsertSql(String tableName) {
		// TODO Auto-generated method stub
		
		//build insert sql
		StringBuilder sql = new StringBuilder("insert into " + tableName + " values(");
		
		//fill all attributes into this sql
		sql.append("'" + hid + "',");
		sql.append("'" + userName + "',");
		sql.append("'" + certName + "'");
		sql.append(");");
		
		return sql.toString();
	}


	@Override
	public void reBuildData(ResultSet rs) {
		// TODO Auto-generated method stub
		
		try
		{
			//rebuild all attributes from result set
			hid = rs.getString("hid");
			userName = rs.getString("userName");
			certName = rs.getString("certName");
		} catch(SQLException e) {
			System.out.println("SQL error!");
		}
	}


	@Override
	public String getSelectSql(String tableName) {
		// TODO Auto-generated method stub
		
		//build query sql
		StringBuilder sql = new StringBuilder("select * from "  + tableName + " ");
		
		//get where clause adhere to query sql
		String whereClause = getWhereClause();
		sql.append(whereClause);
		
		sql.append(";");
		
		return sql.toString();
	}


	@Override
	public String getCreateTableSql(String tableName) {
		// TODO Auto-generated method stub
		
		//build create table sql
		StringBuilder sql = new StringBuilder("create table "  + tableName + " (");
		
		//set table columns
		sql.append("hid varchar(20)" + ",");
		sql.append("userName varchar(20)" + ",");
		sql.append("certName varchar(50)" + ",");

		//set username as the only primary key
		sql.append("PRIMARY KEY (hid)");
		sql.append(");");
		
		return sql.toString();
	}

	@Override
	public String getDeleteSql(String tableName) {
		// TODO Auto-generated method stub
		
		//build delete sql
		StringBuilder sql = new StringBuilder("delete from "  + tableName + " ");
		
		//get where caluse adhere to delete sql
		String whereClause = getWhereClause();
		sql.append(whereClause);
		sql.append(";");
		
		return sql.toString();
	}
	
	private String getWhereClause()
	{
		StringBuilder whereClause = new StringBuilder("where");
		
		//flag if this where clause is needed
		boolean needWhereClause = false;

		if(hid != null && !hid.equals(""))	//if primary key is set, use it as the only clause
		{
			needWhereClause = true;
			whereClause.append(" hid='" + hid + "' ");
		}
		else	//check all other attributes, add not null ones to clause
		{
			if(userName != null && !userName.equals(""))
			{
				needWhereClause = true;
				whereClause.append(" userName='" + userName + "' ");
			}
			if(certName != null && !certName.equals(""))
			{
				if(needWhereClause == true)
				{
					whereClause.append(" and ");
				}
				else
				{
					needWhereClause = true;
				}
				
				whereClause.append(" certName='" + certName + "' ");
			}
		}
		
		if(needWhereClause == true)		//if not all attributes are null, return where clause
		{
			return whereClause.toString();
		}
		else
		{
			return "";
		}
	}
	
}
