package com.example.androidjunitdemo.test;

import android.test.AndroidTestCase;

import com.example.androidjunitdemo.service.CalcService;

public class Test extends AndroidTestCase {
	public void testAdd() {
		CalcService service = new CalcService();
		int result = service.add(3, 8);
		assertEquals(11, result);
	}
}
