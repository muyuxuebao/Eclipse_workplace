package com.example.androidjunitdemo.service;

import android.util.Log;

public class CalcService {
	String tag = "CalcService";

	public int add(int x, int y) {
		int reslut = x + y;

		Log.v(this.tag, "x=" + x); // 打印 Verbose 日志
		Log.d(this.tag, "y=" + y); // 打印 Debug 日志
		Log.i(this.tag, "reslut=" + reslut); // 打印 Info 日志
		Log.w(this.tag, "reslut=" + reslut); // 打印 Warm 日志
		Log.e(this.tag, "reslut=" + reslut); // 打印 Error 日志

		return x + y;
	}
}
