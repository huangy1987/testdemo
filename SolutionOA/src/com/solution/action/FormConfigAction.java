package com.solution.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.solution.entity.Account;
import com.solution.entity.ApprovalProcess;
import com.solution.entity.Department;
import com.solution.entity.FormType;
import com.solution.entity.Module;
import com.solution.entity.Page;
import com.solution.entity.Position;
import com.solution.service.AccountService;
import com.solution.service.ApprovalProcessService;
import com.solution.service.DepartmentService;
import com.solution.service.FormTypeService;
import com.solution.service.PositionService;
import com.solution.tools.VOUtils;

/**
 * 工单配置action
 * @author yue
 *
 */
public class FormConfigAction extends BaseAction{
	private static final long	serialVersionUID	= 4750841199906069664L;
	/**
	 * 日志对象
	 */
	Logger					log						= Logger.getLogger(FormConfigAction.class);
	private String currentPage;//复用
	private int id;	  //复用
//	private int pid;
	private int type; //复用
	private String pageBar;
	List<FormType> fromTypeList;
	List<ApprovalProcess> approvalProcessList;
	private FormTypeService formTypeService;
	private ApprovalProcessService approvalProcessService;
	private FormType formType;//复用
	private ApprovalProcess approvalProcess;
	private DepartmentService departmentService;
	private PositionService positionService;
	private AccountService accountService;
	private String flowNumber;
	private String formTypeID;
	private String loginName;
	
	public String getLoginName(){
		return loginName;
	}

	public void setLoginName(String loginName){
		this.loginName = loginName;
	}

	public String getFlowNumber(){
		return flowNumber;
	}

	public void setFlowNumber(String flowNumber){
		this.flowNumber = flowNumber;
	}

	public String getFormTypeID(){
		return formTypeID;
	}

	public void setFormTypeID(String formTypeID){
		this.formTypeID = formTypeID;
	}

