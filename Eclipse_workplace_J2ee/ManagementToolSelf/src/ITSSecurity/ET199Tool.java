package ITSSecurity;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import ITSSecurity.et199tool.bean.Signature;
import ITSSecurity.et199tool.encrytor.Encryptor;
import ITSSecurity.et199tool.encrytor.RSAEncryptor;
import ITSSecurity.et199tool.system.SystemConfig;
import ITSSecurity.et199tool.util.SystemUtil;
import ITSSecurity.et199tool.util.UserUtil;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: 1. This class is a direct java API to visit ET199Tool.
                             
* JDK version used: <JDK1.6>                                                          
* Author: Wang, Fan       
* Create Date锛� 2014-07-28    
* 
*/
/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: 1. This class is a direct java API to visit ET199Tool.
                             
* JDK version used: <JDK1.6>                                                          
* Author: Wang, Fan       
* Create Date锛� 2014-07-28    
* 
*/
public class ET199Tool {

	static {
		// load library to enable et199 native methods.
		// Can print System.getproperty("java.library.path") to check if library is under system path
		System.loadLibrary("et199tool");
	}

	/** FieldName: encryptor 
	 *		
	 *  Description: 1. This field tells which algorithm ET199 is using for encryption.
	 *  			 2. Encryptor can only be set in initialization process.
	 */
	private Encryptor encryptor;

	private void setEncryptor(Encryptor encryptor) {
		this.encryptor = encryptor;
	}

