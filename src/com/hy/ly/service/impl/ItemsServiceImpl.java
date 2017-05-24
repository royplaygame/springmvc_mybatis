package com.hy.ly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hy.ly.mapper.ItemsMapper;
import com.hy.ly.mapper.ItemsMapperCustom;
import com.hy.ly.po.Items;
import com.hy.ly.po.ItemsCustom;
import com.hy.ly.po.ItemsQueryVo;
import com.hy.ly.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	@Autowired
	ItemsMapperCustom itemsMapperCustom;

	@Autowired
	ItemsMapper itemsMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		// 通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer itemsId) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(itemsId);
		ItemsCustom itemsCustom=null;
		if(items!=null){
			// 中间对商品信息进行业务处理
			// .....
			// 返回ItemsCustom
			itemsCustom = new ItemsCustom();
			// 将Items的属性拷贝到itemsCustom中
			// BeanUtils.copyProperties(items, itemsCustom);
			itemsCustom.setId(items.getId());
			itemsCustom.setName(items.getName());
			itemsCustom.setPrice(items.getPrice());
			itemsCustom.setDetail(items.getDetail());
			itemsCustom.setCreatetime(items.getCreatetime());
			itemsCustom.setPic(items.getPic());
		}
		
		return itemsCustom;
	}

	@Override
	public void updateItemsById(Integer itemsId, ItemsCustom itemsCustom)
			throws Exception {
		// 添加业务校验,通常在service对关键参数进行校验
		// 较验id是否为空，如果为空抛出异常
		itemsCustom.setId(itemsId);
		// 使用updateByPrimaryKeyWithBLOBs会根据id更新商品表中的所有字段,包括大文本类型
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

	@Override
	public void deleteItemsById(Integer itemsId) throws Exception {
		itemsMapper.deleteByPrimaryKey(itemsId);
	}
}
