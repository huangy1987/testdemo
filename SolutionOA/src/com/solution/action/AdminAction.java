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
import com.solution.entity.Module;
import com.solution.entity.Page;
import com.solution.entity.Position;
import com.solution.service.AccountRoleService;
import com.solution.service.AccountService;
import com.solution.service.DepartmentService;
import com.solution.service.ModuleService;
import com.solution.service.PositionService;
import com.solution.tools.VOUtils;

/**
 * 管理员action（账户-角色-模块）
 * @author Administrator
 *
 */
public class AdminAction extends BaseAction{
	/**
	 * 日志对象
	 */
	Logger					log						= Logger.getLogger(AdminAction.class);
	private static final long	serialVersionUID	= 6060989157556203348L;
	private AccountService accountService;
	private AccountRoleService accountRoleService;
	private ModuleService moduleService;
	private DepartmentService departmentService;
	private PositionService positionService;
	private List<Account> accountList;
	private List<AccountRole> roleList;
	private List<Module> moduleList;
	private int id;			//多处使用
	private int idDep;
	private Account account;
	private String time;//多处使用
	private AccountRole role; 
	private Module module;
	private int moduleIndex;
	private int type;  //多处使用
	private int moduleTypeupdate;//更改模块类型判断标识
	private String loginName;
	
