package com.solution.entity;

import java.util.Date;

/**
 * 非地产临时项目立项表
 * @author yue
 *
 */
public class Fdclsxmlxb{
	private int id;					//编号
	private String formNumber;		//工单编号
	private String loginName;		//员工工号
	private String projectName;		//项目名称
	private Double servicePrice;	//服务费单价
	private int staffNumber;   		//坐席服务数
	private int serviceMonths;		//服务月数
	private Double serviceRental;   //服务费总额
	private Double singlePrice;		//单位结算单价
	private Double royaltyRate;		//单位佣金提成比例
	private String executiveTime;	//项目执行时间
	private Date createTime;		//创建时间
	private Date endTime;			//结束时间
	
	
	public String getFormNumber(){
		return formNumber;
	}
	public void setFormNumber(String formNumber){
		this.formNumber = formNumber;
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
	public String getProjectName(){
		return projectName;
	}
	public void setProjectName(String projectName){
		this.projectName = projectName;
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
	public Double getServiceRental(){
		return serviceRental;
	}
	public void setServiceRental(Double serviceRental){
		this.serviceRental = serviceRental;
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
	public String getExecutiveTime(){
		return executiveTime;
	}
	public void setExecutiveTime(String executiveTime){
		this.executiveTime = executiveTime;
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
