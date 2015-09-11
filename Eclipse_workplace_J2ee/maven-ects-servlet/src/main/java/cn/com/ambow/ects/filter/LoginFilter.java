package cn.com.ambow.ects.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends FilterAdapter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpResquest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpResquest.getServletPath();
		System.out.println(path);
		if(request.getAttribute("user")==null){
			httpResponse.sendRedirect(httpResquest.getContextPath() + "/register.jsp");
		}
		else{
			filterChain.doFilter(request, response);
		}
		filterChain.doFilter(request, response);
		
	}
}
