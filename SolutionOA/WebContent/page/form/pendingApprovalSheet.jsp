<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.ApprovalProcessRecord,com.solution.entity.ApprovalProcess" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagebar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dcxmlxb.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/artdialog/skins/aero.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/poshytip/src/tip-violet/tip-violet.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/module/ztree/css/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dcxmlxb.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
	var recordIDall = 0;//当前记录id
	//var recordIDApprover = 0;//申请人记录id
	//进入发起审核页面
	function approval(recordID,formID,formNumber,apptime,remark,depName,posName,name){
		//记录当前记录id
		recordIDall = recordID;
		//如果初始状态为等待修改状态为正在审核
		var status = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb?type=1&id=2&recordID='+recordID, false, null);
		//设置审核页面状态信息
		//var approvalProcessList = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb?type=4&id=2&formNumber='+formNumber + '&formID='+formID, false, null);console.info(approvalProcessList[0].approverStatus);
		//if(approvalProcessList != null){
		//	for(var i = 0;i < approvalProcessList.length;i++){
		//		approvalProcessList
		//	}
		//}
		//获取查看审核页面信息
		var dcxmlxb = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb?type=2&id=2&formNumber='+formNumber, false, null);
		var dcxmlxb = jQuery.parseJSON(dcxmlxb);
		$('#titletime').text('时间:'+dcxmlxb.createTime.split('T')[0]);
		$('#formid').text('编号:'+dcxmlxb.formNumber);
		$('#projectName').text(dcxmlxb.projectName);
		$('#projectPorperty').text(dcxmlxb.projectPorperty);
		$('#servicePrice').text(dcxmlxb.servicePrice);
		$('#staffNumber').text(dcxmlxb.staffNumber);
		$('#serviceMonths').text(dcxmlxb.serviceMonths);
		$('#singlePrice').text(dcxmlxb.singlePrice);
		$('#royaltyRate').text(dcxmlxb.royaltyRate);
		$('#callTime').text(dcxmlxb.callTime.split('T')[0] + ' ' + dcxmlxb.callTime.split('T')[1]);
		$('#intentionCustomer').text(dcxmlxb.intentionCustomer);
		$('#remarks').text(dcxmlxb.remarks);
		$('#sendEmail').text(dcxmlxb.sendEmail);
		$('#contact').text(dcxmlxb.contact);
		$('#consultant').text(dcxmlxb.consultant);
		$('#pName').text(dcxmlxb.projectName);
		//申请人
		$('#departmentName').text(depName);
		$('#positionName').text(posName);
		$('#realName').text(name);
		if(status == '1'){//未审核
			$('#finished').hide();$('#unfinish').show();
			$('#causeRefuse').val('');
			$('#causeRefuse').removeAttr('readonly');
		}
		if(status == '2'){//已审核
			$('#finished').show();$('#unfinish').hide();
			$('#causeRefuse').val(remark);
			$('#causeRefuse').attr('readonly','readonly');//readonly="readonly" 
		}
		popquestion('tab', '发起审核页面');
	}

	//审核确认
	function applyForApproval(value){
		if(value == 2){//通过
			if(!checkData()){
				return;
			}
		}

		if(value == 3){//拒绝
			if(!checkData()){
				return;
			}
		}
		
		//验证数据
		//alert('add');
		
		
		//获取备注
		var remark = $('#causeRefuse').val().replace(/^ +| +$/g, '');
		//记录审核结果
		var map = {'remark':remark,'type':3,'recordID':recordIDall,'value':value};
		var result = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb', false, map);
		window.location.reload();
	}
	//修改审核
	function updateApproval(){
		//获取备注
		var remark = $('#causeRefuse').val().replace(/^ +| +$/g, '');
		//记录审核结果
		var value = 99;
		var map = {'remark':remark,'type':3,'recordID':recordIDall,'value':value};
		var result = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb', false, map);
		window.location.reload();
	}
	
	function checkData(){
		//非空验证
    	   	
    	var flag1 = confirm('确定提交审核结果吗？');
    	if(!flag1){
    		return false;
    	}
		//数据有效性验证
		
		return true;
	}
	 function chexkwarn(elementid, content) {
	    	$('#' + elementid).poshytip( {
	    		content : content,
	    		className : 'tip-violet',
	    		alignTo : 'target',
	    		alignX : 'inner-left',
	    		alignY : 'bottom',
	    		offsetX : 0,
	    		offsetY : 5
	    	});
	    	// $("#" + elementid).focus();
	    	$('#' + elementid).poshytip('show');
	    	setTimeout(function() {
	    		$('#' + elementid).poshytip('destroy');
	    	}, 1400);

	    }
	function popquestion(elementid, titles) {
		//$('.addAccount').display('block');
		var a = art.dialog( {
			id : elementid,
			fixed : true,
			padding : 0,
			lock : true,
			opacity : 0.3,
			title : titles,
			content : document.getElementById(elementid)
		});
	}

	function quickAjaxPost(url, async, maparr) {
		var res = "";
		$.ajax({
			async : async,
			type : 'post',
			url : url,
			data : maparr,// json格式数据
			success : function(result) {
				res = result;
			},
			error : function(result) {
				res = "timeout";
			}
		});
		return res;
	}
