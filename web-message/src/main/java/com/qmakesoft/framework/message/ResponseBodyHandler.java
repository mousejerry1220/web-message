package com.qmakesoft.framework.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> ,InitializingBean{
	
	@Autowired
	ApplicationContext applicationContext;
	
	List<String> excludePaths;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, 
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, 
			ServerHttpRequest request,
			ServerHttpResponse response) {
		
		for(String excludePath : excludePaths) {
			if(request.getURI().getPath().indexOf(excludePath) == 0) {
				return body;
			}
		}
		
		if(body instanceof R) {
			if(((R)body).onlyData) {
				return ((R)body).data;
			}
			return body;
		}
		
		return R.success(body);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		excludePaths = new ArrayList<String>();
		Map<String,Object> emcMap = applicationContext.getBeansWithAnnotation(EnableWebMessageHandler.class);
		for(String key: emcMap.keySet()) {
			EnableWebMessageHandler emc = applicationContext.findAnnotationOnBean(key,EnableWebMessageHandler.class);
			for(String path : emc.excludePath()) {
				if(!path.isEmpty()) {
					excludePaths.add(path);
				}
			}
		}
		
	}

}
