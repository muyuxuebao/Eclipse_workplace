package cn.com.ambow.ects.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.ambow.ects.dao.impl.UserDaoImpl;
import cn.com.ambow.ects.entity.Contcatinfo;
import cn.com.ambow.ects.entity.Country;
import cn.com.ambow.ects.entity.Province;
import cn.com.ambow.ects.entity.User;
import cn.com.ambow.ects.exception.ManagerUserException;

public class AlterUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String cellPhone = request.getParameter("cellPhone");
		String countryId = request.getParameter("country");
		String provinceId = request.getParameter("province");
		String city = request.getParameter("city");
		String homePhone = request.getParameter("homePhone");
		String officePhone = request.getParameter("officePhone");
		Contcatinfo contcatinfo = new Contcatinfo();
		User user = new User();
		Country country = new Country();
		Province province = new Province();

		country.setCountryId(Integer.parseInt(countryId));
		province.setProvinceId(Integer.parseInt(provinceId));

		contcatinfo.setCellPhone(cellPhone);
		contcatinfo.setCity(city);
		contcatinfo.setCountry(country);
		contcatinfo.setEmail(email);
		contcatinfo.setHomePhone(homePhone);
		contcatinfo.setOfficePhone(officePhone);
		contcatinfo.setContcatinfoId(userId); // 少了行,会报错,所以在更新时必须设置从表的主键
		contcatinfo.setProvince(province);

		user.setUserId(userId);
		user.setName(name);
		user.setPassword(password);

		user.setContcatinfo(contcatinfo);
		contcatinfo.setUser(user);

		UserDaoImpl userDaoImpl = new UserDaoImpl();
		try {
			userDaoImpl.update(user);
		} catch (ManagerUserException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/alterUserSucced.jsp").forward(request, response);
		
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("cart");
		session.invalidate();
		ServletContext context = getServletContext();
		
		
		
		
		
	}

}
