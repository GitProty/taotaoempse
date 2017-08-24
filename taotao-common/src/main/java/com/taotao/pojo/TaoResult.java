package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class TaoResult<T> implements Serializable {

	/**
	 * 分页需要的数据
	 */
	private List<T> rows; // 数据结果集
	private Long total; // 总条数

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
