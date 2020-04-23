package com.briup.service.impl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.dao.RoleDao;
import com.briup.dao.UserDao;
import com.briup.service.IUserService;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年3月26日 下午4:51:18 
* 类说明 
* 	和User用户相关的Service层逻辑
*/
@Service
public class UserServiceImpl implements IUserService{
	
	//dao层
		@Autowired
		private UserDao dao;
		@Autowired
		private RoleDao roleDao;
		
		@Override
		public User findByName(String name) {
			User user = dao.findByName(name);
			return user;
		}

		@Override
		public Page<User> findUsersByRole(Integer roleId) {
			return findUsersByRole(roleId, 0);
		}

		@Override
		public Page<User> findUsersByRole(Integer roleId, Integer pageIndex) {
			Page<User> users = null;
			if (roleId == null) {
				//将所有用户查询出来
				users = dao.findAll(PageRequest.of(pageIndex, 3));
			} else {
				//根据角色条件筛选用户
				Role role = new Role();
				role.setId(roleId);
				users = dao.findByRole(role, PageRequest.of(pageIndex, 3));
			}
			return users;
		}

		
		@Override
		public void saveUser(User user) {
			
			dao.save(user);
		}

		@Override
		public void deleteUserById(Integer id) {
			dao.deleteUserById(id);
		}

		@Override
		public User findById(Integer id) {
			 Optional<User> findById = dao.findById(id);
			 User user = findById.get();
			return user;
		}

		@Override
		public List<User> findByJingli(Integer id) {
			
			return dao.findByRoleId(id);
		}

	
	
}
