package com.solution.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.solution.entity.AccountRole;
import com.solution.entity.Module;
import com.solution.service.ModuleService;
import com.solution.tools.VOUtils;

public class FindModuleByRoleAction extends BaseAction {
	private static final long serialVersionUID = 304369458848291231L;
	private ModuleService moduleService;
	
	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	public String FindModuleByRole() throws IOException{
		System.out.println("FindModuleByRole");
		AccountRole role = (AccountRole) ActionContext.getContext().getSession().get("role");
		String moduleStr = role.getModuleList();
		System.out.println(moduleStr);
		List<Module> moduleList = new ArrayList<Module> ();
		Module module = null;String str1 ="";
		System.out.println(moduleStr.contains(","));
		if(moduleStr.contains(",")){
			String[] str = moduleStr.split(",");
			System.out.println("进入"+str.length+"个模块");
			for (int k = 0; k < str.length; k++) { 
				 int index = Integer.parseInt(str[k]);
				 module = moduleService.findById(index);
				 if(module != null){
					 moduleList.add(module);
						str1 = str1 + module.getModuleName() +",";
				 }
			}
			 System.out.println(str1);
		}else{
			System.out.println("进入1个模块");
			module = moduleService.findById(Integer.parseInt(moduleStr));
			 System.out.println(module.getModuleName());
			 moduleList.add(module);
		}
		VOUtils.writeJson(moduleList);
		return null;
	}
	
}
