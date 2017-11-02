package com.baixin.ees.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuResult;
import com.baixin.ees.web.service.MenuService;

@Controller
@RequestMapping("/sys/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 菜单列表
	 * @param sysMenu
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysMenuResult> listMenu(SysMenuResult sysMenu) throws MyException{
		return menuService.queryListByMenu(sysMenu);
	}
	/**
	 * 菜单添加
	 * @param sysMenu
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultMessage add(SysMenu sysMenu){
		return menuService.add(sysMenu);
	}
	/**
	 * 菜单编辑
	 * @param sysMenu
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage update(SysMenu sysMenu){
		return menuService.update(sysMenu);
	}
	/**
	 * 菜单批量删除
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("deleteByIds")
	@ResponseBody
	public ResultMessage deleteByIds(String menuIds){
		return menuService.deleteListById(menuIds);
	}
}
