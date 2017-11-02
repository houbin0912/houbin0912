package com.baixin.ees.web.service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysErrorAcct;
import com.baixin.ees.web.dao.model.SysErrorAcctResult;

public interface ErrorAcctService {
	/**
	 * 条件过滤分页查询
	 * @param draw
	 * @param star
	 * @param length
	 * @param user
	 * @return
	 * @throws MyException 
	 */
	Page<SysErrorAcctResult> selectListResult(SysErrorAcctResult acct) throws MyException;
	
	ResultMessage update(SysErrorAcct acct);
}
