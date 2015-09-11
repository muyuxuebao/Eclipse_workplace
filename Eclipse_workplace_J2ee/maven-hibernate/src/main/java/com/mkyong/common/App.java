package com.mkyong.common;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mkyong.persistence.HibernateUtil;

public class App {
	public boolean add(Stock stock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
		return true;
	}

	public boolean delete(Stock stock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(session.get(Stock.class, stock.getStockId()));
		session.getTransaction().commit();
		return true;
	}

	public Stock findByID(String stockID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Stock stock = (Stock) session.get(Stock.class, Integer.parseInt(stockID));
		return stock;
	}

	public boolean update(Stock stock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(stock);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		return true;
	}

}