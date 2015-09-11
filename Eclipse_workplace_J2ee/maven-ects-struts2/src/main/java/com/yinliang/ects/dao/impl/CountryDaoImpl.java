package com.yinliang.ects.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yinliang.ects.dao.ICountryDao;
import com.yinliang.ects.entity.Country;
import com.yinliang.ects.entity.User;
import com.yinliang.ects.exception.ManagerProductException;

public class CountryDaoImpl implements ICountryDao {

	public Collection findAll() {

		List<Country> countryList = null;
		String hql = "from Country";
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		countryList = query.list();
		return countryList;
	}
	public Country findById(int id){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Country country=null;
		country=(Country) session.get(Country.class, id);
		return country;
	}
	public static void main(String[] args) {
		CountryDaoImpl countryDaoImpl=new CountryDaoImpl();
		List<Country>countrielist=(List<Country>) countryDaoImpl.findAll();
		for (Country country : countrielist) {
			System.out.println(country.getName());
		}
	}
}
