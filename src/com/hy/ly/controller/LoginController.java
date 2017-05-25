package com.hy.ly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

	// 登录
	@RequestMapping("/login.action")
	public String login(HttpSession session,String userName, String passWord) throws Exception {

		//调用service进行身份认证
		
		//在session保存用户的身份信息
		session.setAttribute("username", userName);
		return "redirect:/items/queryItems.action";
	}

	// 退出
	@RequestMapping("/logout.action")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/items/queryItems.action";
	}

}