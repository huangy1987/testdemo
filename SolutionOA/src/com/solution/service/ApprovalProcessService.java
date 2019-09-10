package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.ApprovalProcess;
import com.solution.entity.Page;

public interface ApprovalProcessService {
	//查全部
	public List<ApprovalProcess> findAllApprovalProcess();
	//新增
	public int insert(ApprovalProcess ap);
	//按条件查询
	public ApprovalProcess findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(ApprovalProcess ap);
	//分页查询
	public Page<?> know_querypage(Map<Object, Object> c);
}
