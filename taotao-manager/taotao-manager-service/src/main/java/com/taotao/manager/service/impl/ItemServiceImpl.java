package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.baseService.impl.BaseServiceImpl;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;
import com.taotao.pojo.TaoResult;

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
	
	/**
	 * 分页查询商品
	 */
	@Override
	public TaoResult<Item> queryItemList(Integer page, Integer pageSize) {
		
		//设置查询条件,, 当前页码和每页显示的条数
		PageHelper.startPage(page, pageSize);
		
		//总的数据条数
		List<Item> list = super.queryListByWhere(null);
		
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		//封装分页需要的数据
		TaoResult<Item> taoResult = new TaoResult<>();
		taoResult.setRows(list);
		taoResult.setTotal(pageInfo.getTotal());
		
		return taoResult;
	}
}
