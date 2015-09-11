package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.ambow.ects.dao.impl.OrderDaoImpl;
import cn.com.ambow.ects.dao.impl.UserDaoImpl;
import cn.com.ambow.ects.entity.Cart;
import cn.com.ambow.ects.entity.Myorder;
import cn.com.ambow.ects.entity.Orderstatus;
import cn.com.ambow.ects.entity.Payway;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerOrderException;
import cn.com.ambow.ects.exception.ManagerUserException;

public class AddOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			HttpSession session = request.getSession();
			int payWayId = Integer.parseInt(request.getParameter("paywayId"));
			User user = (User) session.getAttribute("user");
			Cart cart = (Cart) session.getAttribute("cart");
			double cost = cart.getMoney();

			SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEEE-MMMM-dd-yyyy");
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
		} catch (ManagerOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		request.getRequestDispatcher("/order.jsp").forward(request, response);
	}
}
