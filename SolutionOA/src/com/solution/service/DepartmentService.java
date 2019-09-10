package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.Department;
import com.solution.entity.Page;

public interface DepartmentService {
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
	//部门分页
	public Page<?> know_querypage(Map<Object, Object> c);
}
