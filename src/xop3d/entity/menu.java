package xop3d.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class menu {
@Id
//@Column(name ="name")
//private String name;
//public String getName() {
//	return name;
//}
//public void setName(String name) {
//	this.name = name;
//}
//public menu(String name) {
//	super();
//	this.name = name;
//}
//public menu() {
//	super();
//	// TODO Auto-generated constructor stub
//}
@GeneratedValue
@Column(name = "Id")
private int id;
@Column(name = "Name")
private String name;
@Column (name = "Link")
private String link;
@Column (name = "ParentId")
private Integer parentid;
public int getId() {
	return id;
}
public String getLink() {
	return link;
}
public String getName() {
	return name;
}
public Integer getParentid() {
	return parentid;
}
public void setId(int id) {
	this.id = id;
}public void setLink(String link) {
	this.link = link;
}
public void setName(String name) {
	this.name = name;
}
public void setParentid(Integer parentid) {
	this.parentid = parentid;
}
public menu(int id, String name, String link, Integer parentid) {
	super();
	this.id = id;
	this.name = name;
	this.link = link;
	this.parentid = parentid;
}
public menu() {
	super();
	// TODO Auto-generated constructor stub
}

}