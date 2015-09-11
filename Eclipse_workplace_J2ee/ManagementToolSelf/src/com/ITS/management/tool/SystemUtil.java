package com.ITS.management.tool;


public class SystemUtil {

	static
	{
		System.loadLibrary("et199management");
	}
	
	public static native boolean formatTool();
	public static native boolean importCert();
	
	public static void main(String[] args)
	{
		System.out.println(SystemUtil.formatTool());
		
		/*
		ET199Tool tool = new ET199Tool();
		Encryptor encryptor = new RSAEncryptor();		
		tool.initET199(encryptor);
		
		if(tool.isKeyAvailable())
		{
			//get userPin to login
			String userPin = SystemConfig.USERPIN;
				
			//open tool
			if(tool.openET199(userPin) == true)
			{
				System.out.println(SystemUtil.importCert());
				tool.closeET199();
			}
		}
		else
		{
			System.out.println("false");
		}*/
		
	}
	
}
 