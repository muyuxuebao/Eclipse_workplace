package ITSSecurity.et199tool.util;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This class provides a set of user functions to visit ET199Tool.		  	                    
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-07-23                     
*/ 
public class UserUtil {
	
	/** 
	 * FunName: userLogin 
	 	* Description: A native function for user to login.
	 		* Input: 
	 			* @param String userPin
	 			* Description: A password to visit ET199Tool's private domain.
	 		* Return:
	 			* @type: boolean
	 			* Description: Returns true if userPin is valid, false if not valid.
	 			* 		If It returns false, an extra getError process needs to conduct.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public native static boolean userLogin(String userPin);
	
}
