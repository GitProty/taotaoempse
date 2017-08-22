package com.taotao.manager.service;

import java.util.List;

import com.taotao.manager.pojo.ItemCat;

/**
 * 商品类目业务逻辑接口
 * @author Steven
 *
 */
public interface ItemCatService {
	/**
	 * 分页查询商品类目
	 * @return
	 */
	List<ItemCat> queryItemCatByPage(Integer page,Integer rows);
}
