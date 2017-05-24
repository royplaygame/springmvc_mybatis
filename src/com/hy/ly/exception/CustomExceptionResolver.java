package com.hy.ly.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//handler就是处理器适配要执行的handler对象
		
		
		//解析出异常的类型
		
		//如果该异常是系统自定义的异常，直接取出异常信息，在错误页面显示
		/*String message="";
		if(ex instanceof CustomException){
			message=((CustomException) ex).getMessage();
		}else{
			// 如果该异常不是系统自定义的异常，构造一个自定义异常（信息为“未知错误”）
			message="未知错误";
		}*/
		//上面的代码变为：
		CustomException customException=null;
		if(ex instanceof CustomException){
			customException=(CustomException) ex;
		}else{
			customException=new CustomException("未知错误");
		}
		String message=customException.getMessage();
		
		//在错误页面展示
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		
		//指向错误页面
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
