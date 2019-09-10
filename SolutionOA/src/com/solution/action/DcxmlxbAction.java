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
import com.solution.entity.Page;
import com.solution.service.AccountService;
import com.solution.service.ApprovalProcessRecordService;
import com.solution.service.ApprovalProcessService;
import com.solution.service.DcxmlxbService;
import com.solution.service.DepartmentService;
import com.solution.service.FormTypeService;
import com.solution.service.PositionService;
import com.solution.tools.VOUtils;

public class DcxmlxbAction extends BaseAction{
	/**
	 * 日志对象
	 */
	Logger					log						= Logger.getLogger(DcxmlxbAction.class);
	private static final long	serialVersionUID	= -1671386891287525595L;
	private DcxmlxbService dcxmlxbService;
	private FormTypeService formTypeService;
	private ApprovalProcessService approvalProcessService;
	private ApprovalProcessRecordService approvalProcessRecordService;
	private List<ApprovalProcessRecord> approvalProcessRecordList;
	private AccountService accountService;
	private DepartmentService departmentService;
	private PositionService positionService;
	private List<Dcxmlxb> dcxmlxbList;
	private List<ApprovalProcess> approvalProcessList;
	private Dcxmlxb dcxmlxb;
	private ApprovalProcessRecord approvalProcessRecord;
	private int type;
	private int id;
	private int formID;
	private int recordID;
	private String formNumber;
	private String remark;
	private String currentPage;
	private String pageBar;
	private List<ApprovalProcess> appList;
	private int value;
	

	public int getFormID(){
		return formID;
	}

	public void setFormID(int formID){
		this.formID = formID;
	}

	public int getValue(){
		return value;
	}

	public void setValue(int value){
		this.value = value;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getFormNumber(){
		return formNumber;
	}

	public void setFormNumber(String formNumber){
		this.formNumber = formNumber;
	}

	public int getRecordID(){
		return recordID;
	}

	public void setRecordID(int recordID){
		this.recordID = recordID;
	}

	public PositionService getPositionService(){
		return positionService;
	}

	public void setPositionService(PositionService positionService){
		this.positionService = positionService;
	}

	public DepartmentService getDepartmentService(){
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService){
		this.departmentService = departmentService;
	}

	public AccountService getAccountService(){
		return accountService;
	}

	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}

	public ApprovalProcessRecord getApprovalProcessRecord(){
		return approvalProcessRecord;
	}

	public void setApprovalProcessRecord(ApprovalProcessRecord approvalProcessRecord){
		this.approvalProcessRecord = approvalProcessRecord;
	}

	public Dcxmlxb getDcxmlxb(){
		return dcxmlxb;
	}

	public void setDcxmlxb(Dcxmlxb dcxmlxb){
		this.dcxmlxb = dcxmlxb;
	}

	public List<ApprovalProcess> getApprovalProcessList(){
		return approvalProcessList;
	}

	public void setApprovalProcessList(List<ApprovalProcess> approvalProcessList){
		this.approvalProcessList = approvalProcessList;
	}

	public List<ApprovalProcess> getAppList(){
		return appList;
	}

	public void setAppList(List<ApprovalProcess> appList){
		this.appList = appList;
	}

	public List<ApprovalProcessRecord> getApprovalProcessRecordList(){
		return approvalProcessRecordList;
	}

