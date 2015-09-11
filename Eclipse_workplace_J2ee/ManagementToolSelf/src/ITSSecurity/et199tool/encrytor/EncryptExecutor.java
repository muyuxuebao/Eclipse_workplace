package ITSSecurity.et199tool.encrytor;

import ITSSecurity.et199tool.bean.CryptData;
import ITSSecurity.et199tool.bean.Signature;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This class is the real executor of the methods defined in Encryptor interface.	
    * 	All of the methods are native methods within this class. 
    * 	Methods in dll call the corresponding encryptor to deal with the request by "encryptorType". 	                    
* JDK version used:    <JDK1.6>                                                          
* Author：	Wang, Fan       
* Create Date：		2014-07-23
* Version:                      
*/ 

public class EncryptExecutor {

	/** 
	 * FunName: doEncrypt 
	 	* Description: A native function to do encrypting. 
	 	* 	The param CryptData data provides a template for jni method to create a CryptData object.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public native static CryptData doEncrypt(String str, String encryptorType, CryptData data);
	
	/** 
	 * FunName: doDecrypt 
	 	* Description: A native function to do decrypting. 
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public native static String doDecrypt(CryptData data, String encryptorType);
	
	/** 
	 * FunName: doEncrypt 
	 	* Description: A native function to do signing. 
	 	* 	The param Signature sig provides a template for jni method to create a Signature object.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public native static Signature doSign(String str, String entryptorType, Signature sig);
	
	/** 
	 * FunName: doVerify 
	 	* Description: A native function to do verifying. 
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public native static boolean doVerify(Signature signature, String entryptorType);
	
}
