package com.solution.entity;

import java.util.Date;
/**
 * 职位POJO
 * @author yue
 *
 */
public class Position{
	private int id;
	private int departmentID;
	private String positionName;
	private Date createTime;
	private Date endTime;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getDepartmentID(){
		return departmentID;
	}
	public void setDepartmentID(int departmentID){
		this.departmentID = departmentID;
	}
	public String getPositionName(){
		return positionName;
	}
	public void setPositionName(String positionName){
		this.positionName = positionName;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getEndTime(){
		return endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
}	
