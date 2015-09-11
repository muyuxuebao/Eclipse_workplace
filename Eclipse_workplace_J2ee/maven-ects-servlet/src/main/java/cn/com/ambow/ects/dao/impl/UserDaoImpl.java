package cn.com.ambow.ects.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.com.ambow.ects.dao.IUserDao;
import cn.com.ambow.ects.entity.Contcatinfo;
import cn.com.ambow.ects.entity.Country;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerUserException;
import cn.com.ambow.ects.tools.HibernateUtil;

public class UserDaoImpl implements IUserDao {

	public void add(User user) throws ManagerUserException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public User findById(Integer id) throws ManagerUserException {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from User  where name='" + name + "' and password='" + password + "'";
		System.out.println(hql);
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
		Session session = HibernateUtil.getSessionFactory().openSession();
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
		user.setName("121111111");
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		Country country = new Country();
		country.setCountryId(1);

		contcatinfo.setCountry(country);
		contcatinfo.setUser(user);
		user.setContcatinfo(contcatinfo);
		userDaoImpl.add(user);
	}
}
