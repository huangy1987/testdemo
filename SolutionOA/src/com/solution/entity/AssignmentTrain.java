package com.solution.entity;

import java.util.Date;

/**
 * 外派培训单
 * @author yue
 *
 */
public class AssignmentTrain{
	private int id;								//编号
	private String loginName;					//员工工号
	private String projectName;					//项目名称
	private String projectPrincipal;			//项目负责人
	private String trainAddress;				//培训地点
	private String trainTime;					//培训时间
	private int sendNumbers;					//外派人数
	private String staffList;					//人员名单
	private Date createTime;					//创建时间
	private Date endTime;						//结束时间
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
	public String getProjectPrincipal(){
		return projectPrincipal;
	}
	public void setProjectPrincipal(String projectPrincipal){
		this.projectPrincipal = projectPrincipal;
	}
	public String getTrainAddress(){
		return trainAddress;
	}
	public void setTrainAddress(String trainAddress){
		this.trainAddress = trainAddress;
	}
	public String getTrainTime(){
		return trainTime;
	}
	public void setTrainTime(String trainTime){
		this.trainTime = trainTime;
	}
	public int getSendNumbers(){
		return sendNumbers;
	}
	public void setSendNumbers(int sendNumbers){
		this.sendNumbers = sendNumbers;
	}
	public String getStaffList(){
		return staffList;
	}
	public void setStaffList(String staffList){
		this.staffList = staffList;
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
