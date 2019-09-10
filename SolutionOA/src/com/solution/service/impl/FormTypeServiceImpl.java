package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.FormTypeDao;
import com.solution.entity.AccountRole;
import com.solution.entity.FormType;
import com.solution.entity.Page;
import com.solution.service.FormTypeService;

public class FormTypeServiceImpl implements FormTypeService{
	private FormTypeDao formTypeDao;
	
	public FormTypeDao getFormTypeDao(){
		return formTypeDao;
	}

	public void setFormTypeDao(FormTypeDao formTypeDao){
		this.formTypeDao = formTypeDao;
	}

	@Override
	public int delete(int id){
		return formTypeDao.delete(id);
	}

	@Override
	public List<FormType> findAllFormType(){
		return formTypeDao.findAllFormType();
	}

	@Override
	public FormType findById(int id){
		return formTypeDao.findById(id);
	}

	@Override
	public int insert(FormType ft){
		return formTypeDao.insert(ft);
	}

	@Override
	public int update(FormType ft){
		return formTypeDao.update(ft);
	}

	@Override
	public Page<?> know_querypage(Map<Object, Object> c){
		int currentPage = (Integer)c.get("currentPage");
		int pageSize = (Integer)c.get("pageSize");
		String action = (String)c.get("action");
		List<FormType> dataList =  formTypeDao.findAllFormType();
		List<FormType> data = new ArrayList<FormType>();
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
		Page<FormType> p = new Page<FormType>();
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
