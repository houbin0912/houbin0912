package com.baixin.ees.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.MenuTree;
import com.baixin.ees.web.service.AuthService;


@Controller
@RequestMapping("/sys/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	/**
	 * 角色权限添加和编辑
	 * @param anthList
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/addOrUpdateAuth")
	@ResponseBody
	public ResultMessage addOrUpdateAuth(String anthList,Integer roleId) {
		return authService.addOrUpdateAuth(anthList, roleId);
	}
	
	/**
	 * 初始化角色的权限信息
	 * @param empower
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/initAuthList")
	@ResponseBody
	public ResultMessage initAuthList(String empower,String roleIds) {
		return authService.initRolesAuth(roleIds, empower);
	}
	
	/**
	 * 角色权限菜单
	 * @return
	 */
	@RequestMapping("/menu")
	@ResponseBody
	public List<MenuTree> showMenu(Integer roleId) {
		return  authService.queryMenuList(roleId);
	}

}
