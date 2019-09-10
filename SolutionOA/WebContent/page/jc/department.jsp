<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.Department" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagebar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/artdialog/skins/aero.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/poshytip/src/tip-violet/tip-violet.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/module/ztree/css/zTreeStyle.css" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/department.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<title>部门管理</title>
</head>

<body>

<!-- list -->
<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="新增" />添加部门</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 5%;">部门名称</td>
			<td class="td3" style="width: 8%;">主要负责人工号</td>
			<td class="td3" style="width: 15%;">主要负责人姓名</td>
			<td class="td3" style="width: 12%;">创建时间</td>
			<td class="td3" style="width: 12%;">结束时间</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Department> list = (List<Department>)request.getAttribute("departmentList");
			int sum = list.size();
		    if(list != null && list.size() > 0){
		       for(Department kt:list){
		    	   Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		%>
		<tr>
			<td><%=kt.getDepartmentName()%></td>
			<td><%=kt.getDepartmentOwner()%></td>
			<td><span id="departmentList"><%=kt.getDepartmentOwnerName() %></span></td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="updateDepartment(<%=kt.getId()%>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/info/deletedepartment?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要删除<<%= kt.getDepartmentName() %>>吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
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
<div id="add" style="height: 200px; width: 350px; display: none;" >
<form action="${pageContext.request.contextPath}/info/adddepartment" onkeydown="keycheck();" onsubmit="return checkData();">
<table width="350" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">部门名称</td>
		<td width="215"><input type="text" name="department.departmentName" id="departmentNameadd" ></td>
	</tr>

	<tr>
		<td width="220" height="10">主要负责人工号</td>
		<td width="215" height="10"> <input type="text" name="department.departmentOwner" id="departmentOwneradd"></td>
	</tr>
	
	<tr>
		<td width="220" height="10">主要负责人姓名</td>
		<td width="215" height="10"> <input type="text" name="department.departmentOwnerName" id="departmentOwnerNameadd"></td>
	</tr>
</table>
<input value="保存" type="submit" />
</form>
</div>

<!-- update -->
<div id="update" style="height: 200px; width: 350px; display: none;">
<form action="${pageContext.request.contextPath}/info/updatedepartment" method="post" onsubmit="return checkUpdateData();" >
<table width="350" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">部门名称</td>
		<td width="215"><input type="text" name="department.departmentName" id="departmentNameupdate" value=""></td>
	</tr>

	<tr>
		<td width="220" height="10">主要负责人工号</td>
		<td width="215" height="10"> <input type="text" name="department.departmentOwner" id="departmentOwnerupdate" value=""></td>
	</tr>
	
	<tr>
		<td width="220" height="10">主要负责人姓名</td>
		<td width="215" height="10"> <input type="text" name="department.departmentOwnerName" id="departmentOwnerNameupdate" value=""></td>
	</tr>
</table>
<input type="hidden" name="department.id" value="" id="idupdate" />
<input value="保存" type="submit" />
</form>
</div>
</body>
</html>