package xop3d.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import Recaptcha.CaptchaConfig;
import xop3d.bean.cart;
import xop3d.entity.advertise;
import xop3d.entity.customer;
import xop3d.entity.menu;
import xop3d.entity.orderDetail;
import xop3d.entity.product;
import xop3d.entity.productDetail;
import xop3d.entity.user;

@Transactional
@Controller
@RequestMapping("/home")
public class MainController {
	@Autowired
	SessionFactory factory;
	public static int dem = 0;
	public static String key = "HEHE";

	@RequestMapping("index")
	public String Index() {

		return "index";
	}

	@ModelAttribute("menu")
	public List<menu> Nav(ModelMap model) {
		Session session = this.factory.getCurrentSession();
		String hql = "FROM menu";
		Query query = session.createQuery(hql);
		List<menu> list = query.list();
		model.addAttribute("username", username);
		return list;
	}

	@ModelAttribute("banner")
	public List<advertise> banner(ModelMap model) {
		Session session = this.factory.getCurrentSession();
		String hql = "FROM advertise";
		Query query = session.createQuery(hql);
		List<advertise> list = query.list();
		return list;
	}

	@ModelAttribute("newproduct")
	public List<product> newpro(ModelMap model) {
		Session session = this.factory.getCurrentSession();
		String hql = "FROM product pr where pr.status = '1' ORDER BY date DESC"; // loc Giam Dan
		Query query = session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}

	@ModelAttribute("bestproduct")
	public List<product> bestpro(ModelMap model) {
		Session session = this.factory.getCurrentSession();
		String hql = "FROM product pr where pr.status = '1' ORDER BY sold DESC";
		Query query = session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}

	@ModelAttribute("subbanner")
	public List<advertise> Subbanner(ModelMap model) {
		Session session = this.factory.getCurrentSession();
		String hql = "FROM advertise";
		Query query = session.createQuery(hql);
		List<advertise> list = query.list();
		return list;
	}

	public static List<cart> cartlist = new ArrayList<>();

	@ModelAttribute("listGioHang")
	public List<cart> getListGioHang() {
		return cartlist;
	}

