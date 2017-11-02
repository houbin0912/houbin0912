package com.baixin.ees.web.service;

import java.util.List;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysOrg;

public interface OrgService {

	/**
	 * 新增机构
	 * @param sysOrg
	 * @return
	 */
	ResultMessage add(SysOrg sysOrg);
	/**
	 * 更新机构信息
	 * @param sysOrg
	 * @return
	 */
	ResultMessage update(SysOrg sysOrg);
	/**
	 * 删除机构信息
	 * @param user
	 * @return
	 */
	ResultMessage delete(SysOrg user);
	/**
	 * 批量删除机构信息
	 * @param orgIds
	 * @return
	 */
	ResultMessage deleteListById(String orgIds);
	/**
	 * 查询机构信息
	 * @param draw
	 * @param star
	 * @param length
	 * @param sysOrg
	 * @return
	 * @throws MyException 
	 */
	Page<SysOrg> queryListByOrg(SysOrg sysOrg) throws MyException;
	/**
	 * 查询所有的机构信息，用来做下拉窗反显
	 * @return
	 */
	List<SysOrg> selectAllOrg();
}
