package com.solution.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.solution.entity.Account;
import com.solution.entity.AccountRole;
import com.solution.entity.Department;
import com.solution.entity.Page;
import com.solution.entity.Position;
import com.solution.service.AccountService;
import com.solution.service.DepartmentService;
import com.solution.service.PositionService;
import com.solution.tools.VOUtils;

/**
 * 基本信息模块action
 * @author yue
 *
 */
public class BaseInfoAction extends BaseAction{

	private static final long	serialVersionUID	= 5911759320013099785L;
	Logger					log						= Logger.getLogger(BaseInfoAction.class);
	private DepartmentService departmentService;
	private PositionService positionService;
	private AccountService accountService;
	private Department department;
	private String currentPage;
	private List<Department> departmentList;
	private List<Position> positionList;
	private Position position;
	private String pageBar;
	private String loginName;
	private int id;
	private int type;
	
	public Position getPosition(){
		return position;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<Position> getPositionList(){
		return positionList;
	}

	public void setPositionList(List<Position> positionList){
		this.positionList = positionList;
	}

	public PositionService getPositionService(){
		return positionService;
	}

	public void setPositionService(PositionService positionService){
		this.positionService = positionService;
	}

	public String getPageBar(){
		return pageBar;
	}

	public void setPageBar(String pageBar){
		this.pageBar = pageBar;
	}

	public List<Department> getDepartmentList(){
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList){
		this.departmentList = departmentList;
	}

	public String getCurrentPage(){
		return currentPage;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage = currentPage;
	}

	public DepartmentService getDepartmentService(){
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService){
		this.departmentService = departmentService;
	}

	//查找全部部门
	@SuppressWarnings("unchecked")
	public String findAlldepartment(){
		try{
			departmentList = null;//清空公用变量产生的数据缓存
			if(type == 1){//根据部门id获取部门信息
				type = 0;
				try{
					Department dep = departmentService.findByDepId(id);
					id = 0;
					VOUtils.writeJson(dep);
				}catch(Exception e){
					log.error("根据部门id获取部门信息出错", e);
				}
				return null;
			}
			if(type == 2){//验证工号重复
				type = 0;
				try {
					List<Account> accList = accountService.findAllAccount();
					for (int i = 0; i < accList.size(); i++) {
						if(loginName.equals(accList.get(i).getLoginName())){
							VOUtils.changeObj("true");
							return null;
						}
					}
					VOUtils.changeObj("false");
				} catch (Exception e) {
					log.error("查找工号存在出错", e);
				}
				return null;
			}
			
			//获取所有部门名称
			if(type == 3){
				type=0;
				try {
					List<Department> depList = departmentService.findAllDepartment();
					VOUtils.writeJson(depList);
				} catch (Exception e) {
					log.error("获取所有部门名称出错", e);
				}
				return null;
			}
			
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/info/findAlldepartment?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = departmentService.know_querypage(c);
			departmentList = (List<Department>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("分页查询部门处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//更新部门信息
	public String updatedepartment(){
		try{
			System.out.println(department == null);
			departmentService.update(department);
			department = null;
		}catch(Exception e){
			log.error("更新部门出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//更新职位信息
	public String updateposition(){
		try{
			System.out.println(position == null);
			positionService.update(position);
			position = null;
		}catch(Exception e){
			log.error("更新职位出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//查找全部职位
	@SuppressWarnings("unchecked")
	public String findAllposition(){
		try{
			if(type == 1){//根据部门id获取部门信息
				type = 0;
				try{
					Position p = positionService.findByPosId(id);
					id = 0;
					VOUtils.writeJson(p);
				}catch(Exception e){
					log.error("根据部门id获取部门信息出错", e);
				}
				return null;
			}
			
			//获取所有职位
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/info/findAllposition?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = positionService.know_querypage(c);
			positionList = (List<Position>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
			
			departmentList = departmentService.findAllDepartment();
		}catch(Exception e){
			log.error("分页查询职位处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//添加部门
	public String adddepartment(){
		try {
			Date date = new Date();
			department.setCreateTime(date);
			String time = "9999-12-31 23:59:00";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endTime;
			try {
				endTime = sdf.parse(time);
				department.setEndTime(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//乱码处理
			String depName = department.getDepartmentName();
			String name = department.getDepartmentOwnerName();
			depName =new String(depName.getBytes("iso-8859-1"),"UTF-8");
			name =new String(name.getBytes("iso-8859-1"),"UTF-8");
			department.setDepartmentName(depName);
			department.setDepartmentOwnerName(name);
			departmentService.insert(department);
		} catch (Exception e) {
			log.error("添加部门出错", e);
		}
		return SUCCESS;
	}
	
	//添加职位信息
	public String addposition(){
		try{
			if(position != null){
				Date date = new Date();
				position.setCreateTime(date);
				String time = "9999-12-31 23:59:00";
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d = sdf.parse(time);
				position.setEndTime(d);
				//乱码处理
				String positionName = position.getPositionName();
				positionName =new String(positionName.getBytes("iso-8859-1"),"UTF-8");
				position.setPositionName(positionName);
				positionService.insert(position);
				position = null;
			}
		}catch(Exception e){
			log.error("添加职位出错", e);
		}
		return SUCCESS;
	}
	
	public String deletedepartment(){
		try {
			departmentService.delete(id);
			id = 0;
		} catch (Exception e) {
			log.error("删除部门出错", e);
		}
		
		return SUCCESS;
	}
	
	public String deleteposition(){
		try {
			positionService.delete(id);
			id =0;
		} catch (Exception e) {
			log.error("删除职位出错", e);
		}
		
		return SUCCESS;
	}
}
