package com.hy.ly.po;

import java.util.List;


public class ItemsQueryVo {
	//商品信息
	private Items items;
	
	//商品扩展信息
	private ItemsCustom itemsCustom;
	
	//批量商品信息
	private List<ItemsCustom> ItemsList;

	public List<ItemsCustom> getItemsList() {
		return ItemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		ItemsList = itemsList;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
}