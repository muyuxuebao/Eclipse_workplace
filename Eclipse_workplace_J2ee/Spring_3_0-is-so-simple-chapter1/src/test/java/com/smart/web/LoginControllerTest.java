package com.smart.web;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

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
	// 声明Request与Response模拟对象
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	// 执行测试前先初始模拟对象
	@BeforeMethod
	public void before() {
		this.request = new MockHttpServletRequest();
		this.request.setCharacterEncoding("UTF-8");
		this.response = new MockHttpServletResponse();
		this.request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true); // Spring3.1 存在的BUG

	}

	// 测试LoginController#loginCheck()方法
	@Test
	public void loginCheck() throws Exception {
		// 测试登陆成功的情况
		this.request.setRequestURI("/admin/loginCheck.html");
		this.request.addParameter("userName", "admin"); // 设置请求URL及参数
		this.request.addParameter("password", "123456");

		// 向控制发起请求 ” /loginCheck.html”
		ModelAndView mav = this.handlerAdapter.handle(this.request, this.response, this.controller);
		User user = (User) this.request.getSession().getAttribute("user");
		assertNotNull(mav);
		assertEquals(mav.getViewName(), "main");
		assertNotNull(user);
		this.request.getSession().removeAttribute("user");

		// 测试登陆失败的情况
		this.request.setRequestURI("/admin/loginCheck.html");
		this.request.addParameter("userName", "test");
		this.request.addParameter("password", "123456");

		mav = this.handlerAdapter.handle(this.request, this.response, this.controller);
		user = (User) this.request.getSession().getAttribute("user");
		assertNotNull(mav);
		assertEquals(mav.getViewName(), "login");
		assertNull(user);

	}

}
