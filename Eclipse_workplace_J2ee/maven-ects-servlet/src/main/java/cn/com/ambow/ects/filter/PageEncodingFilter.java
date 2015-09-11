package cn.com.ambow.ects.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PageEncodingFilter implements Filter{
	String charset=null;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		
		
		filterChain.doFilter(request, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		charset=filterConfig.getInitParameter("encode");

		
	}

}