	/** 
	 * FunName: userLogin 
	 	* Description: Any process visit private domain need to login first.
	 	* Input: 
	 		* @param String userPin
	 			* Description: userPin is the password for an ET199Tool.
	 	* Return:
	 		* @type: boolean
	 			* Description: 1. Returns a tag to tell if login successful.
	 			* 			   2. UserPin invalid or system error both return false.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean userLogin(String userPin) {
		if (UserUtil.userLogin(userPin) == true) {
			return true;
		} else {
			// if false tag returns, use getError process to check the reason.

			return false;
		}
	}

	/** 
	 * FunName: initET199 
	 	* Description: This function needs to be called before any operation to ET199Tool.
	 	* Input: 
	 		* @param Encryptor encryptor
	 	* Return:
	 		* @type: boolean
	 			* Description: Returns true if initialization successful.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean initET199(Encryptor encryptor) {
		// encryptor needs to be and can only be set in this function
		if (encryptor == null) {
			return false;
		} else {
			this.setEncryptor(encryptor);
		}

		if (SystemUtil.initTool() == true) {
			return true;
		} else {
			// if false tag returns, use getError process to check the reason.
			return false;
		}
	}

	/** 
	 * FunName: clearET199 
	 	* Description: The corresponding function to initET199, call as the last step to operate ET199Tool.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public void clearET199() {
		SystemUtil.clearTool();
	}

	/** 
	 * FunName: openET199 
	 	* Description: Open this tool and login.
	 	* Input: 
	 		* @param String userPin
	 	* Return:
	 		* @type: boolean
	 			* Description: Returns true if open tool and login successful.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean openET199(String userPin) {
		// open tool
		if (SystemUtil.openTool() != true) {
			return false;
		}

		if (userPin != null) {
			// user login
			if (this.userLogin(userPin) == true) {
				return true;
			} else {
				return false;
			}
		} else {
			// if userPin is null, create an outer error and return false
			return false;
		}
	}

	/** 
	 * FunName: closeET199 
	 	* Description: The corresponding function to openET199.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public void closeET199() {
		SystemUtil.closeTool();
	}

	/** 
	 * FunName: sign 
	 	* Description: sign a string and return a signature.
	 	* Input: 
	 		* @param String str
	 	* Return:
	 		* @type: Signature
	 			* Description: Returns a signature from JNI or null.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public Signature sign(String str) {
		// call encryptor's own signing process to sign.
		Signature sig = this.encryptor.sign(str);

		if (sig == null) // an error occurs in signing process
		{
			return null;
		} else {
			return sig;
		}
	}

	/** 
	 * FunName: verify 
	 	* Description: verify if a signature is valid.
	 	* Input: 
	 		* @param Signature sig
	 	* Return:
	 		* @type: boolean
	 			* Description: Returns true if signature is valid, false if not valid or any error occurs.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean verify(Signature sig) {
		// call encryptor's own verifying process to verify.
		if (this.encryptor.verify(sig)) {
			return true;
		} else {
			// call getError to check the reason
			return false;
		}
	}

	/** 
	 * FunName: isKeyAvailable 
	 	* Description: check if any key is inserted.
	 	* Return:
	 		* @type: boolean
	 			* Description: Returns true if at least one key is inserted, false if no key inserted or error occurs.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean isKeyAvailable() {
		if (SystemUtil.isKeyAvailable() == true) {
			return true;
		} else {
			// call getError to check the reason
			return false;
		}
	}

	/** 
	 * FunName: isCerValid 
	 	* Description: get a cert from ET199Tool, and check if this cert is valid.
	 	* Input: 
	 		* @param String cerType
	 			* Description: This function read bytes from JNI, and use them to rebuild a license cert by cerType.
	 		* @param String cerPath
	 			* Description: Root cert's location path. Root cert is used to check if the license cert is valid.
	 	* Return:
	 		* @type: boolean
	 			* Description: Returns true if cert within ET199Tool is valid, false if not valid or error occurs.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean isCerValid(String cerType, String cerPath) {
		try {
			CertificateFactory cf = CertificateFactory.getInstance(cerType);

			// ceof1 is root cert
			FileInputStream in1 = new FileInputStream(cerPath);
			Certificate ceof1 = cf.generateCertificate(in1);
			in1.close();

			// read cert bytes from ET199Tool, and rebuild license cert ceof2
			byte[] cerBytes = SystemUtil.getCerBytes();
			if (cerBytes == null) {
				// cannot read cert from ET199Tool, check the reason
				return false;
			}

			InputStream in2 = new ByteArrayInputStream(cerBytes);
			Certificate ceof2 = cf.generateCertificate(in2);
			in2.close();

			// get root cert's pubkey
			PublicKey pbk = ceof1.getPublicKey();
			boolean pass = false;
			try {
				// license cert had signed by root cert's prikey, so use pubkey to verify
				ceof2.verify(pbk);
				pass = true;
			} catch (SignatureException e) {
				pass = false;

				// SignatureException is thrown when license cert not valid, create an outer error
			}

			return pass;
		} catch (Exception e) {
			// any unknown error occurs, create an outer error
		}

		return false;
	}

	/** 
	 * FunName: isCerComplete 
	 	* Description: A complete process of cert checking, including checking if key pair is matched within ET199Tool, 
	 	* 		and checking if cert is valid.
	 	* Input: 
	 		* @param String cerType
	 			* Description: a param to pass to isCerValid.
	 		* @param String cerPath
	 			* Description: a param to pass to isCerValid.
	 	* Return:
	 		* @type: boolean
	 			* Description: Returns true if cert within ET199Tool is complete, false if not complete or error occurs.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-07-29
	 */
	public boolean isCerComplete(String cerType, String cerPath) {
		// generate a random number for signing
		Double checkingNum = Math.random();

		// ET199Tool use prikey to sign this number
		Signature sig = this.sign(checkingNum.toString());

		if (sig == null) // signing process fail, errors have tackled in sign function
		{
			return false;
		}

		// verify the signature returned
		// ET199Tool uses pubkey to verify. If ok, call isCerValid function.
		if (this.verify(sig) && this.isCerValid(cerType, cerPath)) {
			return true;
		} else {
			return false;
		}
	}

	public String getHid() {
		return SystemUtil.getKeyId();
	}

	public static void main(String[] args) {

		ET199Tool tool = new ET199Tool();
		Encryptor encryptor = new RSAEncryptor();
		tool.initET199(encryptor);

		if (tool.isKeyAvailable()) {
			// get userPin to login
			String userPin = SystemConfig.USERPIN;

			// open tool
			if (tool.openET199(userPin) == true) {
				// System.out.println(SystemUtil.getKeyId());
				// System.out.println(tool.isCerComplete(cerType, cerPath));
				tool.closeET199();
			}
		}
	}
}
