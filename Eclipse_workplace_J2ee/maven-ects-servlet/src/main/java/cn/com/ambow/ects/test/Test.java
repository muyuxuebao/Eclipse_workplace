package cn.com.ambow.ects.test;

import java.util.List;

import cn.com.ambow.ects.dao.impl.OrderDaoImpl;
import cn.com.ambow.ects.dao.impl.ProductDaoImpl;
import cn.com.ambow.ects.dao.impl.UserDaoImpl;
import cn.com.ambow.ects.entity.Myorder;
import cn.com.ambow.ects.entity.Product;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerOrderException;
import cn.com.ambow.ects.exception.ManagerProductException;
import cn.com.ambow.ects.exception.ManagerUserException;

public class Test {
	public static void main(String[] args) throws ManagerUserException,
			ManagerProductException, ManagerOrderException {
		ProductDaoImpl productDaoImpl=new ProductDaoImpl();
		List<Product>products=(List<Product>) productDaoImpl.findByPageId(1);
		for (Product product : products) {
			System.out.println(product.getProductId()+"   "+product.getImages());
		}
	//	System.out.println(productDaoImpl.getPageCount());

	}
}
