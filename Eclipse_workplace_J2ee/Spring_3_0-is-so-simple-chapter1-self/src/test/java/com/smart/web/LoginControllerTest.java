package com.smart.web;

import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.smart.domain.User;

@ContextConfiguration(locations = { "classpath:applicationContext.xml", "file:C:/Eclipse_workplace_Java/Spring_3_0-is-so-simple-chapter1-self/src/main/webapp/WEB-INF/jsp/viewspace-servlet.xml" })
public class LoginControllerTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private AnnotationMethodHandlerAdapter handlerAdapter;
	@Autowired
	private LoginController controller;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeMethod
	public void before() {
		this.request = new MockHttpServletRequest();
		this.request.setCharacterEncoding("UTF-8");
		this.request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);

		this.response = new MockHttpServletResponse();

	}

	@Test
	public void loginCheck() throws Exception {
		this.request.setRequestURI("/admin/loginCheck.html");
		this.request.addParameter("userName", "admin"); // 设置请求URL及参数
		this.request.addParameter("password", "123456");

		// 向控制发起请求 ” /loginCheck.html”
		ModelAndView mav = this.handlerAdapter.handle(this.request, this.response, this.controller);
		User user = (User) this.request.getSession().getAttribute("user");

		assertNotNull(mav);
		assertNotNull(user);
		System.out.println(user.getUserId());

	}
}
