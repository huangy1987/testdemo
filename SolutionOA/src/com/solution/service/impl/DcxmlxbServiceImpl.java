package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.DcxmlxbDao;
import com.solution.entity.AccountRole;
import com.solution.entity.ApprovalProcessRecord;
import com.solution.entity.Dcxmlxb;
import com.solution.entity.Page;
import com.solution.service.DcxmlxbService;

public class DcxmlxbServiceImpl implements DcxmlxbService{
	private DcxmlxbDao dcxmlxbDao;
	
	public DcxmlxbDao getDcxmlxbDao(){
		return dcxmlxbDao;
	}

	public void setDcxmlxbDao(DcxmlxbDao dcxmlxbDao){
		this.dcxmlxbDao = dcxmlxbDao;
	}

	@Override
	public int delete(int id){
		return dcxmlxbDao.delete(id);
	}

	@Override
	public List<Dcxmlxb> findAllDcxmlxb(){
		return dcxmlxbDao.findAllDcxmlxb();
	}

	@Override
	public Dcxmlxb findById(int id){
		return dcxmlxbDao.findById(id);
	}

	@Override
	public int insert(Dcxmlxb dc){
		return dcxmlxbDao.insert(dc);
	}

	@Override
	public int update(Dcxmlxb dc){
		return dcxmlxbDao.update(dc);
	}

	@Override
	public Page<?> know_querypage(Map<Object, Object> c){
		int currentPage = (Integer)c.get("currentPage");
		int pageSize = (Integer)c.get("pageSize");
		String action = (String)c.get("action");
		List<ApprovalProcessRecord> dataList = (List<ApprovalProcessRecord>)c.get("approvalProcessRecordList");
		List<ApprovalProcessRecord> data = new ArrayList<ApprovalProcessRecord>();
		int totalRecordCount = 0;
		if(dataList != null){
			totalRecordCount = dataList.size();
		}
		int first = (currentPage-1) * pageSize + 1;
		int last = currentPage * pageSize;
		int pageNum = 0;
		if(totalRecordCount % pageSize == 0){//整数页
			if(pageNum == 1){//第一页
				first = 0;
			}
			pageNum = totalRecordCount / pageSize;
		}else{//不全页
			pageNum = totalRecordCount / pageSize + 1;
			if(last >= totalRecordCount && (pageNum == 1)){//只有一页
				first = 0;
				last = totalRecordCount;
			}
			if((last < totalRecordCount) && (pageNum > 1) && (first == 1)){//不只一页,查询第一页
				first = 0;
				last= pageSize - 1;
			}
			if(last > totalRecordCount && (pageNum > 1) && (first > 1)){//不只一页,查询最后一页
				first = first - 1;
				last = totalRecordCount;
			}
			if(last < totalRecordCount && pageNum >= 3  && (first > 1)){//不只一页,查询非首尾页
				first = (currentPage-1) * pageSize;
				last = currentPage * pageSize - 1;
				System.out.println(4);
			}
		}
		if(dataList != null){
			for (int i = 0; i < dataList.size(); i++) {
				if((i >= first) && (i <= last)){
					data.add(dataList.get(i));
				}
			}
		}
		// 封装Page
		Page<ApprovalProcessRecord> p = new Page<ApprovalProcessRecord>();
		p.setCurrentPage(currentPage);
		p.setPageSize(pageSize);
		p.setTotalRecordCount(totalRecordCount);
		p.setTotalPageCount(totalRecordCount, pageSize);
		p.setDataList(data);
		p.setAction(action);
		p.setPageBar(currentPage, pageSize, totalRecordCount, action);
		return p;
	}

}
