package com.solution.dao;

import java.util.List;

import com.solution.entity.Module;

public interface ModuleDao {
	//查全部
	public List<Module> findAllModule();
	//新增
	public int insert(Module module);
	//按条件查询
	public Module findById(int id);
	//按模型索引
	public Module findByIndex(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Module module);
}
