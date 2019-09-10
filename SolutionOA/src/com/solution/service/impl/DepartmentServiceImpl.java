package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.DepartmentDao;
import com.solution.entity.Account;
import com.solution.entity.Department;
import com.solution.entity.Page;
import com.solution.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;
	
	public DepartmentDao getDepartmentDao(){
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao){
		this.departmentDao = departmentDao;
	}

	@Override
	public int delete(int id){
		return departmentDao.delete(id);
	}

	@Override
	public List<Department> findAllDepartment(){
		return departmentDao.findAllDepartment();
	}

	@Override
	public Department findByDepId(int id){
		return departmentDao.findByDepId(id);
	}


	@Override
	public int insert(Department department){
		return departmentDao.insert(department);
	}

	@Override
	public int update(Department department){
		return departmentDao.update(department);
	}

	@Override
	public Page<?> know_querypage(Map<Object, Object> c){
		int currentPage = (Integer)c.get("currentPage");
		int pageSize = (Integer)c.get("pageSize");
		String action = (String)c.get("action");
		List<Department> dataList =  departmentDao.findAllDepartment();
		List<Department> data = new ArrayList<Department>();
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
			if(last >= totalRecordCount && (pageNum == 1)){
				first = 0;
				last = totalRecordCount;
			}
			if((last < totalRecordCount) && (pageNum > 1) && (first == 1)){
				first = 0;
				last= pageSize - 1;
			}
			if(last > totalRecordCount && (pageNum > 1) && (first > 1)){
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
			}
		}
		// 封装Page
		Page<Department> p = new Page<Department>();
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
