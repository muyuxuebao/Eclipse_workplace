package com.yinliang.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Payway entity. @author MyEclipse Persistence Tools
 */

public class Payway implements java.io.Serializable {

	// Fields

	private Integer paywayId;
	private String payStyle;
	private Set myorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public Payway() {
	}

	/** full constructor */
	public Payway(String payStyle, Set myorders) {
		this.payStyle = payStyle;
		this.myorders = myorders;
	}

	// Property accessors

	public Integer getPaywayId() {
		return this.paywayId;
	}

	public void setPaywayId(Integer paywayId) {
		this.paywayId = paywayId;
	}

	public String getPayStyle() {
		return this.payStyle;
	}

	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}

	public Set getMyorders() {
		return this.myorders;
	}

	public void setMyorders(Set myorders) {
		this.myorders = myorders;
	}

}