	@RequestMapping("shopping")
	public String shopping(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("coler") String coler, @RequestParam("gia") float gia, @RequestParam("image") String image) {
		boolean isExist = false;
		for (cart item : cartlist) {
			if (item.getIdSanPham().equals(id)) {
				item.setSoLuong(item.getSoLuong() + 1);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			dem++;
			cart ghi = new cart();
			ghi.setIdSanPham(id);
			ghi.setTenSanPham(name);
			ghi.setGiaSanPham(gia);
			ghi.setSoLuong(1);
			ghi.setImage(image);
			cartlist.add(ghi);
		}
		System.out.print(cartlist.toString());
		model.addAttribute("dem", dem);
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "index";
	}

	@RequestMapping("shopping-list")
	public String shoppingl(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("coler") String coler, @RequestParam("gia") float gia, @RequestParam("image") String image) {
		boolean isExist = false;
		for (cart item : cartlist) {
			if (item.getIdSanPham().equals(id)) {
				item.setSoLuong(item.getSoLuong() + 1);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			dem++;
			cart ghi = new cart();
			ghi.setIdSanPham(id);
			ghi.setTenSanPham(name);
			ghi.setGiaSanPham(gia);
			ghi.setSoLuong(1);
			ghi.setImage(image);
			cartlist.add(ghi);
		}
		System.out.print(cartlist.toString());
		model.addAttribute("dem", dem);
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "redirect:/list/pro.htm";
	}

	@RequestMapping("shopping-now")
	public String shoppingnow(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("coler") String coler, @RequestParam("gia") float gia, @RequestParam("image") String image) {
		boolean isExist = false;
		for (cart item : cartlist) {
			if (item.getIdSanPham().equals(id)) {
				item.setSoLuong(item.getSoLuong() + 1);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			dem++;
			cart ghi = new cart();
			ghi.setIdSanPham(id);
			ghi.setTenSanPham(name);
			ghi.setGiaSanPham(gia);
			ghi.setSoLuong(1);
			ghi.setImage(image);
			cartlist.add(ghi);
		}
		System.out.print(cartlist.toString());
		model.addAttribute("dem", dem);
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "redirect:/home/order.htm";
	}

	@RequestMapping("deletecart")
	public String deletecart(Model model, @RequestParam("idSanPham") String idSanPham) {
		int k = 0;
		for (int i = 0; i < cartlist.size(); i++) {
			if (cartlist.get(i).getIdSanPham().equals(idSanPham)) {
				k = i;
			}
		}
		dem--;
		cartlist.remove(k);
		model.addAttribute("dem", dem);
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "index";
	}

	@RequestMapping("deletecart-list")
	public String deletecartl(Model model, @RequestParam("idSanPham") String idSanPham) {
		int k = 0;
		for (int i = 0; i < cartlist.size(); i++) {
			if (cartlist.get(i).getIdSanPham().equals(idSanPham)) {
				k = i;
			}
		}
		dem--;
		cartlist.remove(k);
		model.addAttribute("dem", dem);
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "redirect:/list/pro.htm";
	}

	@RequestMapping("deletecart-od")
	public String deletecarto(Model model, @RequestParam("idSanPham") String idSanPham) {
		int k = 0;
		for (int i = 0; i < cartlist.size(); i++) {
			if (cartlist.get(i).getIdSanPham().equals(idSanPham)) {
				k = i;
			}
		}
		dem--;
		cartlist.remove(k);
		model.addAttribute("dem", dem);
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "redirect:/home/order.htm";
	}

	public static Float tinhTongtien(List<cart> list) {
		Float tongTien = (float) 0;
		for (cart item1 : list) {
			tongTien = (tongTien + item1.getGiaSanPham() * item1.getSoLuong());
		}
		return tongTien;
	}

	public static int tinhTongSP(List<cart> list) {
		int tongSP = 0;
		for (cart item1 : list) {
			tongSP = tongSP + item1.getSoLuong();
		}
		return tongSP;
	}

	@RequestMapping(value = "order")
	public String order(ModelMap model) {
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail";
		Query query = s.createQuery(hql2);
		List<productDetail> list1 = query.list();
		for (productDetail pd : list1) {
			for (cart item : cartlist) {
				if (pd.getProduct().getId().equals(item.getIdSanPham())) {
					item.setSize(pd.getSize());
					break;
				}
			}
		}
		model.addAttribute("customer", new customer());
		model.addAttribute("cart", cartlist);
		model.addAttribute("prode", list1);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
		return "order";
	}

	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String order(Model model, HttpServletRequest re) {
		int sl = 0;
		String size ="";
		ArrayList<String> listid = new ArrayList<>();
		customer cus = new customer();
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		xop3d.entity.order o = new xop3d.entity.order();
		try {
			cus.setName(re.getParameter("name"));
			cus.setEmail(re.getParameter("email"));
			cus.setPhone(Integer.parseInt(re.getParameter("phone")));
			cus.setAddress(re.getParameter("address"));
			cus.setCode(re.getParameter("code"));

			o.setStatus(1);
			o.setUser(userlogin);
			o.setDate(new Date());
			o.setCustomer(cus);
			o.setTotal(tinhTongtien(cartlist));
			s.save(cus);
			s.save(o);
			int madon = 0;
			for (cart item : cartlist) {
				orderDetail od = new orderDetail();
				product pro = (product) s.get(product.class, item.getIdSanPham());
				sl = Integer.parseInt(re.getParameter("sl" + item.getIdSanPham()));
				size = String.valueOf(re.getParameter("size" + item.getIdSanPham()));
				item.setSoLuong(sl);
				item.setSize(size);
				pro.setSold(pro.getSold() + 1);
				s.update(pro);
				od.setSize(size);
				System.out.println(pro.toString());
				od.setProduct(pro);
				od.setOrder(o);
				od.setQuanlity(item.getSoLuong());
				s.save(od);
				madon = od.getId();
				listid.add(pro.getId());
			}

			t.commit();
			System.out.println("thêm");
			for (String string : listid) {
				updatesl(string, size, sl);
			}
			thongbao(madon, re.getParameter("email"), re.getParameter("name"));
			model.addAttribute("message", "Đặt hàng thành công ! Quay về trang chủ để tiếp tục mua hàng");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Đặt hàng thất bại ! ");
			System.out.println("faile");
		} finally {
			s.close();
		}
		return "order";
	}

	@RequestMapping("order-pr")
	public String themsl(ModelMap model, HttpServletRequest re) {
		for (cart item : cartlist) {
			int sl = Integer.parseInt(re.getParameter("sl" + item.getIdSanPham()));
			String size = re.getParameter("size" + item.getIdSanPham());
			item.setSoLuong(sl);
			item.setSize(size);
		}
		order1(model);
		return "order";
	}

	public void order1(ModelMap model) {
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail";
		Query query = s.createQuery(hql2);
		List<productDetail> list1 = query.list();
		model.addAttribute("customer", new customer());
		model.addAttribute("cart", cartlist);
		model.addAttribute("prode", list1);
		model.addAttribute("tongTien", MainController.tinhTongtien(cartlist));
	}

	public static String username = "Guest";
	public static user userlogin;

	@RequestMapping(value = "sign-up", method = RequestMethod.POST)
	public String dangki(Model model, HttpServletRequest re, @ModelAttribute("User") user us, BindingResult err)
			throws ParseException {
		String gRecaptchaResp = re.getParameter("g-recaptcha-response");
		Boolean verify = CaptchaConfig.verify(gRecaptchaResp);
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		boolean temp = true;
		if (us.getUsername().trim().length() == 0) {
			err.rejectValue("username", "User", "Vui lòng nhập tên tài khoản");
			temp = false;
		}
		if (us.getAddress().trim().length() == 0) {
			err.rejectValue("address", "User", "Vui lòng nhập địa chỉ");
			temp = false;
		}
		if (us.getEmail().trim().length() == 0) {
			err.rejectValue("email", "User", "Vui lòng nhập Email");
			temp = false;
		}
		if (us.getPhone().trim().length() != 10) {
			err.rejectValue("phone", "User", "Vui lòng nhập số điện thoại");
			temp = false;
		}
		if (us.getFullname().trim().length() == 0) {
			err.rejectValue("fullname", "User", "Vui lòng nhập Họ và tên");
			temp = false;
		}
		if (us.getGender() == null) {
			err.rejectValue("gender", "User", "Vui lòng chọn giới tính");
			temp = false;
		}
		if (us.getPassword().trim().length() == 0) {
			err.rejectValue("password", "User", "Vui lòng nhập mật khẩu");
			temp = false;
		}
		if (us.getPassword().trim().equals(re.getParameter("rpass").trim()) == false) {
			err.rejectValue("password", "User", "Mật khẩu không giống nhau");
			temp = false;
		}
		if (us.getBirthday() == null) {
			err.rejectValue("birthday", "User", "Vui lòng nhập ngày sinh");
			temp = false;
		}
		String ten = us.getUsername().trim();
		String diachi = us.getAddress().trim();
		String gmail = us.getEmail().trim();
		String phone = us.getPhone();
		String fullname = us.getFullname().trim();
		int gtinh = us.getGender();
		String pass = us.getPassword().trim();
		user us2 = new user();
		if (temp == true && verify) {
			try {
				if (s.get(user.class, us.getUsername().trim()) == null) {
					us2.setUsername(MaHoaDes.Encrypt(ten, key));
					// us2.setPassword(MaHoaDes.Encrypt(pass,key));
					us2.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt(10)));
					us2.setPhone(MaHoaDes.Encrypt(phone, key));
					us2.setFullname(fullname);
					us2.setEmail(MaHoaDes.Encrypt(gmail, key));
					us2.setBirthday(us.getBirthday());
					us2.setGender(us.getGender());
					us2.setAddress(diachi);
					;
					s.save(us2);
					t.commit();
					model.addAttribute("dk", 1);
					model.addAttribute("tb", "Đăng kí thành công");
				} else {
					model.addAttribute("tb", "Tài khoản đã tồn tại , vui lòng đăng nhập");
					System.out.println("faile");
				}
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("tb", "Đăng kí thất bại,vui lòng kiểm tra lại");
				System.out.println("faile");
			} finally {
				s.close();
			}
		}
		return "user-signup";
	}

	@RequestMapping(value = "sign-up", method = RequestMethod.GET)
	public String dangki1(ModelMap model, @ModelAttribute("User") user us) {
		return "user-signup";
	}

	@RequestMapping(value = "forget-pass", method = RequestMethod.GET)
	public String quen(ModelMap model) {
		return "forgetpass";
	}

	@Autowired
	JavaMailSender mailer;

	@RequestMapping(value = "forget-pass", method = RequestMethod.POST)
	public String quen1(ModelMap model, HttpServletRequest re) {
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "FROM user";
		Query query = s.createQuery(hql);
		List<user> list = query.list();
		boolean kiemtra = true;
		for (user kt : list) {
			if (kt.getUsername().equals(re.getParameter("username"))) {
				int random = (int) Math.floor(((Math.random() * 899999) + 100000));/// random sinh số có 6 chữ số
				String matkhaumoi = String.valueOf(random);
				String mailgui;
				mailgui = kt.getEmail();
				kt.setPassword(matkhaumoi);
				s.update(kt);
				kiemtra = false;
				/// update vào sql
				t.commit();
				///// gửi tới mail có cái autowire ở trên cùng nhớ xem
				////////////////////////////////////
				//////////////////////////////////////
				Date date = new Date();
				String from = "trantuminh968@gmail.com";
				String to = mailgui;
				String subject = "Quên mật khẩu";
				String body = "Bạn đã báo quên mật khẩu vào " + date + "  mật khẩu mới của bạn là " + matkhaumoi
						+ "\n Nếu bạn không thực hiện yêu cầu trên , vui lòng liên hệ quản trị viên ngay";
				try {
					System.out.println("tạo mail mailer");
					// tạo mail

					MimeMessage mail = this.mailer.createMimeMessage();

					// gọi lớp trợ giúp
					System.out.println("trợ giúp");
					MimeMessageHelper helper = new MimeMessageHelper(mail);
					System.out.println("new");
					helper.setFrom(from, from);
					helper.setTo(to);
					helper.setReplyTo(from, from);
					helper.setSubject(subject);
					helper.setText(body, true);
					this.mailer.send(mail);
					System.out.println("gửi");
					model.addAttribute("tinnhan", "Mật khẩu mới đã gửi đến email của bạn");
				} catch (Exception ex) {
					model.addAttribute("tinnhan", "gửi mail thất bại");
					t.rollback();
				} finally {
					s.close();
				}
			}
		}
		if (kiemtra == true) {
			model.addAttribute("tinnhan", "Tài khoản không tồn tại");
		}

		return "forgetpass";
	}

	@Autowired
	HttpServletRequest request;

	public String getClientIp() {

		String remoteAddr = "";
		try {
			if (this.request != null) {
				remoteAddr = this.request.getHeader("X-FORWARDED-FOR");
				if (remoteAddr == null || "".equals(remoteAddr)) {
					remoteAddr = this.request.getRemoteAddr();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return remoteAddr;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String dangnhap(Model model, HttpServletRequest re) throws ParseException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String ipclient = "";
		ipclient = getClientIp();
		System.out.println(ipclient);
		String gRecaptchaResp = re.getParameter("g-recaptcha-response");// reCaptcha
		Boolean verify = CaptchaConfig.verify(gRecaptchaResp);// reCaptcha
		String name = MaHoaDes.Encrypt(re.getParameter("user").trim(), "HEHE"); // Mã hóa DES cho username nhập vào
		if (verify) {
			Session session = this.factory.openSession();
			String hql = "FROM user u where u.username= '" + name + "'"; 
			Query query = session.createQuery(hql);
			List<user> list = query.list();
			for (user user : list) {
				try {	
				boolean valuate = BCrypt.checkpw(re.getParameter("pass"), user.getPassword()); // Dùng Bcrypt băm pass//
				if (valuate == true) { // so sánh
					username = re.getParameter("user").trim();
					model.addAttribute("username", username);
					userlogin = user;
					return "index";
				}

			}
				catch(Exception ex)
				{model.addAttribute("tb", "Đăng nhập thất bại vui lòng kiếm tra lại !");
					}
				}
			model.addAttribute("tb", "Đăng nhập thất bại vui lòng kiếm tra lại !");
			
			return "user-login";
		}
		model.addAttribute("tb", "vui lòng xác thực !");
		return "user-login";
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String dangnhap1(ModelMap model) {
		username = "Guest";
		model.addAttribute("username", username);
		return "user-login";
	}

	public void updatesl(String id, String size, int sl) {
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail pd where pd.Product.id = :id and pd.size = :size";
		Query query = s.createQuery(hql2);
		query.setParameter("id", id);
		query.setParameter("size", size);
		List<productDetail> list1 = query.list();
		ArrayList<productDetail> ls = new ArrayList<productDetail>();
		for (productDetail a : list1) {
			ls.add(a);
		}
		for (productDetail a : ls) {
			a.setQuanlity(a.getQuanlity() - sl);
			s.update(a);
			t.commit();
			s.close();
		}
	}

	@Autowired
	JavaMailSender mailer1;

	public void thongbao(int madonhang, String email, String tenkh) {
		Date date = new Date();
		String from = "trantuminh968@gmail.com";
		String to = email;
		String subject = "Đặt hàng thành công !";
		String body = "Xin chào " + tenkh + " đơn hàng số " + madonhang + " của bạn đã đc tạo thành công vào lúc "
				+ date + " . Nhân viên cửa hàng sẽ gửi hàng cho bạn trong thời gian sớm nhất. \n Xin cảm ơn!";
		try {
			MimeMessage mail = this.mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			System.out.println("new");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			this.mailer.send(mail);
		} catch (Exception ex) {
		}
	}
}
