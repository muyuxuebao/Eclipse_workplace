package ITSSecurity.et199tool.system;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Security    
    * Comments: This class defines some constant configurations of this module.	  	                    
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-07-23                     
*/ 
public class SystemConfig {
	
	/** FieldName: USERPIN 	
	 *  Description: This field is set by intel.
	 */
	public static final String USERPIN = "intel";
	
	/** FieldName: CERT_TYPE 	
	 *  Description: Our certificate within ET199Tool is "X.509".
	 */
	public static final String CERT_TYPE = "X.509";
	
	/** FieldName: ROOT_CERT_LOCATION 	
	 *  Description: Root certificate is used to verify if the certificate is issued by intel.
	 */
	public static final String ROOT_CERT_LOCATION = "config/ITSRoot.cer";
}
