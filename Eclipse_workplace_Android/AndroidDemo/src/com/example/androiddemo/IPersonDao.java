package com.example.androiddemo;

import java.util.List;

public interface IPersonDao {

	List<Person> findAll();

	void update(String name, String newNumber);

	Person find(String name);

	void delete(String name);

}
