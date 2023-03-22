package xop3d.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Product")
public class product {

	@Id
	@Column(name="Id")
	private String id;
	@Column(name="Name")
	private String name;
	@Column(name="Content")
	private String content;
	@Column(name="Price")
	private Integer price;
	@Column (name="Sale")
	private Integer sale;
	@Column(name="Img1")
	private String img1;
	@Column(name="Img2")
	private String img2;
	@Column(name="Img3")
	private String img3;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name="Date")
	private Date date;
	@Column(name="Coler")
	private String coler;
	@Column(name="Sold")
	private Integer sold;
	@Column(name="Status")
	private Integer status;
	@ManyToOne
	@JoinColumn(name="GroupProductId")
	private groupProduct GroupProduct;
	
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "Product")
	 Collection<productDetail> ProductDetail;
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "Product")
	 Collection<orderDetail> OderDetail;
	 
	 public Integer getStatus() {
		return status;
	}
	 public void setStatus(Integer status) {
		this.status = status;
	}
	 public Collection<orderDetail> getOderDetail() {
		return OderDetail;
	}
	 public Collection<productDetail> getProductDetail() {
		return ProductDetail;
	}
	 public void setOderDetail(Collection<orderDetail> oderDetail) {
		OderDetail = oderDetail;
	}
	 public void setProductDetail(Collection<productDetail> productDetail) {
		ProductDetail = productDetail;
	}
	 public void setSold(Integer sold) {
		this.sold = sold;
	}
	 public Integer getSold() {
		return sold;
	}
	public void setColer(String coler) {
		this.coler = coler;
	}
	public String getColer() {
		return coler;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setGroupProduct(groupProduct groupProduct) {
		GroupProduct = groupProduct;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setSale(Integer sale) {
		this.sale = sale;
	}
	public String getContent() {
		return content;
	}
	public Date getDate() {
		return date;
	}
	public groupProduct getGroupProduct() {
		return GroupProduct;
	}
	public String getId() {
		return id;
	}
	public String getImg1() {
		return img1;
	}
	public String getImg2() {
		return img2;
	}
	public String getImg3() {
		return img3;
	}
	public String getName() {
		return name;
	}
	public Integer getPrice() {
		return price;
	}
	public Integer getSale() {
		return sale;
	}
	public product(String id, String name, String content, Integer price, Integer sale, String img1, String img2,
			String img3, Date date, String coler, Integer sold, Integer status, groupProduct groupProduct,
			Collection<productDetail> productDetail, Collection<orderDetail> oderDetail) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.price = price;
		this.sale = sale;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.date = date;
		this.coler = coler;
		this.sold = sold;
		this.status = status;
		GroupProduct = groupProduct;
		ProductDetail = productDetail;
		OderDetail = oderDetail;
	}
	public product() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
