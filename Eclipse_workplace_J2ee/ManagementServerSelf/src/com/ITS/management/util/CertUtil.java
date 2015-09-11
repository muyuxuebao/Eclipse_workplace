package com.ITS.management.util;

import java.util.ArrayList;

import com.ITS.management.SystemConstant;
import com.ITS.management.bean.UserInfo;

public class CertUtil {
	
	private static void keyBoardExec()
	{
		KeyBoardSimulator.keyBoardExec(SystemConstant.CERT_PASSWORD);
		KeyBoardSimulator.keyBoardExecTab();
		KeyBoardSimulator.keyBoardExec(SystemConstant.CERT_PASSWORD);
		KeyBoardSimulator.keyBoardExecEnter();
		
		try
		{
			Thread.sleep(1000);
		} catch(Exception e){ e.printStackTrace(); }
		   
		KeyBoardSimulator.keyBoardExec(SystemConstant.CERT_PASSWORD);
		KeyBoardSimulator.keyBoardExecEnter();
	
		try
		{
			Thread.sleep(1000);
		} catch(Exception e){ e.printStackTrace(); }
		   
		KeyBoardSimulator.keyBoardExec(SystemConstant.CERT_PASSWORD);
		KeyBoardSimulator.keyBoardExecEnter();   
		
		try
		{
			Thread.sleep(1000);
		} catch(Exception e){ e.printStackTrace(); }
	}
	
	public static void main(String[] args)
	{
		//createCert("name1");
	}
	
	public static String createCert(String userName, int expireType, int interval)
	{		
		UserInfo user = new UserInfo(userName);
		ArrayList<DataForDB> list = SqliteUtil.queryUser("userinfo", user);
		
		String today = DateUtil.getCurrentDate();
		if(list.size() > 0)
		{
			user = (UserInfo) list.get(0);
			
			String certName = user.getUserName() + System.currentTimeMillis();
			
			String makecertCommand = "makecert.exe";
			makecertCommand = makecertCommand + " -n \"CN=" + user.getUserName() + 
					",OU=" + user.getDepartment() + 
					",O=" + user.getCompany() + 
					",E=" + user.getEmail() + 
					",L=" + user.getLocation() + 
					",S=" + user.getState() + 
					",C=" + user.getCountry() + "\"" + 
					" -b " + today +
					" -e " +  DateUtil.getNextDate(today, expireType, interval) +
					" -iv " + SystemConstant.ROOT_CERT_NAME + ".pvk -ic " + SystemConstant.ROOT_CERT_NAME + ".cer" +
					" -sv " + certName + ".pvk " + certName + ".cer";
			
			String pvkCommand = "pvk2pfx.exe";
			pvkCommand = pvkCommand + 
						" -pvk " + certName + ".pvk" + " -pi " + SystemConstant.CERT_PASSWORD + 
						" -spc " + certName + ".cer" + 
						" -pfx " + certName + ".pfx -f";
			
			try
			{
				Runtime.getRuntime().exec(makecertCommand);
				Thread.sleep(2000);
				keyBoardExec();
				Thread.sleep(1000);
				Runtime.getRuntime().exec(pvkCommand);
				Thread.sleep(1000);
			} catch(Exception e) { e.printStackTrace(); }
			
			return certName + ".pfx";
		}
		else
		{
			return null;
		}
	}
	
}
