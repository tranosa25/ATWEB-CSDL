package xop3d.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Advertise")
public class advertise {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="Link")
	private String link;
	@Column(name="Content")
	private String content;
	@Column(name="Status")
	private int status;
	@Column(name="type")
	private String type;
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return this.content;
	}
	public int getId() {
		return this.id;
	}
	public String getLink() {
		return this.link;
	}
	public String getName() {
		return this.name;
	}
	public int getStatus() {
		return this.status;
	}
	public advertise(int id, String name, String link, String content, int status,String type) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.content = content;
		this.status = status;
		this.type = type;
	}
	public advertise() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
