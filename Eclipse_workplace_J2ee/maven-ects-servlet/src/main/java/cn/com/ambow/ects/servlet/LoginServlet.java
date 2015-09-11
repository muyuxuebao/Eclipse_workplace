package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;

import cn.com.ambow.ects.dao.impl.UserDaoImpl;
import cn.com.ambow.ects.entity.Cart;
import cn.com.ambow.ects.entity.Product;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerUserException;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = request.getParameter("loginname");
		String longinPassword = request.getParameter("loginpassword");
		User user = null;
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		try {
			user = userDaoImpl.login(loginName, longinPassword);
		} catch (ManagerUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if (user == null) {
			request.getRequestDispatcher("/loginFiled.jsp").forward(request,
					response);
		} else {
			Hibernate.initialize(user.getContcatinfo().getCountry());
			Hibernate.initialize(user.getContcatinfo().getProvince());
			Cart cart = new Cart();
			cart.setUser(user);
			session.setAttribute("cart", cart);
			session.setAttribute("user", user);
			request.getRequestDispatcher("/loginSucceed.jsp").forward(request,
					response);
		}

	}

}
