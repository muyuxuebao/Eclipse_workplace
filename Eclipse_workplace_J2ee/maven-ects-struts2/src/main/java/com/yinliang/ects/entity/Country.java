package com.yinliang.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Country entity. @author MyEclipse Persistence Tools
 */

public class Country implements java.io.Serializable {

	// Fields

	private Integer countryId;
	private String name;
	private Set provinces = new HashSet(0);
	private Set contcatinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** full constructor */
	public Country(String name, Set provinces, Set contcatinfos) {
		this.name = name;
		this.provinces = provinces;
		this.contcatinfos = contcatinfos;
	}

	// Property accessors

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getProvinces() {
		return this.provinces;
	}

	public void setProvinces(Set provinces) {
		this.provinces = provinces;
	}

	public Set getContcatinfos() {
		return this.contcatinfos;
	}

	public void setContcatinfos(Set contcatinfos) {
		this.contcatinfos = contcatinfos;
	}

}