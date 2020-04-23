package com.briup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.bean.Role;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年3月27日 下午2:43:34 
* 类说明 
* 	从数据库中查找role相关数据
*/
public interface RoleDao extends JpaRepository<Role, Integer>{
	
}
