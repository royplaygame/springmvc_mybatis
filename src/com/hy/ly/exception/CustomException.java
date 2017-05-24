package com.hy.ly.exception;

/**
 * 自定义异常类
 * @author ssr
 *
 */
public class CustomException extends Exception{
	
	//异常信息
	private String message;
	public CustomException(String message){
		super(message);
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
