package com.ITS.management.test;

public class ThreadTest {

	
	private class AThread extends Thread
	{
		
		public void run()
		{
			int count = 0;
			boolean tag = true;
			
			while(tag)
			{
				System.out.println(count ++);
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				if(isInterrupted())
				{
					break;
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		AThread th = new ThreadTest().new AThread();
		th.start();
		try
		{
			Thread.sleep(3000);
			th.interrupt();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("interrupt...");
	}
	
}

