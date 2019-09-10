package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.Fdclsxmlxb;
import com.solution.entity.Page;

public interface FdclsxmlxbService{
	//查全部
	public List<Fdclsxmlxb> findAllFdclsxmlxb();
	//新增
	public int insert(Fdclsxmlxb dc);
	//按条件查询
	public Fdclsxmlxb findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Fdclsxmlxb dc);
	//分页
	public Page<?> know_querypage(Map<Object, Object> c);
}
