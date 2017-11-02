package com.baixin.ees.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysOrg;
import com.baixin.ees.web.service.OrgService;

@Controller
@RequestMapping("/sys/org")
public class OrgController {

	@Autowired
	private OrgService orgService;
	
	/**
	 * 机构列表
	 * @param sysOrg
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysOrg> listOrg(SysOrg sysOrg) throws MyException{
		return orgService.queryListByOrg(sysOrg);
	}
	
	/**
	 * 机构添加
	 * @param sysOrg
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultMessage add(SysOrg sysOrg){
		return orgService.add(sysOrg);
	}
	
	
	/**
	 * 机构修改
	 * @param sysOrg
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage updateOrg(SysOrg sysOrg){
		return orgService.update(sysOrg);
	}
	
	/**
	 * 机构批量删除
	 * @param orgIds
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public ResultMessage deleteById(String orgIds){
		return orgService.deleteListById(orgIds);
	}
	
	/**
	 * 机构下拉菜单
	 * @return
	 */
	@RequestMapping("/selectAllOrg")
	@ResponseBody
	public List<SysOrg> selectAllOrg(){
		return orgService.selectAllOrg();
	}
	
}
