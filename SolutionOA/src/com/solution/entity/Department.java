package com.solution.entity;

import java.util.Date;
/**
 * 部门pojo
 * @author yue
 *
 */
public class Department{
	private int id;							   //部门编号	
	private String departmentName;			   //部门名称
	private String departmentOwner; 		   //部门主要负责人工号
	private String departmentOwnerName;	       //部门主要负责人姓名
	private Date createTime;				   //创建时间
	private Date endTime;					   //终止时间
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public String getDepartmentOwner(){
		return departmentOwner;
	}
	public void setDepartmentOwner(String departmentOwner){
		this.departmentOwner = departmentOwner;
	}
	public String getDepartmentOwnerName(){
		return departmentOwnerName;
	}
	public void setDepartmentOwnerName(String departmentOwnerName){
		this.departmentOwnerName = departmentOwnerName;
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
