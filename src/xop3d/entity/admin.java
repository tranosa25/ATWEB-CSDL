package xop3d.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
public class admin {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private Integer id;
	@Column(name = "AdminName")
	private String adminName;
	@Column(name= " Password")
	private String password;
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdminName() {
		return this.adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public admin(Integer id,String adminName,String password) {
		this.id=id;
		this.adminName=adminName;
		this.password=password;
	}
	public admin() {
		super();
	}
}
