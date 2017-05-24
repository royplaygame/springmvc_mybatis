package com.hy.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.ly.po.ItemsCustom;

//json交互测试
@Controller
public class JsonController {

	// 请求是json,输出是json
	// @RequestBody将请求信息的json串转化成ItemsCustom
	// @ResponseBody将ItemsCustom转化成json串
	@RequestMapping("/requestJson.action")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {

		// @ResponseBody将ItemsCustom转化成json串
		return itemsCustom;
	}
	
	
	//请求是key/value,输出是json
	@RequestMapping("/responseJson.action")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {

		// @ResponseBody将ItemsCustom转化成json串
		return itemsCustom;
	}

}
