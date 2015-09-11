package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ambow.ects.dao.impl.ProductDaoImpl;
import cn.com.ambow.ects.entity.Product;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerProductException;

public class FindProductDetails extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product=null;
		String productId=request.getParameter("productId");
		ProductDaoImpl productDaoImpl=new ProductDaoImpl();
		try {
			product=productDaoImpl.findById(Integer.parseInt(productId));
			String img=product.getImages();
			img=product.getImages().replace(".jpg", "b.jpg");
			product.setImages(img);
			
			request.setAttribute("product", product);
			request.getRequestDispatcher("/details.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManagerProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
