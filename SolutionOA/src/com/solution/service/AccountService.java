package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.Account;
import com.solution.entity.Page;

/**
 * 
	*@function:账户业务接口
	*@Version 1.0.0
	*@author 黄跃
 */
public interface AccountService {
	//查全部
		public List<Account> findAllAccount();
		//新增
		public int insert(Account account);
		//按条件查询
		public Account findById(int id);
		//删除
		public int delete(int id);
		//修改
		public int update(Account account);
		//分页查询
		public Page<?> know_querypage(Map<Object, Object> c);
}
