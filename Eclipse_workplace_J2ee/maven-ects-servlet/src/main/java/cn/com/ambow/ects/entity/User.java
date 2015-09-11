package cn.com.ambow.ects.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	

	private String name;
	private String password;
	private Contcatinfo contcatinfo;
	private Set<Myorder> myorders = new HashSet<Myorder>();

	// Constructors

	/** default constructor */
	public User() {
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public User(Integer userId, String name, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
	}
	/** full constructor */
	public User(String name, String password, Contcatinfo contcatinfo,
			Set myorders) {
		this.name = name;
		this.password = password;
		this.contcatinfo = contcatinfo;
		this.myorders = myorders;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}
	
	public void addOrder(Myorder myorder) {
		myorders.add(myorder);
		
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Contcatinfo getContcatinfo() {
		return this.contcatinfo;
	}

	public void setContcatinfo(Contcatinfo contcatinfo) {
		this.contcatinfo = contcatinfo;
	}

	public Set getMyorders() {
		return this.myorders;
	}

	public void setMyorders(Set myorders) {
		this.myorders = myorders;
	}

}