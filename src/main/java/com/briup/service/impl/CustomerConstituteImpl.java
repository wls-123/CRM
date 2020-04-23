package com.briup.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Customer;
import com.briup.bean.CustomerConstitute;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerConstituteService;


/** 
* @author 作者 wls: 
* @version 创建时间：2020年4月3日 上午10:44:16 
* 类说明 
*/
@Service
public class CustomerConstituteImpl implements ICustomerConstituteService{
	
	@Autowired
	private CustomerDao dao;
	
	@Override
	public List<CustomerConstitute> regionAnalyze() {
		List<CustomerConstitute> list = new ArrayList();
		
		//根据数据库所有客户信息查找出来(需要获取总数)
		float nums = dao.findAll().size();
		
		String[] regions = {"华中","华南","华东","华北"};
		
		//然后根据地区进行筛选，然后封装List自定义对象
		for (String region : regions) {
			int num = dao.findByRegion(region).size();
			
			float y = num/nums * 100;
			
			CustomerConstitute cc = new CustomerConstitute(region, y, region);
			
			list.add(cc);
		}
		
		return list;
	}

	@Override
	public List<CustomerConstitute> levelAnalyze() {
		List<CustomerConstitute> list = new ArrayList();
		//根据数据库所有客户信息查找出来(需要获取总数)
		float nums = dao.findAll().size();
		
		String[] levels = {"普通客户","大客户","重点开发客户","合作伙伴"};
		
		//然后根据地区进行筛选，然后封装List自定义对象
		for (String level : levels) {
			int num = dao.findByLevel(level).size();
			
			float y = num/nums * 100;
			
			CustomerConstitute cc = new CustomerConstitute(level, y, level);
			
			list.add(cc);
		}
		return list;
	}
	
}
