package cn.com.ambow.ects.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.com.ambow.ects.dao.IProvinceDao;
import cn.com.ambow.ects.entity.Country;
import cn.com.ambow.ects.entity.Province;

public class ProvinceDaoImpl implements IProvinceDao {

	public Collection findAll() {
		List<Country> provinceList = null;
		String hql = "from Province";
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		provinceList = query.list();
		return provinceList;
	}
	public Province findById(int id){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Province province=null;
		province=(Province) session.get(Province.class, id);
		return province;
	}
	public static void main(String[] args) {
		ProvinceDaoImpl provinceDaoImpl=new ProvinceDaoImpl();
		List<Province>provincelList=(List<Province>) provinceDaoImpl.findAll();
		for (Province province : provincelList) {
			String string="<option value ='"+province.getProvinceId()+"'>"+province.getName()+"</option>";
			System.out.println(string);
		}
	}
}
