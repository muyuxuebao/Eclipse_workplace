package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ambow.ects.dao.impl.CountryDaoImpl;
import cn.com.ambow.ects.dao.impl.ProvinceDaoImpl;
import cn.com.ambow.ects.dao.impl.UserDaoImpl;
import cn.com.ambow.ects.entity.Contcatinfo;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerUserException;

public class SaveUserServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String cellPhone=request.getParameter("cellPhone");
		String country=request.getParameter("country");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String homePhone=request.getParameter("homePhone");
		String officePhone=request.getParameter("officePhone");
		CountryDaoImpl countryDaoImpl=new CountryDaoImpl();
		ProvinceDaoImpl provinceDaoImpl=new ProvinceDaoImpl();
		
		User user=new User();
		user.setName(name);
		user.setPassword(password);
		
		Contcatinfo contcatinfo=new Contcatinfo();
		contcatinfo.setCellPhone(cellPhone);
		contcatinfo.setCity(city);
		contcatinfo.setCountry(countryDaoImpl.findById(Integer.parseInt(country)));
		contcatinfo.setEmail(email);
		contcatinfo.setHomePhone(homePhone);
		contcatinfo.setOfficePhone(officePhone);
		
		user.setContcatinfo(contcatinfo);
		contcatinfo.setUser(user);
		
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		try {
			
			
			
			userDaoImpl.add(user);
			
			System.out.println("============================================================");
			System.out.println(user.getContcatinfo().getCountry().getName());
			System.out.println("============================================================");
			
			request.getRequestDispatcher("/registerSucceed.jsp").forward(request, response);
			
		} catch (ManagerUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
