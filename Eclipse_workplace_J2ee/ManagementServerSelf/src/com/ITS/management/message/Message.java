package com.ITS.management.message;

import com.ITS.management.SystemConstant;


public abstract class Message {
	
	public int action;
	
	public Object extra;
	
	public abstract String wrapExtra();
	public abstract void unwrapExtra(String extraStr);
	
	public int expectDataType;
	public Object expectData;
	
	public abstract String wrapMessage();
	public static Message unwrapMessage(String msgStr)
	{
		String args[] = msgStr.split(SystemConstant.DIVEIDER);
		
		String messageType = args[0];
		int action = Integer.parseInt(args[1]);
		int expectDataType = Integer.parseInt(args[2]);
		
		String extraStr = null;
		if(args.length > 3)
		{
			extraStr = args[3];
		}
		
		try
		{
			Class<?> classType = Class.forName(messageType);
			Message msg = (Message) classType.newInstance();
			msg.action = action;
			msg.expectDataType = expectDataType;
			msg.unwrapExtra(extraStr);
			
			return msg;
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
}
