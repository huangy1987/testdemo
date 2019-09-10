package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.Dcxmlxb;
import com.solution.entity.Page;

public interface DcxmlxbService{
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
	//分页
	public Page<?> know_querypage(Map<Object, Object> c);
}
