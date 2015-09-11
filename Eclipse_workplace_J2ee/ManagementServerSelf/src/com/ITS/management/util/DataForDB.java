package com.ITS.management.util;

import java.sql.ResultSet;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This interface defines a set of functions an object needs to implement to use SqliteUtil to visit database.			  	                    
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-08-27                     
*/ 
public interface DataForDB {
	
	/** 
	 * FunName: getInsertSql 
	 	* Description: Construct inserting sql according to attributes within the object itself.
	 	* Input: 
	 		* @param String tableName
	 	* Return:
	 		* @type: String
	 			* Description: The constructed inserting sql.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public abstract String getInsertSql(String tableName);
	
	/** 
	 * FunName: getDeleteSql 
	 	* Description: Construct deleting sql according to the set attributes within the object itself.
	 	* Input: 
	 		* @param String tableName
	 	* Return:
	 		* @type: String
	 			* Description: The constructed deleting sql.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public abstract String getDeleteSql(String tableName);
	
	/** 
	 * FunName: reBuildData 
	 	* Description: Rebuild data according to the values within result set.
	 	* Input: 
	 		* @param ResultSet rs
	 			* Description: Result set contains values needed to rebuild data.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public abstract void reBuildData(ResultSet rs);
	
	/** 
	 * FunName: getSelectSql 
	 	* Description: Construct selecting sql according to the set attributes within the object itself.
	 	* Input: 
	 		* @param String tableName
	 	* Return:
	 		* @type: String
	 			* Description: The constructed selecting sql.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public abstract String getSelectSql(String tableName);
	
	/** 
	 * FunName: getCreateTableSql 
	 	* Description: Construct creating table sql according to all of the attributes the object has.
	 	* Input: 
	 		* @param String tableName
	 	* Return:
	 		* @type: String
	 			* Description: The constructed creating table sql.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public abstract String getCreateTableSql(String tableName);
}
