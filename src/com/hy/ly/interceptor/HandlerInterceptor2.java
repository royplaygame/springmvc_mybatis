package com.hy.ly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//测试拦截器1
public class HandlerInterceptor2 implements HandlerInterceptor {
	
	//进入handler方法之前
	//用于身份认证、省份授权，如果认证不通过，需要此方法拦截，不向下执行。
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2.....preHandle");
		//false表示拦截，true表示放行
		return true;
	}

	//进入handler方法之后，在返回ModelAndView之前
	//ModelAndView,将公用的模型数据传到视图
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2.....postHandle");
	}

	//执行handler完成，执行此方法
	//可以使用统一的的异常处理，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2.....afterCompletion");

	}

}
