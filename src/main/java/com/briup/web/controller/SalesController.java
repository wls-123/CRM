package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Chance;
import com.briup.bean.User;
import com.briup.service.IChanceService;
import com.briup.service.IUserService;


/** 
* @author 作者 wls: 
* @version 创建时间：2020年4月1日 下午9:57:12 
* 类说明 
*	
*/
@Controller
public class SalesController {
	
	@Autowired
	private IChanceService service;
	
	@Autowired
	private IUserService userService;
	

	
	@RequestMapping("toSales")
	public String toSales(HttpSession session, String customer, String address) {
		
		System.out.println(customer+"-----"+address);
		
		List<User> jinglis = userService.findByJingli(4);
		session.setAttribute("jinglis", jinglis);
		
		//查询所有的商机
/*		List<Chance> allSales = service.findAllChance();
		session.setAttribute("allSales", allSales);
*/
		Page<Chance> chances = service.getChances(customer, address);
		session.setAttribute("chances", chances);
		session.setAttribute("customer", customer);
		session.setAttribute("address", address);
		return "pages/sales";
	}
	
	
	  //分页查询
	  
	@RequestMapping("updateSalesPage")
	public String SalesPage(Integer pageIndex, String customer, String address, HttpSession session) {
		Page<Chance> chances = service.getChances(pageIndex, customer, address);
		session.setAttribute("chances", chances);
		return "pages/sales";
	}
	  
	 	
	
	//新增
	@RequestMapping("addSales")
	@ResponseBody
	public String addSales(Chance chance,Integer creatorId,Integer handlerId) {
		Integer id = chance.getId();
		if (id != null) {
			service.saveChance(chance, creatorId, handlerId);
			return "修改成功";
		} else {
			service.saveChance(chance, creatorId, handlerId);
			return "添加成功";
		}
	}
	
	
	//通过id查找销售商机
	@RequestMapping("selectChanceById")
	@ResponseBody
	public Chance selectChanceById(Integer id) {
		return service.findChanceById(id);
	}
	
	//根据id删除销售商机
	@RequestMapping("deleteSales")
	@ResponseBody
	public String deleteSales(Integer id) {
		service.deleteChance(id);
		return "删除成功";
	}
	
	
	//重置
	@RequestMapping("resetSales")
	@ResponseBody
	public String resetSales(HttpSession session) {
		session.removeAttribute("chances");
		return "重置成功";
		
	}
}












