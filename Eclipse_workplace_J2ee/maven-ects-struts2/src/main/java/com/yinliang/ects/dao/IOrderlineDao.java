package com.yinliang.ects.dao;

import java.util.Collection;

import com.yinliang.ects.entity.Orderline;

public interface IOrderlineDao {
	public void add(Orderline orderline) ;
	public Collection findByOrderId(int orderId);
}
