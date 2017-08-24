package com.taotao.manager.service;

import com.taotao.manager.baseService.BaseService;
import com.taotao.manager.pojo.Item;
import com.taotao.pojo.TaoResult;

/**
 *  商品信息
 * @author shao
 */
public interface ItemService extends BaseService<Item> {
	/**
	 * 保存商品
	 */
	public void saveItem(Item item,String desc);

	
	/**
	 * 分页查询
	 */
	public TaoResult<Item> queryItemList(Integer page, Integer pageSize);
}
