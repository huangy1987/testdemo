package com.solution.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.solution.entity.Account;
import com.solution.entity.AccountRole;
import com.solution.entity.Department;
import com.solution.entity.Json;
import com.solution.entity.Module;
import com.solution.entity.Position;
import com.solution.service.AccountRoleService;
import com.solution.service.AccountService;
import com.solution.service.DepartmentService;
import com.solution.service.ModuleService;
import com.solution.service.PositionService;
import com.solution.tools.VOUtils;

/**
 * 账户action
 * 
 * @author yue
 */
public class AccountAction extends BaseAction{
	private static final long	serialVersionUID	= -3321699007939235234L;
	private AccountService		accountService;
	private AccountRoleService accountRoleService;
	private DepartmentService departmentService;
	private PositionService positionService;
	
	public DepartmentService getDepartmentService(){
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService){
		this.departmentService = departmentService;
	}

	public PositionService getPositionService(){
		return positionService;
	}

	public void setPositionService(PositionService positionService){
		this.positionService = positionService;
	}

	public AccountRoleService getAccountRoleService() {
		return accountRoleService;
	}

	public void setAccountRoleService(AccountRoleService accountRoleService) {
		this.accountRoleService = accountRoleService;
	}

	private Account				account;
	private String				loginName;
	private String				passWord;

//	public String login() throws Exception{
	public String login() {
		Json j = new Json();
		List<Account> list = new ArrayList<Account>();
		try{
		list = accountService.findAllAccount();
		}catch(Exception ee){
			ee.printStackTrace();
		}
		
		if(loginName != null && loginName.trim() != ""){
			System.out.println(loginName);
			System.out.println(passWord);
			for (int i = 0; i < list.size(); i++) {
				//判断用户名和密码
				if(loginName.equals(list.get(i).getLoginName()) && passWord.equals(list.get(i).getPassWord())){
					System.out.println("用户名和密码正确！");
					//判断账户是否启用
					Date endtime = list.get(i).getEndTime();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = "9999-12-31 00:00:00";//默认
					if(!sdf.format(endtime).equals(time)){
						System.out.println("账户 " + loginName + "未启用！" );
						break;
					}
					ActionContext.getContext().getSession().put("accountInfo",list.get(i));
					j.setLoginState(true);
					j.setMsg("恭喜登录成功！");
					Account u = new Account();//loginName,passWord
					u.setLoginName(loginName);
					u.setPassWord(passWord);
					j.setAccount(u);
					//处理登录信息
					//更新上次登录时间
					//设置登录用户session
					ActionContext.getContext().getSession().put("user",u);
					ActionContext.getContext().getSession().put("userList", list);
					//获取登陆用户角色id
					int roleID = list.get(i).getAccountRoleID();
					//获取部门id
					//int departmentID = list.get(i).getDepartmentID();
					//获取职位id
					//int positionID = list.get(i).getPositionID();
					
					//获取角色
					AccountRole role = accountRoleService.findById(roleID);
					ActionContext.getContext().getSession().put("role",role);
					VOUtils.writeJson(j);
					System.out.println("登陆成功");
					return null;
				}
			}
			
		}
		//登录失败
		j.setLoginState(false);
		j.setMsg("登录失败");
		j.setAccount(account);
		VOUtils.writeJson(j);
		System.out.println("失败");
		return null;
	}
	

	public Account getAccount(){
		return account;
	}

	public void setAccount(Account account){
		this.account = account;
	}

	

	public String getLoginName(){
		return loginName;
	}

	public void setLoginName(String loginName){
		this.loginName = loginName;
	}


	public String getPassWord(){
		return passWord;
	}

	public void setPassWord(String passWord){
		this.passWord = passWord;
	}

	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}

	public AccountService getAccountService(){
		return accountService;
	}
	
}
