package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.manager.baseService.impl.BaseServiceImpl;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {

	/**
	 * 通过id查询商品类目
	 * @return
	 */
	@Override
	public List<ItemCat> queryItemCatByParentId(Long parentId) {
		
		ItemCat parm = new ItemCat();
		parm.setParentId(parentId);
		
		List<ItemCat> list = super.queryListByWhere(parm);
		
		return list;
	}
}