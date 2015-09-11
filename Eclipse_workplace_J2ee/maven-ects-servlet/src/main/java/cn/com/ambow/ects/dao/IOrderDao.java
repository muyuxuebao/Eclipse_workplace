package cn.com.ambow.ects.dao;

import java.util.Collection;

import cn.com.ambow.ects.entity.Myorder;

import cn.com.ambow.ects.exception.ManagerOrderException;

public interface IOrderDao {
	
	public  void  addOrder(Myorder order)throws ManagerOrderException;
	public  Collection  findAll(String   user_name)throws ManagerOrderException;
	public  Myorder  findById(Integer id)throws  ManagerOrderException;
}
