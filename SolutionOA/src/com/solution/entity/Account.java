package com.solution.entity;

import java.util.Date;

/**
 * 账户POJO
 * 
 * @author yue
 */

public class Account{
	private int		id;				// 账户编号
	private String	loginName;		// 工号
	private String	realName;		// 真实姓名
	private String	passWord;		// 密码
	private int		accountRoleID;	// 角色id
	private int		accountGroupID; // 分组id
	private String	remark;			// 备注
	private Date	createTime;		// 创建时间
	private Date	endTime;		// 结束时间
	private int		departmentID;	// 部门id
	private int		positionID;		// 职位id
	private int		personalInfoID; // 基本信息id
	//追加显示
	private String departmentName;  //部门名称
	private String positionName;	//职位名称
	private String roleName;		//角色名称
	private int accountType;		//账户类型( 0 在线中心 1 OA)
	private int usedState;			//账户启用和停用状态 1 启用 0 停用
	
	
	public int getUsedState(){
		return usedState;
	}
	public void setUsedState(int usedState){
		this.usedState = usedState;
	}
	public int getAccountType(){
		return accountType;
	}
	public void setAccountType(int accountType){
		this.accountType = accountType;
	}
	public String getPositionName(){
		return positionName;
	}
	public void setPositionName(String positionName){
		this.positionName = positionName;
	}
	public String getRoleName(){
		return roleName;
	}
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getLoginName(){
		return loginName;
	}
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	public String getRealName(){
		return realName;
	}
	public void setRealName(String realName){
		this.realName = realName;
	}
	public String getPassWord(){
		return passWord;
	}
	public void setPassWord(String passWord){
		this.passWord = passWord;
	}
	public int getAccountRoleID(){
		return accountRoleID;
	}
	public void setAccountRoleID(int accountRoleID){
		this.accountRoleID = accountRoleID;
	}
	public int getAccountGroupID(){
		return accountGroupID;
	}
	public void setAccountGroupID(int accountGroupID){
		this.accountGroupID = accountGroupID;
	}
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
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
	public int getDepartmentID(){
		return departmentID;
	}
	public void setDepartmentID(int departmentID){
		this.departmentID = departmentID;
	}
	public int getPositionID(){
		return positionID;
	}
	public void setPositionID(int positionID){
		this.positionID = positionID;
	}
	public int getPersonalInfoID(){
		return personalInfoID;
	}
	public void setPersonalInfoID(int personalInfoID){
		this.personalInfoID = personalInfoID;
	}
	
}
