<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.FormType" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
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


<script type="text/javascript" src="${pageContext.request.contextPath}/js/formType.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<title>工单类型配置</title>
</head>

<body>

<!-- list -->
<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="新增" />添加工单类型</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 5%;">部门名称</td>
			<td class="td3" style="width: 8%;">编号</td>
			<td class="td3" style="width: 8%;">工单名称</td>
			<td class="td3" style="width: 12%;">创建时间</td>
			<td class="td3" style="width: 12%;">结束时间</td>
			<td class="td3" style="width: 12%;">备注</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<FormType> list = (List<FormType>)request.getAttribute("fromTypeList");
			int sum = list.size();
		    if(list != null && list.size() > 0){
		       for(FormType kt:list){
		    	   Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		%>
		<tr>
			<td><%=kt.getDepartmentName()%></td>
			<td><%=kt.getId()%></td>
			<td><%=kt.getFormName() %></td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><%=kt.getRemark() %></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="update(<%=kt.getId()%>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/config/deleteformType?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要删除<<%= kt.getFormName() %>>吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
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
<form id="addForm">
<table width="350" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">部门名称</td>
		<td width="215">
			<select id="departmentNameadd" name="formType.departmentID" >
			</select>
		</td>
	</tr>

	<tr>
		<td width="220" height="10">工单名称</td>
		<td width="215" height="10"> <input type="text" name="formType.formName" id="formNameadd"></td>
	</tr>
	
	<tr>
		<td width="226" height="31">备注</td>
		<td colspan="5"><textarea name="formType.remark" id="remarkadd" cols="20" rows="7"></textarea></td>
	</tr>
</table>
<input value="保存" type="button" onclick="checkData();"/>
</form>
</div>

<!-- update -->
<div id="update" style="height: 200px; width: 350px; display: none;">
<form action="${pageContext.request.contextPath}/config/updateformType" method="post" onsubmit="return checkUpdateData();" >
<table width="350" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">部门名称</td>
		<td width="215">
			<select id="departmentNameupdate" name="formType.departmentID" >
			</select>
		</td>
	</tr>

	<tr>
		<td width="220" height="10">工单名称</td>
		<td width="215" height="10"> <input type="text" name="formType.formName" id="formNameupdate"></td>
	</tr>
	
	<tr>
		<td width="226" height="31">备注</td>
		<td colspan="5"><textarea name="formType.remark" id="remarkupdate" cols="20" rows="7"></textarea></td>
	</tr>
</table>
<input value="保存" type="submit" />
<input type="hidden" name="formType.id" value="" id="formTypeid"/>
</form>
</div>

</body>
</html>