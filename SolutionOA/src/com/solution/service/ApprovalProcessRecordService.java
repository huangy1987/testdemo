package com.solution.service;

import java.util.List;

import com.solution.entity.ApprovalProcessRecord;

public interface ApprovalProcessRecordService {
	//查全部
	public List<ApprovalProcessRecord> findAllApprovalProcessRecord();
	//新增
	public int insert(ApprovalProcessRecord apr);
	//按条件查询
	public ApprovalProcessRecord findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(ApprovalProcessRecord apr);
}
