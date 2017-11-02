package com.baixin.ees.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserResult;
import com.baixin.ees.web.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:mybatis-config.xml" })
/**
 * User Service 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestUserService {
	@Autowired
	private UserService userService;

	/**
	 * 查询列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception {
		SysUserResult user = new SysUserResult();
		user.setStart(0);
		user.setLength(3);
		user.setDraw(1);
		Page<SysUserResult> selectListResult = userService.selectListResult(user);
		System.out.println(JSONObject.toJSON(selectListResult));
	}

	/**
	 * 登录名唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkLoginName() throws Exception {
		ResultMessage checkLoginNameExsit = userService.checkLoginNameExsit("b");
		System.out.println(JSONObject.toJSON(checkLoginNameExsit));
	}

	/**
	 * 添加用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void addUser() throws Exception {
		SysUser user = new SysUser();
		user.setUserName("测试");
		user.setLoginName("test");
		user.setStatus("1");
		user.setSex("1");
		user.setOrgId(1);
		user.setRoleId(1);
		user.setRemark("测试数据");
		ResultMessage addUser = userService.add(user);
		System.out.println(JSONObject.toJSON(addUser));
	}

	/**
	 * 修改用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateUser() throws Exception {
		SysUser user = new SysUser();
		user.setUserId(10);
		user.setUserName("测试update");
		user.setLoginName("test");
		user.setStatus("1");
		user.setSex("1");
		user.setOrgId(1);
		user.setRoleId(1);
		user.setRemark("测试数据");
		ResultMessage updateUser = userService.update(user);
		System.out.println(JSONObject.toJSON(updateUser));
	}

	/**
	 * 删除用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByIdsUser() throws Exception {
		String userIds = "10";
		ResultMessage deleteListById = userService.deleteListById(userIds);
		System.out.println(JSONObject.toJSON(deleteListById));
	}

	/**
	 * 批量启用用户状态 1：是启用
	 * 
	 * @throws Exception
	 */
	@Test
	public void upStatusUser() throws Exception {
		String userIds = "5,6,7,8";
		ResultMessage upUserListStatus = userService.upUserListStatus(userIds);
		System.out.println(JSONObject.toJSON(upUserListStatus));
	}

	/**
	 * 批量禁用用户状态 0：是禁用
	 * 
	 * @throws Exception
	 */
	@Test
	public void downStatusUser() throws Exception {
		String userIds = "5,6,7,8";
		ResultMessage upUserListStatus = userService.downUserListStatus(userIds);
		System.out.println(JSONObject.toJSON(upUserListStatus));
	}

	/**
	 * 初始化密码
	 * 
	 * @throws Exception
	 */
	@Test
	public void initPwdUser() throws Exception {
		String userIds = "10";
		ResultMessage initPwd = userService.initPwd(userIds);
		System.out.println(JSONObject.toJSON(initPwd));
	}

	/**
	 * 修改密码
	 * 
	 * @throws Exception
	 */
	@Test
	public void updatePwdUser() throws Exception {
		SysUser user = new SysUser();
		user.setUserId(10);
		user.setPassword("password");
		userService.updatePwd(user.getUserId(), user.getPassword());
	}

	/**
	 * 用户菜单 登录状态下可测
	 * 
	 * @throws Exception
	 */
	@Test
	public void menuUser() throws Exception {
	}

}
