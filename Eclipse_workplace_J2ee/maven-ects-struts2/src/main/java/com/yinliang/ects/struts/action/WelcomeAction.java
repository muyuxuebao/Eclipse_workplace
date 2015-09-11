package com.yinliang.ects.struts.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yinliang.ects.dao.impl.ProductDaoImpl;
import com.yinliang.ects.entity.Product;
import com.yinliang.ects.exception.ManagerProductException;



public class WelcomeAction {
	
	
	int index_p1Id = 1;
	int index_p2Id = 4;
	int index_p3Id =4;
	int index_p4Id =5;
	int index_p5Id = 10;

	int all_p1Id = 11;
	int all_p2Id = 14;
	int all_p3Id = 15;

	int specials_p1Id = 16;
	int specials_p2Id = 25;
	int specials_p3Id = 26;
	int specials_p4Id = 27;
	
	public String add(){
		System.out.println("WelcomeAction--add");
		String message = null;
		
		ProductDaoImpl productDaoImpl = new ProductDaoImpl();

		try {
			Product index_product1 = productDaoImpl.findById(index_p1Id);
			Product index_product2 = productDaoImpl.findById(index_p2Id);
			Product index_product3 = productDaoImpl.findById(index_p3Id);
			Product index_product4 = productDaoImpl.findById(index_p4Id);
			Product index_product5 = productDaoImpl.findById(index_p5Id);

			Product all_product1 = productDaoImpl.findById(all_p1Id);
			Product all_product2 = productDaoImpl.findById(all_p2Id);
			Product all_product3 = productDaoImpl.findById(all_p3Id);

			Product specials_product1 = productDaoImpl.findById(specials_p1Id);
			Product specials_product2 = productDaoImpl.findById(specials_p2Id);
			Product specials_product3 = productDaoImpl.findById(specials_p3Id);
			Product specials_product4 = productDaoImpl.findById(specials_p4Id);

			HttpSession session = ServletActionContext.getRequest().getSession();
			

			index_product1.setImages(index_product1.getImages().replace(".jpg",
					"b.jpg"));
			index_product2.setImages(index_product2.getImages().replace(".jpg",
					"b.jpg"));
			
			session.setAttribute("index_product1", index_product1);
			session.setAttribute("index_product2", index_product2);
			session.setAttribute("index_product3", index_product3);
			session.setAttribute("index_product4", index_product4);
			session.setAttribute("index_product5", index_product5);

			session.setAttribute("all_product1", all_product1);
			session.setAttribute("all_product2", all_product2);
			session.setAttribute("all_product3", all_product3);

			specials_product1.setImages(specials_product1.getImages().replace(
					".jpg", "b.jpg"));
			specials_product2.setImages(specials_product2.getImages().replace(
					".jpg", "b.jpg"));
			specials_product3.setImages(specials_product3.getImages().replace(
					".jpg", "b.jpg"));
			specials_product4.setImages(specials_product4.getImages().replace(
					".jpg", "b.jpg"));
			session.setAttribute("specials_product1", specials_product1);
			session.setAttribute("specials_product2", specials_product2);
			session.setAttribute("specials_product3", specials_product3);
			session.setAttribute("specials_product4", specials_product4);

			message = "数据加载成功!";
			return "succeed";
		} catch (ManagerProductException e) {
			message = "系统繁忙!请稍后再试!";
			return "failed";
			
		}

	}
	
	
}
