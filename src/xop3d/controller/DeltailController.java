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
import xop3d.entity.menu;
import xop3d.entity.product;
import xop3d.entity.productDetail;

@Transactional
@Controller
@RequestMapping("detail")
public class DeltailController<HttpServletRequest> {
	@Autowired
	SessionFactory factory;
	@ModelAttribute("menu")
	public List<menu> Nav(ModelMap model) {
		Session session = this.factory.getCurrentSession();
		String hql="FROM menu";
		Query query=session.createQuery(hql);
		List<menu> list = query.list();
		return list;
	}
	@Autowired
	MainController cc;
	@ModelAttribute("menu")
	public void user(ModelMap model) {
		model.addAttribute("username",this.cc.username);
	}
	@RequestMapping("/{id}")
	public String typePro(ModelMap model, HttpServletRequest  request, @PathVariable("id") String id){
		try {
			Session session = this.factory.getCurrentSession();
			String hql1 = "FROM product p WHERE p.id LIKE :id";
			String hql2 = "FROM productDetail p WHERE p.Product.id LIKE :id";
			Query query1 = session.createQuery(hql1);
			Query query2 = session.createQuery(hql2);
			query1.setParameter("id",id);
			query2.setParameter("id",id);
			List<product> list1 = query1.list();
			List<productDetail> list2 = query2.list();
			List<product> newList1 = new ArrayList<>();
			List<productDetail> newList2= new ArrayList<>();
			System.out.print(list1.toString());
			System.out.print(list2.toString());
			for (int i = 0; i < list1.size(); i++) {
				newList1.add(list1.get(i));
			}
			for (int i = 0; i < list2.size(); i++) {
				newList2.add(list2.get(i));
			}
			model.addAttribute("item", newList1);
			model.addAttribute("itemsize", newList2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}
	
}
