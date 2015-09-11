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

public class DeleteCartItems extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		
		
		
		if(request.getParameter("deleteAll")!=null){
			cart.removeAll();
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
			return;
		}
		
		
		
		Product product=null ;
		int productId=Integer.parseInt(request.getParameter("itemKeyId"));
		
		ProductDaoImpl productDaoImpl=new ProductDaoImpl();
		try {
			product=productDaoImpl.findById(productId);
		} catch (ManagerProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		cart.removeItems(product);
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}
}
