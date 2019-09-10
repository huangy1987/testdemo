<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.ApprovalProcess" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagebar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/artdialog/skins/aero.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/poshytip/src/tip-violet/tip-violet.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/module/ztree/css/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/approvalProcess.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<title>工单审核流程配置</title>
</head>

<body>

<!-- list -->
<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="新增" />添加工单审核流程</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 8%;">工单所属部门</td>
			<td class="td3" style="width: 8%;">工单名称</td>
		    <td class="td3" style="width: 8%;">流程编号</td>
			<td class="td3" style="width: 12%;">审核人职位</td>
			<td class="td3" style="width: 12%;">审核人账户</td>
			<td class="td3" style="width: 12%;">审核人姓名</td>
			<td class="td3" style="width: 12%;">流转约束</td>
			<td class="td3" style="width: 12%;">创建时间</td>
			<td class="td3" style="width: 12%;">结束时间</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<ApprovalProcess> list = (List<ApprovalProcess>)request.getAttribute("approvalProcessList");
			int sum = list.size();
		    if(list != null && list.size() > 0){
		       for(ApprovalProcess kt:list){
		    	   Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		%>
		<tr>
			<td><%=kt.getDepartmentName()%></td>
			<td><%=kt.getFormName()%></td>
			<td><%=kt.getFlowNumber()%></td>
			<td><%=kt.getApproverPosition() %></td>
			<td><%=kt.getApproverAccount() %></td>
			<td><%=kt.getApproverRealName() %></td>
			<td><%=kt.getConstraints() %></td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="update(<%=kt.getId()%>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/config/deleteapprovalProcess?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要删除该工单审核流程吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
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
<!-- add -->
<div id="add" style="height: 350px; width: 550px; display: none;" >
<form id="addForm">
<table width="450" height="100" border="0" cellpadding="0" cellspacing="0">
	
	<tr>
		<td width="80" height="10" align="right">部门名称</td>
		<td width="215" height="10"> 
			<select name="departmentID" id="departmentIDadd" onchange="setFormType(this.value);">
			</select><span style="color: red;">*</span>
		</td>
	</tr>
	
	<tr>
		<td width="80" height="10" align="right">工单名称</td>
		<td width="215" height="10"> 
			<select name="approvalProcess.formTypeID" id="formTypeIDadd">
				<option>请选择</option>
			</select><span style="color: red;">*</span>
		</td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">流程编号</td>
		<td width="315" height="10" >
			<select name="approvalProcess.flowNumber" id="flowNumberadd" onchange="checkValue(this.value);">
				<option value="0">请选择</option>
				<option value="1">一级流程</option>
				<option value="2">二级流程</option>
				<option value="3">三级流程</option>
				<option value="4">四级流程</option>
				<option value="5">五级流程</option>
				<option value="6">六级流程</option>
				<option value="7">七级流程</option>
				<option value="8">八级流程</option>
				<option value="9">九级流程</option>
				<option value="10">十级流程</option>
			</select><span style="color: red;">*</span>
	    </td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">审核人职位</td>
		<td width="215" height="10"> 
		    <input readonly="readonly" name="approvalProcess.approverPosition" id="approverPositionadd" value=""/>
		</td>
	</tr>
	
	<tr>
		<td width="80" height="10" align="right">审核人账户</td>
		<td width="215" height="10">
		 <input type="text" readonly="readonly" style="color: #294B7C;" name="approvalProcess.approverAccount" id="approverAccountadd"></td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">审核人姓名</td>
		<td width="215" height="10"> <input type="text" readonly="readonly" style="color: #294B7C;" name="approvalProcess.approverRealName" id="approverRealNameadd"></td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">流转约束</td>
		<td width="215" height="10"> <input type="text" name="approvalProcess.constraints" id="constraintsadd"></td>
	</tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;
<input value="保存" type="button" onclick="checkData();"/>
</form>
<div id="treeadd" style="border: 5px solid gray;width: 200px;height: 300px;float:left; overflow: auto;margin-left: 280px;margin-top: -180px;">
请选择审核人
<ul id="ztreeadd" class="ztree" style="width: 150px; height: 450px; hoverflow: auto;"></ul>
</div>
</div>
<!-- update -->
<div id="update" style="height: 350px; width: 550px; display: none;" >
<form id="updateForm">
<table width="450" height="100" border="0" cellpadding="0" cellspacing="0">
	
	<tr>
		<td width="80" height="10" align="right">部门名称</td>
		<td width="215" height="10"> 
			<select name="departmentIDupdate" id="departmentIDupdate" onchange="setFormTypeupdate(this.value);">
			</select><span style="color: red;">*</span>
		</td>
	</tr>
	
	<tr>
		<td width="80" height="10" align="right">工单名称</td>
		<td width="215" height="10"> 
			<select name="approvalProcess.formTypeID" id="formTypeIDupdate">
				<option>请选择</option>
			</select><span style="color: red;">*</span>
		</td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">流程编号</td>
		<td width="315" height="10" >
			<select name="approvalProcess.flowNumber" id="flowNumberupdate" onchange="checkValueupdate(this.value);">
				<option value="0">请选择</option>
				<option value="1">一级流程</option>
				<option value="2">二级流程</option>
				<option value="3">三级流程</option>
				<option value="4">四级流程</option>
				<option value="5">五级流程</option>
				<option value="6">六级流程</option>
				<option value="7">七级流程</option>
				<option value="8">八级流程</option>
				<option value="9">九级流程</option>
				<option value="10">十级流程</option>
			</select><span style="color: red;">*</span>
	    </td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">审核人职位</td>
		<td width="215" height="10"> 
		    <input readonly="readonly" name="approvalProcess.approverPosition" id="approverPositionupdate" value=""/>
		</td>
	</tr>
	
	<tr>
		<td width="80" height="10" align="right">审核人账户</td>
		<td width="215" height="10">
		 <input type="text" readonly="readonly" style="color: #294B7C;" name="approvalProcess.approverAccount" id="approverAccountupdate"></td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">审核人姓名</td>
		<td width="215" height="10"> <input type="text" readonly="readonly" style="color: #294B7C;" name="approvalProcess.approverRealName" id="approverRealNameupdate"></td>
	</tr>
	<tr>
		<td width="80" height="10" align="right">流转约束</td>
		<td width="215" height="10"> <input type="text" name="approvalProcess.constraints" id="constraintsupdate"></td>
	</tr>
</table>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;
<input value="保存" type="button" onclick="checkUpdateData();"/>
<input type="hidden" name="approvalProcess.id" value="" id="idupdate"/>
<input type="hidden" name="accIDupdate" value="" id="accIDupdate"/>
</form>
<div id="treeupdate" style="border: 5px solid gray;width: 200px;height: 300px;float:left; overflow: auto;margin-left: 280px;margin-top: -180px;">
请选择审核人
<ul id="ztreeupdate" class="ztree" style="width: 150px; height: 450px; hoverflow: auto;"></ul>
</div>
</div>
</body>
</html>