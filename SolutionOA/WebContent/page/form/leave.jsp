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
<script type="text/javascript"><!--
	$(function() {
		//获取数据
		
		//更改页面状态
		
		//判断审核状态
		
	});
	//进入发起审核页面
	function add(){
		var result = quickAjaxPost(sybp() + '/dcxmlxb/godcxmlxb?type=2', false, null);console.info(result);
		$('#titletime').text('时间:'+result.split(',')[0]);
		$('#formid').text('编号:' + result.split(',')[1]);
		$('#formTypeIDadd').val(result.split(',')[2]);
		popquestion('tab', '发起审核页面');
	}

	//申请审核,提交数据
	function applyForApproval(){
		//验证数据
		//alert('add');
		if(!checkData()){
			return;
		}
		//设置隐藏域
		var result = quickAjaxPost(sybp() + '/dcxmlxb/godcxmlxb?type=2', false, null);
		$('#formNumberadd').val(result.split(',')[1]);
		//提交
		$('#add').attr('method','post');
		$('#add').attr('action',sybp() + '/dcxmlxb/adddcxmlxb');
		$('#add').submit();
	}

	//查询工单详情
	function lookForm(id){//id为申请审核记录id
		var result = quickAjaxPost(sybp() + '/dcxmlxb/godcxmlxb?type=3&id='+id, false, null);
		result = jQuery.parseJSON(result);
		//赋值页面
		$('#titletime1').text('时间:'+result.createTime.split('T')[0] + ' ' + result.createTime.split('T')[1]);
		$('#formid1').text('编号:' + result.formNumber);
		//$('#formTypeIDadd').val(result.split(',')[2]);
		$('#projectName1').val(result.projectName);
		$('#projectPorperty1').val(result.projectPorperty);
		$('#servicePrice1').val(result.servicePrice);
		$('#staffNumber1').val(result.staffNumber);
		$('#serviceMonths1').val(result.serviceMonths);
		$('#singlePrice1').val(result.singlePrice);
		$('#royaltyRate1').val(result.royaltyRate);
		$('#callTime1').val(result.callTime.split('T')[0] + ' ' + result.callTime.split('T')[1]);
		$('#intentionCustomer1').val(result.intentionCustomer);
		$('#remarks1').val(result.remarks);
		$('#sendEmail1').val(result.sendEmail);
		$('#contact1').val(result.contact);
		$('#consultant1').val(result.consultant);
		popquestion('look', '修改发起审核工单');
	}
	
	function checkData(){
		//非空验证
    	if ($('#projectName').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('projectName', '项目名称不能为空!');
    		return false;
    	}
    	if ($('#projectPorperty').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('projectPorperty', '项目属性不能为空!');
    		return false;
    	}
    	if ($('#servicePrice').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('servicePrice', '基础服务费单价不能为空!');
    		return false;
    	}
    	
    	var flag1 = confirm('确定提交申请审核吗？');
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
<title>恒大雅苑  地产项目立项表</title>
</head>
<body>

<!-- list -->
<div class="tab-content default-tab" id="list"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="发起申请" />发起审核</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 5%;">工单编号</td>
			<td class="td3" style="width: 5%;">工单名称</td>
			<td class="td3" style="width: 8%;">工单类型编号</td>
			<td class="td3" style="width: 5%;">流程编号</td>
			<td class="td3" style="width: 5%;">审核人职位</td>
			<td class="td3" style="width: 5%;">审核人账户</td>
			<td class="td3" style="width: 5%;">审核人姓名</td>
			<td class="td3" style="width: 5%;">审核状态</td>
			<td class="td3" style="width: 5%;">审核时间</td>
			<td class="td3" style="width: 10%;">备注</td>
			<td class="td3" style="width: 5%;">查看工单详情</td>
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
		%>
		<tr>
			<td><%=kt.getFormID()%></td>
			<td><%=kt.getFormName()%></td>
			<td><%=kt.getFormTypeID()%></td>
			<td><%=kt.getFlowNumber()%></td>
			<td><%=kt.getApproverPosition()%></td>
			<td><%=kt.getApproverAccount()%></td>
			<td><%=kt.getApproverRealName()%></td>
			<td>
				<% 	if(kt.getApproverStatus() == 0){
				%>
					等待
				<% }else if(kt.getApproverStatus() == 1){
				%>
					正在审核
				<% }else if(kt.getApproverStatus() == 2){
				%>	
					通过
				<% }else if(kt.getApproverStatus() == 3){
					%>
					 拒绝
				<%}%>	
			</td>
			<td><%=attime%></td>
			<td><%=kt.getRemark()%></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="lookForm(<%=kt.getId()%>);" title="查看工单详情" /></td>
		</tr>
		<%	
			}
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


