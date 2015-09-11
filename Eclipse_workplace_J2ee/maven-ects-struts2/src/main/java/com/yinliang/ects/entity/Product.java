package com.yinliang.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer productId;
	private Category category;
	private String name;
	private String author;
	private String publish;
	private Integer pages;
	private String images;
	private Double basePrice;
	private String descriptions;
	private Set orderlines = new HashSet(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		
		if (productId == other.getProductId()) {
			return true;
		} 
		return false;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", productId=" + productId + "]";
	}

	/** full constructor */
	public Product(Category category, String name, String author,
			String publish, Integer pages, String images, Double basePrice,
			String descriptions, Set orderlines) {
		this.category = category;
		this.name = name;
		this.author = author;
		this.publish = publish;
		this.pages = pages;
		this.images = images;
		this.basePrice = basePrice;
		this.descriptions = descriptions;
		this.orderlines = orderlines;
	}

	// Property accessors

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return this.publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public Integer getPages() {
		return this.pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Double getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Set getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(Set orderlines) {
		this.orderlines = orderlines;
	}

}