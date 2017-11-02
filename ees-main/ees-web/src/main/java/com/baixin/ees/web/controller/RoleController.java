package com.baixin.ees.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysRoleResult;
import com.baixin.ees.web.service.RoleService;

@Controller
@RequestMapping("/sys/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	/**
	 * 角色列表
	 * @param role
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysRoleResult> listUser(SysRoleResult role) throws MyException {
		return roleService.selectListResult(role);
	}
	/**
	 * 角色名称唯一校验
	 * @param roleName
	 * @return
	 */
	@RequestMapping("/check/roleName")
	@ResponseBody
	public ResultMessage checkNameExist(String roleName) {
		return roleService.checkroleNameExsit(roleName);
	}
	/**
	 * 角色添加
	 * @param role
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultMessage add(SysRole role) {
		return roleService.add(role);
	}
	/**
	 * 角色编辑
	 * @param role
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage update(SysRole role) {
		return roleService.update(role);
	}
	/**
	 * 角色批量删除
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public ResultMessage deleteByIds(String roleIds) {
		return roleService.deleteListById(roleIds);
	}
	/**
	 * 机构下所有角色
	 * @param orgId
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/selectRoles")
	@ResponseBody
	public List<SysRole> selectRoles(Integer orgId) throws MyException {
		return roleService.selectRolesByOrgId(orgId);
	}

}
