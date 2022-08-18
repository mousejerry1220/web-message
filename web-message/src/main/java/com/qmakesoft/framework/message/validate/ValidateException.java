package com.qmakesoft.framework.message.validate;

import java.util.List;

import com.qmakesoft.framework.message.validate.ValidatorUtils.FieldErrorMessage;

public class ValidateException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	List<FieldErrorMessage> list;
	
	public ValidateException(List<FieldErrorMessage> list){
		this.list = list;
	}

	public List<FieldErrorMessage> getList() {
		return list;
	}
	
}
