package com.baixin.ees.web.service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuResult;

public interface MenuService {

	/**
	 * 新增菜单
	 * @param sysMenu
	 * @return
	 */
	ResultMessage add(SysMenu sysMenu);
	/**
	 * 更新菜单信息
	 * @param sysMenu
	 * @return
	 */
	ResultMessage update(SysMenu sysMenu);
	/**
	 * 删除菜单信息
	 * @param sysMEnu
	 * @return
	 */
	ResultMessage delete(SysMenu sysMEnu);
	/**
	 * 批量删除菜单信息
	 * @param menuIds
	 * @return
	 */
	ResultMessage deleteListById(String menuIds);
	/**
	 * 根据过滤条件分页查询菜单信息
	 * @param sysMenu
	 * @return
	 * @throws MyException 
	 */
	Page<SysMenuResult> queryListByMenu(SysMenuResult sysMenu) throws MyException;
}
