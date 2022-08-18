package com.qmakesoft.framework.message;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.qmakesoft.framework.message.validate.EnableValidatorInterceptor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ResponseBodyHandler.class,SystemExceptionHandler.class})
@EnableValidatorInterceptor
public @interface EnableWebMessageHandler {
	
	String[] excludePath() default {"/swagger","/v2/api-docs"};
	
}