package com.ITS.management.message;

public abstract class Message {
	
	/**
	 * action field marks which kind of message it is.
	 */
	public int action;
	
	/**
	 * extra field is the extra object this message has to take.
	 * example: if a message commands server to create a user, then username and userinfo are needed to send.
	 * 
	 * a real message extends  this class has to provide method to serialize its extra object for net transfer.
	 */
	public Object extra;
	public abstract String wrapExtra();
	//public abstract void unwrapExtra(String extraStr);
	
	/**
	 * a message may require a response from server, expectDataType field marks witch kind of object needs to accept.
	 * this class's caller needs to implement method to receive response string, and fill it into expectData, like expectData = receiveBoolean(resStr).
	 */
	public int expectDataType;
	public Object expectData;
	
	/**
	 * this method wraps message into a string for net transfer.
	 */
	public abstract String wrapMessage();
	
	/*
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
	}*/
}
