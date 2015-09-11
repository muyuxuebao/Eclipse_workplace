package com.yinliang.ects.dao;

import java.util.Collection;

import com.yinliang.ects.entity.Product;
import com.yinliang.ects.exception.ManagerProductException;

public interface IProductDao {
	
	public  Product  findById(Integer  id )throws ManagerProductException;
	public  Collection   findAll( )throws ManagerProductException;
	
	
}
