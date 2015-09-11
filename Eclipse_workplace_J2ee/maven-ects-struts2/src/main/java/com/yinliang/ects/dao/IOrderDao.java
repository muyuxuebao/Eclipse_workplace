package com.yinliang.ects.dao;

import java.util.Collection;

import com.yinliang.ects.entity.Myorder;

import com.yinliang.ects.exception.ManagerOrderException;

public interface IOrderDao {
	
	public  void  addOrder(Myorder order)throws ManagerOrderException;
	public  Collection  findAll(String   user_name)throws ManagerOrderException;
	public  Myorder  findById(Integer id)throws  ManagerOrderException;
}
