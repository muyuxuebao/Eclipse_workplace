package ITSSecurity.et199tool.encrytor;

import ITSSecurity.et199tool.bean.CryptData;
import ITSSecurity.et199tool.bean.Signature;

/** 
* Owner:   	Intel                        
    * Project:		Intelligent Testing System                                        
* Module:		Security    
    * Comments:		 This class is an implement of Encryptor using rsa algorithm.			  	                    
* JDK version used:    <JDK1.6>                                                          
* Author：	Wang, Fan       
* Create Date：		2014-07-23
* Version:                      
*/ 
public class RSAEncryptor implements Encryptor{

	//set encryptor type to rsa
	private final static String encryptorTypeRsa = "rsa";
	
	@Override
	public CryptData encrypt(String str) {
		// TODO Auto-generated method stub
		return EncryptExecutor.doEncrypt(str, encryptorTypeRsa, new CryptData());
	}

	@Override
	public String decrypt(CryptData data) {
		// TODO Auto-generated method stub
		return EncryptExecutor.doDecrypt(data, encryptorTypeRsa);
	}
	@Override
	public Signature sign(String str) {
		// TODO Auto-generated method stub
		return EncryptExecutor.doSign(str, encryptorTypeRsa, new Signature());
	}
	@Override
	public boolean verify(Signature signature) {
		// TODO Auto-generated method stub
		return EncryptExecutor.doVerify(signature, encryptorTypeRsa);
	}

}
