package com.baixin.ees.web.controller;

import com.baixin.ees.web.dao.model.BooleanResultModel;

public class BaseControl {

	/**
	 * 根据sql的返回结果，制定返回值，只有true和false
	 * @param resultNum
	 * @return
	 */
	public String getResult(int resultNum){
		if(resultNum >= 0){
			return "true";
		}
		return "false";
	}
	/**
	 * 根据sql的返回结果，返回booleanresultmodel
	 * @param resultNum
	 * @param errorMessage
	 * @param successUrl
	 * @param failUrl
	 * @return
	 */
	public BooleanResultModel getBooleanResultModel(int resultNum,String errorMessage,String successUrl,String failUrl) {
		BooleanResultModel model = new BooleanResultModel();
		if(resultNum >= 0){
			model.setActionResult("true");
			model.setRefUrl(successUrl);
		}else {
			model.setActionResult("false");
			model.setErrMessage(errorMessage);
			model.setRefUrl(failUrl);
		}
		return model;
	}
	
}
