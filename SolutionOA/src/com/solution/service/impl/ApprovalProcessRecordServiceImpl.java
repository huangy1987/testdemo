package com.solution.service.impl;

import java.util.List;

import com.solution.dao.ApprovalProcessRecordDao;
import com.solution.entity.ApprovalProcessRecord;
import com.solution.service.ApprovalProcessRecordService;

public class ApprovalProcessRecordServiceImpl implements ApprovalProcessRecordService{
	private ApprovalProcessRecordDao approvalProcessRecordDao;
	
	public ApprovalProcessRecordDao getApprovalProcessRecordDao(){
		return approvalProcessRecordDao;
	}

	public void setApprovalProcessRecordDao(ApprovalProcessRecordDao approvalProcessRecordDao){
		this.approvalProcessRecordDao = approvalProcessRecordDao;
	}

	@Override
	public int delete(int id){
		return approvalProcessRecordDao.delete(id);
	}

	@Override
	public List<ApprovalProcessRecord> findAllApprovalProcessRecord(){
		return approvalProcessRecordDao.findAllApprovalProcessRecord();
	}

	@Override
	public ApprovalProcessRecord findById(int id){
		return approvalProcessRecordDao.findById(id);
	}

	@Override
	public int insert(ApprovalProcessRecord apr){
		return approvalProcessRecordDao.insert(apr);
	}

	@Override
	public int update(ApprovalProcessRecord apr){
		return approvalProcessRecordDao.update(apr);
	}

}
