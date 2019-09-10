package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.AccountDao;
import com.solution.entity.Account;
import com.solution.entity.Page;
import com.solution.service.AccountService;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	public AccountDao getAccountDao(){
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao){
		this.accountDao = accountDao;
	}

	@Override
	public int delete(int id){
		return accountDao.delete(id);
	}

	@Override
	public List<Account> findAllAccount(){
		return accountDao.findAllAccount();
	}

	@Override
	public Account findById(int id){
		return accountDao.findById(id);
	}

	@Override
	public int insert(Account account){
		return accountDao.insert(account);
	}

	@Override
	public int update(Account account){
		return accountDao.update(account);
	}
	
	
	public Page<?> know_querypage(Map<Object, Object> conditions) {
		int currentPage = (Integer)conditions.get("currentPage");
		int pageSize = (Integer)conditions.get("pageSize");
		String action = (String)conditions.get("action");
		List<Account> dataList =  accountDao.findAllAccount();
		List<Account> data = new ArrayList<Account>();
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
		
		for (int i = 0; i < dataList.size(); i++) {
			if((i >= first) && (i <= last)){
				data.add(dataList.get(i));
				System.out.print(dataList.get(i).getRealName() + "-");
			}
		}
		// 封装Page
		Page<Account> p = new Page<Account>();
		p.setCurrentPage(currentPage);
		p.setPageSize(pageSize);
		p.setTotalRecordCount(totalRecordCount);
		p.setTotalPageCount(totalRecordCount, pageSize);
		p.setDataList(data);
		p.setAction(action);
		p.setPageBar(currentPage, pageSize, totalRecordCount, action);
		System.out.println("datasize:"+data.size());
		return p;
	}


}