	public AccountService getAccountService(){
		return accountService;
	}

	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}


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

	public List<ApprovalProcess> getApprovalProcessList(){
		return approvalProcessList;
	}

	public void setApprovalProcessList(List<ApprovalProcess> approvalProcessList){
		this.approvalProcessList = approvalProcessList;
	}

	public ApprovalProcess getApprovalProcess(){
		return approvalProcess;
	}

	public void setApprovalProcess(ApprovalProcess approvalProcess){
		this.approvalProcess = approvalProcess;
	}

	public ApprovalProcessService getApprovalProcessService(){
		return approvalProcessService;
	}

	public void setApprovalProcessService(ApprovalProcessService approvalProcessService){
		this.approvalProcessService = approvalProcessService;
	}

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public FormType getFormType(){
		return formType;
	}

	public void setFormType(FormType formType){
		this.formType = formType;
	}

	public FormTypeService getFormTypeService(){
		return formTypeService;
	}

	public void setFormTypeService(FormTypeService formTypeService){
		this.formTypeService = formTypeService;
	}

	public List<FormType> getFromTypeList(){
		return fromTypeList;
	}

	public void setFromTypeList(List<FormType> fromTypeList){
		this.fromTypeList = fromTypeList;
	}

	public String getPageBar(){
		return pageBar;
	}

	public void setPageBar(String pageBar){
		this.pageBar = pageBar;
	}

	public String getCurrentPage(){
		return currentPage;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage = currentPage;
	}

	//查找全部工单类型
	@SuppressWarnings("unchecked")
	public String findAllformType(){
		try{
			if(type == 1){//通过工单类型id查询工单类型对象
				type = 0;
				FormType formType =	formTypeService.findById(id);
				VOUtils.writeJson(formType);
				return null;
			}
			if(type == 2){//查询所有工单类型对象
				type = 0;
				List<FormType> ftList = formTypeService.findAllFormType();
				VOUtils.writeJson(ftList);
				return null;
			}
//			List<Position> positions = positionService.findAllPosition();
//			ActionContext.getContext().getSession().put("poList",positions);
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/config/findAllformType?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = formTypeService.know_querypage(c);
			fromTypeList = (List<FormType>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("查询账户分页处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//查找全部工单审核流程
	@SuppressWarnings("unchecked")
	public String findAllapprovalProcess(){
		try{
			if(type == 1){//根据审核流程id查找审核流程对象
				type = 0;
				ApprovalProcess approvalProcess = approvalProcessService.findById(id);
				id = 0;
				int formTypeID = approvalProcess.getFormTypeID();
				FormType ft = formTypeService.findById(formTypeID);
				approvalProcess.setDepartmentName(departmentService.findByDepId(ft.getDepartmentID()).getDepartmentName() );
				approvalProcess.setFormName(ft.getFormName());
				System.out.println(approvalProcess.getDepartmentName());
				VOUtils.writeJson(approvalProcess);
				return null;
			}
			
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/config/findAllapprovalProcess?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = approvalProcessService.know_querypage(c);
			approvalProcessList = (List<ApprovalProcess>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("查询账户分页处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//添加工单类型
	public String addformType(){
		try{
			Date date = new Date();
			formType.setCreateTime(date);
			String time = "9999-12-31 23:59:00";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endTime = sdf.parse(time);
			formType.setEndTime(endTime);
			//乱码处理
			String formName = formType.getFormName();
			formName = new String(formName.getBytes("iso-8859-1"),"UTF-8");
			formType.setFormName(formName);
			String remark = formType.getRemark();
			remark = new String(remark.getBytes("iso-8859-1"),"UTF-8");
			formType.setRemark(remark);
			formTypeService.insert(formType);
		}catch(Exception e){
			log.error("添加工单类型出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//添加工单审核流程
	public String addapprovalProcess(){
		try{
			if(type == 1){//加载部门账户树结构数据
				type = 0;
				try {
					// 查询所有部门
					List<Department> deps = new ArrayList<Department>();
					//查询所有职位
					List<Account> poss = new ArrayList<Account>();
					deps = departmentService.findAllDepartment();
					poss = accountService.findAllAccount();
					// list集合
					List<Map<String, String>> list_ktype = null;
					// map集合
					Map<String, String> maptable = null;
					
					if(deps != null){
						list_ktype = new ArrayList<Map<String, String>>();
						// 赋值菜单
						for(Department ks : deps){
							maptable = new HashMap<String, String>();
							maptable.put("id", "p-"+ks.getId());
							maptable.put("pid", "p-0");
							maptable.put("name", ks.getDepartmentName());
							list_ktype.add(maptable);
						}
					}
					// 循环这个类型map集合 如果说mao集合中的id等于问题集合中的分类ID
					Map<String, String> kttable = null;
					// 组合map，然后添加到list_ktype集合中
					if(poss != null){
						// 赋值功能模块
						for(Account kt : poss){
							kttable = new HashMap<String, String>();
							kttable.put("id", kt.getId()+"");
							kttable.put("pid", "p-"+kt.getDepartmentID());
							kttable.put("name", kt.getRealName());
							list_ktype.add(kttable);
						}
					}
					VOUtils.writeJson(list_ktype);
					} catch (Exception e) {
						log.error("获取菜单和功能模块出错", e);
					}
					return null;
			}
			if(type == 2){//根据账户id获取账户对象
				type = 0;
				Account account = accountService.findById(id);
				int posID = account.getPositionID();
				account.setPositionName(positionService.findByPosId(posID).getPositionName()) ;
				VOUtils.writeJson(account);
				return null;
			}
			if(type == 3){//验证添加的审核流程是否已经存在
				type = 0;
			    List<ApprovalProcess> approvalProcessList	= approvalProcessService.findAllApprovalProcess();
			    for(int i = 0; i < approvalProcessList.size(); i++){
					if(approvalProcessList.get(i).getFlowNumber() == Integer.parseInt(flowNumber) 
							&& approvalProcessList.get(i).getFormTypeID() == Integer.parseInt(formTypeID)){
						VOUtils.changeObj("true");
					}
				}
				return null;
			}
			Date date = new Date();
			approvalProcess.setCreateTime(date);
			String time = "9999-12-31 23:59:00";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endTime = sdf.parse(time);
			approvalProcess.setEndTime(endTime);
			//乱码处理
			String posName = approvalProcess.getApproverPosition();
			posName = new String(posName.getBytes("iso-8859-1"),"UTF-8");
			approvalProcess.setApproverPosition(posName);
			String realName = approvalProcess.getApproverRealName();
			realName = new String(realName.getBytes("iso-8859-1"),"UTF-8");
			approvalProcess.setApproverRealName(realName);
			approvalProcessService.insert(approvalProcess);
		}catch(Exception e){
			log.error("添加工单审核流程出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	//删除工单类型
	public String deleteformType(){
		try{
			formTypeService.delete(id);
			id = 0;
		}catch(Exception e){
			log.error("删除工单类型出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	//删除工单审核流程
	public String deleteapprovalProcess(){
		try{
			approvalProcessService.delete(id);
			id = 0;
		}catch(Exception e){
			log.error("删除工单审核流程出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//更新工单
	public String updateformType(){
		try{
			formTypeService.update(formType);
			formType = null;
		}catch(Exception e){
			log.error("修改工单类型出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//更新工单审核流程
	public String updateapprovalProcess(){
		try{
			if(type == 1){//根据工号查询账户id
				type = 0;
				if(loginName != null && !"".equals(loginName) && !loginName.trim().equals("")){
					List<Account> accList = new ArrayList<Account>();
					accList = accountService.findAllAccount();
					for(int i = 0; i < accList.size(); i++){
						if(loginName.equals(accList.get(i).getLoginName())){
							int idd = accList.get(i).getId();
							VOUtils.changeObj(idd +"");
						}
					}
				}
				return null;
			}
			//乱码处理
			String posName = approvalProcess.getApproverPosition();
			posName = new String(posName.getBytes("iso-8859-1"),"UTF-8");
			approvalProcess.setApproverPosition(posName);
			String realName = approvalProcess.getApproverRealName();
			realName = new String(realName.getBytes("iso-8859-1"),"UTF-8");
			approvalProcess.setApproverRealName(realName);
			approvalProcessService.update(approvalProcess);
			approvalProcess = null;
		}catch(Exception e){
			log.error("修改工单审核流程出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
}
