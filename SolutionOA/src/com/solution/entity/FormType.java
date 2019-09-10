package com.solution.entity;

import java.util.Date;

public class FormType{
	private int id;					//表单类型编号
	private int departmentID;		//部门编号
	private String formName;		//工单名称
	private Date createTime;		//创建时间
	private Date endTime;			//结束时间
	private String remark;			//备注
	private String departmentName;  //部门名称
	
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public int getDepartmentID(){
		return departmentID;
	}
	public void setDepartmentID(int departmentID){
		this.departmentID = departmentID;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getFormName(){
		return formName;
	}
	public void setFormName(String formName){
		this.formName = formName;
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
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	
}
