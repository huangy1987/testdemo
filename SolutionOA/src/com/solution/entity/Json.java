package com.solution.entity;

import java.io.Serializable;

public class Json implements Serializable {

	private static final long serialVersionUID = 5618148883080506926L;
	private String msg;
	private Account account;
	private boolean loginState;
	
	
	public Json(String msg, Account account, boolean loginState){
		super();
		this.msg = msg;
		this.account = account;
		this.loginState = loginState;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isLoginState() {
		return loginState;
	}
	public void setLoginState(boolean loginState) {
		this.loginState = loginState;
	}
	
	
	public Account getAccount(){
		return account;
	}
	public void setAccount(Account account){
		this.account = account;
	}
	public Json() {
		super();
	}
	
}
