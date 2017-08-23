package com.taotao.manager.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;		//类目ID

	@Column(name = "parent_id")
	private Long parentId;		//父类目ID=0时，代表的是一级的类目

	private String name;	//类目名称

	private Integer status;		//状态。可选值:1(正常),2(删除)

	@Column(name = "sort_order")	
	private Integer sortOrder;		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数

	@Column(name = "is_parent")
	private Boolean isParent;		//该类目是否为父类目，1为true，0为false

	// 将名称设置到树的控件中
	public String getText() {
		return this.getName();
	}

	// 判断节点是不是父节点,如果是父节点就展开(open),是子节点就(closed)
	public String getState() {
		return this.isParent ? "closed" : "open";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	@Override
	public String toString() {
		return "ItemCat [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status + ", sortOrder="
				+ sortOrder + ", isParent=" + isParent + "]";
	}
}
