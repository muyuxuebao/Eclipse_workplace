package cn.com.ambow.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Myorder entity. @author MyEclipse Persistence Tools
 */

public class Myorder implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private User user;
	private Orderstatus orderstatus;
	private Payway payway;
	private String name;
	private Double cost;
	private Set orderlines = new HashSet(0);

	// Constructors

	/** default constructor */
	public Myorder() {
	}

	/** full constructor */
	public Myorder(User user, Orderstatus orderstatus, Payway payway,
			String name, Double cost, Set orderlines) {
		this.user = user;
		this.orderstatus = orderstatus;
		this.payway = payway;
		this.name = name;
		this.cost = cost;
		this.orderlines = orderlines;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Payway getPayway() {
		return this.payway;
	}

	public void setPayway(Payway payway) {
		this.payway = payway;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Set getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(Set orderlines) {
		this.orderlines = orderlines;
	}

}