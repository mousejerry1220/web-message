package com.qmakesoft.framework.message.validate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;

@Aspect
public class ControllerValidatorInterceptor {
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) "
			+ "|| @annotation(org.springframework.web.bind.annotation.PostMapping) "
			+ "|| @annotation(org.springframework.web.bind.annotation.GetMapping) "
			+ "|| @annotation(org.springframework.web.bind.annotation.PutMapping) "
			+ "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) ")
    public void pointcut(){}
	
	@Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
		for(Object obj : joinPoint.getArgs()) {
        	if(obj instanceof BindingResult) {
        		ValidatorUtils.validateBindingResult((BindingResult)obj);
        	}
        }
    }
	
}
