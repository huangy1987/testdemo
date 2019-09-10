<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.ApprovalProcessRecord,com.solution.entity.ApprovalProcess,com.solution.entity.Fdclsxmlxb" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
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
	//进入发起审核页面
	function add(){
		//清空上次未发起审核填写的残留数据项
		$('#projectName').val('');
		$('#servicePrice').val('');
		$('#staffNumber').val('');
		$('#serviceMonths').val('');
		$('#serviceRental').val('');
		$('#singlePrice').val('');
		$('#royaltyRate').val('');
		$('#executiveTime').val('');
		$('#pNameadd').text('');
		//获取基础数据
		var result = quickAjaxPost(sybp() + '/fdclsxmlxb/gofdclsxmlxb?type=2', false, null);
		$('#titletime').text('时间:'+result.split(',')[0]);
		$('#formid').text('编号:' + result.split(',')[1]);
		//$('#formTypeIDadd').val(result.split(',')[2]);
		popquestion('tab', '发起审核页面');
	}

	//申请审核,提交数据
	function applyForApproval(){
		//验证数据
		if(!checkData()){
			return;
		}
		//var map = {'id':$('#formTypeIDadd').val()};
		//设置隐藏域
		var result = quickAjaxPost(sybp() + '/fdclsxmlxb/gofdclsxmlxb?type=2', false, null);
		$('#formNumberadd').val(result.split(',')[1]);
		//提交
		$('#add').attr('method','post');
		$('#add').attr('action',sybp() + '/fdclsxmlxb/addfdclsxmlxb');
		$('#add').submit();
	}
	//查询工单详情
	function lookForm(id,fw,formID,formTypeID){//id为申请审核记录id
		for(var i=0;i<10;i++){
			//清空数据
			$('#tr'+i).remove();
			$('#tr'+i+''+i).remove();
		}
		//$('#formid1').val('');
		//$('#formid1').val(formID);
		
		//获取工单流程中的审核记录
		var result = quickAjaxPost(sybp() + '/fdclsxmlxb/findApproval?id='+formTypeID +'&formID='+formID, false, null);
		//console.info(result);
		result = jQuery.parseJSON(result);
		for(var i = 0;i < result.length;i++){
			//页面追加tr
			var table1 = $('#tablook'); 
			var firstTr = table1.find('tbody>tr:last'); 
			var row = $("<tr id='tr" + i + "'></tr>"); 
			var td = $("<td align='center'></td>"); 
			td.append("<span id='fw" + i + "'>" + result[i].flowNumber + "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("<span id='depName" + i + "'>" + result[i].approverDepName + "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("<span id='posName" + i + "'>" + result[i].approverPosition + "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("<span id='realName" + i + "'>" + result[i].approverRealName +  "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
			//判断状态	
			var st = result[i].approverStatus;
			if(st == 0){
				td.append("<span id='status" + i +  "'>等待</span>");
			}
			if(st == 1){
				td.append("<span id='status" + i +  "'>正在审核</span>");
			}
			if(st == 2){
				td.append("<span id='status" + i +  "'>通过</span>");
			}
			if(st == 3){
				td.append("<span id='status" + i +  "'>拒绝</span>");
			}
			row.append(td);
				td = $("<td align='center'></td>");
				//如果未审核时间为空
				if(st == 2 || st == 3){
					td.append("<span id='aTime" + i + "'>" + result[i].approverTime.split('T')[0] + " " + result[i].approverTime.split('T')[1] +  "</span>");
				}
			row.append(td);
			table1.append(row); 
			row = $("<tr id='tr" +　i　+ "" + i + "'></tr>"); 
				td = $("<td align='center'></td>");
				td.append("备注");
			row.append(td); 
				td = $("<td colspan='6'></td>");
				td.append("<textarea readonly='readonly' name='remark" + i + "' id='remark" + i + "' cols='90' rows='2' >" +result[i].remark +"</textarea>");
			row.append(td); 
			table1.append(row); 
		}
		
		//获取工单详细数据
		result = quickAjaxPost(sybp() + '/fdclsxmlxb/gofdclsxmlxb?type=3&id='+id, false, null);
		//console.info(result);
		result = jQuery.parseJSON(result);
		//赋值页面
		$('#titletime1').text('时间:'+result.createTime.split('T')[0] + ' ' + result.createTime.split('T')[1].split('.')[0]);
		$('#formid1').text('编号:' + result.formNumber);
		//$('#formTypeIDadd').val(result.split(',')[2]);
		$('#projectName1').text(result.projectName);
		$('#servicePrice1').text(result.servicePrice);
		$('#staffNumber1').text(result.staffNumber);
		$('#serviceMonths1').text(result.serviceMonths);
		$('#singlePrice1').text(result.singlePrice);
		$('#royaltyRate1').text(result.royaltyRate);
		$('#serviceRental1').text(result.serviceRental);
		$('#executiveTime1').text(result.executiveTime);
		$('#pName').text(result.projectName);
		
		popquestion('look', '修改发起审核工单');
	}
	
	function checkData(){
		//非空验证
    	if ($('#projectName').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('projectName', '项目名称不能为空!');
    		return false;
    	}
    	if ($('#servicePrice').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('servicePrice', '基础服务费单价不能为空!');
    		return false;
    	}
    	if ($('#staffNumber').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('staffNumber', '坐席服务数不能为空!');
    		return false;
    	}
    	if ($('#serviceMonths').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('serviceMonths', '服务月数不能为空!');
    		return false;
    	}
    	if ($('#serviceRental').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('serviceRental', '服务费总额不能为空!');
    		return false;
    	}
    	if ($('#singlePrice').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('singlePrice', '单位结算单价不能为空!');
    		return false;
    	}
    	if ($('#royaltyRate').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('royaltyRate', '单位佣金提成比例不能为空!');
    		return false;
    	}
    	if ($('#executiveTime').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('executiveTime', '项目执行时间不能为空!');
    		return false;
    	}
    	var flag1 = confirm('确定提交申请审核吗？');
    	if(!flag1){
    		return false;
    	}
		//数据有效性验证
		
		return true;
	}
	//动态改变题目
	function getProjectName(value){
		$('#pNameadd').text('');
		$('#pNameadd').text(value);
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
<title>临时项目立项表</title>
</head>
<body>

<!-- list -->
<div class="tab-content default-tab" id="list"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="发起申请" />发起工单</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 5%;">工单编号</td>
			<td class="td3" style="width: 5%;">项目名称</td>
			<td class="td3" style="width: 5%;">申请时间</td>
			<td class="td3" style="width: 5%;">审核状态</td>
			<td class="td3" style="width: 5%;">查看工单详情</td>
		</tr>
	</thead>
	<tbody>
		<%
			String formNumberALL = "";//记录当前选中的工单
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Fdclsxmlxb> fdclist = (List<Fdclsxmlxb>)request.getAttribute("fdclsxmlxbList");
			List<ApprovalProcessRecord> list = (List<ApprovalProcessRecord>)request.getAttribute("approvalProcessRecordList");
			
			//定义变量
		    String pName = "";			//项目名称
		    String pp = "";				//项目属性
		    String formNumber = "";		//工单编号
		    String proposerTime = "";	//申请时间
		    if(fdclist != null && fdclist.size() > 0){
		    	 for(Fdclsxmlxb fdclsxmlxb:fdclist){
		    		if(list != null && list.size() > 0  ){
		    			 //设置当前流程编号
		    			 int currentFW = 0;
		    			 for(ApprovalProcessRecord kt:list){
		    				if(kt.getFormID() == fdclsxmlxb.getId()){
					    	 	if( kt.getApproverStatus() == 3){
									currentFW = kt.getFlowNumber();break;
								}
								if( kt.getApproverStatus() == 1){
									currentFW = kt.getFlowNumber();break;
								}
								if( kt.getApproverStatus() == 0){
									currentFW = kt.getFlowNumber();
									for(ApprovalProcessRecord approvalProcessRecord1 : list){
										if(approvalProcessRecord1.getApproverStatus() == 3){
											currentFW = approvalProcessRecord1.getFlowNumber();break;
										}
										if(approvalProcessRecord1.getApproverStatus() == 1){
											currentFW = approvalProcessRecord1.getFlowNumber();break;
										}
									}
									break;
								}
								if( kt.getApproverStatus() == 2){
									currentFW = kt.getFlowNumber();
									for(ApprovalProcessRecord approvalProcessRecord1 : list){
										if(approvalProcessRecord1.getApproverStatus() == 3){
											currentFW = approvalProcessRecord1.getFlowNumber();break;
										}
										if(approvalProcessRecord1.getApproverStatus() == 1){
											currentFW = approvalProcessRecord1.getFlowNumber();break;
										}
									}
									break;
								}	
		    				 }
		    			 }
		    			//设置数据项
		    			 for(ApprovalProcessRecord kt:list){
							if( kt.getFlowNumber() == currentFW && fdclsxmlxb.getId() == kt.getFormID()){
			    				  	 formNumberALL = fdclsxmlxb.getFormNumber();
			    			 		 pName = fdclsxmlxb.getProjectName();
			    			  		 formNumber = fdclsxmlxb.getFormNumber();
			    			 		 proposerTime = sdf.format(fdclsxmlxb.getCreateTime());
			    			 		 int status = kt.getApproverStatus();
			    	  %>
		<tr>
			<td><%=formNumber %></td>
			<td><%=pName%></td>
			<td><%=proposerTime%></td>
			<td>
				<% 
					if(status == 0 || status == 1){
					%>
							正在审核
					<% }else if(status == 2){
					%>	
							通过
					<% }else if(status == 3){
					%>
						          拒绝
					<%	}
					%>
			</td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="lookForm(<%=kt.getId()%>,<%=kt.getFlowNumber() %>,<%=kt.getFormID()%>,<%=kt.getFormTypeID() %>);" title="查看工单详情" /></td>
		</tr>
							<%   	
								break;		} 
												}  
		    									   }
														}
		    	 											}
							%>

	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
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


<div class = "tab" id="tab" style="display: none;">
<form id="add">
<table class="table_border" style="font-size: 12px;background-color: #ccc;" border="1">
	<thead>
		<tr>
			<td colspan="5" align="center" style="font-size: 16px;"><span id="pNameadd"></span>临时项目立项表</td>
		</tr>
		<tr>
			<td colspan="5" align="right" style="font-size: 14px;"><span id = "titletime" style="text-align: right"></span></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="left"><span id="formid"></span></td>
			<td colspan="4" align="center">基础服务费部分</td>
            
		</tr>
        <tr>
        
        	<td>项目名称</td>
			<td>服务费单价</td>
			<td>坐席服务数</td>
			<td>服务月数</td>
            <td>服务费总额</td>
        </tr>
         <tr>
        	<td><input type="text" align="middle" name="lsxmlxb.projectName" size="18" id="projectName" value="" onchange="getProjectName(this.value)" style="border:thin 1px black;height: 20px;"/></td>
            <td><input type="text" align="middle" name="lsxmlxb.servicePrice" size="18" id="servicePrice" value="" style="border:thin 1px black;height: 20px;" /></td>
            <td><input type="text" align="middle" name="lsxmlxb.staffNumber" size="18" id="staffNumber" value="" style="border:thin 1px black;height: 20px;"/></td>
            <td><input type="text" align="middle" name="lsxmlxb.serviceMonths" size="18" id="serviceMonths" value="" style="border:thin 1px black;height: 20px;" /></td>
            <td><input type="text" align="middle" name="lsxmlxb.serviceRental" size="18" id="serviceRental" value="" style="border:thin 1px black;height: 20px;" /></td>
        </tr>
         <tr>
        	<td colspan="2" align="center">佣金部分</td>
        	<td colspan="3">&nbsp;</td>
        </tr>
         <tr>
            <td>单位结算单价（元/套 单位）</td>
			<td>单位佣金提成比例（%）</td>
			<td width="172">项目执行时间</td>
            <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
			<td width="172"><input type="text" size="18" align="middle" name="lsxmlxb.singlePrice" id="singlePrice" value="" style="border:thin 1px black;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="lsxmlxb.royaltyRate" id="royaltyRate" value="" style="border:thin 1px black;height: 20px;"/></td>
            <td width="172"><input type="text" size="18" align="middle" name="lsxmlxb.executiveTime" id="executiveTime" value="" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="border:thin 1px black;height: 20px;"/></td>
            <td colspan="2">&nbsp;</td>
        </tr>
		<tr>
			<td align="center">审核流程:</td>
			<td align="center">部门</td>
			<td align="center">职位</td>
			<td align="center">姓名</td>
			<td>&nbsp;</td>
		</tr>
		<%
		List<ApprovalProcess> appList = (List<ApprovalProcess>)request.getAttribute("approvalProcessList");
		int formTypeID = 0; 
		if(appList != null && appList.size() > 0){
			for(int i=0;i<appList.size();i++){
				int fw = appList.get(i).getFlowNumber();
				String depName = appList.get(i).getApproverDepName();
				String posName = appList.get(i).getApproverPosition();
				String realName = appList.get(i).getApproverRealName();
				formTypeID = appList.get(i).getFormTypeID();
		%>
		<tr>
			<td align="center"><%=fw%></td>
			<td align="center"><%=depName%></td>
			<td align="center"><%=posName%></td>
			<td align="center"><%=realName%></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<%	
		}%>
		<tr>
			<td align="center">申请人:</td>
			<td align="center">${accountInfo.departmentName } </td>
			<td align="center">${accountInfo.positionName } </td>
			<td align="center">${accountInfo.realName } </td>
			<td>&nbsp;<input type="hidden" name="approvalProcessRecord.formTypeID" id="formTypeIDadd" value="<%=formTypeID%>" /></td>
		</tr>
		<%	}
		%>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5" align="center">
				<input type="button" value="申请审核" onclick="applyForApproval();" style="border: none;background-color:#ccc;font-size:14px; border-bottom-style:dashed;  height: 25px;font-weight: bolder"/>
			</td>
		</tr>
	</tfoot>

</table>
	<input type="hidden" name="lsxmlxb.loginName" id="loginNameadd" value="${accountInfo.loginName }" />
	<input type="hidden" name="lsxmlxb.formNumber" id="formNumberadd" value="" />
</form>
</div>


<div class = "tab" id="look" style="display: none;height: 600px;width: 920px;">
<form id="lookform">
<table class="table_border" id="tablook" style="font-size: 12px;background-color: #ccc;height: 600px;width: 920px;" border="1">
	<thead>
		<tr>
			<td colspan="6" align="center" style="font-size: 16px;"><span id="pName"></span>临时项目立项表</td>
		</tr>
		<tr>
			<td colspan="6" align="right" style="font-size: 14px;"><span id = "titletime1" style="text-align: right"></span></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="left" ><span id="formid1"></span></td>
			<td colspan="5" align="center">基础服务费部分</td>
		</tr>
		<tr style="height: 2%;">
			<td align="center">项目名称</td>
			<td align="center">服务费单价</td>
			<td align="center">坐席服务数</td>
			<td align="center">服务月数</td>
			<td align="center">服务费总额</td>
			<td>&nbsp;</td>
		</tr>
		<tr style="height: 2%;">
			<td style="width:6%;" align="center"><span id="projectName1"></span></td>
			<td style="width:5%;" align="center"><span id="servicePrice1"></span></td>
			<td style="width:5%;" align="center"><span id="staffNumber1"></span></td>
			<td style="width:5%;" align="center"><span id="serviceMonths1"></span></td>
			<td style="width:5%;" align="center"><span id="serviceRental1"></span></td>
			<td style="width:5%;" align="center">&nbsp;</td>
		</tr>
        <tr style="height: 2%;">
        	<td colspan="2" align="center">佣金部分</td>
			<td colspan="4">&nbsp;</td>
        </tr>
         <tr style="height: 2%;">
            <td align="center">单位结算单价（元/套 单位）</td>
			<td align="center">单位佣金提成比例（%）</td>
            <td align="center">项目执行时间</td>
            <td colspan="3">&nbsp;</td>
        </tr>
         <tr style="height: 2%;">
            <td align="center"><span id="singlePrice1"></span></td>
			<td align="center"><span id="royaltyRate1"></span></td>
            <td align="center"><span id="executiveTime1"></span></td>
            <td align="center" colspan="3">&nbsp;</td>
        </tr>
        <tr style="height: 2%;">
			<td align="center">申请人:</td>
			<td align="center">${accountInfo.departmentName } </td>
			<td align="center">${accountInfo.positionName } </td>
			<td align="center">${accountInfo.realName } </td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr style="height: 2%;">
			<td align="center">审核流程:</td>
			<td align="center">部门</td>
			<td align="center">职位</td>
			<td align="center">姓名</td>
			<td align="center">审核状态</td>
			<td align="center">审核时间</td>
		</tr>
	</tbody>
	<tfoot>
	</tfoot>

</table>
	
	
	
</form>
</div>
</body>
</html>