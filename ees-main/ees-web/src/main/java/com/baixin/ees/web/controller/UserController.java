package com.baixin.ees.web.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baixin.ees.shiro.SessionObject;
import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.util.UserUtils;
import com.baixin.ees.web.dao.model.MenuTree;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserResult;
import com.baixin.ees.web.service.UserService;

@Controller
@RequestMapping("/sys/user")
public class UserController{

	@Autowired
	private UserService userService;
	/**
	 * 用户列表
	 * @param user
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysUserResult> listUser(SysUserResult user) throws MyException {
		return userService.selectListResult(user);
	}
	/**
	 * 
	 * 用户登录名唯一校验
	 * @param loginName
	 * @return
	 */
	@RequestMapping("/check/loginName")
	@ResponseBody
	public ResultMessage checkNameExist(String loginName) {
		return userService.checkLoginNameExsit(loginName);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultMessage delete(SysUser user) {
		return userService.delete(user);
	}
	/**
	 * 用户删除
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public ResultMessage deleteById(String userIds) {
		return userService.deleteListById(userIds);
	}
	/**
	 * 用户添加
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultMessage add(SysUser user) throws SQLException {
		return userService.add(user);
	}
	/**
	 * 用户编辑
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage update(SysUser user) {
		return userService.update(user);
	}
	/**
	 * 用户启用
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/upStatus")
	@ResponseBody
	public ResultMessage upUserStatus(String userIds) {
		return userService.upUserListStatus(userIds);
	}
	/**
	 * 用户禁用
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/downStatus")
	@ResponseBody
	public ResultMessage downUserStatus(String userIds) {
		return userService.downUserListStatus(userIds);
	}
	/**
	 * 用户密码初始化
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/initPwd")
	@ResponseBody
	public ResultMessage initPwd(String userIds){
		return userService.initPwd(userIds);
	}
	/**
	 * 用户修改密码
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public ResultMessage updatePwd(SysUser sysUser){
		return userService.updatePwd(sysUser.getUserId(), sysUser.getPassword());
	}
	/**
	 * 用户动态菜单
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/menu")
	@ResponseBody
	public List<MenuTree> menu() throws MyException{
		return userService.getUserMenu();
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public SessionObject info(){
		SessionObject initSessionObject = UserUtils.initSessionObject();
		return initSessionObject;
	}
}
