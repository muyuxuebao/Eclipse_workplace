package com.yinliang.ects.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yinliang.ects.dao.IProductDao;
import com.yinliang.ects.entity.Product;
import com.yinliang.ects.entity.User;
import com.yinliang.ects.exception.ManagerProductException;

public class ProductDaoImpl implements IProductDao {
	int pageSize=12;
	
	
	public int getPageCount() throws ManagerProductException {
		List productList = null;
		String hql = "select count(product) from Product product";
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query query=session.createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString())/pageSize+1;
		
	}
	public Collection findByPageId(int pageId) throws ManagerProductException {
		List productList = null;
		String sql = "select * from ects.product limit "+(pageId-1)*pageSize+",12";
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sql).addEntity(Product.class);
		productList = query.list();
		return productList;
	}

	public Collection findAll() throws ManagerProductException {
		List productList = null;
		String hql = "from Product";
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		query = session.createQuery(hql);
		productList = query.list();
		return productList;
	}

	public Product findById(Integer id) throws ManagerProductException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Product product = null;
		try {
			product = (Product) session.get(Product.class, id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return product;
	}
	

}
