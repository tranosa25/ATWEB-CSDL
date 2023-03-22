package xop3d.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class groupProduct {
 @Id
 @Column(name="Id")
 private String id;
 @Column(name="Name")
 private String name;
 @Column(name="Content")
 private String content;
 @Column (name="Img")
 private String img;
 @Column (name="Brands")
 private Integer brands;
 
 @OneToMany(fetch = FetchType.EAGER, mappedBy = "GroupProduct")
 Collection<product> Product;

 public void setBrands(Integer brands) {
	this.brands = brands;
}
 public Integer getBrands() {
	return this.brands;
}
 public Collection<product> getProduct() {
	return this.Product;
}
 public void setProduct(Collection<product> product) {
	this.Product = product;
}
 public void setContent(String content) {
	this.content = content;
}
 public void setId(String id) {
	this.id = id;
}
public void setImg(String img) {
	this.img = img;
}
public void setName(String name) {
	this.name = name;
}
public void setStatus(Integer status) {
	this.brands = status;
}
public String getContent() {
	return this.content;
}
public String getId() {
	return this.id;
}
public String getImg() {
	return this.img;
}
public String getName() {
	return this.name;
}
public Integer getStatus() {
	return this.brands;
}
public groupProduct(String id, String name, String content, String img, Integer brands, Collection<product> product) {
	super();
	this.id = id;
	this.name = name;
	this.content = content;
	this.img = img;
	this.brands = brands;
	this.Product = product;
}
public groupProduct() {
	super();
	// TODO Auto-generated constructor stub
}

}
