package com.briup.service;
/** 
* @author 作者 wls: 
* @version 创建时间：2020年3月26日 下午4:47:43 
* 类说明 
*/


import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.User;

public interface IUserService {
	
	User findByName(String name);
	User findById(Integer  id);
	
	//查询User信息
	Page<User> findUsersByRole(Integer roleId);
	//根据分页查询User的相关信息
	Page<User> findUsersByRole(Integer roleId,Integer pageIndex);
	
	//新增
	void saveUser(User user);
	
	
	void deleteUserById(Integer id);
	
	//查询所有角色为经理的用户
	List<User> findByJingli(Integer id);
}
