package com.solution.entity;

import java.util.Date;
/**
 * 角色POJO
 * @author yue
 *
 */
public class AccountRole{
	private int id;				//角色编号
	private String roleName;	//角色名称
	private String moduleList;  //模型块ID
	private String remark;		//备注
	private Date createTime;	//创建时间
	private Date endTime;		//结束时间
	private int departmentID;   //部门编号
	private int roleType;		//类型 （0 在线平台 1 OA）
	private String departmentName; //部门名称
	
	
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getModuleList() {
		return moduleList;
	}
	public void setModuleList(String moduleList) {
		this.moduleList = moduleList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	
	
	
}
