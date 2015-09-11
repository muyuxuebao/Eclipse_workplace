package com.example.androiddemo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDaoImpl2 implements IPersonDao {
	private PersonSQLiteOpenHelper helper = null;

	public PersonDaoImpl2(Context context) {
		this.helper = new PersonSQLiteOpenHelper(context);
	}

	public void add(String name, String number) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("number", number);
		db.insert("person", null, values); // 第二个参数是用来确定当有的列没有要插入的值时,用NULL填充那一列.
		db.close();

	}

	@Override
	public void delete(String name) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		db.delete("person", "name=?", new String[] { name });
	}

	@Override
	public Person find(String name) {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		Cursor cursor = db.query("person", null, "name=?", new String[] { name }, null, null, null); // 第二个参数为null表示返回所有的列
		cursor.moveToNext();
		return new Person(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")),
				cursor.getString(cursor.getColumnIndex("number")));
	}

	@Override
	public List<Person> findAll() {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		Cursor cursor = db.query("person", null, null, null, null, null, null);
		Person person = null;
		List<Person> persons = new ArrayList<Person>();
		while (cursor.moveToNext()) {
			person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setNumber(cursor.getString(cursor.getColumnIndex("number")));
			persons.add(person);
			person = null;
		}
		cursor.close();
		db.close();
		return persons;
	}

	@Override
	public void update(String name, String newNumber) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("number", newNumber);
		db.update("person", values, "name=?", new String[] { name });
	}
}
