package com.qmakesoft.framework.message;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.qmakesoft.framework.message.validate.ValidateException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class SystemExceptionHandler {
	
	@ResponseBody
    @ExceptionHandler(value = HttpStatusCodeException.class)
    public R httpStatusCodeException(HttpStatusCodeException ex , HttpServletResponse response) {
		response.setStatus(((HttpStatusCodeException) ex).getRawStatusCode());
		return R.error(((HttpStatusCodeException) ex).getRawStatusCode() , ex.getMessage());
	}
	
	
	@ResponseBody
    @ExceptionHandler(value = ValidateException.class)
    public R validateException(ValidateException ex , HttpServletResponse response) {
		response.setStatus(410);
		return R.error(410, ((ValidateException)ex).getList());
	}
	
	@ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public R httpMessageNotReadableException(HttpMessageNotReadableException ex , HttpServletResponse response) {
		return R.error(500, "请检查参数格式是否为正确格式的JSON");
	}
	
	@ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public R businessException(BusinessException ex , HttpServletResponse response) {
		response.setStatus(500);
		return R.error(ex.getCode(), ex.getMessage());
	}
	
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception ex , HttpServletResponse response) {
       //未知异常
	   log.error(getStackTrace(ex));
       return R.error(500, ex.getMessage());
    }
	
	private String getStackTrace(Exception e) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		sb.append(sdf.format(new Date())).append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
		StackTraceElement[] stes = e.getStackTrace();
		for(StackTraceElement ste : stes) {
			sb.append("\t").append(ste.getClassName()).append(".").append(ste.getMethodName()).append("(").append(ste.getFileName()).append(":").append(ste.getLineNumber()).append(")").append("\n");
		}
		return sb.toString();
	}
	
}
