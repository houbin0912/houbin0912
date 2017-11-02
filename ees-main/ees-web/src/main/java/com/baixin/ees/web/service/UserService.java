package com.baixin.ees.web.service;

import java.util.List;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.MenuTree;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserResult;

public interface UserService {

	/**
	 * 用户添加接口
	 * @param user
	 * @return
	 */
	ResultMessage add(SysUser user);
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	ResultMessage update(SysUser user);
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	ResultMessage delete(SysUser user);
	/**
	 * 通过用户id删除用户
	 * @param userId 用户id
	 * @return
	 */
	ResultMessage deleteById(Integer userId);
	/**
	 * 批量删除用户
	 * @param userIdList  用户id的list
	 * @return
	 */
	ResultMessage deleteListById(String userIds);
	/**
	 * 用户存在性校验
	 * @param userName  用户名称
	 * @return
	 */
	ResultMessage checkLoginNameExsit(String loginName);
	/**
	 * 批量启用用户状态
	 * @param userIds
	 * @return
	 */
	ResultMessage upUserListStatus(String userIds);
	/**
	 * 批量禁用用户状态
	 * @param userIds
	 * @return
	 */
	ResultMessage downUserListStatus(String userIds);
	
	/**
	 * 初始化密码，初始密码设置为123456
	 * @param userIds
	 * @return
	 */
	ResultMessage initPwd(String userIds);
	/**
	 * 重置密码
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	ResultMessage updatePwd(Integer userId,String newPwd);
	
	/**
	 * 递归组装用户所有角色的权限菜单
	 * @param rootNodeId 根节点的Id
	 * @param sysMenuList 需要组装的所有menu列表
	 * @return
	 */
	List<MenuTree> getUserMenuTree(Integer rootNodeId , List<SysMenu> sysMenuList);
	
	/**
	 * 获取用户菜单
	 * @throws MyException 
	 */
	List<MenuTree> getUserMenu() throws MyException;
	/**
	 * 条件过滤分页查询
	 * @param draw
	 * @param star
	 * @param length
	 * @param user
	 * @return
	 * @throws MyException 
	 */
	Page<SysUserResult> selectListResult(SysUserResult user) throws MyException;
}
