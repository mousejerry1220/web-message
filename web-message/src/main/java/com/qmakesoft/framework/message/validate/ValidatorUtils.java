package com.qmakesoft.framework.message.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public class ValidatorUtils {
	
	@Data
	public static class FieldErrorMessage{
		String fieldName;
		String message;
	}
	
	static ObjectMapper objectMapper = new ObjectMapper();
	
	public static void validateBindingResult(BindingResult bindingResult) throws JsonProcessingException {
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			List<FieldErrorMessage> list = new ArrayList<>();
			for(FieldError error : errors) {
				FieldErrorMessage fem = new FieldErrorMessage();
				fem.fieldName = error.getField();
				fem.message = error.getDefaultMessage();
				list.add(fem);
			}
			throw new ValidateException(list);
		}
	}
}
