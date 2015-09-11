package cn.com.ambow.ects.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import cn.com.ambow.ects.dao.IOrderDao;
import cn.com.ambow.ects.entity.Myorder;
import cn.com.ambow.ects.entity.User;

import cn.com.ambow.ects.exception.ManagerOrderException;

public class OrderDaoImpl implements IOrderDao {

	public void addOrder(Myorder myorder) throws ManagerOrderException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(myorder);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		}finally{
			session.close();
		}
		
	}
	public Collection findAll(int userId) throws ManagerOrderException{
		
		List orderList = null;
		String hql = "select myorder from Myorder as myorder where myorder.user.userId='"
				+ userId + "'";
		System.out.println(hql);
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		query = session.createQuery(hql);
		orderList = query.list();
		return orderList;
	}
	public Collection findAll(String userName) throws ManagerOrderException {
		List orderList = null;
		String hql = "select myorder from Myorder as myorder where myorder.user.name='"
				+ userName + "'";
		System.out.println(hql);
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		query = session.createQuery(hql);
		orderList = query.list();
		return orderList;
	}

	public Myorder findById(Integer id) throws ManagerOrderException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Myorder myorder = null;
		try {
			myorder = (Myorder) session.get(Myorder.class, id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return myorder;
	}
	public static void main(String[] args) throws ManagerOrderException {
		OrderDaoImpl orderDaoImpl=new OrderDaoImpl();
		List<Myorder> orders=(List<Myorder>) orderDaoImpl.findAll(46);
		for (Myorder myorder : orders) {
			System.out.println(myorder.getName());
		}
	}

}
