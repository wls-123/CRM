package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.service.IRoleService;
import com.briup.service.IUserService;

/** 
* @author 作者 wls: 
* @version 创建时间：2020年3月26日 下午4:32:25 
* 类说明 
*/
@Controller
public class UserCotroller {//service层
	@Autowired
	private IUserService service;
	@Autowired
	private IRoleService roleService;
	
	//获取前台输入的用户名密码，进行校验
	@RequestMapping("user/login")
	@ResponseBody
	public String login( String name,String password,HttpSession session) {
		//需要判断用户名和密码是否为空，但是前台已经完成。
		
		//调用service层，来判断用户名和密码是否正确。
		User user = service.findByName(name);
		
		//如果user为空，则表示用户名不存在。
		if(user==null) {
			return "当前用户不存在！";
		}
		
		//判断密码是否正确。
		if(!user.getPassword().equals(password)) {
			return "密码错误！";
		}
		
		//判断用户状态：1为正常，2为注销
		if(user.getFlag()!=1) {
			return "该用户已被注销！";
		}
		
		//System.out.println("----------"+user);
		//将数据保存在session范围中，供前台使用。
		session.setAttribute("user", user);
		
		return "success";
		
	}
	
	
	
	//退出功能
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	//显示用户管理的界面
	@RequestMapping("toUser")
	public String toUser(HttpSession session,Integer roleId) {
		session.setAttribute("roleId", roleId);
		
		//查询用户信息
		Page<User> users = service.findUsersByRole(roleId);
		session.setAttribute("users", users);
		//查询角色信息-角色名字
		List<Role> allRoles = roleService.allRoles();
		session.setAttribute("allRoles", allRoles);
		return "pages/user";
	}
	
	
	//分页查询
	@RequestMapping("updateUserPage")
	public String userPage(Integer pageIndex, HttpSession session) {
		Integer roleId = (Integer) session.getAttribute("roleId");
		
		Page<User> users = service.findUsersByRole(roleId, pageIndex);
		session.setAttribute("users", users);
		
		return "pages/user";
	}
	
	//重置
	@RequestMapping("resetUser")
	@ResponseBody
	public String resetUser(HttpSession session) {
		session.removeAttribute("roleId");
		return "重置成功";
	}
	
	//新增
	@RequestMapping(value = "saveUser",method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(User user,Integer roleId) {
		
		Role role = roleService.findRoleById(roleId);
		user.setRole(role);
		if(user.getId()==null) {
			service.saveUser(user);
			return "添加成功";
		}else {
			service.saveUser(user);
			return "修改成功";
		}
	}
	
	//更新用户信息
	@RequestMapping(value = "user/{id}",method = RequestMethod.GET)
	@ResponseBody
	public User selectUserById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	
	//删除
	@RequestMapping("deleteUser")
	@ResponseBody
	public String deleteUser(Integer id) {
		service.deleteUserById(id);
		System.out.println(id);
		return "删除成功";
	}
	
	//根据id查询指定的角色
	@RequestMapping("findById")
	@ResponseBody
	public User findById(Integer id) {
		User user = service.findById(id);
		return user;
	}
}





