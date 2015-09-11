package com.smart.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.smart.domain.User;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserService userService;

	@Test
	public void hasMatchUser() {
		boolean b1 = this.userService.hasMatchUser("admin", "123456");
		boolean b2 = this.userService.hasMatchUser("admin", "1111");
		assertTrue(b1);
		assertTrue(!b2);
	}

	@Test
	public void findUserByUserName() {
		User user = this.userService.findUserByUserName("admin");
		assertEquals(user.getUserName(), "admin");
	}

	@Test
	public void loginSuccess() {
		User user = this.userService.findUserByUserName("admin");
		user.setUserId(1);
		user.setUserName("admin");
		user.setLastIp("192.168.12.7");
		user.setLastVisit(new Date());
		this.userService.loginSuccess(user);
	}
}