	public void setApprovalProcessRecordList(List<ApprovalProcessRecord> approvalProcessRecordList){
		this.approvalProcessRecordList = approvalProcessRecordList;
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

	public List<Dcxmlxb> getDcxmlxbList(){
		return dcxmlxbList;
	}

	public void setDcxmlxbList(List<Dcxmlxb> dcxmlxbList){
		this.dcxmlxbList = dcxmlxbList;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public ApprovalProcessRecordService getApprovalProcessRecordService(){
		return approvalProcessRecordService;
	}

	public void setApprovalProcessRecordService(ApprovalProcessRecordService approvalProcessRecordService){
		this.approvalProcessRecordService = approvalProcessRecordService;
	}

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
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

	public DcxmlxbService getDcxmlxbService(){
		return dcxmlxbService;
	}

	public void setDcxmlxbService(DcxmlxbService dcxmlxbService){
		this.dcxmlxbService = dcxmlxbService;
	}

	
	@SuppressWarnings("unchecked")
	public String dcxmlxb(){//进入发起审核状态列表
		System.out.println("进入发起审核状态列表");
		try{
			System.out.println(type);
			
			if(type == 5){//获取当前账户的真实名字
				type = 0;
				Account acc = new Account();
				acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
				VOUtils.changeObj(acc.getRealName());
				return null;
			}
			int formTypeID = 0;
			this.dcxmlxbList = dcxmlxbService.findAllDcxmlxb();
			System.out.println("地产立项表工单所有列表数据:");
			for(Dcxmlxb dcxmlxb : this.dcxmlxbList){
				System.out.println(dcxmlxb.getFormNumber());
			}
			List<ApprovalProcess> approvalProcessList = new ArrayList<ApprovalProcess>();
			List<ApprovalProcess> appList = new ArrayList<ApprovalProcess>();
			//根据工单类型获取审核流程
			approvalProcessList = approvalProcessService.findAllApprovalProcess();
			List<ApprovalProcessRecord> appRecordList2 = new ArrayList<ApprovalProcessRecord>();//存储工单审核涉及到的记录
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
			this.approvalProcessList = appList;
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
				List<Dcxmlxb> dcList = dcxmlxbService.findAllDcxmlxb();
				int mid = 0;int temp = 0;int iid = 0;
				int formMaxID = 0;
				String indexStr = "00" + formMaxID;
				String str ="FDC001";
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
					str = "FDC" + indexStr;
				}
				String randID = nowStr + "," + now + str + "," + formTypeID;//创建时间 -编号-工单类型id
				VOUtils.changeObj(randID);
				return null;
			}
			if(type == 3){//查询详情
				//根据id获取立项表所有数据
				ApprovalProcessRecord approvalProcessRecord = approvalProcessRecordService.findById(id);
				int formID = approvalProcessRecord.getFormID();
				Dcxmlxb dcxmlxb = dcxmlxbService.findById(formID);
				VOUtils.writeJson(dcxmlxb);
				type = 0;
				id = 0;
				return null;
			}
			
			//存在该工单类型的审核流程
			//获取当前用户发起的地产项目立项单的审核记录列表
			if(appList != null && appList.size() > 0){
				List<ApprovalProcessRecord> aprList = new ArrayList<ApprovalProcessRecord>();
				aprList= approvalProcessRecordService.findAllApprovalProcessRecord();
				//获取当前账户信息
				Account acc = new Account();
				acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
				List<ApprovalProcessRecord> approvalProcessRecordList = new ArrayList<ApprovalProcessRecord> ();
				System.out.println("当前用户发起的工单审核记录");
				for(ApprovalProcessRecord approvalProcessRecord1 : aprList){
					int formid = approvalProcessRecord1.getFormID();
					//获取当前用户发起的审核记录
					if(approvalProcessRecord1.getFormTypeID() == id && dcxmlxbService.findById(formid).getLoginName().equals(acc.getLoginName())){
						//根据审核人账户查询审核人部门
						String loginName = approvalProcessRecord1.getApproverAccount();
						List<Account> accList = new ArrayList<Account>();
						accList = accountService.findAllAccount();
						if(accList != null && accList.size() > 0){
							for(Account account : accList){
								if(account.getLoginName().equals(loginName)){
									approvalProcessRecord1.setApproverDepName(account.getDepartmentName());break;
								}
							}
						}
						//设置当前用户发起的工单审核记录集合
						approvalProcessRecordList.add(approvalProcessRecord1);
						System.out.println("fw:" + approvalProcessRecord1.getFlowNumber() +"fromid:"+approvalProcessRecord1.getFormID() + "审核人:" + approvalProcessRecord1.getApproverRealName());
					}
				}
				//分页显示
				if(currentPage == null){
					currentPage = "1";
				}
				// 每页显示条数
				int pageSize = 12;
				// 分页时连接的Action
				String action = "SolutionOA/dcxmlxb/godcxmlxb?id=1&currentPage=";
				Map<Object, Object> c = new HashMap<Object, Object>();
				c.put("currentPage", Integer.parseInt(currentPage));
				c.put("pageSize", pageSize);
				c.put("action", action.toString());
				c.put("approvalProcessRecordList", approvalProcessRecordList);
				// 查询数据
				Page<?> p = dcxmlxbService.know_querypage(c);
				this.approvalProcessRecordList = (List<ApprovalProcessRecord>) p.getDataList();
				pageBar = p.getPageBar();
				currentPage = null;
			}
			System.out.println("完成分页查询dcxmlxb");
		}catch(Exception e){
			log.error("分页显示发起的地产项目立项表审核记录列表出错！", e);
		}
		return SUCCESS;
	}
	public String adddcxmlxb(){//提交审核
		try{
			//添加地产立项表数据
			dcxmlxb.setCreateTime(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTime = "9999-12-31 00:00:00";//默认
			dcxmlxb.setEndTime(sdf.parse(endTime));
			dcxmlxbService.insert(dcxmlxb);
			//获取工单id
			int formID = 0;
			List<Dcxmlxb> dcxmlxbList = dcxmlxbService.findAllDcxmlxb();
			for (int i = 0; i < dcxmlxbList.size(); i++) {
				if(dcxmlxbList.get(i).getFormNumber().equals(dcxmlxb.getFormNumber())){
					formID = dcxmlxbList.get(i).getId();
				}
			}
			//根据审核流程添加各级地产项目立项表审核记录
			//根据申请人信息查找部门经理信息
			Account acc = new Account();
			acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
			int depID = acc.getDepartmentID();
			String loginName = departmentService.findByDepId(depID).getDepartmentOwner();
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
			approvalProcessRecord.setApproverTime(sdf.parse(endTime));
			approvalProcessRecord.setRemark("正在审核");
			approvalProcessRecordService.insert(approvalProcessRecord);//添加第一级审核记录
			//根据工单类型id获取审核流程
			int formTypeID = approvalProcessRecord.getFormTypeID();
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
			log.error("提交地产项目立项单审核出错！", e);
		}
		return SUCCESS;
	}
	/**
	 * 地产立项单审核列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String approvalDcxmlxb(){
		try{
			System.out.println("进入地产立项单审核列表");
			if(type == 2){//获取查看审核页面地产立项单信息
				type = 0;
				List<Dcxmlxb> dcxmlxbList = new ArrayList<Dcxmlxb>();
				dcxmlxbList = dcxmlxbService.findAllDcxmlxb();
				for(Dcxmlxb dcxmlxb : dcxmlxbList){
					if(formNumber.equals(dcxmlxb.getFormNumber())){
						VOUtils.writeJson(dcxmlxb);
						return null;
					}
				}
			}
			
			System.out.println("进入地产立项单审核列表");
			//根据工单类型获取审核流程
			List<ApprovalProcess> approvalProcessList = new ArrayList<ApprovalProcess>();
			List<ApprovalProcess> appList = new ArrayList<ApprovalProcess>();
			approvalProcessList = approvalProcessService.findAllApprovalProcess();
//			int maxFN = 0;
			if(approvalProcessList != null && approvalProcessList.size() > 0){
				for(int i = 0; i < approvalProcessList.size(); i++){
					if(approvalProcessList.get(i).getFormTypeID() == id){
						if(type == 4){
							//遍历工单记录
							List<ApprovalProcessRecord> appRecordList1 = new ArrayList<ApprovalProcessRecord>();
							appRecordList1 = approvalProcessRecordService.findAllApprovalProcessRecord();
							for(ApprovalProcessRecord approvalProcessRecord : appRecordList1){
									if(approvalProcessRecord != null){
										if(formID == approvalProcessRecord.getFormID() && approvalProcessList.get(i).getFlowNumber() == approvalProcessRecord.getFlowNumber()){
											approvalProcessList.get(i).setApproverStatus(approvalProcessRecord.getApproverStatus());
											approvalProcessList.get(i).setApproverTime(approvalProcessRecord.getApproverTime());
											System.out.println(formID +""+ approvalProcessList.get(i).getFlowNumber()+approvalProcessRecord.getApproverStatus() + approvalProcessRecord.getApproverTime().toString());
											break;
										}
									}
									
							}
							VOUtils.writeJson(approvalProcessList);
							this.approvalProcessList = appList;
							type = 0;
							id = 0;
							formID = 0;
							return null;
						}
						//获取审核人所在的部门
						if(approvalProcessList.get(i).getApproverAccount().trim() != null && !(approvalProcessList.get(i).getApproverAccount().trim().equals(""))){
							List<Account> aList = new ArrayList<Account>();
							aList = accountService.findAllAccount();
							for(Account account : aList){
								if(approvalProcessList.get(i).getApproverAccount().equals(account.getLoginName())){
									approvalProcessList.get(i).setApproverDepName(account.getDepartmentName());
									break;
								}
							}
						}else{
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
						appList.add(approvalProcessList.get(i));
					}
				}
				//获取最大流程号
//				maxFN = approvalProcessList.size();
			}
			this.approvalProcessList = appList;
			//根据工单类型获取审核记录
			List<ApprovalProcessRecord> approvalProcessRecordList1 = new ArrayList<ApprovalProcessRecord>();
			List<ApprovalProcessRecord> appRecordList = new ArrayList<ApprovalProcessRecord>();
			approvalProcessRecordList1 = approvalProcessRecordService.findAllApprovalProcessRecord();
			for(int i = 0; i < approvalProcessRecordList1.size(); i++){
				int fid = approvalProcessRecordList1.get(i).getFormTypeID() ;
				if(fid == id){
					appRecordList.add(approvalProcessRecordList1.get(i));
				}
			}
			
			//通过审核人账户查找所有待审工单
			List<ApprovalProcessRecord> apList = new ArrayList<ApprovalProcessRecord>();//属于当前用户的审核流程
			Account acc = new Account();
			acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
			if(appRecordList != null && appRecordList.size() > 0){
				if(type == 1){//如果初始状态为等待修改状态为正在审核
					type = 0;
					for(int i = 0; i < appRecordList.size(); i++){
						if(recordID == appRecordList.get(i).getId()){
							if(appRecordList.get(i).getApproverStatus() == 0){
								ApprovalProcessRecord apr= new ApprovalProcessRecord();
								apr = approvalProcessRecordService.findById(recordID);
								apr.setApproverStatus(1);
								approvalProcessRecordService.update(apr);
								VOUtils.changeObj("1");
								return null;
							}
							if(appRecordList.get(i).getApproverStatus() == 1){
								VOUtils.changeObj("1");
								return null;
							}
							if(appRecordList.get(i).getApproverStatus() == 2 || appRecordList.get(i).getApproverStatus() == 3){
								VOUtils.changeObj("2");
								return null;
							}
						}
					}
					return null;
				}
				if(type == 3){//提交审核结果(按照审核流程进行)
					System.out.println("提交审核结果");
					type = 0;
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					//remark
					//recordID
					for(int i = 0; i < appRecordList.size(); i++){
						if(recordID == appRecordList.get(i).getId()){
							//更新用户当前审核状态
							ApprovalProcessRecord aprd = new ApprovalProcessRecord();
							aprd = appRecordList.get(i);
							if(value != 99){
								aprd.setApproverStatus(value);
							}
							aprd.setRemark(remark);
							aprd.setApproverTime(new Date());
							approvalProcessRecordService.update(aprd);
							System.out.println("更新用户当前审核状态");
							int fw = aprd.getFlowNumber();
							if(value == 2){//通过审核执行,不通过不生成
								//同一个流程多个审核人处理
								System.out.println("同一个流程多个审核人处理。。。");
								int count =0;
								for(ApprovalProcess approvalProcess : appList){
									if(fw == approvalProcess.getFlowNumber()){
										count ++;
									}
								}
								boolean flag = true;//默认当前流程同一级 不存在多个审核人
								if(count > 1){//当前同一级流程存在多个审核人
									//当前流程获取同级其他审核人的审核结果
									for(ApprovalProcessRecord approvalProcessRecord : appRecordList){
										if(fw == approvalProcessRecord.getFlowNumber()){
											if(approvalProcessRecord.getApproverStatus() != 2){//存在不通过的
												flag = false;
											}
										}
									}
								}
								if(flag){//如果都通过则进入下一个流程
									//修改下一个审核记录(下一个流程衔接)
									int formID = aprd.getFormID();
									int fwNext = aprd.getFlowNumber() + 1;
									for (ApprovalProcessRecord approvalProcessRecord : appRecordList) {
										if(approvalProcessRecord.getFormID() == formID && fwNext == approvalProcessRecord.getFlowNumber()){
											//判断下一级流程存在多个审核人，审核状态均为1
											approvalProcessRecord.setApproverStatus(1);
											approvalProcessRecord.setRemark("正在审核");
											approvalProcessRecordService.update(approvalProcessRecord); 
										}
									}
								}
							}
							value = 0;
							remark = "";
							System.out.println(sdf.format(date)+"完成审核");
							return "app";
						}
					}
				}
				
				for(int i = 0; i < appRecordList.size(); i++){
					if(appRecordList.get(i).getApproverAccount().equals(acc.getLoginName())){
						//根据工单数据id查询申请人账户信息
						int fid = appRecordList.get(i).getFormID();
						Dcxmlxb dcxm = dcxmlxbService.findById(fid);
						appRecordList.get(i).setProposerAccount(dcxm.getLoginName());
						appRecordList.get(i).setProposerTime(dcxm.getCreateTime());
						appRecordList.get(i).setFormBornID(dcxm.getFormNumber());
						//根据账户信息查询申请人信息
						List<Account> accList = new ArrayList<Account>();
						accList = accountService.findAllAccount();
						if(accList != null && accList.size() > 0){
							for(int j = 0; j < accList.size(); j++){
								if(dcxm.getLoginName().equals(accList.get(j).getLoginName())){
									appRecordList.get(i).setProposerDepartment(accList.get(j).getDepartmentName());
									appRecordList.get(i).setProposerPosition(accList.get(j).getPositionName());
									appRecordList.get(i).setProposerName(accList.get(j).getRealName());
									break;
								}
							}
						}
						apList.add(appRecordList.get(i));
					}
				}
			}
			//分页显示
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/dcxmlxb/approvalDcxmlxb?id=1&currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			c.put("approvalProcessRecordList", apList);
			// 查询数据
			Page<?> p = dcxmlxbService.know_querypage(c);
			approvalProcessRecordList = (List<ApprovalProcessRecord>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("分页显示待审地产项目立项单列表出错！", e);
		}
		return SUCCESS;
	}
	//当前用户查询自己接受到的审核记录和发出的审核申请记录
	//var result = quickAjaxPost(sybp() + '/dcxmlxb/godcxmlxb?type=4&id='+formTypeID +'&formID='+formID, false, null);
	public String findApprovalProcessRecord(){
		System.out.println("findApprovalProcessRecord");
		List<ApprovalProcessRecord> recordList= new ArrayList<ApprovalProcessRecord>();
		List<ApprovalProcessRecord> appRecordList= new ArrayList<ApprovalProcessRecord>();
		recordList = approvalProcessRecordService.findAllApprovalProcessRecord();
		if(recordList != null && recordList.size() > 0){
			for(ApprovalProcessRecord approvalProcessRecord : recordList){
				System.out.println("0000" + approvalProcessRecord.getApproverRealName());
				if(id == approvalProcessRecord.getFormTypeID() && formID == approvalProcessRecord.getFormID()){
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
