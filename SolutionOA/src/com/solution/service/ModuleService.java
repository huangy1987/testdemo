package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.Module;
import com.solution.entity.Page;

public interface ModuleService {
	//查全部
	public List<Module> findAllModule();
	//新增
	public int insert(Module ar);
	//按条件查询
	public Module findById(int id);
	//按模型索引
	public Module findByIndex(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Module ar);
	//分页
	public Page<?> know_querypage(Map<Object, Object> c);
}
