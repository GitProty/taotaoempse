package com.taotao.manager.service;

import java.util.List;

import com.taotao.manager.baseService.BaseService;
import com.taotao.manager.pojo.ItemCat;

/**
 * 商品类目业务逻辑接口
 * @author Steven
 *
 */
public interface ItemCatService extends BaseService<ItemCat> {
	/**
	 * 通过id查询商品类目
	 * @return
	 */
	public List<ItemCat> queryItemCatByParentId(Long parentId);
	
}
