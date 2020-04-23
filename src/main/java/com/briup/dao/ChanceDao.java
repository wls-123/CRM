package com.briup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.bean.Chance;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年4月1日 下午10:08:21 
* 类说明 
*/
public interface ChanceDao extends JpaRepository<Chance, Integer>{
	
	//根据潜在客户名，查询对应的销售商机并分页
	Page<Chance> findByCustomerLike(String customer,Pageable able);
	
	//根据区域名，查询对应的销售商机并分页
	Page<Chance> findByAddress(String address,Pageable able);
	
	//根据潜在客户名和区域，一起查询对应的销售商机并分页
	Page<Chance> findByCustomerAndAddress(String customer,String address,Pageable able);
}