<div class = "tab" id="tab" style="display: none;">
<form id="add">
<table class="table_border" style="font-size: 12px;background-color: #ccc;" border="1">
	<thead>
		<tr>
			<td colspan="7" align="center" style="font-size: 16px;">恒大雅苑  地产项目立项表</td>
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
        	<td>项目名称</td>
			<td>项目属性</td>
			<td width="172">基础服务费单价</td>
			<td width="172">坐席数</td>
			<td width="172">服务月数</td>
            <td width="172">单套结算单价(元/套)</td>
			<td width="172">单套房款佣金提成比例(%)</td>
        </tr>
         <tr>
        	<td><input type="text" align="middle" name="dcxmlxb.projectName" size="18" id="projectName" value="" style="border:thin 1px black;height: 20px;"/></td>
            <td><input type="text" align="middle" name="dcxmlxb.projectPorperty" size="18" id="projectPorperty" value="" style="border:thin 1px black;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.servicePrice" size="18" id="servicePrice" value="" style="border:thin 1px black;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.staffNumber" size="18" id="staffNumber" value="" style="border:thin 1px black;height: 20px;"/></td>
            <td><input type="text" align="middle" name="dcxmlxb.serviceMonths" size="18" id="serviceMonths" value="" style="border:thin 1px black;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.singlePrice" size="18" id="singlePrice" value="" style="border:thin 1px black;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.royaltyRate" size="18" id="royaltyRate" value="" style="border:thin 1px black;height: 20px;" /></td>
        </tr>
         <tr>
            <td width="172">起呼时间</td>
			<td width="172">意向客户数</td>
			<td width="172">数据筛选方向</td>
            <td width="172">意向客户发送地址:</td>
			<td width="172">联系人及电话</td>
			<td width="172">短信下发号码及联系人</td>
            <td>&nbsp;</td>
        </tr>
         <tr>
        	<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.callTime" id="callTime" value="" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="border:thin 1px black;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.intentionCustomer" id="intentionCustomer" value="" style="border:thin 1px black;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.remarks" id="remarks" value="" style="border:thin 1px black;height: 20px;"/></td>
            <td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.sendEmail" id="sendEmail" value="" style="border:thin 1px black;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.contact" id="contact" value="" style="border:thin 1px black;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.consultant" id="consultant" value="" style="border:thin 1px black;height: 20px;"/></td>
            <td>&nbsp;</td>
        </tr>
		<tr>
			<td align="center">审核流程:</td>
			<td align="center">部门</td>
			<td align="center">职位</td>
			<td align="center">姓名</td>
			<td colspan="3">&nbsp;</td>
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
		%>
		<tr>
			<td align="center"><%=fw%></td>
			<td align="center"><%=depName%></td>
			<td align="center"><%=posName%></td>
			<td align="center"><%=realName%></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<%	
		}
			}
		%>
		<tr>
			<td align="center">申请人:</td>
			<td align="center">${accountInfo.departmentName } </td>
			<td align="center">${accountInfo.positionName } </td>
			<td align="center">${accountInfo.realName } </td>
			<td colspan="3">&nbsp;</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="13" align="center">
				<input type="button" value="申请审核" onclick="applyForApproval();" style="border: none;background-color:#ccc;font-size:14px; border-bottom-style:dashed;  height: 25px;font-weight: bolder"/>
			</td>
		</tr>
	</tfoot>

</table>
	<input type="hidden" name="dcxmlxb.loginName" id="loginNameadd" value="${accountInfo.loginName }" />
	<input type="hidden" name="dcxmlxb.formNumber" id="formNumberadd" value="" />
	<input type="hidden" name="approvalProcessRecord.formTypeID" id="formTypeIDadd" value="" />
	
</form>
</div>


