package com.baixin.ees.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorResult;
import com.baixin.ees.web.service.AcctErrorService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:mybatis-config.xml" })
/**
 * Acct Service 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestAcctService {

	@Autowired
	private AcctErrorService acctService;

	/**
	 * 查询列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception {
		AcctErrorResult acct = new AcctErrorResult();
		acct.setStart(0);
		acct.setLength(3);
		acct.setDraw(1);
		Page<AcctErrorResult> selectListResult = acctService.selectListResult(acct);
		System.out.println(JSONObject.toJSON(selectListResult));
	}

	/**
	 * 修改错账
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateRole() throws Exception {
		AcctError acct = new AcctError();
		acct.setAcctErrId("7");
		acct.setProcessResult("0");
		acct.setErrReason("是错账");
		ResultMessage updateRole = acctService.update(acct);
		System.out.println(JSONObject.toJSON(updateRole));
	}
}
