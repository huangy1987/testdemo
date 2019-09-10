package com.solution.dao;

import java.util.List;

import com.solution.entity.Account;

public interface AccountDao {
	//查全部
	public List<Account> findAllAccount();
	//新增
	public int insert(Account ac);
	//按条件查询
	public Account findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Account ac);

}