<div class = "tab" id="look" style="display: none;">
<form id="lookform">
<table class="table_border" style="font-size: 12px;background-color: #ccc;" border="1">
	<thead>
		<tr>
			<td colspan="7" align="center" style="font-size: 16px;">恒大雅苑  地产项目立项表</td>
		</tr>
		<tr>
			<td colspan="7" align="right" style="font-size: 14px;"><span id = "titletime1" style="text-align: right"></span></td>
		</tr>
		<tr>
			<td colspan="3" align="left"><span id="formid1"></span></td>
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
        	<td>项目名称</td>
			<td>项目属性</td>
			<td width="172">基础服务费单价</td>
			<td width="172">坐席数</td>
			<td width="172">服务月数</td>
            <td width="172">单套结算单价(元/套)</td>
			<td width="172">单套房款佣金提成比例(%)</td>
        </tr>
         <tr>
        	<td><input type="text" align="middle" name="dcxmlxb.projectName" size="18" id="projectName1" disabled="disabled" value="" style="border:none;background-color:#ccc;height: 20px;"/></td>
            <td><input type="text" align="middle" name="dcxmlxb.projectPorperty" size="18" id="projectPorperty1" disabled="disabled" value="" style="border:none;background-color:#ccc;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.servicePrice" size="18" id="servicePrice1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.staffNumber" size="18" id="staffNumber1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;"/></td>
            <td><input type="text" align="middle" name="dcxmlxb.serviceMonths" size="18" id="serviceMonths1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.singlePrice" size="18" id="singlePrice1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;" /></td>
            <td><input type="text" align="middle" name="dcxmlxb.royaltyRate" size="18" id="royaltyRate1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;" /></td>
        </tr>
         <tr>
            <td width="172">起呼时间</td>
			<td width="172">意向客户数</td>
			<td width="172">数据筛选方向</td>
            <td width="172">意向客户发送地址:</td>
			<td width="172">联系人及电话</td>
			<td width="172">短信下发号码及联系人</td>
            <td>&nbsp;</td>
        </tr>
         <tr>
        	<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.callTime" id="callTime1" value="" disabled="disabled" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="border:none;background-color:#ccc;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.intentionCustomer" id="intentionCustomer1" disabled="disabled" value="" style="border:none;background-color:#ccc;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.remarks" id="remarks1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;"/></td>
            <td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.sendEmail" id="sendEmail1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.contact" id="contact1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;"/></td>
			<td width="172"><input type="text" size="18" align="middle" name="dcxmlxb.consultant" id="consultant1" value="" disabled="disabled" style="border:none;background-color:#ccc;height: 20px;"/></td>
            <td>&nbsp;</td>
        </tr>
		<tr>
			<td align="center">审核流程:</td>
			<td align="center">部门</td>
			<td align="center">职位</td>
			<td align="center">姓名</td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<%
		List<ApprovalProcess> appList1 = (List<ApprovalProcess>)request.getAttribute("approvalProcessList");
		if(appList1 != null && appList1.size() > 0){
			for(int i=0;i<appList1.size();i++){
				int fw = appList1.get(i).getFlowNumber();
				String depName = appList1.get(i).getApproverDepName();
				String posName = appList1.get(i).getApproverPosition();
				String realName = appList1.get(i).getApproverRealName();
				int formTypeID = appList1.get(i).getFormTypeID();
		%>
		<tr>
			<td align="center"><%=fw%></td>
			<td align="center"><%=depName%></td>
			<td align="center"><%=posName%></td>
			<td align="center"><%=realName%></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<%	
		}
			}
		%>
		<tr>
			<td align="center">申请人:</td>
			<td align="center">${accountInfo.departmentName } </td>
			<td align="center">${accountInfo.positionName } </td>
			<td align="center">${accountInfo.realName } </td>
			<td colspan="3">&nbsp;</td>
		</tr>
	</tbody>
	<tfoot>
	</tfoot>

</table>
	<input type="hidden" name="dcxmlxb.loginName" id="loginNameadd1" value="${accountInfo.loginName }" />
	<input type="hidden" name="dcxmlxb.formNumber" id="formNumberadd1" value="" />
	<input type="hidden" name="approvalProcessRecord.formTypeID" id="formTypeIDadd1" value="" />
	
</form>
</div>
</body>
</html>