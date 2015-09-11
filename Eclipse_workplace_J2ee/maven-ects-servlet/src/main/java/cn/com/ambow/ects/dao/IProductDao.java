package cn.com.ambow.ects.dao;

import java.util.Collection;

import cn.com.ambow.ects.entity.Product;
import cn.com.ambow.ects.exception.ManagerProductException;

public interface IProductDao {
	
	public  Product  findById(Integer  id )throws ManagerProductException;
	public  Collection   findAll( )throws ManagerProductException;
	
	
}
