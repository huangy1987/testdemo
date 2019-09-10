package com.solution.dao;

import java.util.List;

import com.solution.entity.AccountRole;

public interface AccountRoleDao {
	//查全部
	public List<AccountRole> findAllRole();
	//新增
	public int insert(AccountRole ar);
	//按条件查询
	public AccountRole findById(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(AccountRole ar);

}
