package com.ITS.management.tool;

import com.ITS.management.Connection;
import com.ITS.management.message.Message;

public class MessageThreadFactory {

	private Connection threadContent;
	
	public MessageThreadFactory(Connection content)
	{
		this.threadContent = content;
	}
	
	public void executeOneThread(Message msg)
	{
		threadContent.setMessage(msg);
		Thread t = new Thread(threadContent);
		
		t.start();
		try
		{
			t.join();
		} catch(Exception e) { e.printStackTrace(); }
	}
}
