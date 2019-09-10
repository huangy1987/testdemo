package com.solution.entity;

import java.util.Date;

/**
 * 地产项目立项单
 * @author yue
 *
 */
public class Dcxmlxb{
	private int id;							//编号
	private String loginName;				//员工工号
	private String formNumber;				//工单编号
	private String projectName;				//项目名称
	private String projectPorperty;			//项目属性
	private Double servicePrice;		    //基础服务费单价
	private int staffNumber;				//坐席数
	private int serviceMonths;				//服务月数
	private Double singlePrice;				//单套结算单价(元/套)
	private Double royaltyRate;				//单套房款佣金提成比例(%) 
	private Date callTime;					//起呼时间 
	private String intentionCustomer;	    //意向客户数
	private String remarks;  				//数据筛选方向
	private String sendEmail;				//意向客户发送地址
	private String contact;					//联系人及电话
	private String consultant;				//短信下发号码及联系人-职业顾问
	//追加
	private Date createTime;				//创建时间		
	private Date endTime;					//结束时间
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getFormNumber(){
		return formNumber;
	}
	public void setFormNumber(String formNumber){
		this.formNumber = formNumber;
	}
	public String getLoginName(){
		return loginName;
	}
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	public String getProjectName(){
		return projectName;
	}
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	public String getProjectPorperty(){
		return projectPorperty;
	}
	public void setProjectPorperty(String projectPorperty){
		this.projectPorperty = projectPorperty;
	}
	public Double getServicePrice(){
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice){
		this.servicePrice = servicePrice;
	}
	public int getStaffNumber(){
		return staffNumber;
	}
	public void setStaffNumber(int staffNumber){
		this.staffNumber = staffNumber;
	}
	public int getServiceMonths(){
		return serviceMonths;
	}
	public void setServiceMonths(int serviceMonths){
		this.serviceMonths = serviceMonths;
	}
	public Double getSinglePrice(){
		return singlePrice;
	}
	public void setSinglePrice(Double singlePrice){
		this.singlePrice = singlePrice;
	}
	public Double getRoyaltyRate(){
		return royaltyRate;
	}
	public void setRoyaltyRate(Double royaltyRate){
		this.royaltyRate = royaltyRate;
	}
	public Date getCallTime(){
		return callTime;
	}
	public void setCallTime(Date callTime){
		this.callTime = callTime;
	}
	public String getIntentionCustomer(){
		return intentionCustomer;
	}
	public void setIntentionCustomer(String intentionCustomer){
		this.intentionCustomer = intentionCustomer;
	}
	public String getRemarks(){
		return remarks;
	}
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	public String getSendEmail(){
		return sendEmail;
	}
	public void setSendEmail(String sendEmail){
		this.sendEmail = sendEmail;
	}
	public String getContact(){
		return contact;
	}
	public void setContact(String contact){
		this.contact = contact;
	}
	public String getConsultant(){
		return consultant;
	}
	public void setConsultant(String consultant){
		this.consultant = consultant;
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
