package com.solution.dao;

import java.util.List;

import com.solution.entity.Position;

public interface PositionDao {
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
}
