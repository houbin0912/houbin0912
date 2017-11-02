package com.baixin.ees.util;
/**
 * 异常统一处理
 * @author zhangguoshuai
 *
 */
public class MyException extends Exception{

	private static final long serialVersionUID = 1L;
	/**
	 * 错误代码
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String message;
	
	public MyException() {
		super();
	}
	public MyException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
