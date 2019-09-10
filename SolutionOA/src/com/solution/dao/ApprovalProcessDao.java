package com.solution.dao;

import java.util.List;

import com.solution.entity.ApprovalProcess;

public interface ApprovalProcessDao {
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
}
