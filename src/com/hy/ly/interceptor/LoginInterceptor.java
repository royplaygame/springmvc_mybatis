package com.hy.ly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//测试拦截器1
public class LoginInterceptor implements HandlerInterceptor {
	
	//进入handler方法之前
	//用于身份认证、省份授权，如果认证不通过，需要此方法拦截，不向下执行。
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// 获取请求的Url,
		String url=request.getRequestURI();
		//判断url是否是公开地址
		if(url.indexOf("login.action")>0){
			//登录提交放行
			return true;
		}
		
		//判断session
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		
		if(username!=null){
			//认证通过，放行
			return true;
		}
		
		//所有认证信息不通过，跑转到登录页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		
		//false表示拦截，true表示放行
		return true;
	}

	//进入handler方法之后，在返回ModelAndView之前
	//ModelAndView,将公用的模型数据传到视图
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor1.....postHandle");
	}

	//执行handler完成，执行此方法
	//可以使用统一的的异常处理，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor1.....afterCompletion");

	}

}
