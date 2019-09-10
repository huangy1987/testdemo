package com.solution.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.solution.entity.Account;
import com.solution.tools.VOUtils;
/**
 * action基类
 * @author huangyue
 *
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 7816229122081336725L;
//	public String loginName;
//	
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//	//验证用户登录
//	
//	//
//	public String loginSuccess(){
//		System.out.println(loginName);
//		return SUCCESS;
//	}
//	
	public String lookRight(){
		System.out.println("BaseAction");
		Account user = (Account)ActionContext.getContext().getSession().get("user");
		
		if(user == null){
			return "base";
		}
		System.out.println("BaseAction" + user.getLoginName());
		//return "error";
		return null;
	}
}
