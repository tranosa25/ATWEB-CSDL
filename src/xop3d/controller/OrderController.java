package xop3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xop3d.bean.cart;
import xop3d.entity.customer;
import xop3d.entity.menu;

@Transactional
@Controller
public class OrderController {
	@Autowired
	SessionFactory factory;
	List<cart> list = new ArrayList<>();
	@RequestMapping(value="order", method=RequestMethod.GET)
	public String order(ModelMap model){
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail";
		Query query=s.createQuery(hql2);
		List<menu> list1 = query.list();
		model.addAttribute("customer", new customer());
		model.addAttribute("cart", this.list);
		model.addAttribute("prode", list1);
		model.addAttribute("tongTien",this.tinhTongtien(this.list));
		return "order";
	}
	@Autowired
	MainController cc;
	@ModelAttribute("menu")
	public void user(ModelMap model) {
		model.addAttribute("username",this.cc.username);
	}
	@RequestMapping(value = "order", method=RequestMethod.POST)
	public String order(Model model,@ModelAttribute("customer") customer cus) {
		Session s = this.factory.openSession();
		Transaction t = s.beginTransaction();
		try{
			s.save(cus);
			t.commit();
			System.out.println("thêm");
			model.addAttribute("message", "Thêm Thành Công");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Không Thành Công");
			System.out.println("faile");
		}
		finally{
			s.close();
		}
		return "index";
	}
	public float tinhTongtien (List<cart> list) {
		float tongTien=0;
		for(cart item1: list) {
			tongTien=tongTien+item1.getGiaSanPham()*item1.getSoLuong();
		}
		return tongTien;
	}
}
