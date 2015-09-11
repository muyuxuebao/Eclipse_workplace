package com.mkyong.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
	private App app = new App();

	@Test
	public void testAdd() {
		boolean tag = true;
		for (int i = 0; i < 10; i++) {
			Stock stock = new Stock();
			stock.setStockCode("StockCode" + i);
			stock.setStockName("StockName" + i);
			if (this.app.add(stock) == false) {
				tag = false;
				break;
			}
		}
		assertEquals(true, tag);
	}

	@Test
	public void testDelete() {
		Stock stock = new Stock();
		stock.setStockId(2);
		assertEquals(true, this.app.delete(stock));
	}

	@Test
	public void testFindByID() {
		Stock stock = this.app.findByID("1");
		assertEquals(1, (int) stock.getStockId());
	}

	@Test
	public void testUpdate() {
		Stock stock = this.app.findByID("1");
		stock.setStockCode("aaaaaaa");
		this.app.update(stock);

		assertEquals("aaaaaaa", this.app.findByID("1").getStockCode());
	}
}
