package com.example.androiddemo;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestDB extends AndroidTestCase {
	public void testCreateDB() throws Exception {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(this.getContext());
		helper.getWritableDatabase();
	}

	public void testAdd() throws Exception {
		PersonDaoImpl personDaoImpl = new PersonDaoImpl(this.getContext());
		personDaoImpl.add("wangwu", "999");
	}

	public void testFind() throws Exception {
		PersonDaoImpl personDaoImpl = new PersonDaoImpl(this.getContext());
		assertEquals("wangwu", personDaoImpl.find("wangwu").getName());
	}

	public void testUpdate() throws Exception {
		PersonDaoImpl personDaoImpl = new PersonDaoImpl(this.getContext());
		personDaoImpl.update("wangwu", "119");
	}

	public void testDelete() throws Exception {
		PersonDaoImpl personDaoImpl = new PersonDaoImpl(this.getContext());
		personDaoImpl.delete("wangwu");
	}

	public void testFindAll() throws Exception {
		PersonDaoImpl personDaoImpl = new PersonDaoImpl(this.getContext());
		List<Person> persons = personDaoImpl.findAll();
		for (Person person : persons) {
			Log.d("TestDB", person.toString());
			System.out.println(person);
		}

	}

	public void testTransaction() throws Exception {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(this.getContext());
		SQLiteDatabase db = helper.getWritableDatabase();

		// 开启数据库的事务
		db.beginTransaction();
		try {
			db.execSQL("update person set account=account-1000 where name=?", new Object[] { "wangdandan" });

			db.execSQL("update person set account=account+1000 where name=?", new Object[] { "nimabi" });

			// 标记数据库事务执行成功
			db.setTransactionSuccessful();
		} catch (Exception e) {

		} finally {
			db.endTransaction();
		}

		db.close();

	}
}
