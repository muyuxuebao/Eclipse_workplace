package com.ITS.management.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ITS.management.SystemConstant;

public class DateUtil {

	public static String getCurrentDate()
	{   
        return getCurrentDate("MM/dd/yyyy");   
    }   
	
	public static String getCurrentTime()
	{
		return getCurrentDate("MM/dd/yyyy hh:mm:ss");
	}
      
    public static String getCurrentDate(String format)
    {   
        Calendar day = Calendar.getInstance();    
        day.add(Calendar.DATE, 0);    
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(day.getTime());   
        return date;   
    }   
    
    public static String getCurrentTime(String format)
    {   
        Calendar day = Calendar.getInstance();    
        day.add(Calendar.DATE, 0);    
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(day.getTime());   
        return date;   
    }   
    
    public static String getNextDate(String dateStr, int expireType, int interval)
    {
    	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	
    	try
    	{
    		Date d = df.parse(dateStr);
    		
    		Calendar day = Calendar.getInstance();
    		day.setTime(d); 
    		
    		switch(expireType)
    		{
    		case SystemConstant.EXPIRE_TYPE_YEAR:
    		{
    			day.add(Calendar.YEAR, interval);
    			break;
    		}
    		
    		case SystemConstant.EXPIRE_TYPE_MONTH:
    		{
    			day.add(Calendar.MONTH, interval);
    			
    			break;
    		}
    		
    		case SystemConstant.EXPIRE_TYPE_DAY:
    		{
    			day.add(Calendar.DATE, interval);
    			
    			break;
    		}
    		}
    		
    		return df.format(day.getTime());   
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return null;
    }
       
    public static void main(String[] args) {   
        // TODO Auto-generated method stub   
    	//String today = getCurrentDate();
    	//System.out.println(getNextDate(today, SystemConstant.EXPIRE_TYPE_MONTH, -10));
    	
    	String s1 = getCurrentTime();
    	try
    	{
    		Thread.sleep(1000);
    	}
    	catch(Exception e)
    	{
    		
    	}
    	String s2 = getCurrentTime();
    	
    	System.out.println(s1.compareTo(s2));
    }   
}
