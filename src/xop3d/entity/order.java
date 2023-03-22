package xop3d.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="[Order]")
public class order {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name="CustomerId")
	private customer Customer;
	@Column(name="Total")
	private Float total;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name="Date")
	private Date date;
	@Column(name="Status")
	private Integer status;
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "Order")
	 Collection<orderDetail> OrderDetail;
	 @ManyToOne
	 @JoinColumn(name="Username")
	 private user User;
	 public void setOrderDetail(Collection<orderDetail> orderDetail) {
		OrderDetail = orderDetail;
	}
	 public void setUser(user user) {
		User = user;
	}
	 public Collection<orderDetail> getOrderDetail() {
		return OrderDetail;
	}
	 public user getUser() {
		return User;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public customer getCustomer() {
		return Customer;
	}
	public void setCustomer(customer customer) {
		Customer = customer;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public order(Integer id, customer customer, Float total, Date date, Integer status,
			Collection<orderDetail> orderDetail, user user) {
		super();
		this.id = id;
		Customer = customer;
		this.total = total;
		this.date = date;
		this.status = status;
		OrderDetail = orderDetail;
		User = user;
	}
	public order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
