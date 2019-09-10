package com.solution.dao;

import java.util.List;

import com.solution.entity.Department;

public interface DepartmentDao {
	//查全部
	public List<Department> findAllDepartment();
	//新增
	public int insert(Department department);
	//按条件查询
	public Department findByDepId(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Department department);
	//分页测试
	public List<Department> findAllPagination(int pageSize,int currentPage,int sumSize);
}
