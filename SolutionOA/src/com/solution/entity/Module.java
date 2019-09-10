package com.solution.entity;

import java.util.Date;
/**
 * 模型POJO
 * @author yue
 *
 */
public class Module{
	private int id;				//模型编号
	private int moduleIndex;	//模型索引
	private String moduleIcon;  //模型图标
	private String moduleName;  //模型名称
	private String ModulePath;  //模型请求路径
	private int moduleParentID; //模型所属编号
	private String remark;      //备注
	private Date createTime;    //创建时间
	private Date endTime;		//结束时间
	private int moduleType;		//模型类型( 0 在线平台 1 OA)
	//追加
	private String moduleParentName;
	
	public String getModuleParentName(){
		return moduleParentName;
	}
	public void setModuleParentName(String moduleParentName){
		this.moduleParentName = moduleParentName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getModuleIndex() {
		return moduleIndex;
	}
	public void setModuleIndex(int moduleIndex) {
		this.moduleIndex = moduleIndex;
	}
	public String getModuleIcon() {
		return moduleIcon;
	}
	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModulePath() {
		return ModulePath;
	}
	public void setModulePath(String modulePath) {
		ModulePath = modulePath;
	}
	public int getModuleParentID() {
		return moduleParentID;
	}
	public void setModuleParentID(int moduleParentID) {
		this.moduleParentID = moduleParentID;
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
	public int getModuleType() {
		return moduleType;
	}
	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}
	
	
	
	
}
