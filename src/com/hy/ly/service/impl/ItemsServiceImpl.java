package com.hy.ly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hy.ly.mapper.ItemsMapperCustom;
import com.hy.ly.po.ItemsCustom;
import com.hy.ly.po.ItemsQueryVo;
import com.hy.ly.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	@Autowired
	ItemsMapperCustom itemsMapperCustom;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		// 通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

}
