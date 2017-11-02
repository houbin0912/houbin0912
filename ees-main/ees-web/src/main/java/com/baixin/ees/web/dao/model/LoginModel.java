package com.baixin.ees.web.dao.model;

public class LoginModel {

	private String loginResult = "false";
	private String errMessage = "";
	private String menuUrl = "";
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	
	
}
