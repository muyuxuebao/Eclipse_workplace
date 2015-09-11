package com.yinliang.maven.maven_jdbc_mysql;

import junit.framework.TestCase;

public class JDBCDemoTest extends TestCase {

	private JDBCDemo jdbcDemo = new JDBCDemo();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testOpenConnection() {
		assertEquals(true, this.jdbcDemo.openConnection());
	}

}
