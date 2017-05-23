package com.hy.ly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.ly.po.ItemsCustom;
import com.hy.ly.po.ItemsQueryVo;
import com.hy.ly.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	//查询所有商品列表
	//@RequestMapping("/queryItems.action")
	//限制http请求方法
	@RequestMapping(value="/queryItems.action",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception {
		
		String itemsId=request.getParameter("itemsId");
		
		List<ItemsCustom> list = itemsService.findItemsList(itemsQueryVo);

		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("items/ItemsList");
		return mv;
	}
	
	@RequestMapping(value="/queryItems1.action",method={RequestMethod.POST,RequestMethod.GET})
	public String queryItems1(Model model) throws Exception {
		List<ItemsCustom> list = itemsService.findItemsList(null);
		
		//通过形参中的model将数据发送到页面中
		model.addAttribute("list", list);
		return "items/ItemsList";
	}
	
	//修改商品信息的展示
	@RequestMapping("/editItems.action")
	public ModelAndView editItems(@RequestParam(value="itemsId",required=true,defaultValue="1")Integer itemsId) throws Exception{
		//调用service查询音品信息
		ItemsCustom itemsCustom=itemsService.findItemsById(itemsId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("items/editItems");
		mv.addObject("itemsCustom", itemsCustom);
		return mv;
	}
	
	//修改商品信息的提交
	@RequestMapping("/editItemsSubmit.action")
	public String editItemsSubmit(HttpServletRequest request,@RequestParam(value="itemsId")Integer id,ItemsCustom itemsCustom) throws Exception{
		//调用service修改音品信息
		itemsService.updateItemsById(id, itemsCustom);
		
		//重定向和转发的写法
		//redirect:queryItems.action;
		
		//转发可以传数
		return "forward:queryItems.action";
	}
	
	@RequestMapping("/deleMoreItems.action")
	public String deleMoreItems(HttpServletRequest request,@RequestParam(value="itemsIds")Integer[] ids)throws Exception{
		for(Integer id:ids){
			//删除商品
			itemsService.deleteItemsById(id);
		}
		return "redirect:queryItems.action";
	}
}
