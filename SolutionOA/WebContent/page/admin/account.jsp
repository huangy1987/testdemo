<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.Account" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagebar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/artdialog/skins/aero.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/poshytip/src/tip-violet/tip-violet.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/account.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/accountadd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/accountupdate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<title>账户管理</title>
</head>
<body>

<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;"><img src="${pageContext.request.contextPath}/images/add.png" title="新增" />添加账户</a>
<table style="font-size: 10px;">
	<thead>
		<tr>
			<td class="td3" style="width: 6%;">登陆工号</td>
			<td class="td3" style="width: 6%;">姓名</td>
			<td class="td3" style="width: 6%;">密码</td>
			<td class="td3" style="width: 8%;">角色名称</td>
			<td class="td3" style="width: 13%;">部门名称</td>
			<td class="td3" style="width: 10%;">职位名称</td>
			<td class="td3" style="width: 12%;">创建时间</td>
			<td class="td3" style="width: 12%;">停用时间</td>
			<td class="td3" style="width: 15%;">备注</td>
			<td class="td3" style="width: 15%;">操作</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Account> list = (List<Account>)request.getAttribute("accountList");
		    if(list != null && list.size() > 0){
		       for(Account kt:list){
		    	   Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		    	   Date now = new Date();
		    	   String nowStr = sdf.format(now);
		    	   String time = "9999-12-31 00:00:00";
		    	   Date dtime =  sdf.parse(time);
		%>
		<tr>
			<td><%=kt.getLoginName()%></td>
			<td><%=kt.getRealName()%></td>
			<td><%=kt.getPassWord() %></td>
			<td><%=kt.getRoleName()%></td>
			<td><%=kt.getDepartmentName()%></td>
			<td><%=kt.getPositionName()%></td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><%=kt.getRemark()%></td>
			
			<td>
				<%if(kt.getUsedState() == 1){//kt.getUsedState() == 1 //time.equals(ettime)
					%>
					<input type="button" onclick="stopTime(<%=kt.getId()%>,'<%=nowStr%>');" value="停用" name="stop" id="stop"/>
				<%}else{ %>
				 	<input type="button" onclick="startTime(<%=kt.getId()%>,'<%=time%>');" value="启用" name="start" id="start" />
				<%
				}
				%>
			</td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="updateAccount(<%=kt.getId()%>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/admin/deleteaccount?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要删除<<%= kt.getRealName() %>>的账户吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
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
<div id="add" style="height: 400px; width: 620px; display: none; border: 1px solid red;" onkeydown="keycheck();">
<form action="${pageContext.request.contextPath}/admin/addaccount" method="post" onsubmit="return checkAddAccount();" enctype="multipart/form-data">
<table width="450" height="300" border="0" cellpadding="0" cellspacing="0" style="margin-left: 10px;">
	<tr>
		<td width="826" height="31">工号</td>
		<td width="415"><input type="text" name="account.loginName" id="loginNameadd" onblur="checkSame(this.value);"></td>
		<td width="470" height="31">真实姓名</td>
		<td width="415"><input type="text" name="account.realName" id="realNameadd"></td>
		<td width="100" height="31">密码</td>
		<td width="415"><input type="text" name="account.passWord" id="passWordadd"></td>
	</tr>
	<tr>
		<td width="600" height="31">部门</td>
		<td width="415"><select name="account.departmentID" id="departmentIDadd" onchange="setPosadd(this.value);">
			<option selected="selected" value="0">请选择</option>
			<c:forEach items="${departments}" var="dep">
				<option value="${dep.id}">${dep.departmentName}</option>
			</c:forEach>
		</select></td>
		<td width="426" height="31">职位</td>
		<td width="415"><select name="account.positionID" id="positionIDadd">
			<option selected="selected" value="0">请选择</option>
		</select></td>
		<td width="426" height="31">角色</td>
		<td width="415"><select name="account.accountRoleID" id="accountRoleIDadd">
			<option selected="selected" value="0">请选择</option>
		</select></td>
	</tr>
	<tr>
		<td width="426" height="31">备注</td>
		<td colspan="5"><textarea name="account.remark" id="remarkadd" cols="90" rows="7"></textarea></td>
	</tr>
</table>
<input type="hidden" name="acc" /> <br />
<br />
<center><input type="submit" value="添加" /> <input type="button" value="返回" onclick="location.href='${pageContext.request.contextPath}/admin/findAllaccount'" /></center>
</form>
</div>

<!-- update -->
<div id="update" style="height: 400px; width: 670px; display: none;">
<form action="${pageContext.request.contextPath}/admin/updateaccount" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
<table width="450" height="300" border="0" cellpadding="0" cellspacing="0" style="margin-left: 15px; margin-top: 5px;">
	<tr>
		<td width="226" height="31">工号</td>
		<td width="315"><input type="text" readonly="readonly" name="account.loginName" id="loginNameupdate" value="" style="color: #ccc; font-size: 12px; font-weight: bold;"></td>
		<td width="250" height="31">真实姓名</td>
		<td width="415"><input type="text" name="account.realName" id="realNameupdate" value=""></td>
		<td width="200" height="31">密码</td>
		<td width="415"><input type="text" name="account.passWord" id="passWordupdate" value=""></td>
	</tr>
	<tr>
		<td width="200" height="31">部门</td>
		<td width="415"><select name="account.departmentID" onchange="setPos(this.value);" id="departmentIDupdate">
			<c:forEach items="${departments}" var="dep">
				<c:choose>
					<c:when test="${dep.id eq account.departmentID}">
						<option selected="selected" value="${dep.id}">${dep.departmentName}</option>
					</c:when>
					<c:otherwise>
						<option value="${dep.id}">${dep.departmentName}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></td>
		<td width="426" height="31">职位</td>
		<td width="415"><select name="account.positionID" id="positionIDupdate" >
		</select></td>
		<td width="426" height="31">角色</td>
		<td width="415"><select name="account.accountRoleID" id="accountRoleIDupdate">
		</select></td>


	</tr>
	<tr>

		<td width="426" height="31">备注</td>
		<td colspan="5"><textarea name="account.remark" cols="90" rows="7" id="remarkupdate"></textarea></td>
	</tr>
</table>
<input type="hidden" name="account.id" value="" id="idupdate"/>
<input type="hidden" name="type" value="1" />
<center><input type="submit" value="修改" /> <input type="button" value="返回" onclick="location.href='${pageContext.request.contextPath}/admin/findAllaccount'" /></center>


</form>

</div>
</body>
</html>