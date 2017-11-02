package com.baixin.ees.web.controller;

import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.service.CBSInteriorFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/sys/errdatail")
public class CBSInteriorFlowController {

	@Autowired
	private CBSInteriorFlowService cbsInteriorFlowService;
	/**
	 * 错帐列表
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<Map<String,String>> list(){
		return null;
	}


}
