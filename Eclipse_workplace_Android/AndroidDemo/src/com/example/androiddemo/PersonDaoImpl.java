package com.example.androiddemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDaoImpl implements IPersonDao {
	private PersonSQLiteOpenHelper helper = null;

	public PersonDaoImpl(Context context) {
		this.helper = new PersonSQLiteOpenHelper(context);
	}

	public void add(String name, String number) {
		SQLiteDatabase db = this.helper.getReadableDatabase();

		db.execSQL("insert into person values (null , ?, ?)", new Object[] { name, number });

		db.close();

	}

	@Override
	public void update(String name, String newNumber) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		db.execSQL("update person set number = ? where name = ?", new String[] { newNumber, name });
		db.close();
	}

	@Override
	public Person find(String name) {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where name=?", new String[] { name });
		Person person = new Person();
		cursor.moveToNext();

		person.setId(cursor.getInt(cursor.getColumnIndex("id")));
		person.setName(cursor.getString(cursor.getColumnIndex("name")));
		person.setNumber(cursor.getString(cursor.getColumnIndex("number")));

		cursor.close();
		db.close();
		return person;
	}

	@Override
	public void delete(String name) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		db.execSQL("delete from person where name = ?", new String[] { name });
		db.close();
	}

	@Override
	public List<Person> findAll() {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person ", null);
		Person person;
		List<Person> persons = new ArrayList<Person>();
		while (cursor.moveToNext()) {
			person = new Person();

			person.setId(cursor.getInt(cursor.getColumnIndex("id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setNumber(cursor.getString(cursor.getColumnIndex("number")));
			persons.add(person);
		}
		cursor.close();
		db.close();
		return persons;
	}
}
