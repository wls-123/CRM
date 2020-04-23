package com.briup.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.Chance;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年4月1日 下午10:08:50 
* 类说明 
*/
public interface IChanceService {
	
	List<Chance> findAllChance();
	
	
	
	//显示
	Page<Chance> getChances(String customer, String address);
	Page<Chance> getChances(Integer pageIndex,String customer, String address);
	
	//删除
	void deleteChance(Integer id);
	
	//新增销售商机
	void saveChance(Chance chance,Integer creatorId,Integer handlerId);
	
	//根据id查指定chance
	Chance findChanceById(Integer id);
	
}
