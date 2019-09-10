package com.solution.entity;

import java.util.Date;
/**
 * 审核流程POJO
 * @author yue
 *
 */
public class ApprovalProcess{
	private int id;						//审核流程编号
	private int formTypeID;				//工单类型编号
	private int flowNumber;				//流程编号
	private String approverPosition;	//审核人职位
	private String approverAccount;		//审核人账户
	private String approverRealName;	//审核人姓名
	private String constraints;			//流转约束
	private Date createTime;			//创建时间
	private Date endTime;				//有效时间
	//追加
	private String formName;		    //工单名称
	private String departmentName;		//工单所属部门
	private String approverDepName;		//审核人所在部门	
	
	//
	private int approverStatus;
	private Date approverTime;
	
	
	public int getApproverStatus(){
		return approverStatus;
	}
	public void setApproverStatus(int approverStatus){
		this.approverStatus = approverStatus;
	}
	public Date getApproverTime(){
		return approverTime;
	}
	public void setApproverTime(Date approverTime){
		this.approverTime = approverTime;
	}
	public String getApproverDepName(){
		return approverDepName;
	}
	public void setApproverDepName(String approverDepName){
		this.approverDepName = approverDepName;
	}
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public String getFormName(){
		return formName;
	}
	public void setFormName(String formName){
		this.formName = formName;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public int getFlowNumber() {
		return flowNumber;
	}
	public void setFlowNumber(int flowNumber) {
		this.flowNumber = flowNumber;
	}
	public int getFormTypeID(){
		return formTypeID;
	}
	public void setFormTypeID(int formTypeID){
		this.formTypeID = formTypeID;
	}
	public String getApproverPosition(){
		return approverPosition;
	}
	public void setApproverPosition(String approverPosition){
		this.approverPosition = approverPosition;
	}
	public String getApproverAccount(){
		return approverAccount;
	}
	public void setApproverAccount(String approverAccount){
		this.approverAccount = approverAccount;
	}
	public String getApproverRealName(){
		return approverRealName;
	}
	public void setApproverRealName(String approverRealName){
		this.approverRealName = approverRealName;
	}
	public String getConstraints(){
		return constraints;
	}
	public void setConstraints(String constraints){
		this.constraints = constraints;
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
