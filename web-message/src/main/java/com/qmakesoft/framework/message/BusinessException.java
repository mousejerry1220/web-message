package com.qmakesoft.framework.message;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	int code;
	
	public BusinessException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	public BusinessException(String message) {
		super(message);
		this.code = 500;
	}

	public int getCode() {
		return code;
	}
}
