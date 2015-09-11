package ITSSecurity.et199tool.bean;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This class defines a template for data encrypted by ET199Tool's public key.                                       
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-07-23                  
*/ 

public class CryptData {

	
	/** 
	* Description: This constructing method is mainly used by JNI to create a new CrypteData,
	*	so no param needs to be provided.
	*/ 
	public CryptData() {
		super();
	}
	
	/** FieldName: contentByteArray 
	 *		
	 *  Description: It stores encrypted string from JNI. 
	 *  In dll, this string's is a char array, char's range is -128 - 127, the same as byte in java.
	 */
	private byte[] contentByteArray;
	
	/** FieldName: len 
	 *		
	 *  Description: The length of encrypted string.
	 */
	private int len;
	
	/** FieldName: blockNum 
	 *		
	 *  Description: A encrypted string may be divided into several blocks storing in ET199Tool.
	 */
	private int blockNum;
	
	public byte[] getContentByteArray() {
		return contentByteArray;
	}
	public void setContentByteArray(byte[] contentByteArray) {
		this.contentByteArray = contentByteArray;
	}
	
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	
	public int getBlockNum() {
		return blockNum;
	}
	public void setBlockNum(int blockNum) {
		this.blockNum = blockNum;
	}
}
	