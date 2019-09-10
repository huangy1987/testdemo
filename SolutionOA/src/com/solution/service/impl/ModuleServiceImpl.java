package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.AccountRoleDao;
import com.solution.dao.ModuleDao;
import com.solution.entity.AccountRole;
import com.solution.entity.Module;
import com.solution.entity.Page;
import com.solution.service.AccountRoleService;
import com.solution.service.ModuleService;

public class ModuleServiceImpl implements ModuleService {
	private ModuleDao moduleDao;
	
	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	public List<Module> findAllModule() {
		return moduleDao.findAllModule();
	}

	@Override
	public int insert(Module ar) {
		return moduleDao.insert(ar);
	}

	@Override
	public Module findById(int id) {
		return moduleDao.findById(id);
	}

	@Override
	public int delete(int id) {
		return moduleDao.delete(id);
	}

	@Override
	public int update(Module ar) {
		return moduleDao.update(ar);
	}

	@Override
	public Module findByIndex(int id) {
		return moduleDao.findByIndex(id);
	}
	public Page<?> know_querypage(Map<Object, Object> c){
		int currentPage = (Integer)c.get("currentPage");
		int pageSize = (Integer)c.get("pageSize");
		String action = (String)c.get("action");
		List<Module> dataList =  moduleDao.findAllModule();
		List<Module> data = new ArrayList<Module>();
		int totalRecordCount = dataList.size();
		int first = (currentPage-1) * pageSize + 1;
		int last = currentPage * pageSize;
		int pageNum = 0;
		System.out.println(4);
		if(totalRecordCount % pageSize == 0){//整数页
			if(pageNum == 1){//第一页
				first = 0;
			}
			pageNum = totalRecordCount / pageSize;
		}else{//不全页
			pageNum = totalRecordCount / pageSize + 1;
			if(last >= totalRecordCount && (pageNum == 1)){//只有一页
				first = 0;
				last = totalRecordCount;System.out.println(1);
			}
			if((last < totalRecordCount) && (pageNum > 1) && (first == 1)){//不只一页,查询第一页
				first = 0;
				last= pageSize - 1;System.out.println(2);
			}
			if(last > totalRecordCount && (pageNum > 1) && (first > 1)){//不只一页,查询最后一页
				first = first - 1;
				last = totalRecordCount;System.out.println(3);
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
		Page<Module> p = new Page<Module>();
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
