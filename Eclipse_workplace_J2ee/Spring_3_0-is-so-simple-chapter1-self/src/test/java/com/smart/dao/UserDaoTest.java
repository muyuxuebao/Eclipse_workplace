package com.smart.dao;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.smart.domain.User;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserDaoTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private UserDao userDao;

	@Test
	public void hasMatchUser() {
		int count = this.userDao.getMatchCount("admin", "123456");
		assertTrue(count > 0);
	}

	@Test
	public void findUserByUserName() {
		User user = this.userDao.findUserByUserName("admin");
		assertNotNull(user);
		assertEquals(user.getUserName(), "admin");
	}
}
