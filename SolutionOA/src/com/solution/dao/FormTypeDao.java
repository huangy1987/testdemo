package com.solution.dao;

import java.util.List;

import com.solution.entity.FormType;

public interface FormTypeDao {
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

}
