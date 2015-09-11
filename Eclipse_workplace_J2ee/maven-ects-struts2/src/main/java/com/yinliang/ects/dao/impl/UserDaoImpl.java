package com.yinliang.ects.dao.impl;

import com.yinliang.ects.dao.IUserDao;
import com.yinliang.ects.entity.Contcatinfo;
import com.yinliang.ects.entity.Country;
import com.yinliang.ects.entity.User;
import com.yinliang.ects.exception.ManagerUserException;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDaoImpl implements IUserDao {


	public void add(User user) throws ManagerUserException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public User findById(Integer id) throws ManagerUserException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User user = null;

		try {
			user = (User) session.get(User.class, id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return user;
	}

	public User login(String name, String password) throws ManagerUserException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from User  where name='" + name + "' and password='"
				+ password + "'";
		User user = null;
		Query query = session.createQuery(hql);

		// jdbc index 1
		// hiberante index 0
		user = (User) query.uniqueResult();
		try {
			// session.save();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return user;
	}

	public void update(User user) throws ManagerUserException {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) throws ManagerUserException {
		Contcatinfo contcatinfo = new Contcatinfo();
		contcatinfo.setEmail("45678989@123.com");
		User user = new User();
		user.setName("¿ñ");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		Country country=new Country();
		country.setName("ÈÕ±¾");
		contcatinfo.setCountry(country);
		contcatinfo.setUser(user);
		user.setContcatinfo(contcatinfo);
		userDaoImpl.add(user);
	}
}





