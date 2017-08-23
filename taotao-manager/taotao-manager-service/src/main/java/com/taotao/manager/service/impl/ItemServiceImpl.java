package com.taotao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manager.baseService.impl.BaseServiceImpl;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

	@Autowired
	private ItemDescService itemDescService;	
	/**
	 * 保存商品信息
	 */
	@Override
	public void saveItem(Item item,String desc) {
		
		item.setStatus(1);	//设置上架 
		super.save(item);	//保存商品

		ItemDesc itemDesc = new ItemDesc();
		//商品id
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		
		//保存商品描述
		itemDescService.save(itemDesc);	
	}
}
