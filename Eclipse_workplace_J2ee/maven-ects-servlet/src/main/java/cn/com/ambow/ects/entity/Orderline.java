package cn.com.ambow.ects.entity;

/**
 * Orderline entity. @author MyEclipse Persistence Tools
 */

public class Orderline implements java.io.Serializable {

	// Fields

	private Integer orderLineId;
	private Product product;
	private Myorder myorder;
	private Integer amount;

	// Constructors

	/** default constructor */
	public Orderline() {
	}

	/** full constructor */
	public Orderline(Product product, Myorder myorder, Integer amount) {
		this.product = product;
		this.myorder = myorder;
		this.amount = amount;
	}

	// Property accessors

	public Integer getOrderLineId() {
		return this.orderLineId;
	}

	public void setOrderLineId(Integer orderLineId) {
		this.orderLineId = orderLineId;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Myorder getMyorder() {
		return this.myorder;
	}

	public void setMyorder(Myorder myorder) {
		this.myorder = myorder;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}