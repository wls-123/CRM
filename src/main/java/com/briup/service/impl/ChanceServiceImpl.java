package com.briup.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Chance;
import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.dao.ChanceDao;
import com.briup.dao.UserDao;
import com.briup.service.IChanceService;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年4月1日 下午10:17:22 
* 类说明 
*/
@Service
public class ChanceServiceImpl implements IChanceService{
	
	@Autowired
	private ChanceDao dao;
	
	@Autowired 
	private UserDao userDao;
	

	@Override
	public List<Chance> findAllChance() {
		return dao.findAll();
	}



	@Override
	public void saveChance(Chance chance, Integer creatorId, Integer handlerId) {
		User creatorUser = userDao.findById(creatorId).get();
		User handlerUser = userDao.findById(handlerId).get();
		
		chance.setCreator(creatorUser);
		chance.setHandler(handlerUser);
		dao.save(chance);
	}




	@Override
	public Page<Chance> getChances(String customer, String address) {
		return getChances(0, customer, address);
	}

	@Override
	public Page<Chance> getChances(Integer pageIndex, String customer, String address) {
		if((customer!=null && customer.trim()!="") && (address!=null && address.trim()!="" && !address.equals("请选择所属地区"))) {
			return dao.findByCustomerAndAddress("%"+customer+"%", address, PageRequest.of(pageIndex, 3));
		}else if (customer!=null && customer.trim()!="") {
			return dao.findByCustomerLike("%"+customer+"%", PageRequest.of(pageIndex, 3));
		}else if(address!=null && address.trim()!="" && !address.equals("请选择所属地区")){
			return dao.findByAddress(address,  PageRequest.of(pageIndex, 3));
		}else {
			return dao.findAll(PageRequest.of(pageIndex, 3));
		}
	}

	@Override
	public void deleteChance(Integer id) {
		
		dao.deleteById(id);
	}


	@Override
	public Chance findChanceById(Integer id) {
		return dao.findById(id).get();
	}

	
}
