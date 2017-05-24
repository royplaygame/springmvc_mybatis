package com.hy.ly.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.ly.controller.validation.ValidGroupOne;
import com.hy.ly.exception.CustomException;
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
		
		//判断查询出来的商品是否为空，根据id查询出来的商品为空
		if(itemsCustom==null){
			throw new CustomException("修改的商品信息不存在");
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("items/editItems");
		mv.addObject("itemsCustom", itemsCustom);
		return mv;
	}
	
	//修改商品信息的提交
	//在需要校验的pojo前遍添加@Validated，在需要校验的pojo后面添加BindingResult参数来接收校验出错的信息
	//注意：如果有多个pojo要校验，@Validated和BindingResult是成对出现的，并且顺序是固定的，一前一后。
	//@Validated(value={ValidGroupOne.class})指定ValidGroupOne分组的校验
	//@ModuleAttribute回显到
	@RequestMapping("/editItemsSubmit.action")
	public String editItemsSubmit(Model model,HttpServletRequest request,@RequestParam(value="itemsId")Integer id,@Validated(value={ValidGroupOne.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
		//获取校验错误信息
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for(ObjectError obEr:allErrors){
				System.out.println(obEr.getDefaultMessage());
			}
			//将错误消息返回给页面
			model.addAttribute("allErrors", allErrors);
			//使用modul将pojo返回给页面
			//model.addAttribute("itemsCustom", "itemsCustom");
			//出错生新到商品修改页面
			return "items/editItems";
		}
		
		//调用service修改音品信息
		itemsService.updateItemsById(id, itemsCustom);
		
		//重定向和转发的写法
		//redirect:queryItems.action;
		
		//转发可以传数
		return "forward:queryItems.action";
	}
	//批量删除
	@RequestMapping("/deleMoreItems.action")
	public String deleMoreItems(HttpServletRequest request,@RequestParam(value="itemsIds")Integer[] ids)throws Exception{
		for(Integer id:ids){
			//删除商品
			itemsService.deleteItemsById(id);
		}
		return "redirect:queryItems.action";
	}
	
	//批量修改商品页面
	@RequestMapping(value="/editItemsQuery.action")
	public ModelAndView editItemsQuery(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception {
		
		List<ItemsCustom> list = itemsService.findItemsList(itemsQueryVo);

		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("items/editItemsQuery");
		return mv;
	}
	//批量修改商品提交
	//通过ItemsQueryVo接收批量提交的商品信息，把商品信息保存在itemsQueryVo中的itemsList属性中。
	@RequestMapping("/editItemsAllSubmit.action")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)throws Exception{
		for(ItemsCustom ic:itemsQueryVo.getItemsList()){
			//调用service修改音品信息
			itemsService.updateItemsById(ic.getId(), ic);
		}
		return "redirect:queryItems.action";
	}
	
	//itemTypes表示最终将方法的返回值放在request中的key
	@ModelAttribute("itemTypes")
	public Map<String,String> getItemTypes(){
		Map<String,String> itemTypes=new HashMap<String,String>();
		itemTypes.put("1001", "数码商品");
		itemTypes.put("1002", "母婴用品");
		itemTypes.put("1003", "生活用品");
		itemTypes.put("1004", "体育用品");
		itemTypes.put("1005", "办公用品");
		return itemTypes;
	}
}