	public String getTime(){
		return time;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getLoginName(){
		return loginName;
	}

	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	//分页
	private String currentPage;  //当前页
	private String pageBar;			//分页工具条	
	
	
	public int getIdDep(){
		return idDep;
	}

	public void setIdDep(int idDep){
		this.idDep = idDep;
	}

	public int getModuleTypeupdate() {
		return moduleTypeupdate;
	}

	public void setModuleTypeupdate(int moduleTypeupdate) {
		this.moduleTypeupdate = moduleTypeupdate;
	}

	public int getModuleIndex() {
		return moduleIndex;
	}

	public void setModuleIndex(int moduleIndex) {
		this.moduleIndex = moduleIndex;
	}

	public Module getModule(){
		return module;
	}

	public void setModule(Module module){
		this.module = module;
	}

	public AccountRole getRole(){
		return role;
	}

	public void setRole(AccountRole role){
		this.role = role;
	}

	public List<Module> getModuleList(){
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList){
		this.moduleList = moduleList;
	}

	public List<AccountRole> getRoleList(){
		return roleList;
	}

	public void setRoleList(List<AccountRole> roleList){
		this.roleList = roleList;
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

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public Account getAccount(){
		return account;
	}

	public void setAccount(Account account){
		this.account = account;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public List<Account> getAccountList(){
		return accountList;
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

	public void setAccountList(List<Account> accountList){
		this.accountList = accountList;
	}

	public AccountService getAccountService(){
		return accountService;
	}

	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}

	public AccountRoleService getAccountRoleService(){
		return accountRoleService;
	}

	public void setAccountRoleService(AccountRoleService accountRoleService){
		this.accountRoleService = accountRoleService;
	}

	public ModuleService getModuleService(){
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService){
		this.moduleService = moduleService;
	}
	
	//查找全部
	@SuppressWarnings("unchecked")
	public String findAllaccount(){
		try{
			//进入更改账户
			if(type == 4){
				type=0;//清空负责下次访问这个方法可能返回一个字符串
				System.out.println(id);
				Account acc = accountService.findById(id);
				int dID = acc.getDepartmentID();
				int pID = acc.getPositionID();
				int rID = acc.getAccountRoleID();
				
				acc.setDepartmentName(departmentService.findByDepId(dID).getDepartmentName());
				acc.setPositionName(positionService.findByPosId(pID).getPositionName());
				acc.setRoleName((accountRoleService.findById(rID).getRoleName()));
				VOUtils.writeJson(acc);
				return null;
			}
			//获取所有角色
			List<AccountRole> roles = accountRoleService.findAllRole();
			ActionContext.getContext().getSession().put("roles",roles);
			//获取所有部门
			List<Department> deps = departmentService.findAllDepartment();
			ActionContext.getContext().getSession().put("departments",deps);
			//获取所有职位
			List<Position> positions = positionService.findAllPosition();
			ActionContext.getContext().getSession().put("poList",positions);
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/admin/findAllaccount?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = accountService.know_querypage(c);
			accountList = (List<Account>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("查询账户分页处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//添加账户时工号重名验证
	public String findAllSameName(){
		try{
			List<Account> accList= accountService.findAllAccount();
			if(loginName != null && loginName != "" && loginName.trim() != ""){
				for(int i = 0; i < accList.size(); i++){
					if(loginName.equals(accList.get(i).getLoginName())){
						System.out.println(loginName + "-等于--"+accList.get(i).getLoginName());
						VOUtils.changeObj("true");
						return null;
					}
				}
			}
			VOUtils.changeObj("false");
		}catch(Exception e){
			log.error("查询相同工号出错", e);
		}
		return null;
	}
	
	//根据部门级联查询部门下所有职位
	public String findAllPositonByDep(){
		System.out.println("部门编号："+id);
		List<Position> poses = positionService.findAllPosition();
		List<Position> pos1 = new ArrayList<Position> ();
		for(int i = 0; i < poses.size(); i++){
			if(poses.get(i).getDepartmentID() == id){
				pos1.add(poses.get(i));
			}
		}
		id = 0;
		ActionContext.getContext().getSession().put("posList",pos1);
		VOUtils.writeJson(pos1);
		return null;
	}
	
	//一级流程自动设置职位
	public String findAllAutoSetPositonOneLevel(){
		try{
			List<Department> deps = departmentService.findAllDepartment();
			//获取当前用户所属的部门id
//			Account acc = new Account();
//			acc = (Account)ActionContext.getContext().getSession().get("accountInfo");
//			int depID = acc.getDepartmentID();
			int depID = id;
			String loginName = "";
			if(deps != null && deps.size() > 0){
				for(int i = 0; i < deps.size(); i++){
					if(deps.get(i).getId() == depID){
						loginName = deps.get(i).getDepartmentOwner();
						System.out.println(loginName);
					}
				}
			}
			List<Account> accList = new ArrayList<Account>();
			accList = accountService.findAllAccount();
			for(Account account : accList){
				if(account.getLoginName().equals(loginName)){
					VOUtils.changeObj(account.getPositionName());
					System.out.println("=="+account.getPositionName());
				}
			}
			id = 0;
		}catch(Exception e){
			log.error("一级流程自动设置职位出错", e);
		}
		return null;
	}
	
	//根据部门级联查询部门下面所有的角色
	public String findAllRoleByDep(){
		try{
			System.out.println("部门编号："+id);
			List<AccountRole> roles = accountRoleService.findAllRole();
			List<AccountRole> rolesDep = new ArrayList<AccountRole>();
			if(roles != null){
				for(int i = 0; i < roles.size(); i++){
					if(id == roles.get(i).getDepartmentID()){
						rolesDep.add(roles.get(i));
					}
				}
			}
			id = 0;
			VOUtils.writeJson(rolesDep);
			return null;
		}catch(Exception e){
			log.error("查询账户中部门对应的所有角色出错", e);
			return SUCCESS;
		}
	}
	//根据职位id获取部门id
	public String findAllDepByPos() throws IOException{
		System.out.println(id);
		Position p = positionService.findByPosId(id);
		id = 0;
		int depID = p.getDepartmentID();
		VOUtils.changeObj(depID + "");
		return null;
	}
	//根据角色id获取部门id
	public String findAllDepByRole() throws IOException{
		System.out.println(id);
		AccountRole role = accountRoleService.findById(id);
		id = 0;
		int depID =role.getDepartmentID();
		VOUtils.changeObj(depID + "");
		return null;
	}
	//根据角色id获取角色对象
	public String findAllroleByID(){
		try {
			System.out.println("角色id:" + id);
			AccountRole role = accountRoleService.findById(id);
			id = 0;
			VOUtils.writeJson(role);
		} catch (Exception e) {
			log.error("根据id获取角色对象出错", e);
		}
		return null;
	}
	//根据职位id获取部门id
	public String findAllDepsByPos(){
		System.out.println("职位编号："+id);
		
		Position pos = positionService.findByPosId(id);
		int depID = pos.getDepartmentID();
		
		Department department = departmentService.findByDepId(depID);
		System.out.println("所在部门" + depID + department.getDepartmentName());
		try {
			VOUtils.changeObj(depID +"");
		} catch (IOException e) {
			log.error("查询账户修改中职位对应部门出错", e);
			e.printStackTrace();
		}
		return null;
	}
	//根据职位id获取部门对象
	public String findAllDepObjByPos(){
		System.out.println("职位编号："+id);
		
		Position pos = positionService.findByPosId(id);
		int depID = pos.getDepartmentID();
		
		Department department = departmentService.findByDepId(depID);
		try {
			VOUtils.writeJson(department);
		} catch (Exception e) {
			log.error("查询账户修改中职位对应部门出错", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public String findAllrole(){
		try{
			//获得所有部门
			List<Department> ds = departmentService.findAllDepartment();
			ActionContext.getContext().getSession().put("departments",ds);
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/admin/findAllrole?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = accountRoleService.know_querypage(c);
			roleList = (List<AccountRole>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("查询角色分页处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//加载功能树
	@SuppressWarnings("unused")
	public String findAllMenus(){
		try {
		//查询所有模块和菜单
		List<Module> modules = moduleService.findAllModule();
		// 查询所有菜单
		List<Module> menus = new ArrayList<Module>();
		//查询所有功能模块
		List<Module> functions = new ArrayList<Module>();
		if(modules != null){
			for (int i = 0; i < modules.size(); i++) {
				if(modules.get(i).getModuleParentID() == 0){
					menus.add(modules.get(i));
				}else{
					functions.add(modules.get(i));
				}
			}
		}
		// list集合
		List<Map<String, String>> list_ktype = null;
		// map集合
		Map<String, String> maptable = null;
		
		if(menus != null){
			list_ktype = new ArrayList<Map<String, String>>();
			// 赋值菜单
			for(Module ks : menus){
				maptable = new HashMap<String, String>();
				maptable.put("id", "p-"+ks.getId());
				maptable.put("pid", "p-0");
				maptable.put("name", ks.getModuleName());
				list_ktype.add(maptable);
			}
		}
		// 循环这个类型map集合 如果说mao集合中的id等于问题集合中的分类ID
		Map<String, String> kttable = null;
		// 组合map，然后添加到list_ktype集合中
		if(functions != null){
			// 赋值功能模块
			for(Module kt : functions){
				kttable = new HashMap<String, String>();
				kttable.put("id", kt.getId()+"");
				kttable.put("pid", "p-"+kt.getModuleParentID());
				kttable.put("name", kt.getModuleName());
				list_ktype.add(kttable);
			}
		}
		VOUtils.writeJson(list_ktype);
		} catch (Exception e) {
			log.error("获取菜单和功能模块出错", e);
		}
		return null;
	}
	public String findAllmodule(){
		try{
			//获取所有部门
			List<Module> deps = moduleService.findAllModule();
			if(type == 9){//判断模型索引是否重复
				type = 0;//清空
				System.out.println(moduleIndex);
				for (int i = 0; i < deps.size(); i++) {
					if(moduleIndex == deps.get(i).getModuleIndex()){
						VOUtils.changeObj("true");
						return null;
					}
				}
				VOUtils.changeObj("false");
				return null;
			}
			if(type == 8){//获取模型索引集合
				type = 0;//清空
				StringBuffer indexs = new StringBuffer();
				for (int i = 0; i < deps.size(); i++) {
					indexs.append(deps.get(i).getModuleIndex() + "");
				}
				VOUtils.changeObj(indexs.toString());
				return null;
			}
			
			List<Module> depsP = new ArrayList<Module>();		//菜单项
			ActionContext.getContext().getSession().put("modules",deps);//模型集合
			for(int i = 0; i < deps.size(); i++){
				if(deps.get(i).getModuleParentID() == 0){
					depsP.add(deps.get(i));
				}
			}
			ActionContext.getContext().getSession().put("moduleMenu",depsP);//模型菜单集合
			if(currentPage == null){
				currentPage = "1";
			}
			// 每页显示条数
			int pageSize = 12;
			// 分页时连接的Action
			String action = "SolutionOA/admin/findAllmodule?currentPage=";
			Map<Object, Object> c = new HashMap<Object, Object>();
			c.put("currentPage", Integer.parseInt(currentPage));
			c.put("pageSize", pageSize);
			c.put("action", action.toString());
			// 查询数据
			Page<?> p = moduleService.know_querypage(c);
			moduleList = (List<Module>) p.getDataList();
			pageBar = p.getPageBar();
			currentPage = null;
		}catch(Exception e){
			log.error("查询模型分页处理出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	//删除
	public String deleteaccount(){
		try{
			int d = accountService.delete(id);
			if(d == id){
				log.info("成功删除账户编号"+id);
			}
			id = 0;
		}catch(Exception e){
			log.error("删除账户出错", e);
		}
		return SUCCESS;
	}
	public String deleterole(){
		try{
			int d = accountRoleService.delete(id);
			if(d == id){
				log.info("成功删除角色编号"+id);
			}
			id = 0;
		}catch(Exception e){
			log.error("删除角色出错", e);
		}
		return SUCCESS;
	}
	public String deletemodule(){
		try{
			int d = moduleService.delete(id);
			if(d == id){
				log.info("成功删除模型编号"+id);
			}
			id = 0;
		}catch(Exception e){
			log.error("删除模型出错", e);
		}
		return SUCCESS;
	}
	//添加
	public String addaccount(){
		account.setCreateTime(new Date());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = "9999-12-31 00:00:00";//默认
		account.setAccountGroupID(0);//默认
		try{
			account.setEndTime(sdf.parse(endTime));
		}catch(ParseException e1){
			e1.printStackTrace();
		}
		try{
			accountService.insert(account);
			account = null;
		}catch(Exception e){
			log.error("新增账户出错", e);
		}
		return SUCCESS;
	}
	public String addrole(){
		try{
			role.setCreateTime(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTime = "9999-12-31 00:00:00";//默认
			try{
				role.setEndTime(sdf.parse(endTime));
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			//乱码处理
			String sr = role.getRemark();
			sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
			role.setRemark(sr);
				
			sr = role.getRoleName();
			sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
			role.setRoleName(sr);
			role.setModuleList(role.getModuleList().trim());
			//角色模型排序
//			String str = role.getModuleList();
			accountRoleService.insert(role);
			role = null;
		}catch(Exception e){
			log.error("新增角色出错", e);
		}
		return SUCCESS;
	}
	public String addmodule(){
		try{
			module.setCreateTime(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTime = "9999-12-31 00:00:00";//默认
			try{
				module.setEndTime(sdf.parse(endTime));
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			//乱码处理
			String sr = module.getModuleName();
			sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
			module.setModuleName(sr);
			
			sr = module.getModulePath();
			sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
			module.setModulePath(sr);
			
			sr = module.getRemark();
			sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
			module.setRemark(sr);
			moduleService.insert(module);
			module = null;
		}catch(Exception e){
			log.error("新增模型出错", e);
		}
		return SUCCESS;
	}
	//修改
	public String updateaccount(){
		try{
			if(type == 1){//修改
				type = 0;
				int i = account.getId();
				account.setCreateTime(accountService.findById(i).getCreateTime());
				account.setEndTime(accountService.findById(i).getEndTime());
				accountService.update(account);
				account = null;
			}
			if(type == 2){//进入修改
				type = 0;
				//根据id获取该账户信息
				account = accountService.findById(id);
				id = 0;
				//根据账户id查询部门名称和职位名称
				String dname= departmentService.findByDepId(account.getDepartmentID()).getDepartmentName();
				account.setDepartmentName(dname);
				String pname= positionService.findByPosId(account.getPositionID()).getPositionName();
				account.setPositionName(pname);
				String rname= accountRoleService.findById(account.getAccountRoleID()).getRoleName();
				account.setRoleName(rname);
				return "in";
			}
			if(type == 3){//停用账户
				type = 0;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Account acc = accountService.findById(id);
				id = 0;
				acc.setEndTime(sdf.parse(time));
				time = null;
				//acc.setUsedState(0);
				System.out.println("停用后时间" + sdf.format(acc.getEndTime()));
				accountService.update(acc);
				accountList = accountService.findAllAccount();
				return SUCCESS;
			}
			if(type == 4){//启动账户
				type =0;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Account acc = accountService.findById(id);
				id = 0;
				acc.setEndTime(sdf.parse(time));
				time = null;
				//acc.setUsedState(1);
				System.out.println("启动后时间"+sdf.format(acc.getEndTime()));
				accountService.update(acc);
				return SUCCESS;
			}
		}catch(Exception e){
			log.error("修改账户出错", e);
		}
		return SUCCESS;
	}
	public String updaterole(){
		try{
			if(type == 1){//修改
				type = 0;
				String moduleList = role.getModuleList(); //去掉前后逗号
				String[] ms = moduleList.split(",");
				StringBuffer sf = new StringBuffer();
				for(int i = 0; i < ms.length; i++){
					if(!ms[i].equals("") && ms[i] != null && !(ms[i].trim()).equals("") ){
						sf.append(ms[i]+",");
					}
				}
				role.setModuleList(sf.toString().trim());
				accountRoleService.update(role);
				role = null;
			}
			if(type == 2){//进入修改
				type = 0;
				//根据id获取该账户信息
				role = accountRoleService.findById(id);
				id = 0;
				return "in";
			}
			
		}catch(Exception e){
			log.error("修改账户出错", e);
		}
		return SUCCESS;
	}
	public String updatemodule(){
			if(type == 1){//修改
				type = 0;
				try {
				System.out.println("模块类型"+moduleTypeupdate);
				//乱码处理
				String sr = module.getModuleName();
				sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
				module.setModuleName(sr);
				
				sr = module.getModulePath();
				sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
				module.setModulePath(sr);
				
				sr = module.getRemark();
				sr=new String(sr.getBytes("iso-8859-1"),"UTF-8");
				module.setRemark(sr);
				System.out.println("更改模块");
				if(moduleTypeupdate == 1){//菜单
					module.setModuleParentID(0);
				}
				int v = moduleService.update(module);
				module = null;
				} catch (Exception e) {
					log.error("修改账户出错", e);
				}
				
			}
			if(type == 2){//进入修改
				type = 0;
				try {
					if(module != null){
						System.out.println(module.getModuleName());
					}
				
					//根据id获取该账户信息
					//module = moduleService.findById(id);
					VOUtils.writeJson(moduleService.findById(id));
					id = 0;
				} catch (Exception e2) {
					log.error("修改账户出错", e2);
				}
				return null;
			}
		return SUCCESS;
	}
	
}
