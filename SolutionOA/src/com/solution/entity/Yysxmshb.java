package com.solution.entity;

import java.util.Date;

/**
 * 运营商项目试呼表
 * @author yue
 *
 */
public class Yysxmshb{
	private int id;
	private String projectName;
	private Date callTime;
	private String callData;
	private int callNumbers;
	private Double perOutput;
	private Double connectSinglePrice;
	private Double appendSinglePrice;
	private Double generalIncome;
	private String remark;
	private String loginName;
	private Date createTime;
	private Date endTime;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getProjectName(){
		return projectName;
	}
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	public Date getCallTime(){
		return callTime;
	}
	public void setCallTime(Date callTime){
		this.callTime = callTime;
	}
	public String getCallData(){
		return callData;
	}
	public void setCallData(String callData){
		this.callData = callData;
	}
	public int getCallNumbers(){
		return callNumbers;
	}
	public void setCallNumbers(int callNumbers){
		this.callNumbers = callNumbers;
	}
	public Double getPerOutput(){
		return perOutput;
	}
	public void setPerOutput(Double perOutput){
		this.perOutput = perOutput;
	}
	public Double getConnectSinglePrice(){
		return connectSinglePrice;
	}
	public void setConnectSinglePrice(Double connectSinglePrice){
		this.connectSinglePrice = connectSinglePrice;
	}
	public Double getAppendSinglePrice(){
		return appendSinglePrice;
	}
	public void setAppendSinglePrice(Double appendSinglePrice){
		this.appendSinglePrice = appendSinglePrice;
	}
	public Double getGeneralIncome(){
		return generalIncome;
	}
	public void setGeneralIncome(Double generalIncome){
		this.generalIncome = generalIncome;
	}
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getLoginName(){
		return loginName;
	}
	public void setLoginName(String loginName){
		this.loginName = loginName;
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
