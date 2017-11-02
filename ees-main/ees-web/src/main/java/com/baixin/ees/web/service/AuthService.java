package com.baixin.ees.web.service;


import java.util.List;

import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.MenuTree;

public interface AuthService {
	/**
	 * 通过roleId删除权限
	 * @param roleId
	 * @return
	 */
	ResultMessage deleteListByRoleId(Integer roleId);
	/**
	 * 通过roleId查该角色的所有权限的菜单树
	 * @param roleId
	 * @return
	 */
	List<MenuTree> queryMenuList(Integer roleId); 
	/**
	 * 初始化角色的权限信息
	 * @param roleIds
	 * @param auths
	 * @return
	 */
	ResultMessage initRolesAuth(String roleIds,String auths);
	/**
	 * 更新角色的权限信息
	 * @param anthList
	 * @param roleId
	 * @return
	 */
	ResultMessage addOrUpdateAuth(String anthList, Integer roleId);
}
