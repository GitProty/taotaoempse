package com.taotao.manager.baseService.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.taotao.manager.baseService.BaseService;

/**
 * 通用service接口的实现
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private Mapper<T> mapper; // 通用mapper接口
	private Class<T> clazz; // 泛型的class

	public BaseServiceImpl() {
		//获取父类的类型
		Type type = this.getClass().getGenericSuperclass();
		
		//强转为ParameterizedType，获取父类类型的方法
		ParameterizedType pType = (ParameterizedType) type;
		
		//获取泛型的class
		this.clazz = (Class<T>) pType.getActualTypeArguments()[0];
	}

	@Override
	public T queryById(Long id) {
		T t = this.mapper.selectByPrimaryKey(id);
		return t;
	}

	@Override
	public List<T> queryAll() {
		List<T> list = this.mapper.select(null);
		return list;
	}

	@Override
	public Integer queryCountByWhere(T t) {
		int count = this.mapper.selectCount(t);
		return count;
	}

	@Override
	public List<T> queryListByWhere(T t) {
		List<T> list = this.mapper.select(t);
		return list;
	}

	@Override
	public List<T> queryByPage(Integer page, Integer rows) {
		//设置分页条件
		PageHelper.startPage(page, rows);
		
		List<T> list = this.mapper.select(null);
		return list;
	}

	@Override
	public T queryOne(T t) {
		T result = this.mapper.selectOne(t);
		return result;
	}

	@Override
	public void save(T t) {
		this.mapper.insert(t);
	}

	@Override
	public void saveSelective(T t) {
		this.mapper.insertSelective(t);
	}

	@Override
	public void updateById(T t) {
		this.mapper.updateByPrimaryKey(t);
	}

	@Override
	public void updateByIdSelective(T t) {
		this.mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void deleteById(Long id) {
		this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByIds(List<Object> ids) {
		Example example = new Example(this.clazz);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", ids);
		
		this.mapper.deleteByExample(example);
	}
}
