package com.solution.entity;

import java.util.Date;

/**
 * 审核记录
 * @author yue
 */
public class ApprovalProcessRecord{
	private int id;						//审核记录编号
	private int formTypeID;				//工单类型编号
	private int formID;					//工单编号
	private int flowNumber;				//流程编号
	private String approverPosition;	//审核人职位
	private String approverAccount;		//审核人账户
	private String approverRealName;	//审核人姓名
	private int approverStatus;		    //审核状态(0 等待 1 正在审核 2 通过 3 拒绝)
	private Date approverTime;			//审核时间
	private String remark;				//备注
	
	//追加
	private String formName;			//工单名称
	
	//审核追加
	private String proposerDepartment;  //申请人部门
	private String proposerPosition;    //申请人职位
	private String proposerAccount;     //申请人账户
	private String proposerName;        //申请人姓名
	private Date proposerTime;			//申请时间
	private String 	formBornID;			//工单生成编号
	private String approverDepName;		//审核人所在部门
//	private int currentFlowNumber;   //当前流程号
	
	
//	public int getCurrentFlowNumber(){
//		return currentFlowNumber;
//	}
//	public void setCurrentFlowNumber(int currentFlowNumber){
//		this.currentFlowNumber = currentFlowNumber;
//	}
	public String getFormBornID(){
		return formBornID;
	}
	public String getApproverDepName(){
		return approverDepName;
	}
	public void setApproverDepName(String approverDepName){
		this.approverDepName = approverDepName;
	}
	public void setFormBornID(String formBornID){
		this.formBornID = formBornID;
	}
	public Date getProposerTime(){
		return proposerTime;
	}
	public void setProposerTime(Date proposerTime){
		this.proposerTime = proposerTime;
	}
	public String getProposerDepartment(){
		return proposerDepartment;
	}
	public void setProposerDepartment(String proposerDepartment){
		this.proposerDepartment = proposerDepartment;
	}
	public String getProposerPosition(){
		return proposerPosition;
	}
	public void setProposerPosition(String proposerPosition){
		this.proposerPosition = proposerPosition;
	}
	public String getProposerAccount(){
		return proposerAccount;
	}
	public void setProposerAccount(String proposerAccount){
		this.proposerAccount = proposerAccount;
	}
	public String getProposerName(){
		return proposerName;
	}
	public void setProposerName(String proposerName){
		this.proposerName = proposerName;
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
	public int getFormTypeID(){
		return formTypeID;
	}
	public void setFormTypeID(int formTypeID){
		this.formTypeID = formTypeID;
	}
	public int getFormID(){
		return formID;
	}
	public void setFormID(int formID){
		this.formID = formID;
	}
	public int getFlowNumber(){
		return flowNumber;
	}
	public void setFlowNumber(int flowNumber){
		this.flowNumber = flowNumber;
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
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	
}
