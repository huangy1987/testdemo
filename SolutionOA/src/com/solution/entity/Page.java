package com.solution.entity;

import java.util.ArrayList;
import java.util.List;

public class Page<T>{
	
	private int currentPage = 1;    //当前第几页
	private int pageSize;			//每页记录数
	private int totalRecordCount;	//总记录数
	private int totalPageCount;		//总页数
	private String action;			//跳转路径
	private String pageBar;			//分页条
	private List<T> dataList = new ArrayList<T>();	//数据List
	
	public Page(){
		super();
	}
	
	public int getCurrentPage(){
		return currentPage;
	}
	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getPageSize(){
		return pageSize;
	}
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	public int getTotalRecordCount(){
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount){
		this.totalRecordCount = totalRecordCount;
	}
	
	public List<T> getDataList(){
		return dataList;
	}
	public void setDataList(List<T> dataList){
		this.dataList = dataList;
	}
	
	public String getAction(){
		return action;
	}
	public void setAction(String action){
		this.action = action;
	}
	
	public int getTotalPageCount(){
		return totalPageCount;
	}
	public void setTotalPageCount(int totalRecordCount, int pageCount){
		this.totalPageCount = totalPageCount(totalRecordCount, pageCount);
	}
	
	public String getPageBar(){
		return pageBar;
	}
	public void setPageBar(int page, int pageCount, int totalRecordCount, String action){
		this.pageBar = pageBar(page, pageCount, totalRecordCount, action);
	}
	
	//总页数
	public int totalPageCount(int totalRecordCount, int pageCount){
		int totalPageCount = 0;
		if(totalRecordCount % pageCount == 0){
			totalPageCount = totalRecordCount / pageCount;
		}else{
			totalPageCount = totalRecordCount / pageCount + 1;
		}
		return (totalPageCount == 0) ? 1 : totalPageCount;
	}
	
	//分页条
	public String pageBar(int page, int pageCount, int totalRecordCount, String action){
		//总页数
		int totalPageCount = totalPageCount(totalRecordCount, pageCount);
		//拼接HTML
		StringBuffer p = new StringBuffer();
		p.append("<span class='pageNum' style='width: 45px' title='" + totalRecordCount + "'>" + page + "/" + totalPageCount + "</span>");
		if(page == 1){
			p.append("<span class='chWord'>上一页</span>&nbsp;");
		}else{
			p.append("<span><a href='" + action + (page - 1) + "' class='chWordA'>上一页</a></span>&nbsp;");
		}
		//总页数小于等于5
		if(totalPageCount <= 9){
			for(int i = 1; i < (totalPageCount + 1); i++){
				if(page == i){
					p.append("<span class='curNum'>"+ i +"</span>&nbsp;");
				}else{
					p.append("<span><a href='" + action + i + "' class='num'>"+ i +"</a></span>&nbsp;");
				}
			}
		}else{	  //总页数大于5
			if(page <= 5){	   //当前页小于等于5
				for(int i = 1; i <= 5; i++){
					if(page == i){
						p.append("<span class='curNum'>"+ i +"</span>&nbsp;");
					}else{
						p.append("<span><a href='" + action + i + "' class='num'>"+ i +"</a></span>&nbsp;");
					}
				}
				p.append("<span class='moreNum'>…</span>&nbsp;<span class='moreNum'>…</span>&nbsp;<span class='moreNum'>…</span>&nbsp;<span><a href='" + (action + totalPageCount) + "' class='num'>" + totalPageCount + "</a></span>&nbsp;");
			}
			else if(page >= totalPageCount - 4){	//当前页大于等于倒数第5
				p.append("<span><a href='" + (action + 1) + "' class='num'>1</a></span>&nbsp;<span class='moreNum'>…</span>&nbsp;<span class='moreNum'>…</span>&nbsp;<span class='moreNum'>…</span>&nbsp;");
				for(int i = totalPageCount - 4; i <= totalPageCount; i++){
					if(page == i){
						p.append("<span class='curNum'>"+ i +"</span>&nbsp;");
					}else{
						p.append("<span><a href='" + action + i + "' class='num'>"+ i +"</a></span>&nbsp;");
					}
				}
			}
			//其他情况
			else{
				p.append("<span><a href='" + (action + 1) + "' class='num'>1</a></span>&nbsp;<span class='moreNum'>…</span>&nbsp;<span><a href='" + action + (page - 2) + "' class='num'>"+ (page - 2) +"</a></span>&nbsp;<span><a href='" + action + (page - 1) + "' class='num'>"+ (page - 1) +"</a></span>&nbsp;" + "<span class='curNum'>"+ page +"</span>" + "&nbsp;<span><a href='" + action + (page + 1) + "' class='num'>"+ (page + 1) +"</a></span>&nbsp;<span><a href='" + action + (page + 2) + "' class='num'>"+ (page + 2) +"</a></span>&nbsp;<span class='moreNum'>…</span>&nbsp;<span><a href='" + (action + totalPageCount) + "' class='num'>" + totalPageCount + "</a></span>&nbsp;");
			}
		}
		if(page == totalPageCount){
			p.append("<span class='chWord'>下一页</span>");
		}else{
			p.append("<span><a href='" + action + (page + 1) + "' class='chWordA'>下一页</a></span>");
		}
		return p.toString();
	}
	
}