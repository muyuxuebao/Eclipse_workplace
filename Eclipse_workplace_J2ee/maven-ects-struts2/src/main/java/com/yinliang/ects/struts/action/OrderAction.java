package com.yinliang.ects.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

import com.yinliang.ects.dao.impl.OrderDaoImpl;
import com.yinliang.ects.dao.impl.OrderlineDaoImpl;
import com.yinliang.ects.dao.impl.UserDaoImpl;
import com.yinliang.ects.entity.Cart;
import com.yinliang.ects.entity.Myorder;
import com.yinliang.ects.entity.Orderline;
import com.yinliang.ects.entity.Orderstatus;
import com.yinliang.ects.entity.Payway;
import com.yinliang.ects.entity.Product;
import com.yinliang.ects.entity.User;
import com.yinliang.ects.exception.ManagerOrderException;


public class OrderAction{
	HttpServletRequest request=ServletActionContext.getRequest();
	
	public String add() {
		String message = null;
		
		try {
			OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			HttpSession session = request.getSession();
			int payWayId = Integer.parseInt(request.getParameter("paywayId"));
			User user = (User) session.getAttribute("user");
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart==null){
				message="您尚没有登录!请先登录!";
				request.setAttribute("message", message);
				
				request.setAttribute("message", message);
				return "failed";
			}
			if(cart.getProductsMap().size()==0){
				message="您尚没有购买任何商品!";
				request.setAttribute("message", message);
				
				request.setAttribute("message", message);
				return "failed";
			}
			double cost = cart.getMoney();

			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/mm/dd");
			Date date = new Date();
			String dataTime=bartDateFormat.format(date);
	
			
			
			
			
			
			
			
			

		
			

			Payway payway = new Payway();
			payway.setPaywayId(payWayId);

			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(1);

			Myorder order = new Myorder();
			order.setCost(cost);
			order.setName(dataTime);
			order.setOrderstatus(orderstatus);
			order.setPayway(payway);
			order.setUser(user);
			orderDaoImpl.addOrder(order);
		

			

			List<Myorder> orderList = (List<Myorder>) orderDaoImpl
					.findAll(user.getUserId());

			double Total = 0;
			for (Myorder myorder : orderList) {
				Total += myorder.getCost();
			}
			request.setAttribute("Total", Total);
			request.setAttribute("orderList", orderList);
			
			
			
			
			
			
			
			OrderlineDaoImpl orderlineDaoImpl=new OrderlineDaoImpl();
			Set<Product> set=cart.getProductsMap().keySet();
			
			Orderline orderline=new Orderline();
			for(Product product:set){
				orderline.setMyorder(order);
				orderline.setProduct(product);
				orderline.setAmount(cart.getProductsMap().get(product));
				orderlineDaoImpl.add(orderline);
			}
			cart.removeAll();
			request.getSession().setAttribute("cart", cart);
			message="添加订单成功!";
			request.setAttribute("message", message);
			return "succeed";
			

			
			
		} catch (ManagerOrderException e) {
			message="添加订单失败!";
			request.setAttribute("message", message);
			return "failed";
		} 
		
		
	}
	public String FindOrderDetails() {
		String message = null;
		try {
			int orderId=Integer.parseInt((String) request.getParameter(("orderId")));
			
			OrderlineDaoImpl orderlineDaoImpl=new OrderlineDaoImpl();
			List<Orderline>orderlines=(List<Orderline>) orderlineDaoImpl.findByOrderId(orderId);
			
			OrderDaoImpl orderDaoImpl=new OrderDaoImpl();
			Myorder order=orderDaoImpl.findById(orderId);
			
			request.setAttribute("order", order);
			request.setAttribute("orderlines", orderlines);
			
			message="数据加载成功!!!";
			request.setAttribute("message", message);
			return "succeed";
			
		} catch (Exception e) {
			message="数据加载失败!!!";
			request.setAttribute("message", message);
			return "failed";
		}
		
	}
}
