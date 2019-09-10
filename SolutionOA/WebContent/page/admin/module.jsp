<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.Module" 
pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagebar.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/artdialog/skins/aero.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css"></link>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/poshytip/src/tip-violet/tip-violet.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#menu1').hide();$('#menu2').hide();
});
/**
 * 验证提醒框
 * 
 * @param elementid
 * @param content
 * @return
 */
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
		$('#' + elementid).poshytip('hide');
	}, 1700);

}
function checkIsNull(){
	
	$('#moduleIndex').poshytip('destroy');// 销毁提醒框
	if ($('#moduleIndex').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('moduleIndex', '模型索引不能为空!');
		return false;
	}
	$('#moduleIndex').poshytip('destroy');// 销毁提醒框
	var reg = /^[0-9]{1,3}$/;
    if(!reg.test($('#moduleIndex').val())){
    	chexkwarn('moduleIndex', '模型索引只能为1-3位数字!');
    	return false;
    } 
	$('#moduleName').poshytip('destroy');// 销毁提醒框
	if ($('#moduleName').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('moduleName', '模型名称不能为空!');
		return false;
	}
	$('#moduleType').poshytip('destroy');// 销毁提醒框
	if ($('#moduleType').val() == 0) {
		chexkwarn('moduleType', '模块类型不能为空!');
		return false;
	}
	$('#moduleParentID').poshytip('destroy');// 销毁提醒框
	if ($('#moduleParentID').val() == 0 && $('#moduleType').val() == 2) {
		chexkwarn('moduleParentID', '挂靠菜单不能为空!');
		return false;
	}
	$('#moduleIconadd').poshytip('destroy');// 销毁提醒框
	if ($('#moduleIconadd').val() == 0) {
		chexkwarn("moduleIconadd", '模型图标不能为空!');
		return false;
	}
	
	$('#modulePathadd').poshytip('destroy');// 销毁提醒框
	if ($('#modulePathadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('modulePathadd', '模型路径不能为空!');
		return false;
	}
	
	$('#createTimeadd').poshytip('destroy');// 销毁提醒框
	if ($('#createTimeadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('createTimeadd', '创建时间不能为空!');
		return false;
	}
	$('#endTimeadd').poshytip('destroy');// 销毁提醒框
	if ($('#endTimeadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('endTimeadd', '结束时间不能为空!');
		return false;
	}
	return true;
}
</script>
<title>功能模块管理</title>
</head>
<body>
<div class="tab-content default-tab" id="tab1"><a onclick="add();" style="font-family: sans-serif; font-size: 16px; color: #ftc; border: none; font-weight: bold;">新增模型<img src="${pageContext.request.contextPath}/images/add.png" title="新增" /></a>
<table style="font-size: 10px;">
	<thead>
		<tr>
			<td class="td3" style="width: 6%;">模型索引</td>
			<td class="td3" style="width: 6%;">模型图标</td>
			<td class="td3" style="width: 12%;">模型名称</td>
			<td class="td3" style="width: 6%;">模型请求路径</td>
			<td class="td3" style="width: 6%;">挂靠菜单</td>
			<td class="td3" style="width: 15%;">模型父级</td>
			<td class="td3" style="width: 5%;">类型</td>
			<td class="td3" style="width: 10%;">创建时间</td>
			<td class="td3" style="width: 10%;">停用时间</td>
			<td class="td3" style="width: 15%;">备注</td>
			<td class="td3" style="width: 5%;">修改</td>
			<td class="td3" style="width: 5%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Module> list = (List<Module>)request.getAttribute("moduleList");
		    if(list != null && list.size() > 0){
		       for(Module kt:list){
		    	  Date ct = kt.getCreateTime();
		    	   Date et = kt.getEndTime();
		    	   String cttime = sdf.format(ct);
		    	   String ettime = sdf.format(et);
		%>
		<tr>
			<td><%=kt.getModuleIndex()%></td>
			<td><%=kt.getModuleIcon()%></td>
			<td><%=kt.getModuleName() %></td>
			<td><%=kt.getModulePath()%></td>
			<td>
			<%if(kt.getModuleParentName() == null){
			%>	
				本级
			<%}else{
			%>	<%=kt.getModuleParentName() %>
			<%} %>
			</td>
			<td><%=kt.getModuleParentID()%></td>
			<td>
			<%
					if(kt.getModuleParentID() == 0){
				%> 菜单 <%
					}else{
				%> 功能 <%	
					}
				%>
			</td>
			<td><%=cttime%></td>
			<td><%=ettime%></td>
			<td><%=kt.getRemark()%></td>
			<td><img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="updateModule(<%=kt.getId() %>);" title="修改" /></td>
			<td><a href="${pageContext.request.contextPath}/admin/deletemodule?id=<%=kt.getId()%>" title="删除" onclick="return confirm('你确定要模型<<%= kt.getModuleName() %>>吗？');"><img src="${pageContext.request.contextPath}/images/delete.png" /></a></td>
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
<div id="add" style="height: 400px; width: 580px; display: none;">
<script type="text/javascript">
	var countHelp = 1;//ajax请求次数
	function checkIndex(value){
		//alert(countHelp);
		if(value != ""){
			var res = "";
			$.ajax({
				async : false,
				type : 'post',
				url : sybp()+'/admin/findAllmodule',
				data : 'moduleIndex=' + value + '&type=9',// json格式数据
				success : function(result) {
					res = result;
				},
				error : function(result) {
					res = "timeout";
					alert('请求超时');
				}
			});
			if(countHelp > 3){
				alert('请求了' + countHelp + '次');
			}
			countHelp++;
			if(res == 'true'){
				$('#indexError').text('索引已经存在');
				setTimeout(function(){
					 $('#indexError').text('');
					 $('#moduleIndex').val('');
					},1700);
			}
			if(res == 'false'){
				$('#indexError').text('');
			} 
		}
		
		
	};

</script>
<form action="${pageContext.request.contextPath}/admin/addmodule" onsubmit="return checkIsNull();">
<%-- <h3>已经存在的模型索引:</h3>
<h4>
<c:forEach items="${modules}" var="ms">
	${ms.moduleIndex},
</c:forEach>

</h4> --%>
<table style="margin-left: -140px;">
	<tr>
		<td width="326" height="31" align="right">模型索引</td>
		<td  width="490"><input type="text" value="" name="module.moduleIndex" id="moduleIndex" onblur=""/><span id="indexError" style="color: red;"></span></td>
		<td width="326" height="31" align="right">模型名称</td>
		<td><input type="text" value="" name="module.moduleName" id="moduleName" /></td>
	</tr>
	<tr>
		<td width="326" height="31" align="right">模块类型</td>
		<td><select name="moduleType" id="moduleType" onchange="showMenu(this.value);">
			<option selected="selected" value="0">请选择</option>
			<option value="1" id="moduleType1">菜单</option>
			<option value="2" id="moduleType2">功能</option>
		</select></td>

		<td width="326" height="31" align="right"><span id="menu1">挂靠菜单</span></td>
		<td>
		<div id="menu2" >
			<select name="module.moduleParentID" id="moduleParentID"  onchange="setType(this.value);">
				<option value="0" selected="selected">请选择</option>
				<c:forEach var="mod1" items="${moduleMenu}">
					<option value="${mod1.id}">${mod1.moduleName}</option>
				</c:forEach>
			</select>
		</div>
		</td>
	</tr>
	<tr>
		<td width="326" height="31" align="right">模型图标</td>
		<td><select name="module.moduleIcon" id="moduleIconadd">
			<option selected="selected" value="0">请选择</option>
			<option value="icon-sys">icon-sys</option>
			<option value="icon-nav">icon-nav</option>
			<option value="icon-box">icon-box</option>
			<option value="icon-set">icon-set</option>
			<option value="icon-add">icon-add</option>
			<option value="icon-add1">icon-add1</option>
			<option value="icon-users">icon-users</option>
			<option value="icon-role">icon-role</option>
			<option value="icon-set">icon-set</option>
			<option value="icon-log">icon-log</option>
			<option value="icon-delete">icon-delete</option>
			<option value="icon-edit">icon-edit</option>
			<option value="icon-magic">icon-magic</option>
			<option value="icon-database">icon-database</option>
			<option value="icon-expand">icon-expand</option>
			<option value="icon-collapse">icon-collapse</option>
			<option value="icon-page">icon-page</option>
			<option value="icon-class">icon-class</option>
		</select></td>
		<td width="926" height="31" align="right">模型请求路径</td>
		<td><input type="text" value="" name="module.modulePath" id="modulePathadd" /></td>
	</tr>
	<tr>
		<td width="926" height="31" align="right">备注&nbsp;&nbsp;&nbsp;</td>
		<td><textarea name="module.remark" id="remark" cols="30" rows="10"></textarea></td>
	</tr>
	<tr>
		<td width="926" height="31" align="right">
			<input type="submit" value="添加" />
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</div>
<div id="update"  style="height: 400px; width: 670px; display: none;">
<form action="${pageContext.request.contextPath}/admin/updatemodule" onsubmit="return checkIsNullupdate();">
<input type="hidden" value="1" name="type"/>
<%-- <h3>已经存在的模型索引:</h3>
<h4>
<c:forEach items="${modules}" var="ms">
	${ms.moduleIndex},
</c:forEach>

</h4> --%>
<table>
	<tr>
		<td width="926" height="31">模型索引</td>
		<td><input type="text" value="" name="module.moduleIndex" id="moduleIndexupdate" onblur=""/><span id="indexErrorupdate" style="color: red;"></span></td>
		<td width="926" height="31">模型名称</td>
		<td><input type="text" value="" name="module.moduleName" id="moduleNameupdate" /></td>
	</tr>
	<tr>
		<td width="926" height="31">模块类型</td>
		<td><select name="moduleTypeupdate" id="moduleTypeupdate" onchange="showMenuupdate(this.value);">
			 	<option value="1" id="moduleType11">菜单</option>
			 	<option value="2" id="moduleType22">功能</option>
		</select></td>

		<td width="926" height="31"><span id="menu11">挂靠菜单</span></td>
		<td>
		<div id="menu22" >
			<select name="module.moduleParentID" id="moduleParentIDupdate" >
				<c:forEach var="mod1" items="${moduleMenu}">
					<option value="${mod1.id}">${mod1.moduleName}</option>
				</c:forEach>
			</select>
		</div>
		</td>
	</tr>
	<tr>
		<td width="926" height="31">模型图标</td>
		<td><select name="module.moduleIcon" id="moduleIconupdate">
			<option value="icon-sys">icon-sys</option>
			<option value="icon-nav">icon-nav</option>
			<option value="icon-box">icon-box</option>
			<option value="icon-set">icon-set</option>
			<option value="icon-add">icon-add</option>
			<option value="icon-add1">icon-add1</option>
			<option value="icon-users">icon-users</option>
			<option value="icon-role">icon-role</option>
			<option value="icon-set">icon-set</option>
			<option value="icon-log">icon-log</option>
			<option value="icon-delete">icon-delete</option>
			<option value="icon-edit">icon-edit</option>
			<option value="icon-magic">icon-magic</option>
			<option value="icon-database">icon-database</option>
			<option value="icon-expand">icon-expand</option>
			<option value="icon-collapse">icon-collapse</option>
			<option value="icon-page">icon-page</option>
			<option value="icon-class">icon-class</option>
		</select></td>
		<td width="926" height="31">模型请求路径</td>
		<td><input type="text" value="" name="module.modulePath" id="modulePathupdate" /></td>
	</tr>
	
	<tr>
		<td width="926" height="31">备注</td>
		<td><textarea name="module.remark" id="remarkupdate" cols="30" rows="10">
		</textarea></td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="修改" />
		</td>
	</tr>
</table>
<input type="hidden" name="module.createTime" value="" id="createTimeupdate"/>
<input type="hidden" name="module.endTime" value="" id="endTimeupdate"/>
<input type="hidden" name="module.id" value="" id="idupdate"/>
</form>

</div>
</body>
</html>