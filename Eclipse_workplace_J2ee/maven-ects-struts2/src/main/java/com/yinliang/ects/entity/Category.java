package com.yinliang.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer categoryId;
	private String name;
	private String descriptions;
	private Set products = new HashSet(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String name, String descriptions, Set products) {
		this.name = name;
		this.descriptions = descriptions;
		this.products = products;
	}

	// Property accessors

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}