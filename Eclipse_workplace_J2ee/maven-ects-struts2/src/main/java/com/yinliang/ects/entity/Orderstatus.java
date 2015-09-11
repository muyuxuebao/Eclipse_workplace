package com.yinliang.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Orderstatus entity. @author MyEclipse Persistence Tools
 */

public class Orderstatus implements java.io.Serializable {

	// Fields

	private Integer orderStatusId;
	private String name;
	private String descriptions;
	private Set myorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public Orderstatus() {
	}

	/** full constructor */
	public Orderstatus(String name, String descriptions, Set myorders) {
		this.name = name;
		this.descriptions = descriptions;
		this.myorders = myorders;
	}

	// Property accessors

	public Integer getOrderStatusId() {
		return this.orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Set getMyorders() {
		return this.myorders;
	}

	public void setMyorders(Set myorders) {
		this.myorders = myorders;
	}

}