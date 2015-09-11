package cn.com.ambow.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */

public class Province implements java.io.Serializable {

	// Fields

	private Integer provinceId;
	private Country country;
	private String name;
	private Set contcatinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** full constructor */
	public Province(Country country, String name, Set contcatinfos) {
		this.country = country;
		this.name = name;
		this.contcatinfos = contcatinfos;
	}

	// Property accessors

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getContcatinfos() {
		return this.contcatinfos;
	}

	public void setContcatinfos(Set contcatinfos) {
		this.contcatinfos = contcatinfos;
	}

}