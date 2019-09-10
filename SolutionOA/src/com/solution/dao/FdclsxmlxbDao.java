package com.solution.dao;

import java.util.List;

import com.solution.entity.Fdclsxmlxb;


public interface FdclsxmlxbDao{
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
}
