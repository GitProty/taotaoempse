package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import com.taotao.pojo.TaoResult;

@Controller
@RequestMapping("/item")
public class ItemController {
	// type: "POST",
	// url: "/rest/content",

	@Autowired
	private ItemService itemService;

	/**
	 * 保存商品
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String saveItem(Item item, String desc) {
		this.itemService.saveItem(item, desc);
		return "0";
	}

	/**
	 * 分页查询商品
	 */
	// url:'/rest/item',method:'get',pageSize:30
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public TaoResult<Item> queryItemList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize) {

		TaoResult<Item> taoResult = itemService.queryItemList(page, pageSize);
		return taoResult;
	}
}
