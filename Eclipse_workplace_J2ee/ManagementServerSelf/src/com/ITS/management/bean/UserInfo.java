package com.ITS.management.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ITS.management.util.DataForDB;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: 1. This class defines all info needed for a user of ITS.		  	
    * 			2. This class implements DataForDB to visit sqlite database.                    
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-08-27                     
*/ 
public class UserInfo implements DataForDB{

	private String userName;	//The user's name, most probably a company's name
	private String department;	//The name of the department
	private String company;		//The name of the company
	private String email;		//The email address
	private String location;	//The location of the company
	private String state;		//The state of the company
	private String country;		//The country of the company
	
	/** 
	 * FunName: UserInfo 
	 	* Description: Constructing methods, supporting three modes: none args, only prikey arg, full args.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public UserInfo()
	{
		super();
	}
	
	public UserInfo(String userName)
	{
		super();
		this.userName = userName;
	}
	
	public UserInfo(String userName, String department, String company,
			String email, String location, String state, String country) {
		super();
		this.userName = userName;
		this.department = department;
		this.company = company;
		this.email = email;
		this.location = location;
		this.state = state;
		this.country = country;
	}
	
	public UserInfo(ArrayList<KeyValueCell> list)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		for(KeyValueCell cell: list)
		{
			map.put(cell.getInfoName(), cell.getInfoValue());
		}
		
		this.userName = map.get("userName");
		this.department = map.get("department");
		this.company = map.get("company");
		this.email = map.get("email");
		this.location = map.get("location");
		this.state = map.get("state");
		this.country = map.get("country");
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserName() {
		return userName;
	}


	public String getDepartment() {
		return department;
	}


	public String getCompany() {
		return company;
	}


	public String getEmail() {
		return email;
	}


	public String getLocation() {
		return location;
	}


	public String getState() {
		return state;
	}


	public String getCountry() {
		return country;
	}

	@Override
	public String getInsertSql(String tableName) {
		// TODO Auto-generated method stub
		
		//build insert sql
		StringBuilder sql = new StringBuilder("insert into " + tableName + " values(");
		
		//fill all attributes into this sql
		sql.append("'" + userName + "',");
		sql.append("'" + department + "',");
		sql.append("'" + company + "',");
		sql.append("'" + email + "',");
		sql.append("'" + location + "',");
		sql.append("'" + state + "',");
		sql.append("'" + country + "'");
		sql.append(");");
		
		return sql.toString();
	}


	@Override
	public void reBuildData(ResultSet rs) {
		// TODO Auto-generated method stub
		
		try
		{
			//rebuild all attributes from result set
			userName = rs.getString("userName");
			department = rs.getString("department");
			company = rs.getString("company");
			email = rs.getString("email");
			location = rs.getString("location");
			state = rs.getString("state");
			country = rs.getString("country");
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
		sql.append("username varchar(20)" + ",");
		sql.append("department varchar(50)" + ",");
		sql.append("company varchar(50)" + ",");
		sql.append("email varchar(50)" + ",");
		sql.append("location varchar(50)" + ",");
		sql.append("state varchar(50)" + ",");
		sql.append("country varchar(50)" + ",");
		
		//set username as the only primary key
		sql.append("PRIMARY KEY (username)");
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

		if(userName != null && !userName.equals(""))	//if primary key is set, use it as the only clause
		{
			needWhereClause = true;
			whereClause.append(" userName='" + userName + "' ");
		}
		else	//check all other attributes, add not null ones to clause
		{
			if(department != null && !department.equals(""))
			{
				needWhereClause = true;
				whereClause.append(" department='" + department + "' ");
			}
			if(company != null && !company.equals(""))
			{
				if(needWhereClause == true)
				{
					whereClause.append(" and ");
				}
				else
				{
					needWhereClause = true;
				}
				
				whereClause.append(" company='" + company + "' ");
			}
			if(email != null && !email.equals(""))
			{
				if(needWhereClause == true)
				{
					whereClause.append(" and ");
				}
				else
				{
					needWhereClause = true;
				}
				
				whereClause.append(" email='" + email + "' ");
			}
			if(location != null && !location.equals(""))
			{
				if(needWhereClause == true)
				{
					whereClause.append(" and ");
				}
				else
				{
					needWhereClause = true;
				}
				
				whereClause.append(" location='" + location + "' ");
			}
			if(state != null && !state.equals(""))
			{
				if(needWhereClause == true)
				{
					whereClause.append(" and ");
				}
				else
				{
					needWhereClause = true;
				}
				
				whereClause.append(" state='" + state + "' ");
			}
			if(country != null && !country.equals(""))
			{
				if(needWhereClause == true)
				{
					whereClause.append(" and ");
				}
				else
				{
					needWhereClause = true;
				}
				
				whereClause.append(" country='" + country + "' ");
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
