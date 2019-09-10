package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.AccountRoleDao;
import com.solution.entity.Account;
import com.solution.entity.AccountRole;
import com.solution.entity.Page;
import com.solution.service.AccountRoleService;

public class AccountRoleServiceImpl implements AccountRoleService {
	private AccountRoleDao accountRoleDao;
	
	public AccountRoleDao getAccountRoleDao() {
		return accountRoleDao;
	}

	public void setAccountRoleDao(AccountRoleDao accountRoleDao) {
		this.accountRoleDao = accountRoleDao;
	}

	@Override
	public List<AccountRole> findAllRole() {
		return accountRoleDao.findAllRole();
	}

	@Override
	public int insert(AccountRole ar) {
		return accountRoleDao.insert(ar);
	}

	@Override
	public AccountRole findById(int id) {
		return accountRoleDao.findById(id);
	}

	@Override
	public int delete(int id) {
		return accountRoleDao.delete(id);
	}

	@Override
	public int update(AccountRole ar) {
		return accountRoleDao.update(ar);
	}
	public Page<?> know_querypage(Map<Object, Object> c){
		int currentPage = (Integer)c.get("currentPage");
		int pageSize = (Integer)c.get("pageSize");
		String action = (String)c.get("action");
		List<AccountRole> dataList =  accountRoleDao.findAllRole();
		List<AccountRole> data = new ArrayList<AccountRole>();
		int totalRecordCount = dataList.size();
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
		//
		for (int i = 0; i < dataList.size(); i++) {
			if((i >= first) && (i <= last)){
				data.add(dataList.get(i));
			}
		}
		// 封装Page
		Page<AccountRole> p = new Page<AccountRole>();
		p.setCurrentPage(currentPage);
		p.setPageSize(pageSize);
		p.setTotalRecordCount(totalRecordCount);
		p.setTotalPageCount(totalRecordCount, pageSize);
		p.setDataList(data);
		p.setAction(action);
		p.setPageBar(currentPage, pageSize, totalRecordCount, action);
		System.out.println("data:"+data.size());
		return p;
	}
}
