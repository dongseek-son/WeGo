package com.ktds.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class QoSLogInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = LoggerFactory.getLogger(QoSLogInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI();
		String method = request.getMethod();
		
		logger.warn("#" + ip + "#" + uri + "#" + method);
		
		return true;
	}
	
}
