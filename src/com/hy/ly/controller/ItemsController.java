package com.hy.ly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hy.ly.po.ItemsCustom;
import com.hy.ly.service.ItemsService;

@Controller
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems.action")
	public ModelAndView queryTrainInfo() throws Exception {
		List<ItemsCustom> list = itemsService.findItemsList(null);

		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("items/ItemsList");
		return mv;
	}
}
