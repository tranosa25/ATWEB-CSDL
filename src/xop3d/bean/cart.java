package xop3d.bean;

public class cart {
	private String idSanPham;
	private String tenSanPham;
	private String ColerSanPham;
	private float giaSanPham;
	private int soLuong;
	private String image;
	private String size;
	public String getSize() {
		return this.size;
	}
	public void setSize(String size2) {
		this.size = size2;
	}
	public String getIdSanPham() {
		return this.idSanPham;
	}
	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}
	public String getTenSanPham() {
		return this.tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public float getGiaSanPham() {
		return this.giaSanPham;
	}
	public void setGiaSanPham(float giaSanPham) {
		this.giaSanPham = giaSanPham;
	}
	public int getSoLuong() {
		return this.soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getColerSanPham() {
		return this.ColerSanPham;
	}
	public void setColerSanPham(String colerSanPham) {
		this.ColerSanPham = colerSanPham;
	}
	public cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public cart(String idSanPham, String tenSanPham, String colerSanPham, float giaSanPham, int soLuong, String image) {
		super();
		this.idSanPham = idSanPham;
		this.tenSanPham = tenSanPham;
		this.ColerSanPham = colerSanPham;
		this.giaSanPham = giaSanPham;
		this.soLuong = soLuong;
		this.image = image;
	}
	public cart(String idSanPham, String tenSanPham, String colerSanPham, float giaSanPham, int soLuong, String image,
			String size) {
		super();
		this.idSanPham = idSanPham;
		this.tenSanPham = tenSanPham;
		this.ColerSanPham = colerSanPham;
		this.giaSanPham = giaSanPham;
		this.soLuong = soLuong;
		this.image = image;
		this.size = size;
	}
	public cart(int soLuong, String size) {
		super();
		this.soLuong = soLuong;
		this.size = size;
	}
	
}
