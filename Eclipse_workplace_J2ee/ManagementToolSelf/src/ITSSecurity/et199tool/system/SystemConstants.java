package ITSSecurity.et199tool.system;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This class defines some constants used in this module.	  
    * 		1.ActionCode and InnerErrorCode are used	to get an error report.                
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-07-23                     
*/ 
public class SystemConstants {

	//ActionCode 
	public static final int ACTION_INITTOOL	= 1;
	public static final int ACTION_OPENTOOL = 2;
	public static final int ACTION_CLOSETOO = 3;
	public static final int ACTION_CLEARTOOL = 4;
	public static final int ACTION_ENCRYPT = 5;
	public static final int ACTION_DECRYPT = 6;
	public static final int ACTION_SIGN	= 7;
	public static final int ACTION_VERIFY = 8;
	public static final int ACTION_GETCERT = 9;
	public static final int ACTION_CHECKKEY = 10;
	public static final int ACTION_LOGIN = 11;

	//InnerErrorCode	error within ET199Tool
	public static final int ERROR_LOAD_LIBRARY = 1;
	public static final int ERROR_GET_FUNCTION_LIST = 2;
	public static final int ERROR_GET_FUNCTION_POINTER = 3;
	public static final int ERROR_Initialize_PROCESS = 4;
	public static final int ERROR_GETSLOT = 5;
	public static final int ERROR_OPEN_SESSION = 6;
	public static final int ERROR_FIND_PUBLIC_KEY = 7;
	public static final int ERROR_FIND_PRIVATE_KEY = 8;
	public static final int ERROR_NO_PUBKEY_FOUND = 9;
	public static final int ERROR_NO_PRIKEY_FOUND = 10;
	public static final int ERROR_SIGN_DIGEST = 11;
	public static final int ERROR_SIGNING_PROCESS = 12;
	public static final int ERROR_NOT_VALID_TYPE = 13;
	public static final int ERROR_SIG_NOT_VALID = 14;
	public static final int ERROR_VERIFYING_PROCESS = 15;
	public static final int ERROR_NO_DEVICE_FOUND = 16;
	public static final int ERROR_CREATE_OBJECT = 17;
	public static final int ERROR_FIND_OBJECT = 18;
	public static final int ERROR_NO_OBJECT_FOUND = 19;
	public static final int ERROR_OBJECT_HANDLE_NULL = 20;
	public static final int ERROR_WRITING_PROCESS = 21;
	public static final int ERROR_READING_PROCESS = 22;
	public static final int ERROR_DELETING_PROCESS = 23;
	public static final int ERROR_USERPIN_NOT_VALID	= 24;
	public static final int ERROR_ENCRYPT_PROCESS = 25;
	public static final int ERROR_DECRYPT_PROCESS = 26;
	
	//OuterErrorCode	error not within ET199Tool
	
	//OuterErrorCode	error not within ET199Tool
	public static final int ERROR_CERT_NOT_MATCHED = 1;
	public static final int ERROR_NO_ENCRYPTOR_SET = 2;
	public static final int ERROR_UNKNOWN_ERROR = 3;
	public static final int ERROR_USERPIN_NULL = 4;
}
