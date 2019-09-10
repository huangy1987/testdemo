<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.AccountRole" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/role.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<title>角色管理</title>
</head>

<body>
<!-- list -->
<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="新增" />添加角色</a>
<table style="font-size: 12px;">
	<thead>
		<tr>
			<td class="td3" style="width: 5%;">部门名称</td>
			<td class="td3" style="width: 8%;">角色名称</td>
			<td class="td3" style="width: 15%;">授权功能</td>
			<td class="td3" style="width: 12%;">创建时间</td>
			<td class="td3" style="width: 12%;">停用时间</td>
			<td class="td3" style="width: 15%;">备注</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<AccountRole> list = (List<AccountRole>)request.getAttribute("roleList");
			int sum = list.size();
		    if(list != null && list.size() > 0){
		       for(AccountRole kt:list){
		    	   Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		    	   
		%>
		<tr>
			<td><%=kt.getDepartmentName()%></td>
			<td><%=kt.getRoleName()%></td>
			<td><span id="moduleList"><%=kt.getModuleList() %></span></td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><%=kt.getRemark()%></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="updateRole(<%=kt.getId()%>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/admin/deleterole?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要删除角色<<%= kt.getRoleName() %>>吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
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
<div id="add" style="height: 360px; width: 550px; display: none;">
<form action="${pageContext.request.contextPath}/admin/addrole" onsubmit="return checkIsNull();">
<table width="550" height="100" border="0" cellpadding="0" cellspacing="0" style="border: 1px solid red">
	<tr>
		<td width="220" height="31">部门</td>
		<td width="215"><select id="departmentNameadd" name="role.departmentID">
			<option value="0">请选择</option>
			<c:forEach items="${departments }" var="dep">
				<option value="${dep.id}">${dep.departmentName }</option>
			</c:forEach>

		</select></td>
		<td width="220" height="10">角色名称</td>
		<td width="215" height="10"> <input type="text" name="role.roleName" id="realNameadd"></td>
	</tr>

	<tr>
		<td width="126" height="10">备注</td>
		<td width="615" height="10" align="left" colspan="3"><textarea rows="2" cols="70" id="remarkadd" name="role.remark"></textarea></td>
	</tr>
</table>
<h2>授权功能</h2>
<input type="hidden" name="role.moduleList" value="" id="moduleListadd" />
<div id="treediv" style="width: 520px; height: 180px; border: 10px solid #ccc; overflow-y: scroll;">
<div id="left_menu" style="width: 320px; height: 220px; margin-left: 30px;">
<h3>功能目录</h3>
<ul id="ztreeRoleadd" class="ztree" style="width: 150px; height: 450px; hoverflow: auto;"></ul>
</div>
</div>
<input value="保存" type="submit" /></form>
</div>

<!-- update -->
<div id="update" style="height: 360px; width: 550px; display: none;">
<form action="${pageContext.request.contextPath}/admin/updaterole?type=1" method="post" onsubmit="return checkIsNullUpdate();" enctype="multipart/form-data">
<table width="600" height="100" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="220" height="31">部门</td>
		<td width="415"><select id="departmentNameupdate" name="role.departmentID">
			<c:forEach items="${departments }" var="dep">
				<option value="${dep.id}">${dep.departmentName }</option>
			</c:forEach>

		</select></td>
		<td width="220" height="10">角色名称</td>
		<td width="415"><input type="text" name="role.roleName" id="roleNameupdate" value=""></td>
		</tr>
		<tr>
		<td width="220" height="10">备注</td>
		<td width="615" height="10" align="left" colspan="3"><textarea rows="2" cols="70" id="remarkupdate" name="role.remark"></textarea></td>
	</tr>
		<tr>
			<td width="126" height="10">授权功能</td>
			<td width="326" height="10">&nbsp;</td>
			<td width="326" height="10">&nbsp;</td>
			<td width="326" height="10">&nbsp;</td>
		</tr>
		
</table>
<input type="hidden" name="role.moduleList" value="" id="moduleListupdate"/>
<div id="treediv" style="width: 520px; height: 220px; border: 10px solid #ccc; overflow-y: scroll;">
<div id="left_menu" style="width: 320px; height: 220px; margin-left: 30px;">功能目录
<ul id="ztreeRoleupdate" class="ztree" style="width: 150px; height: 450px; hoverflow: auto;"></ul>
</div>
</div>
<input value="保存" type="submit" />
<input type="hidden" name="role.moduleList" value="" id="ztreeRoleupdatevalue"/>
<input type="hidden" name="role.id" value="" id="roleIDupdate"/>
</form>
</div>

</body>
</html>