package xop3d.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductDetail")
public class productDetail {
@Id
@GeneratedValue
@Column(name="Id")
private Integer id;
@Column(name="Size")
private String size;
@Column(name="Quanlity")
private Integer quanlity;
@Column(name="Status")
private Integer status;
 @ManyToOne
 @JoinColumn(name="ProductId")
 private product Product;
public Integer getId() {
	return id;
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public void setId(Integer id) {
	this.id = id;
}

public Integer getQuanlity() {
	return quanlity;
}
public void setQuanlity(Integer quanlity) {
	this.quanlity = quanlity;
}
public product getProduct() {
	return Product;
}
public void setProduct(product product) {
	Product = product;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}

public productDetail(Integer id, String size, Integer quanlity, Integer status, product product) {
	super();
	this.id = id;
	this.size = size;
	this.quanlity = quanlity;
	this.status = status;
	Product = product;
}
public productDetail() {
	super();
	// TODO Auto-generated constructor stub
}


}
