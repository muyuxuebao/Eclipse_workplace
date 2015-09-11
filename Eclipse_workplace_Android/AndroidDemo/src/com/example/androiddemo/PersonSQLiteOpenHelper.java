package com.example.androiddemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {
	/**
	 * 数据库的构造方法 用来定义数据库的名称, 数据库查询的结果集, 数据库的版本
	 * 
	 * @param context
	 */
	public PersonSQLiteOpenHelper(Context context) {
		super(context, "person.db", null, 1); // 第三个参数为 null
												// 表示使用系统默认的cursor对象去遍历结果集
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person (id integer primary key autoincrement , name varchar(20) , number varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
