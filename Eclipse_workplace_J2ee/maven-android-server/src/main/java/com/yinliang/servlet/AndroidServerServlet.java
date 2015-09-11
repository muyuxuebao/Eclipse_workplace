package com.yinliang.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class AndroidServerServlet
 */
public class AndroidServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidServerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			jb.append(line);
		}

		String jsonString = URLDecoder.decode(jb.toString(), "UTF-8");

		JSONObject jsonObject = new JSONObject(jsonString);

		String username = jsonObject.get("username").toString().trim();
		String password = jsonObject.get("password").toString().trim();

		System.out.println("Post " + username + ", " + password);

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		if ("test".equals(username) && "123".equals(password)) {
			out.println("登陆成功!");
		} else {
			out.println("登陆失败!");
		}

		out.flush();
		out.close();

	}

}
