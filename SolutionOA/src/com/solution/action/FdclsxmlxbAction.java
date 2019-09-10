package com.solution.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.solution.entity.Account;
import com.solution.entity.ApprovalProcess;
import com.solution.entity.ApprovalProcessRecord;
import com.solution.entity.Dcxmlxb;
import com.solution.entity.Department;
import com.solution.entity.Fdclsxmlxb;
import com.solution.entity.Page;
import com.solution.service.AccountService;
import com.solution.service.ApprovalProcessRecordService;
import com.solution.service.ApprovalProcessService;
import com.solution.service.DepartmentService;
import com.solution.service.FdclsxmlxbService;
import com.solution.service.FormTypeService;
import com.solution.service.PositionService;
import com.solution.tools.VOUtils;

/**
 * 临时项目立项单action
 * @author yue
 */
public class FdclsxmlxbAction extends BaseAction{
	private static final long	serialVersionUID	= -1143176453024554699L;
	/**
	 * 日志对象
	 */
	Logger					log						= Logger.getLogger(FdclsxmlxbAction.class);
	private FdclsxmlxbService fdclsxmlxbService;
	private FormTypeService formTypeService;
	private ApprovalProcessService approvalProcessService;
	private ApprovalProcessRecordService approvalProcessRecordService;
	private AccountService accountService;
	private DepartmentService departmentService;
	private ApprovalProcessRecord approvalProcessRecord;
	private PositionService positionService;
	private List<ApprovalProcessRecord> approvalProcessRecordList;
	private List<ApprovalProcess> approvalProcessList;
	private List<Fdclsxmlxb> fdclsxmlxbList;
	private String currentPage;
	private String pageBar;
	private Fdclsxmlxb lsxmlxb; 
	private int id;
	private int type;
	private int formID;
	
	
	public ApprovalProcessRecord getApprovalProcessRecord(){
		return approvalProcessRecord;
	}
	public void setApprovalProcessRecord(ApprovalProcessRecord approvalProcessRecord){
		this.approvalProcessRecord = approvalProcessRecord;
	}
	public List<ApprovalProcess> getApprovalProcessList(){
		return approvalProcessList;
	}
	public void setApprovalProcessList(List<ApprovalProcess> approvalProcessList){
		this.approvalProcessList = approvalProcessList;
	}
	public int getFormID(){
		return formID;
	}
	public void setFormID(int formID){
		this.formID = formID;
	}
	public List<Fdclsxmlxb> getFdclsxmlxbList(){
		return fdclsxmlxbList;
	}
	public void setFdclsxmlxbList(List<Fdclsxmlxb> fdclsxmlxbList){
		this.fdclsxmlxbList = fdclsxmlxbList;
	}
	public int getType(){
		return type;
	}
	public void setType(int type){
		this.type = type;
	}
	public Fdclsxmlxb getLsxmlxb(){
		return lsxmlxb;
	}
	public void setLsxmlxb(Fdclsxmlxb lsxmlxb){
		this.lsxmlxb = lsxmlxb;
	}
	public List<ApprovalProcessRecord> getApprovalProcessRecordList(){
		return approvalProcessRecordList;
	}
	public void setApprovalProcessRecordList(List<ApprovalProcessRecord> approvalProcessRecordList){
		this.approvalProcessRecordList = approvalProcessRecordList;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getCurrentPage(){
		return currentPage;
	}
	public void setCurrentPage(String currentPage){
		this.currentPage = currentPage;
	}
	public String getPageBar(){
		return pageBar;
	}
	public void setPageBar(String pageBar){
		this.pageBar = pageBar;
	}
	public FdclsxmlxbService getFdclsxmlxbService(){
		return fdclsxmlxbService;
	}
	public void setFdclsxmlxbService(FdclsxmlxbService fdclsxmlxbService){
		this.fdclsxmlxbService = fdclsxmlxbService;
	}
	public FormTypeService getFormTypeService(){
		return formTypeService;
	}
	public void setFormTypeService(FormTypeService formTypeService){
		this.formTypeService = formTypeService;
	}
	public ApprovalProcessService getApprovalProcessService(){
		return approvalProcessService;
	}
	public void setApprovalProcessService(ApprovalProcessService approvalProcessService){
		this.approvalProcessService = approvalProcessService;
	}
	public ApprovalProcessRecordService getApprovalProcessRecordService(){
		return approvalProcessRecordService;
	}
	public void setApprovalProcessRecordService(ApprovalProcessRecordService approvalProcessRecordService){
		this.approvalProcessRecordService = approvalProcessRecordService;
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
	//临时项目立项单发起审核列表
	@SuppressWarnings("unchecked")
	public String fdclsxmlxb(){
		try{
			if(type == 5){//获取当前账户的真实名字
				type = 0;
				Account acc = new Account();
				acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
				VOUtils.changeObj(acc.getRealName());
				return null;
			}
			this.fdclsxmlxbList = fdclsxmlxbService.findAllFdclsxmlxb();
			System.out.println("临时项目立项表工单所有列表数据:");
			for(Fdclsxmlxb fdclsxmlxb : this.fdclsxmlxbList){
				System.out.println(fdclsxmlxb.getFormNumber());
			}
			List<ApprovalProcess> approvalProcessList = new ArrayList<ApprovalProcess>();
			List<ApprovalProcess> appList = new ArrayList<ApprovalProcess>();
			//根据工单类型获取审核流程
			approvalProcessList = approvalProcessService.findAllApprovalProcess();
			List<ApprovalProcessRecord> appRecordList2 = new ArrayList<ApprovalProcessRecord>();//存储工单审核涉及到的记录
			int formTypeID = 0;
			if(approvalProcessList != null && approvalProcessList.size() > 0){
				//这里的部门名称为工单所属部门而不是审核人所在部门
				for(int i = 0; i < approvalProcessList.size(); i++){
					if(approvalProcessList.get(i).getFormTypeID() == id){
						formTypeID = id;
						//获取审核人所在的部门列表list
						if(approvalProcessList.get(i).getApproverAccount().trim() != null && !(approvalProcessList.get(i).getApproverAccount().trim().equals(""))){
							List<Account> aList = new ArrayList<Account>();
							aList = accountService.findAllAccount();
							for(Account account : aList){
								if(approvalProcessList.get(i).getApproverAccount().equals(account.getLoginName())){
									approvalProcessList.get(i).setApproverDepName(account.getDepartmentName());
									break;
								}
							}
						}else{//第一级流程
							approvalProcessList.get(i).setApproverDepName(approvalProcessList.get(i).getDepartmentName());
							//获取部门负责人姓名
							List<Department> depList = new ArrayList<Department>();
							depList = departmentService.findAllDepartment();
							if(depList != null && depList.size() > 0){
								for(Department department : depList){
									if(department.getDepartmentName().equals(approvalProcessList.get(i).getApproverDepName())){
										approvalProcessList.get(i).setApproverRealName(department.getDepartmentOwnerName());
										break;
									}
								}
							}
						}
						if(type == 4){//查询审核结果获取当前选中工单各级流程中审核状态和审核时间 js获取
							System.out.println("获取流程审核信息");
							//遍历工单记录
							List<ApprovalProcessRecord> appRecordList1 = new ArrayList<ApprovalProcessRecord>();
							appRecordList1 = approvalProcessRecordService.findAllApprovalProcessRecord();
							for(ApprovalProcessRecord approvalProcessRecord : appRecordList1){
								if(approvalProcessRecord != null){
									//判断同一审核流程有不同审核人时，要区分审核流程记录
									int count = 0;
									for(ApprovalProcessRecord app: appRecordList1){
										System.out.println("%"+app.getApproverDepName() + app.getApproverPosition() + app.getApproverRealName());
										if(app.getFlowNumber() == approvalProcessList.get(i).getFlowNumber()){
											count ++;
										}
									}
									if(formID == approvalProcessRecord.getFormID() && approvalProcessList.get(i).getFlowNumber() == approvalProcessRecord.getFlowNumber()){	
										approvalProcessList.get(i).setApproverStatus(approvalProcessRecord.getApproverStatus());
										approvalProcessList.get(i).setApproverTime(approvalProcessRecord.getApproverTime());
										System.out.println(formID +"FlowNumber:"+ approvalProcessList.get(i).getFlowNumber()+ "ApproverStatus:"+approvalProcessRecord.getApproverStatus() + approvalProcessRecord.getApproverTime().toString());
										//根据审核人工号查找审核人所在部门
										String loginName = approvalProcessRecord.getApproverAccount();
										List<Account> aList = new ArrayList<Account>();
										aList = accountService.findAllAccount();
										for (Account account : aList) {
											if(account.getLoginName().equals(loginName)){
												approvalProcessRecord.setApproverDepName(account.getDepartmentName());
											}
										}
										appRecordList2.add(approvalProcessRecord);
										System.out.println("%%%" + approvalProcessRecord.getApproverDepName() + approvalProcessRecord.getApproverPosition() + approvalProcessRecord.getApproverRealName());
										break;
									}
								}
							}
							
						}
						appList.add(approvalProcessList.get(i));
					}
				}
			}
			if(type == 4){
				type = 0;
				id = 0;
				formID = 0;
				VOUtils.writeJson(appRecordList2);
				return null;
			}
			this.approvalProcessList = appList;//添加申请审核页面流程
			if(type == 1){//进入申请审核页面获取当前用户发起的工单对应的所有审核流程
				type = 0;
				VOUtils.writeJson(appList);
				return null;
			}
			if(type == 2){//进入申请审核页面加载的数据
				type = 0;
				//生成工单随机编号
				Date date = new Date();
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				String nowStr = sdf1.format(date);
				SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
				String now = sdf2.format(date);
				//获取工单
				List<Fdclsxmlxb> dcList = fdclsxmlxbService.findAllFdclsxmlxb();
				int mid = 0;int temp = 0;int iid = 0;
				int formMaxID = 0;
				String indexStr = "00" + formMaxID;
				String str ="LS001";
				if(dcList != null && dcList.size() > 0){
					for (int i = 0; i < dcList.size(); i++) {
						iid = dcList.get(i).getId();
						temp = dcList.get(0).getId();
						if(i == 0){
							mid = temp;
						}
						if(i > 0){
							if(i == 1 && iid > temp){
								mid = iid;
							}else{
								mid = temp;
							}
							if(iid > dcList.get(i - 1).getId()){
								mid = iid;
							}else{
								mid = dcList.get(i - 1).getId();
							}
						}
					}
					formMaxID = mid + 1;
					if(formMaxID >= 0){
						indexStr = "00" + formMaxID;
					}
					if(formMaxID >= 10){
						indexStr = "0" + formMaxID;
					}
					if(formMaxID >= 100){
						indexStr = "" + formMaxID;
					}
					str = "LS" + indexStr;
				}
				String randID = nowStr + "," + now + str + "," + formTypeID;//创建时间 -编号-工单类型id
				VOUtils.changeObj(randID);System.out.println(randID);
				return null;
			}
			if(type == 3){//查询详情
				//根据id获取立项表所有数据
				ApprovalProcessRecord approvalProcessRecord = approvalProcessRecordService.findById(id);
				int formID = approvalProcessRecord.getFormID();
				Fdclsxmlxb ls = fdclsxmlxbService.findById(formID);
				VOUtils.writeJson(ls);
				type = 0;
				id = 0;
				return null;
			}
			
			//获取当前账户信息
			Account acc = new Account();
			acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
			List<ApprovalProcessRecord> aprList = new ArrayList<ApprovalProcessRecord>(); 
			List<ApprovalProcessRecord> approvalProcessRecordList = new ArrayList<ApprovalProcessRecord>(); 
			aprList = approvalProcessRecordService.findAllApprovalProcessRecord();
			System.out.println("工单审核记录");
			
			for(ApprovalProcessRecord apr : aprList){
				if(apr.getFormTypeID() == id && fdclsxmlxbService.findById(apr.getFormID()).getLoginName().equals(acc.getLoginName())){
					approvalProcessRecordList.add(apr);
					System.out.println("显示临时项目立项表：类型："+id  + "工单id:" + apr.getFormID());
					System.out.println(apr.getApproverPosition() + apr.getApproverRealName() + apr.getFlowNumber() + apr.getFormName());
				}
			}
			
			//分页显示
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/fdclsxmlxb/gofdclsxmlxb?id=4&currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			c.put("approvalProcessRecordList", approvalProcessRecordList);
			// 查询数据
			Page<?> p = fdclsxmlxbService.know_querypage(c);
			this.approvalProcessRecordList = (List<ApprovalProcessRecord>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
			id = 0;
			type = 0;
			System.out.println("完成分页查询fdclsxmlxb");
			log.info("完成分页查询fdclsxmlxb");
		}catch(Exception e){
			log.error("分页显示发起的临时项目立项表审核记录列表出错！", e);
		}
		return SUCCESS;
	}
	//发起临时项目立项单审核申请
	public String addfdclsxmlxb(){
		System.out.println("发起临时项目立项单审核申请");
		try{
			//添加临时项目立项单数据
			Date date = new Date();
			lsxmlxb.setCreateTime(date);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String time = "9999-12-31 00:00:00";
			lsxmlxb.setEndTime(sdf.parse(time));
			try{
				fdclsxmlxbService.insert(lsxmlxb);
				System.out.println("添加临时项目立项单数据成功");
			}catch(Exception e1){
				log.error("添加临时项目立项单数据失败",e1);
			}
			//System.out.println(approvalProcessRecord.getFormTypeID());
			//添加审核记录数据
			//获取工单id
			int formID = 0;
			List<Fdclsxmlxb> fdclsxmlxbList = fdclsxmlxbService.findAllFdclsxmlxb();
			for (int i = 0; i < fdclsxmlxbList.size(); i++) {
				if(fdclsxmlxbList.get(i).getFormNumber().equals(lsxmlxb.getFormNumber())){
					formID = fdclsxmlxbList.get(i).getId();
				}
			}
			//根据审核流程添加各级地产项目立项表审核记录
			//根据申请人信息查找部门经理信息
			Account acc = new Account();
			acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
			int depID = acc.getDepartmentID();
			String loginName = departmentService.findByDepId(depID).getDepartmentOwner();
			int formTypeID = approvalProcessRecord.getFormTypeID();
			ApprovalProcessRecord approvalProcessRecord = new ApprovalProcessRecord();
			approvalProcessRecord.setFormTypeID(formTypeID);//需要获取最好不直接设置？
			approvalProcessRecord.setApproverAccount(loginName);
			approvalProcessRecord.setApproverRealName(departmentService.findByDepId(depID).getDepartmentOwnerName());
			List<Account> accList = new ArrayList<Account>();
			accList = accountService.findAllAccount();
			for(Account account : accList){
				if(account.getLoginName().equals(loginName)){
					int posID = account.getPositionID();
					approvalProcessRecord.setApproverPosition(positionService.findByPosId(posID).getPositionName());
					break;
				}
			}			
			approvalProcessRecord.setFormID(formID);
			approvalProcessRecord.setFlowNumber(1);
			approvalProcessRecord.setApproverStatus(1);
			approvalProcessRecord.setApproverTime(sdf.parse(time));
			approvalProcessRecord.setRemark("正在审核");
			approvalProcessRecordService.insert(approvalProcessRecord);//添加第一级审核记录
			//根据工单类型id获取审核流程
			formTypeID = approvalProcessRecord.getFormTypeID();
			List<ApprovalProcess> approvalProcessList = new ArrayList<ApprovalProcess>();
			//根据工单类型获取审核流程
			approvalProcessList = approvalProcessService.findAllApprovalProcess();
			if(approvalProcessList != null && approvalProcessList.size() > 0){
				for(ApprovalProcess approvalProcess : approvalProcessList){
					if(approvalProcess.getFormTypeID() == formTypeID && approvalProcess.getFlowNumber() != 1){
						approvalProcessRecord.setFlowNumber(approvalProcess.getFlowNumber());
						approvalProcessRecord.setApproverAccount(approvalProcess.getApproverAccount());
						approvalProcessRecord.setApproverPosition(approvalProcess.getApproverPosition());
						approvalProcessRecord.setApproverRealName(approvalProcess.getApproverRealName());
						approvalProcessRecord.setRemark("等待审核");
						approvalProcessRecord.setApproverStatus(0);
						approvalProcessRecordService.insert(approvalProcessRecord);//添加下一级审核记录
					}
				}
			}
			
		}catch(Exception e){
			log.error("发起临时项目立项单申请出错！",e);
		}
		//approvalProcessList
		return SUCCESS;
	}
	/**
	 * 查找当前用户申请的工单审核记录
	 * @return
	 */
	public String findApprovalProcessRecord(){
		System.out.println("findApprovalProcessRecord");
		List<ApprovalProcessRecord> recordList= new ArrayList<ApprovalProcessRecord>();
		List<ApprovalProcessRecord> appRecordList= new ArrayList<ApprovalProcessRecord>();
		recordList = approvalProcessRecordService.findAllApprovalProcessRecord();
		if(recordList != null && recordList.size() > 0){
			for(ApprovalProcessRecord approvalProcessRecord : recordList){
				System.out.println("0000" + approvalProcessRecord.getApproverRealName());
				if(approvalProcessRecord.getFormTypeID() == id && formID == approvalProcessRecord.getFormID()){
					//根据审核人工号查找审核人所在部门
					String loginName = approvalProcessRecord.getApproverAccount();
					List<Account> aList = new ArrayList<Account>();
					aList = accountService.findAllAccount();
					for (Account account : aList) {
						if(account.getLoginName().equals(loginName)){
							approvalProcessRecord.setApproverDepName(account.getDepartmentName());
						}
					}
					appRecordList.add(approvalProcessRecord);
				}
			}	
		}
		type = 0;
		id = 0;
		formID = 0;
		VOUtils.writeJson(appRecordList);
		return null;
	}
}
