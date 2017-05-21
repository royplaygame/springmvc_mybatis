package com.hy.ly.mapper;

import java.util.List;

import com.hy.ly.po.ItemsCustom;
import com.hy.ly.po.ItemsQueryVo;

public interface ItemsMapperCustom {
   //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}