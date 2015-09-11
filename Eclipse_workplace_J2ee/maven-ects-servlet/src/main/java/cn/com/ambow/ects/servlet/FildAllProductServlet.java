package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ambow.ects.dao.impl.ProductDaoImpl;
import cn.com.ambow.ects.entity.Product;
import cn.com.ambow.ects.exception.ManagerProductException;

public class FildAllProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDaoImpl productDaoImpl=new ProductDaoImpl();
		int nowCount=1;
		int pageCount=0;
		List<Product>productsList=null;
		
		if(request.getParameter("pageCount")!=null){
			pageCount=Integer.parseInt(request.getParameter("pageCount"));
		}else {
			try {
				pageCount=productDaoImpl.getPageCount();
			} catch (ManagerProductException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("nowCount")!=null){
			nowCount=Integer.parseInt(request.getParameter("nowCount"));
			if(nowCount<1){
				nowCount=1;
			}
			if(nowCount>pageCount){
				nowCount=pageCount;
			}
		}
		try {
			productsList=(List<Product>) productDaoImpl.findByPageId(nowCount);
			
		} catch (ManagerProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("productsList", productsList);
		request.setAttribute("nowCount", nowCount);
		System.out.println(nowCount);
		
		request.getRequestDispatcher("/category.jsp").forward(request, response);
	}

}
