package com.baixin.ees.web.service;


import java.util.List;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysRoleResult;

public interface RoleService {
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	ResultMessage add(SysRole role);
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	ResultMessage update(SysRole role);
	/**
	 * 删除角色
	 * @param user
	 * @return
	 */
	ResultMessage delete(SysRole user);
	/**
	 * 通过id删除角色
	 * @param roleId
	 * @return
	 */
	ResultMessage deleteById(Integer roleId);
	/**
	 * 批量删除角色
	 * @param roleIds
	 * @return
	 */
	ResultMessage deleteListById(String roleIds);
	/**
	 * 通过系统来查询所属系统的角色
	 * @param roleId
	 * @return
	 * @throws MyException 
	 */
	List<SysRole> selectRolesByOrgId(Integer roleId) throws MyException;
	/**
	 * 验证新添加的角色是否存在
	 * @param roleName
	 * @return
	 */
	ResultMessage checkroleNameExsit(String roleName);
	/**
	 * 分页关联查询角色信息
	 * @param draw
	 * @param star
	 * @param length
	 * @param user
	 * @return
	 * @throws MyException 
	 */
	Page<SysRoleResult> selectListResult(SysRoleResult role) throws MyException;

}
