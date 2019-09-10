package com.solution.dao;

import java.util.List;

import com.solution.entity.Dcxmlxb;

public interface DcxmlxbDao{
	//查全部
	public List<Dcxmlxb> findAllDcxmlxb();
	//新增
	public int insert(Dcxmlxb dc);
	//按条件查询
	public Dcxmlxb findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Dcxmlxb dc);
}
