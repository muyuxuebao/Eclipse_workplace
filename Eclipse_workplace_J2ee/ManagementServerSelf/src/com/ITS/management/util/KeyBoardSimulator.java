package com.ITS.management.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/** 
* Owner: Intel                        
    * Project: Intelligent Testing System                                        
* Module: Key Management Tool
    * Comments: This class provides methods for keyboard simulating.                    
* JDK version used: <JDK1.6>                                                          
* Author： Wang, Fan       
* Create Date： 2014-09-25                     
*/ 
public class KeyBoardSimulator {

	/** FieldName: robot
	 *		
	 *  Description: real object to execute keyboard simulating.
	 */
	private static Robot robot = null;
	
	/** FieldName: keyMap
	 *		
	 *  Description: contains char and its' mapping code
	 */
	private static HashMap<Character, Integer> keyMap;
	
	static
	{
		try
		{
			//create a new keyboard executor
			robot = new Robot(); 
		} catch(Exception e) { e.printStackTrace(); }
		
		//fill keymap, only numbers, chars and space are supported
		keyMap = new HashMap<Character, Integer>();
		keyMap.put('0', KeyEvent.VK_0);
		keyMap.put('1', KeyEvent.VK_1);
		keyMap.put('2', KeyEvent.VK_2);
		keyMap.put('3', KeyEvent.VK_3);
		keyMap.put('4', KeyEvent.VK_4);
		keyMap.put('5', KeyEvent.VK_5);
		keyMap.put('6', KeyEvent.VK_6);
		keyMap.put('7', KeyEvent.VK_7);
		keyMap.put('8', KeyEvent.VK_8);
		keyMap.put('9', KeyEvent.VK_9);
		
		keyMap.put('a', KeyEvent.VK_A);
		keyMap.put('b', KeyEvent.VK_B);
		keyMap.put('c', KeyEvent.VK_C);
		keyMap.put('d', KeyEvent.VK_D);
		keyMap.put('e', KeyEvent.VK_E);
		keyMap.put('f', KeyEvent.VK_F);
		keyMap.put('g', KeyEvent.VK_G);
		keyMap.put('h', KeyEvent.VK_H);
		keyMap.put('i', KeyEvent.VK_I);
		keyMap.put('j', KeyEvent.VK_J);
		keyMap.put('k', KeyEvent.VK_K);
		keyMap.put('l', KeyEvent.VK_L);
		keyMap.put('m', KeyEvent.VK_M);
		keyMap.put('n', KeyEvent.VK_N);
		keyMap.put('o', KeyEvent.VK_O);
		keyMap.put('p', KeyEvent.VK_P);
		keyMap.put('q', KeyEvent.VK_Q);
		keyMap.put('r', KeyEvent.VK_R);
		keyMap.put('s', KeyEvent.VK_S);
		keyMap.put('t', KeyEvent.VK_T);
		keyMap.put('u', KeyEvent.VK_U);
		keyMap.put('v', KeyEvent.VK_V);
		keyMap.put('w', KeyEvent.VK_W);
		keyMap.put('x', KeyEvent.VK_X);
		keyMap.put('y', KeyEvent.VK_Y);
		keyMap.put('z', KeyEvent.VK_Z);
		
		keyMap.put(' ', KeyEvent.VK_SPACE);
	}
	
	/** 
	 * FunName: keyBoardExec 
	 	* Description: function to execute keyboard simulator.
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-09-25
	 */ 
	public static void keyBoardExec(String str)
	{
		char[] array = str.toCharArray();
		Integer keyEvent = -1;
		
		//iterate all characters in input string and execute corresponding keyboard input
		for(Character ch: array)
		{
			keyEvent = keyMap.get(ch);
			
			if(keyEvent != null && keyEvent > 0)
			{
				robot.keyPress(keyEvent);   
				robot.keyRelease(keyEvent); 
			}
		}
	}
	
	/** 
	 * FunName: keyBoardExecTab 
	 	* Description: function to execute Tab input
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-09-25
	 */ 
	public static void keyBoardExecTab()
	{
		robot.keyPress(KeyEvent.VK_TAB);   
		robot.keyRelease(KeyEvent.VK_TAB); 
	}
	
	/** 
	 * FunName: keyBoardExecEnter 
	 	* Description: function to execute Enter input
	 * @Author:	Wang, Fan 
	 * @Create Date: 2014-09-25
	 */ 
	public static void keyBoardExecEnter()
	{
		robot.keyPress(KeyEvent.VK_ENTER);   
		robot.keyRelease(KeyEvent.VK_ENTER); 
	}
	
}
