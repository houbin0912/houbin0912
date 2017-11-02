package com.baixin.ees.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorResult;
import com.baixin.ees.web.service.AcctErrorService;

@Controller
@RequestMapping("/sys/acct")
public class ErrorAcctController {

	@Autowired
	private AcctErrorService acctService;
	/**
	 * 错账列表
	 * @param acct
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<AcctErrorResult> listUser(AcctErrorResult acct) throws MyException {
		return acctService.selectListResult(acct);
	}
	/**
	 * 错账编辑
	 * @param acct
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage update(AcctError acct) {
		return acctService.update(acct);
	}
	/**
	 * 指明日期格式
	 * @param dataBinder
	 */
	@InitBinder  
	public void InitBinder(WebDataBinder dataBinder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setLenient(false);  
	    dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	} 
	
}
