package com.ssm.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor {

	/**
	 * 执行完了handler之后执行此方法
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	/**
	 * 请求到了handler之后，返回ModelAndView之前 
	 * 使用：给指定的资源统一添加资源
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 执行handler之前调用此方法，
	 * return false:直接结束请求
	 * return true:放行
	 * 
	 * 使用：身份认证
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
