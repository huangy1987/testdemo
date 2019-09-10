package com.solution.service;

import java.util.List;
import java.util.Map;

import com.solution.entity.Page;
import com.solution.entity.Position;

public interface PositionService {
	//查全部
	public List<Position> findAllPosition();
	//新增
	public int insert(Position position);
	//按条件查询
	public Position findByPosId(int id);
	//删除
	public int delete(int id);
	//修改
	public int update(Position position);
	//分页查询
	public Page<?> know_querypage(Map<Object, Object> c);
}
