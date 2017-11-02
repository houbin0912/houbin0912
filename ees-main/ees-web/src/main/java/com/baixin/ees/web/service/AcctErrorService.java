package com.baixin.ees.web.service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorResult;

public interface AcctErrorService {
	/**
	 * 条件过滤分页查询
	 * @param draw
	 * @param star
	 * @param length
	 * @param user
	 * @return
	 * @throws MyException 
	 */
	Page<AcctErrorResult> selectListResult(AcctErrorResult acct) throws MyException;
	
	ResultMessage update(AcctError acct);
}
