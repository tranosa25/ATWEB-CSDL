package xop3d.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xop3d.bean.cart;
import xop3d.entity.menu;
import xop3d.entity.product;

@Transactional
@Controller
@RequestMapping("list")
public class ListController {
	@Autowired

	SessionFactory factory;
	public static String name;
	@ModelAttribute("menu")
	public List<menu> Nav(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql="FROM menu";
		Query query=session.createQuery(hql);
		List<menu> list = query.list();
		System.out.print(name);
		return list;
	}
	@Autowired
	MainController cc;
	@ModelAttribute("menu")
	public void user(ModelMap model) {
		model.addAttribute("username",cc.username);
	}
	@RequestMapping("pro")
	public String insert(){
		return "redirect:/list/"+ name +".htm";
	}
	@RequestMapping("product")
	public String allPro(ModelMap model) {
		name = "product";
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1'";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			List<product> newList = new ArrayList<>();
			//model.addAttribute("products", list);
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("other")
	public String OtherPro(ModelMap model) {
		name = "other";	
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product p WHERE p.GroupProduct.brands = '0' and  p.status = '1'"; // status = 0 là đã xóa . 
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			List<product> newList = new ArrayList<>();
			//model.addAttribute("products", list);
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("best")
	public String bestPro(ModelMap model) {
		name = "best";	
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1' ORDER BY sold DESC";// Loc va sap xep giam dan , dùng bảng sold ( đã bán )
			Query query = session.createQuery(hql).setMaxResults(20);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			List<product> newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("new")
	public String newPro(ModelMap model) {
		name = "new";
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1' ORDER BY date DESC";
			Query query = session.createQuery(hql).setMaxResults(20);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			List<product> newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("sale")
	public String salePro(ModelMap model) {
		name = "sale";
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1' and sale != 0";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			List<product> newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("/{type}")
	public String typePro(ModelMap model, HttpServletRequest  request, @PathVariable("type") String type){
		try {
			Session session = factory.getCurrentSession();
			String hql1 = "FROM product p WHERE p.GroupProduct.id LIKE :type and p.status = '1'"; // dieu kien ( LIKE )
			Query query1 = session.createQuery(hql1);
			name = type;
			query1.setParameter("type", type);
			List<product> list1 = query1.list();
			List<product> newList = new ArrayList<>();
			for (int i = 0; i < list1.size(); i++) {
				newList.add(list1.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	@Autowired
	MainController mc;
	@ModelAttribute("cart")
	public List<cart> getListGioHang() {
		return MainController.cartlist;
	}
	@ModelAttribute("dem")
	public int dem() {
		return MainController.dem;
	}
	@ModelAttribute("tongTien")
	public Float tien() {
		return MainController.tinhTongtien(MainController.cartlist);
	}
}