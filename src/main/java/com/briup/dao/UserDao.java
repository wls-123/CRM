package com.briup.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.briup.bean.Role;
import com.briup.bean.User;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年3月26日 下午4:53:59 
* 类说明 :
* 	User对象和数据库进行保存
*/
public interface UserDao extends JpaRepository<User, Integer>{
	
	User findByName(String name);
	
	Page<User> findByRole(Role role,Pageable able);
		
	
	@Transactional
	@Modifying
	@Query(value = "delete from user where id=?1",nativeQuery = true)
	void deleteUserById(@Param("id")Integer id);

	List<User> findByRoleId(Integer id);
}
