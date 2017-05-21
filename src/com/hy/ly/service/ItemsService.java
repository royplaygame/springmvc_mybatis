package com.hy.ly.service;

import java.util.List;

import com.hy.ly.po.ItemsCustom;
import com.hy.ly.po.ItemsQueryVo;

public interface ItemsService {

	// 商品的查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
