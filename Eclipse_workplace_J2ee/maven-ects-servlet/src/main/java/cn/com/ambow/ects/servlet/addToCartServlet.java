package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.ambow.ects.dao.impl.ProductDaoImpl;
import cn.com.ambow.ects.entity.Cart;
import cn.com.ambow.ects.entity.Product;
import cn.com.ambow.ects.exception.ManagerProductException;

public class addToCartServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String number=request.getParameter("number");
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null){
			request.getRequestDispatcher("/promptLogin.jsp").forward(request, response);
			return;
		}
		ProductDaoImpl productDaoImpl=new ProductDaoImpl();
		String id=request.getParameter("productId");
		Product product=null;
		try {
			product = productDaoImpl.findById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManagerProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cart.addItems(product,Integer.parseInt(number));
		session.setAttribute("cart", cart);
		System.out.println("======================"+number);
		
		request.getRequestDispatcher("/addItemSucceed .jsp").forward(request, response);
	//	System.out.println(cart);
	}
}
