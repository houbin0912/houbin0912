package com.baixin.ees.web.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysRoleResult;
import com.baixin.ees.web.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:mybatis-config.xml" })
/**
 * Role Service 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestRoleService {
	@Autowired
	private RoleService roleService;

	/**
	 * 查询列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception {
		SysRoleResult role = new SysRoleResult();
		role.setStart(0);
		role.setLength(3);
		role.setDraw(1);
		Page<SysRoleResult> selectListResult = roleService.selectListResult(role);
		System.out.println(JSONObject.toJSON(selectListResult));
	}

	/**
	 * 登录名唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkRoleName() throws Exception {
		ResultMessage checkRoleNameExsit = roleService.checkroleNameExsit("q1");
		System.out.println(JSONObject.toJSON(checkRoleNameExsit));
	}

	/**
	 * 添加角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void addRole() throws Exception {
		SysRoleResult role = new SysRoleResult();
		role.setRoleName("q3");
		role.setHomeUrl("q3");
		role.setRemark("测试3");
		role.setBelongOrg(1);
		ResultMessage addRole = roleService.add(role);
		System.out.println(JSONObject.toJSON(addRole));
	}

	/**
	 * 修改角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateRole() throws Exception {
		SysRole role = new SysRole();
		role.setRoleId(6);
		role.setRoleName("q3Update");
		role.setHomeUrl("q3");
		role.setRemark("测试3Update");
		role.setBelongOrg(1);
		ResultMessage updateRole = roleService.update(role);
		System.out.println(JSONObject.toJSON(updateRole));
	}

	/**
	 * 删除角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByIdsRole() throws Exception {
		String roleIds = "6";
		ResultMessage deleteListById = roleService.deleteListById(roleIds);
		System.out.println(JSONObject.toJSON(deleteListById));
	}

	/**
	 * 查询角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void selectRoles() throws Exception {
		Integer orgId = 1;
		List<SysRole> list = roleService.selectRolesByOrgId(orgId);
		System.out.println(JSONObject.toJSON(list));
	}
}
