package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.FormType;
import com.solution.entity.Module;
import com.solution.entity.Page;

public interface FormTypeService {
	//查全部
	public List<FormType> findAllFormType();
	//新增
	public int insert(FormType ft);
	//按条件查询
	public FormType findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(FormType ft);
	//分页查询
	public Page<?> know_querypage(Map<Object, Object> c);
}
