package com.solution.action;
/**
 * 请假工单action
 * @author yue
 *
 */
public class VacateAction extends BaseAction{
	private static final long	serialVersionUID	= -1396255730708854912L;

	public String vacateApply(){
		try{
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return ERROR;
	}
}
