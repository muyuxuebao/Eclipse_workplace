package com.yinliang.ects.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yinliang.ects.dao.IOrderlineDao;
import com.yinliang.ects.entity.Myorder;
import com.yinliang.ects.entity.Orderline;
import com.yinliang.ects.entity.Product;

public class OrderlineDaoImpl implements IOrderlineDao {
	public void add(Orderline orderline) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(orderline);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	public Collection findByOrderId(int orderId) {
		List<Orderline>orderlines=null;
	
		String hql = "from Orderline orderline where orderline.myorder.orderId='"+orderId+"'";
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		query = session.createQuery(hql);
		orderlines = query.list();
		
		return orderlines;
	}
	public static void main(String[] args) {
		Product product=new Product();
		product.setProductId(1);
		Myorder order=new Myorder();
		order.setOrderId(23);
		
		Orderline orderline=new Orderline();
		
		orderline.setMyorder(order);
		orderline.setProduct(product);
		orderline.setAmount(3);
		
		
		OrderlineDaoImpl orderlineDaoImpl=new OrderlineDaoImpl();
		orderlineDaoImpl.add(orderline);
		
		
	}
}
