<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.Position,com.solution.entity.Department" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/position.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<title>职位管理</title>
</head>

<body>
<!--<div class="easyui-layout" data-options="fit:true">
<div region="north" title='筛选条件' data-options="iconCls:'icon-search',border:false" style="height: 180px; padding: 5px; vertical-align: middle;">
<form id="acction-search-form">
<table class="tableForm" style="margin: auto auto">
	<tr>
		<th>登录帐户</th>
		<td><input type="text" id="queryLoginName" /></td>
		<th>真实姓名</th>
		<td><input type="text" id="queryRealName" /></td>
		<th>所属角色</th>
		<td><input id="queryAccountRole" /></td>
	</tr>
	<tr>
		<th>创建时间</th>
		<td><input type="text" name="role.createTime" id="createTime1" value="" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"></td>
		<th style="text-align: center">至</th>
		<td><input type="text" name="role.createTime" id="createTime2" value="" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"></td>
		<th>&nbsp;</th>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<th>停用时间</th>
		<td><input type="text" name="role.endTime" id="endTime1" value="" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /></td>
		<th style="text-align: center">至</th>
		<td><input type="text" name="role.endTime" id="endTime2" value="" onclick="WdatePicker({skin:'ext',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /></td>
		<th>&nbsp;</th>
		<td>&nbsp;</td>
	</tr>
</table>
<div style="padding: 6px; margin: auto auto; text-align: center;"><a href="#" id='search-button' class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a> <a href="#" id='reset-button' class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"> 清空</a></div>
</form>
</div>
</div>

<hr />
-->
<!-- list -->
<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="新增" />添加职位</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 5%;">所属部门</td>
			<td class="td3" style="width: 8%;">职位名称</td>
			<td class="td3" style="width: 12%;">创建时间</td>
			<td class="td3" style="width: 12%;">结束时间</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Position> list = (List<Position>)request.getAttribute("positionList");
			List<Department> dlist = (List<Department>)request.getAttribute("departmentList");
			
		    if(list != null && list.size() > 0){
		       for(Position kt:list){
		    	   Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		    	   
		%>
		<tr>
			<td>
			<%
				if(dlist != null && dlist.size() > 0){
					for(Department dep:dlist){
						if(dep.getId() == kt.getDepartmentID()){
						%>	
							<%=dep.getDepartmentName()%>
			<%			break;}
					}
				}
			%>
			
			
			
			</td>
			<td><%=kt.getPositionName()%></td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="updatePosition(<%=kt.getId()%>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/info/deleteposition?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要删除职位<<%= kt.getPositionName()%>>吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
		</tr>
		<%	}}	%>

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
<div id="add" style="height: 260px; width: 350px; display: none;">
<form id="addForm" >
<table width="550" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">所属部门名称</td>
	</tr>
	<tr>	
		<td width="215">
		<select id="departmentNameadd" name="position.departmentID" >
		</select>
		</td>
	</tr>
	<tr>
		<td width="220" height="10">职位名称</td>
	</tr>
	<tr>	<td width="215" height="10"> <input type="text" name="position.positionName" id="positionNameadd" value=""></td>
	</tr>
</table>

<input value="保存" type="button" onclick="return PositionSubmitCheck();"/>
</form>
</div>

<!-- update -->
<div id="update" style="height: 360px; width: 550px; display: none;">
<form action="${pageContext.request.contextPath}/info/updateposition" method="post" onsubmit="return checkUpdateData();" enctype="multipart/form-data">
<table width="600" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">所属部门名称</td>
	</tr>
	<tr>
		<td width="415">
		<select id="departmentNameupdate" name="position.departmentID">
		</select>
		</td>
	</tr>
	<tr>	<td width="220" height="10">职位名称</td>
	</tr>
	<tr>	<td width="415"><input type="text" name="position.positionName" id="positionNameupdate" value=""></td>
	</tr>
</table>
<input value="保存" type="submit" />
<input type="hidden" name="position.id" value="" id="positionIDupdate"/>
</form>
</div>

</body>
</html>