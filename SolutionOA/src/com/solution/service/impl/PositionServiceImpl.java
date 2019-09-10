package com.solution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.solution.dao.PositionDao;
import com.solution.entity.Department;
import com.solution.entity.Page;
import com.solution.entity.Position;
import com.solution.service.PositionService;

public class PositionServiceImpl implements PositionService{
	private PositionDao positionDao;
	
	public PositionDao getPositionDao(){
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao){
		this.positionDao = positionDao;
	}

	@Override
	public int delete(int id){
		return positionDao.delete(id);
	}

	@Override
	public List<Position> findAllPosition(){
		return positionDao.findAllPosition();
	}

	@Override
	public Position findByPosId(int id){
		return positionDao.findByPosId(id);
	}

	@Override
	public int insert(Position position){
		return positionDao.insert(position);
	}

	@Override
	public int update(Position position){
		return positionDao.update(position);
	}

	@Override
	public Page<?> know_querypage(Map<Object, Object> c){
		int currentPage = (Integer)c.get("currentPage");
		int pageSize = (Integer)c.get("pageSize");
		String action = (String)c.get("action");
		List<Position> dataList =  positionDao.findAllPosition();
		List<Position> data = new ArrayList<Position>();
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
				System.out.println(3);
			}
			if(last > totalRecordCount && (pageNum > 1) && (first > 1)){
				first = first - 1;
				last = totalRecordCount;
				System.out.println(4);
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
		Page<Position> p = new Page<Position>();
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
