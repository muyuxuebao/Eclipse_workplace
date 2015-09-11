package ITSSecurity.et199tool.encrytor;

import ITSSecurity.et199tool.bean.CryptData;
import ITSSecurity.et199tool.bean.Signature;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This interface defines a set of functions a encryptor needs to implement. 				  	                    
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-07-23                     
*/ 
public interface Encryptor {

	/** 
	 * FunName: encrypt 
	 	* Description: This function is used to encrypt a string and get the result.
	 	* Input: 
	 		* @param String str 
	 			* Description: The string needs to be encrypted.
	 	* Return:
	 		* @type: CryptData
	 			* Description: It returns a encrypted data if encrypting process is OK, and null if an error occur.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public CryptData encrypt(String str);

	/** 
	 * FunName: decrypt 
	 	* Description: This function is used to decrypt a data get from encrypting process.
	 	* Input: 
	 		* @param CryptData data 
	 			* Description: This data is from encrypting process, and can be recovered to source string.
	 	* Return:
	 		* @type: String
	 			* Description: It returns the source string is OK, and null if an error occur in decrypting process.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public String decrypt(CryptData data);
	
	/** 
	 * FunName: sign 
	 	* Description: This function is used to sign a data and get a signature.
	 	* Input: 
	 		* @param String str
	 			* Description: String needs to be signed.
	 	* Return:
	 		* @type: Signature
	 			* Description: Returns a signature if the signing process is OK, and null if an error occurs.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public Signature sign(String str);
	
	/** 
	 * FunName: verify 
	 	* Description: This function is used to verify if a signature is valid.
	 	* Input: 
	 		* @param Signature signature
	 			* Description: This data is from signing process.
	 	* Return:
	 		* @type: boolean
	 			* Description: It returns true if the signature is valid, false if not valid or any error occurs.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-23
	 */ 
	public boolean verify(Signature signature);
	
}
