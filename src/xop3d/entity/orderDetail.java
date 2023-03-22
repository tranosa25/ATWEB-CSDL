package xop3d.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderDetail")
public class orderDetail {
 @Id
 @GeneratedValue
 @Column(name="Id")
 private Integer id;
 @ManyToOne
 @JoinColumn(name="OrderId")
 private order Order;
 @ManyToOne
 @JoinColumn(name="ProductId")
 private product Product;
 @Column(name="Quanlity")
 private Integer quanlity;
 @Column(name="Size")
 private String size;
 public void setSize(String size) {
	this.size = size;
}
 public String getSize() {
	return size;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public order getOrder() {
	return Order;
}
public void setOrder(order order) {
	Order = order;
}
public product getProduct() {
	return Product;
}
public void setProduct(product product) {
	Product = product;
}
public Integer getQuanlity() {
	return quanlity;
}
public void setQuanlity(Integer quanlity) {
	this.quanlity = quanlity;
}

public orderDetail(Integer id, order order, product product, Integer quanlity, String size) {
	super();
	this.id = id;
	Order = order;
	Product = product;
	this.quanlity = quanlity;
	this.size = size;
}
public orderDetail() {
	super();
	// TODO Auto-generated constructor stub
}
 
}
