package com.solution.action;

import com.opensymphony.xwork2.ActionContext;
import com.solution.entity.Account;

public class LogoutAction extends BaseAction {

	/**
	 * 退出action
	 */
	private static final long serialVersionUID = 412784896629658595L;
	
	public String logout(){
		Account acc = new Account();
		acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
//		System.out.println(acc);
		System.out.println("用户<" + acc.getRealName() + ">退出系统");
		//清空session
		ActionContext.getContext().getSession().remove("user");
		ActionContext.getContext().getSession().remove("accountInfo");
		System.out.println("LogoutAction");
		if(ActionContext.getContext().getSession() == null){
			System.out.println("getSession() == null");
			return SUCCESS;
		}
		
		if(ActionContext.getContext().getSession().get("user") == null){
			System.out.println("getSession().get('user') == null");
			return SUCCESS;
		}
		return SUCCESS;
	}

}
