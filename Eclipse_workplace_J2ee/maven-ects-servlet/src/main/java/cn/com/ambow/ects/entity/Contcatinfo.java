package cn.com.ambow.ects.entity;

/**
 * Contcatinfo entity. @author MyEclipse Persistence Tools
 */

public class Contcatinfo implements java.io.Serializable {

	// Fields

	private Integer contcatinfoId;
	private Province province;
	private User user;
	private Country country;
	private String street1;
	private String street2;
	private String city;
	private String zip;
	private String email;
	private String homePhone;
	private String cellPhone;
	private String officePhone;

	// Constructors

	/** default constructor */
	public Contcatinfo() {
	}

	/** minimal constructor */
	public Contcatinfo(Integer contcatinfoId, User user) {
		this.contcatinfoId = contcatinfoId;
		this.user = user;
	}

	/** full constructor */
	public Contcatinfo(Integer contcatinfoId, Province province, User user,
			Country country, String street1, String street2, String city,
			String zip, String email, String homePhone, String cellPhone,
			String officePhone) {
		this.contcatinfoId = contcatinfoId;
		this.province = province;
		this.user = user;
		this.country = country;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.zip = zip;
		this.email = email;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.officePhone = officePhone;
	}

	// Property accessors

	public Integer getContcatinfoId() {
		return this.contcatinfoId;
	}

	public void setContcatinfoId(Integer contcatinfoId) {
		this.contcatinfoId = contcatinfoId;
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStreet1() {
		return this.street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return this.street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

}