</script>
<title>各种待审核信息</title>
</head>
<body>
<!-- list -->
<div class="tab-content default-tab" id="list">
各种待审核信息
<table style="font-size: 12px;">
	<thead>
		<tr>
		<td class="td3" style="width: 5%;">申请人部门</td>
			<td class="td3" style="width: 5%;">工单名称</td>
			<td class="td3" style="width: 5%;">工单编号</td>
			<td class="td3" style="width: 5%;">申请人职位</td>
			<td class="td3" style="width: 5%;">申请人姓名</td>
			<td class="td3" style="width: 5%;">申请时间</td>
			<td class="td3" style="width: 5%;">审核状态</td>
			<td class="td3" style="width: 5%;">审核时间</td>
			<td class="td3" style="width: 5%;">审核</td>
		</tr>
	</thead>
	<tbody>
		<%
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<ApprovalProcessRecord> list = (List<ApprovalProcessRecord>)request.getAttribute("approvalProcessRecordList");
		    if(list != null && list.size() > 0){
		       for(ApprovalProcessRecord kt:list){
		    	   Date at = kt.getApproverTime();
		    	   String attime = sdf.format(at);
		    	   Date applyTime = kt.getProposerTime();
		    	   String apptime = sdf.format(applyTime);
		%>
		<tr>
			<td><%=kt.getProposerDepartment()%></td>
			<td><%=kt.getFormName()%></td>
			<td><%=kt.getFormBornID()%></td>
			<td><%=kt.getProposerPosition()%></td>
			<td><%=kt.getProposerName()%></td>
			<td><%=apptime%></td>
			<td>
				<% 	if(kt.getApproverStatus() == 0 || kt.getApproverStatus() == 1){
				%>
					未审核
				<% }else if(kt.getApproverStatus() == 2 || kt.getApproverStatus() == 3){
				%>	
					已审核
				<%}%>	
			</td>
			<td><%=attime%></td>
			<td>
			<% 	if(kt.getApproverStatus() == 0 || kt.getApproverStatus() == 1){
				%>
					<img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="approval(<%=kt.getId()%>,<%=kt.getFormID() %>,'<%=kt.getFormBornID()%>','<%=apptime%>','<%=kt.getRemark() %>','<%=kt.getProposerDepartment()%>','<%=kt.getProposerPosition()%>','<%=kt.getProposerName()%>');" title="审核" />
				<% }else if(kt.getApproverStatus() == 2 || kt.getApproverStatus() == 3){
				%>	
					<img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="approval(<%=kt.getId()%>,<%=kt.getFormID() %>,'<%=kt.getFormBornID()%>','<%=apptime%>','<%=kt.getRemark() %>','<%=kt.getProposerDepartment()%>','<%=kt.getProposerPosition()%>','<%=kt.getProposerName()%>');" title="查询详情" />
				<%}%>	
			
			
			</td>
		</tr>
		<%	
			}
		  }else{
		%>
		<tr>
			<td colspan="7">&lt;<span id="" style="color: red;">页面没有数据</span>&gt;</td>		
		
		</tr> 
		<%	  
				
		  }
		    %>

	</tbody>
	<tfoot>
		<tr>
			<td colspan="15">
			<%
									String pagebar = (String)request.getAttribute("pageBar");
									if(pagebar != null){
										out.println(pagebar);
									}
								%>
			</td>
		</tr>
	</tfoot>

</table>
</div>


