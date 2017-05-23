package com.hy.ly.service;

import java.util.List;

import com.hy.ly.po.ItemsCustom;
import com.hy.ly.po.ItemsQueryVo;

public interface ItemsService {

	// 商品的查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	// 根据Id查询商品信息
	public ItemsCustom findItemsById(Integer itemsId) throws Exception;

	// 根据Id修改商品信息
	public void updateItemsById(Integer itemsId,ItemsCustom itemsCustom) throws Exception;
	
	//删除商品
	public void deleteItemsById(Integer itemsId) throws Exception;
}
