package com.solution.entity;

import java.util.Date;

/**
 * 运营商项目立项表
 * @author yue
 *
 */
public class Yysxmlxb{
	private int id; 				//编号
	private String loginName;		//员工工号
	private String projectName;		//项目名称
	private Double callResult;		//全业务人均产值（试呼结果）
	private Double singlePrice;		//执行单价(元/个)
	private Double appendPrice;		//成功追加单价(元/个)
	private int callNumbers;		//目标成功外呼量
	private Double generalIncome;	//预估总收入
	private Date createTime;		//创建时间
	private Date endTime;			//结束时间
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
	public Double getCallResult(){
		return callResult;
	}
	public void setCallResult(Double callResult){
		this.callResult = callResult;
	}
	public Double getSinglePrice(){
		return singlePrice;
	}
	public void setSinglePrice(Double singlePrice){
		this.singlePrice = singlePrice;
	}
	public Double getAppendPrice(){
		return appendPrice;
	}
	public void setAppendPrice(Double appendPrice){
		this.appendPrice = appendPrice;
	}
	public int getCallNumbers(){
		return callNumbers;
	}
	public void setCallNumbers(int callNumbers){
		this.callNumbers = callNumbers;
	}
	public Double getGeneralIncome(){
		return generalIncome;
	}
	public void setGeneralIncome(Double generalIncome){
		this.generalIncome = generalIncome;
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