<div class = "tab" id="tab" style="display: none;width: 805px;height: 635px;" >
<form id="approval">
<table class="table_border" style="font-size: 12px;background-color: #ccc;width: 800px;height: 500px;" border="1">
	<thead>
		<tr>
			<td colspan="7" align="center" style="font-size: 16px;"><span id="pName"></span>地产项目立项表</td>
		</tr>
		<tr>
			<td colspan="7" align="right" style="font-size: 14px;"><span id = "titletime" style="text-align: right"></span></td>
		</tr>
		<tr>
			<td colspan="3" align="left"><span id="formid"></span></td>
			<td colspan="4">&nbsp;</td>
		</tr>
		
	</thead>
	<tbody>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td colspan="3" align="center">基础服务费部分</td>
            <td colspan="2" align="center">佣金部分</td>
		</tr>
        <tr>
        	<td width="72">项目名称</td>
			<td width="72">项目属性</td>
			<td width="172">基础服务费单价</td>
			<td width="172">坐席数</td>
			<td width="172">服务月数</td>
            <td width="172">单套结算单价(元/套)</td>
			<td width="172">单套房款佣金提成比例(%)</td>
        </tr>
         <tr>
        	<td><span id="projectName"></span></td>
            <td><span id="projectPorperty"></span></td>
            <td><span id="servicePrice"></span></td>
            <td><span id="staffNumber"></span></td>
            <td><span id="serviceMonths"></span></td>
            <td><span id="singlePrice"></span></td>
            <td><span id="royaltyRate"></span></td>
        </tr>
         <tr>
            <td width="72">起呼时间</td>
			<td width="172">意向客户数</td>
			<td width="172">数据筛选方向</td>
            <td width="172">意向客户发送地址:</td>
			<td width="172">联系人及电话</td>
			<td width="172">短信下发号码及联系人</td>
            <td>&nbsp;</td>
        </tr>
         <tr>
        	<td width="72"><span id="callTime"></span></td>
        	<td width="172"><span id="intentionCustomer"></span></td>
        	<td width="172"><span id="remarks"></span></td>
        	<td width="172"><span id="sendEmail"></span></td>
        	<td width="172"><span id="contact"></span></td>
        	<td width="172"><span id="consultant"></span></td>
            <td>&nbsp;</td>
        </tr>
		<tr>
			<td align="center">审核流程:</td>
			<td align="center">部门</td>
			<td align="center">职位</td>
			<td align="center">姓名</td>
			<td align="center">审核状态</td>
			<td align="center">审核时间</td>
			<td>&nbsp;</td>
		</tr>
		<%
		List<ApprovalProcess> appList = (List<ApprovalProcess>)request.getAttribute("approvalProcessList");
		if(appList != null && appList.size() > 0){
			for(int i=0;i<appList.size();i++){
				int fw = appList.get(i).getFlowNumber();
				String depName = appList.get(i).getApproverDepName();
				String posName = appList.get(i).getApproverPosition();
				String realName = appList.get(i).getApproverRealName();
				int formTypeID = appList.get(i).getFormTypeID();
				int status = appList.get(i).getApproverStatus();
				Date appTime = appList.get(i).getApproverTime();
				String setTime = "9999-12-31 00:00:00";
				if(appTime == null){
					appTime = sdf.parse(setTime);
				}
				String aTime = sdf.format(appTime);
		%>
		<tr>
			<td align="center"><%=fw%></td>
			<td align="center"><%=depName%></td>
			<td align="center"><%=posName%></td>
			<td align="center"><%=realName%></td>
			<td align="center">
			<%
				if(fw == 1){
					if(status == 0 || status == 1){
			%>
						正在审核
			<%
					}
					if(status == 2){
			%>
						通过
			<%
					}
					
					if(status == 3){
			%>
						拒绝
			<%	
					}
				}else{
					if(status == 0){
			%>
						等待
			<%
					}
					if(status == 1){
			%>
						正在审核						
			<%
					}
					if(status == 2){
			%>
						通过
			<%
					}
					if(status == 3){
			%>
						拒绝
			<%	
					}
				}
			%>
			</td>
			<td align="center"><%=aTime %></td>
			<td>&nbsp;</td>
		</tr>
		<%	
		}
			}
		%>
		<tr>
			<td align="center">申请人:</td>
			<td align="center"><span id="departmentName"></span></td>
			<td align="center"><span id="positionName"></span></td>
			<td align="center"><span id="realName"></span></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td align="center">备注:</td>
			<td colspan="6"><textarea name="remark" readonly="readonly" cols="90" rows="7" id="causeRefuse"></textarea>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr id="finished">
			<td colspan="7" align="center">
			</td>
		</tr>
		<tr id="unfinish">
			<td colspan="7" align="center">
				<input type="button" value="通过" onclick="applyForApproval(2);" style="border: none;background-color:#ccc;font-size:14px; border-bottom-style:dashed;  height: 25px;font-weight: bolder"/>
				<input type="button" value="拒绝" onclick="applyForApproval(3);" style="border: none;background-color:#ccc;font-size:14px; border-bottom-style:dashed;  height: 25px;font-weight: bolder"/>
			</td>
		</tr>
	</tfoot>

</table>
	<input type="hidden" name="dcxmlxb.loginName" id="loginNameadd" value="" />
	<input type="hidden" name="dcxmlxb.formNumber" id="formNumberadd" value="" />
	<input type="hidden" name="approvalProcessRecord.formTypeID" id="formTypeIDadd" value="" />
	
</form>
</div>
</body>
</html>