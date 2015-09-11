package com.ITS.management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ITS.management.bean.KeyUserMapInfo;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: 1. This class defines a set of operations to visit sqlite
    *           2. All objects need to visit sqlite have to implement DataForDB interface                            
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-08-27                  
*/ 
public class SqliteUtil {

	private static final String driverName = "org.sqlite.JDBC";
	private static final String dbName = "itsDB";
	
	/** FieldName: conn
	 *		
	 *  Description: Connector to sqlite datebase, created in openDB function, and closed in closeDB function. 
	 */
	private static Connection conn; 
	
	/** FieldName: stmt
	 *		
	 *  Description: Statement used to execute sql, created in openDB function by conn, and closed in closeDB function. 
	 */
	private static Statement stmt;
	
	/** 
	 * FunName: loadDriver 
	 	* Description: Load the driver database.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static void loadDriver()
	{
		try 
		{  
            Class.forName(driverName);  
        } catch (ClassNotFoundException e) {  
            System.out.println("No driver found!");  
        }  
	}
	
	/** 
	 * FunName: openDB 
	 	* Description: Function to open database, mainly create connector and statement.
	 	* 	By default, if no database exists by dbName, system will create one.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static void openDB(String dbName)
	{
		try
		{
			//connect to database
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbName, null, null);  
	        conn.setAutoCommit(false);
	        
	        //create statement
	        stmt = conn.createStatement();  
		} catch (SQLException e) {  
            System.out.println("SQL error!");  
        }  
	}
	
	/** 
	 * FunName: createTable 
	 	* Description: This function is used to create a table under current database.
	 	* Input: 
	 		* @param String tableName
	 		* @param DataForDB dataTemplate
	 			* Description: 1. This table can only stores data for the provided data type. 
	 			* 			   2. The provided data has to implement getCreateTableSql() function in DataForDB interface.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static void createTable(String tableName, DataForDB dataTemplate)
	{
		try 
		{  
			//check if this table needs to be created exists
		    ResultSet rsTables = conn.getMetaData().getTables(null, null, tableName, null);  
		    if(rsTables.next())
		    {  
		    	System.out.println("Table exists!");  
		    } 
		    else //if table not exists, create it
		    {  
		    	//get creating sql for the provided data type
		    	String sql = dataTemplate.getCreateTableSql(tableName);
		    	
		    	//execute this sql
		    	stmt.executeUpdate(sql.toString());  
		    	conn.commit();
		    }  
		} catch (SQLException e) {  
            System.out.println("SQL error!");  
        }  
	}
	
	/** 
	 * FunName: insert 
	 	* Description: This function is used to insert data into the appointed table
	 	* Input: 
	 		* @param String tableName
	 		* @param DataForDB dataTemplate
	 			* Description: 1. This dataTemplate will be inserted.
	 			* 			   2. This dataTemplate has to implement getInsertSql() function in DataForDB interface.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static void insert(String tableName, DataForDB dataTemplate)
	{
		if(conn == null || stmt == null)
		{
			loadDriver();
			openDB(dbName);
		}
		
		//get inserting sql for dataTemplate
		String sql = dataTemplate.getInsertSql(tableName);
		try
		{
			//execute this sql
			stmt.executeUpdate(sql.toString());  
	    	conn.commit();
		}
		catch (SQLException e) {  
            System.out.println("SQL error!");  
        }  
	}
	
	/** 
	 * FunName: delete 
	 	* Description: This function is used to delete data within the appointed table
	 	* Input: 
	 		* @param String tableName
	 		* @param DataForDB dataTemplate
	 			* Description: 1. Some attributes have to be set in dataTemplate, and table will delete data fitting to this dataTemplate.
	 			* 			   2. This dataTemplate has to implement getDeleteSql() function in DataForDB interface.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static void delete(String tableName, DataForDB dataTemplate)
	{
		if(conn == null || stmt == null)
		{
			loadDriver();
			openDB(dbName);
		}
		
		//get deleting sql for dataTemplate
		String sql = dataTemplate.getDeleteSql(tableName);
		
		try
		{
			//execute this sql
			stmt.executeUpdate(sql.toString());  
	    	conn.commit();
		}
		catch (SQLException e) {  
            System.out.println("SQL error!");  
        }  
	}
	
	/** 
	 * FunName: insert 
	 	* Description: This function is used to query data within the appointed table
	 	* Input: 
	 		* @param String tableName
	 		* @param DataForDB dataTemplate
	 			* Description: 1. Some attributes have to be set in dataTemplate, and table will query data fitting to this dataTemplate.
	 			* 			   2. This dataTemplate has to implement getSelectSql() and reBuildData() functions in DataForDB interface.
	 	* Return:
	 		* @type: ArrayList<DataForDB>
	 			* Description: It returns a list of data fitting to the provided dataTemplate.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static ArrayList<DataForDB> queryUser(String tableName, DataForDB dataTemplate)
	{
		if(conn == null || stmt == null)
		{
			loadDriver();
			openDB(dbName);
		}
		
		ArrayList<DataForDB> list = new ArrayList<DataForDB>();
		
		try
		{
			//This function use reflection to rebuild data, so needs to get the real class type of the provided dataTemplate 
			Class<?> classType = Class.forName(dataTemplate.getClass().getName());
			
			//get select sql for dataTemplate and execute sql
			ResultSet rs = stmt.executeQuery(dataTemplate.getSelectSql(tableName));  
			while(rs.next()) 
			{  
				//iterate this result set and rebuild data for each rs
				DataForDB data = (DataForDB) classType.newInstance();
				data.reBuildData(rs);
				
				//add this data into returned list
				list.add(data);
			}  
			
			rs.close();  
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("error!");
		}
		
		return list;	
	}
	
	/** 
	 * FunName: closeDB 
	 	* Description: Function to close database, mainly close statement and connector.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-08-27
	 */ 
	public static void closeDB()
	{
		try
		{
			stmt.close();
            conn.close();  
        } catch (SQLException e) {  
        	System.out.println("SQL error!");  
		}
	}
	
	public static void main(String[] args)
	{
		//UserInfo user = new UserInfo("wangfan", "department", "intel", "email-wangfan", "location1", "shanghai", "china");
		//UserInfo user1 = new UserInfo("flower", "department", "intel", "email-wangfan", "location1", "shanghai", "china");
		//UserInfo user2 = new UserInfo("loveting", "department2", "intel2", "email-loveting", "location1", "shanghai", "china");
		
		KeyUserMapInfo mapInfo = new KeyUserMapInfo();
		loadDriver();
		openDB("itsDB");
		
		
		//createTable("usercert", mapInfo);
		//insert("userinfo", user);
		//insert("userinfo", user1);
		//insert("userinfo", user2);
		
		KeyUserMapInfo resultMap = new KeyUserMapInfo();
		resultMap = (KeyUserMapInfo) queryUser("usercert", resultMap).get(0);
		//delete("userinfo", resultUser);
		
		System.out.println(resultMap.getHid());
		System.out.println(resultMap.getuserName());
		System.out.println(resultMap.getcertName());
		//System.out.println(resultUser.getDepartment());
		//System.out.println(resultUser.getEmail());
		
		closeDB();
	}
	
}
