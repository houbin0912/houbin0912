package com.baixin.ees.util;

public class ResultMessage {
	private boolean success;
	private String errorMessage;
	private String successMessage;
	private String refUrl;
	
	public ResultMessage() {
		super();
	}

	public ResultMessage(boolean success, String errorMessage, String successMessage, String refUrl) {
		super();
		this.success = success;
		this.errorMessage = errorMessage;
		this.successMessage = successMessage;
		this.refUrl = refUrl;
	}
	
	public static ResultMessage isOk() {
		return new ResultMessage(true, null, "操作成功",null);
	}

	public static ResultMessage isOk(String refUrl) {
		return new ResultMessage(true, null, "操作成功", refUrl);
	}
	
	public static ResultMessage isOk(String successMessage,String refUrl) {
		return new ResultMessage(true, null, successMessage, refUrl);
	}
	
	public static ResultMessage isError() {
		return new ResultMessage(false, "操作失败",null, null);
	}
	
	public static ResultMessage isError(String errorMessage) {
		return new ResultMessage(false,errorMessage, null, null);
	}
	public static ResultMessage isError(String errorMessage,String refUrl) {
		return new ResultMessage(false,errorMessage, null, refUrl);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getRefUrl() {
		return refUrl;
	}
	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}
}
