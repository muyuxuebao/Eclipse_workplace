package com.yinliang.ects.dao;

import com.yinliang.ects.entity.User;
import com.yinliang.ects.exception.ManagerUserException;

public interface IUserDao {
	
	public void add(User user) throws ManagerUserException;

	//public void delete(User user) throws ManagerUserException;

	public void update(User user) throws ManagerUserException;

	//public Collection findAll() throws ManagerUserException;

	public User findById(Integer id) throws ManagerUserException;

	public  User  login(String name, String passwd)throws ManagerUserException;

}
