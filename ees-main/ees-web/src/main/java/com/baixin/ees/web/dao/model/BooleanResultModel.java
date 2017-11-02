package com.baixin.ees.web.dao.model;

public class BooleanResultModel {

	private String actionResult = "false";
	
	private String errMessage = ""; 
	
	private String refUrl = "";

	public BooleanResultModel() {}

	public BooleanResultModel(String actionResult, String errMessage, String refUrl) {
		super();
		this.actionResult = actionResult;
		this.errMessage = errMessage;
		this.refUrl = refUrl;
	}

	public BooleanResultModel(int resultNum) {
		if(resultNum >= 0){
			this.actionResult = "true";
			this.errMessage = "操作成功";
		}else{
			this.actionResult = "false";
			this.errMessage = "操作失败";
		}
	}
	
	public String getActionResult() {
		return actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getRefUrl() {
		return refUrl;
	}

	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}
	
	
}
