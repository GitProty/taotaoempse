package com.taotao.manager.baseService;

import java.util.List;
/**
 *  通用service接口
 */
public interface BaseService<T> {

	/**
	 * 通过id查询数据
	 */
	public T queryById(Long id);

	/**
	 * 查询所有数据
	 */
	public List<T> queryAll();

	/**
	 * 根据条件查询数据条数
	 */
	public Integer queryCountByWhere(T t);

	/**
	 * 根据条件查询数据
	 */
	public List<T> queryListByWhere(T t);

	/**
	 * 分页查询数据
	 */
	public List<T> queryByPage(Integer page, Integer rows);

	/**
	 * 根据条件查询一条数据
	 */
	public T queryOne(T t);

	/**
	 * 新增
	 */
	public void save(T t);

	/**
	 * 新增，忽略空参数
	 */
	public void saveSelective(T t);

	/**
	 * 根据主键更新
	 */
	public void updateById(T t);

	/**
	 * 根据主键更新，忽略空参数
	 */
	public void updateByIdSelective(T t);

	/**
	 * 根据id删除数据
	 */
	public void deleteById(Long id);

	/**
	 * 根据ids批量删除数据
	 */
	public void deleteByIds(List<Object> ids);
}
