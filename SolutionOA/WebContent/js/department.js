//进入添加页面
function add(){
	popquestion('add', '添加部门信息');
}
//添加数据提交前检查
function checkData(){
	//alert(1);
	var flag = checkIsNULL();
	if(!flag){
		return false;
	}
	return true;
}
//验证数据
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
//添加部门检查工号是否存在
function checkSame(loginName){//alert(3);
//	if(loginName == '' || loginName == null || loginName.replace(/^ +| +$/g, '') == ''){
//		return false;
//	}
//	maparr= {'loginName':loginName,'type':2};
//	var url = sybp()+'/info/findAlldepartment';
//	//根据部门id获取所有角色
//	var result = quickAjaxPost(url, false, maparr);
//	if(result == 'true'){
//		return true;
//	}
//	$('#departmentOwneradd').poshytip('destroy');
//	chexkwarn("departmentOwneradd", '工号不存在,请先添加相应账户！');
//	return false;
}
//添加部门捕获回车提交事件
function keycheck(){
	if(event.keyCode == 13){
		//checkData();
	}
}

//添加部门信息非空验证
function checkIsNULL(){//alert(2);
	$('#departmentNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentNameadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn("departmentNameadd", '部门名称不能为空！');
		return false;
	}
	
	/*$('#departmentOwneradd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentOwneradd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('departmentOwneradd', '主要负责人工号不能为空!');
		return false;
	}*/
	/*$('#departmentOwneradd').poshytip('destroy');// 销毁提醒框
	var reg = /^[0-9]{3,4}$/;
	if (!(reg.test($('#departmentOwneradd').val()))) {
		chexkwarn('departmentOwneradd', '工号只能为3-4位数字!');
		return false;
	}*/
	/*//检查工号是否存在//必须存在
	var flag = checkSame($('#departmentOwneradd').val());
	if(!flag){
		return false;
	}
	$('#departmentOwnerNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentOwnerNameadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('departmentOwnerNameadd', '主要负责人姓名不能为空!');
		return false;
	}*/
	return true;
}
//进入更新部门界面
function updateDepartment(id){
	var map = {'id':id,'type':1};
	var dep = quickAjaxPost(sybp() + '/info/findAlldepartment', false, map);
	dep = jQuery.parseJSON(dep);
	//给页面赋值
	$('#departmentNameupdate').val(dep.departmentName);
	$('#departmentOwnerupdate').val(dep.departmentOwner);
	$('#departmentOwnerNameupdate').val(dep.departmentOwnerName);
	$('#idupdate').val(dep.id);
	popquestion('update', '修改部门信息');
}
//添加部门检查工号是否存在
/*function checkSameUpdate(loginName){//alert(3);
	if(loginName == '' || loginName == null || loginName.replace(/^ +| +$/g, '') == ''){
		return false;
	}
	maparr= {'loginName':loginName,'type':2};
	var url = sybp()+'/info/findAlldepartment';
	//根据部门id获取所有角色
	var result = quickAjaxPost(url, false, maparr);
	if(result == 'true'){
		return true;
	}
	$('#departmentOwnerupdate').poshytip('destroy');
	chexkwarn("departmentOwnerupdate", '工号不存在,请确认部门负责人账户！');
	return false;
}*/
//修改部门非空验证
function checkUpdateData(){
	$('#departmentNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#departmentNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn("departmentNameupdate", '部门名称不能为空！');
		return false;
	}
	$('#departmentOwnerupdate').poshytip('destroy');// 销毁提醒框
	if ($('#departmentOwnerupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('departmentOwnerupdate', '主要负责人工号不能为空!');
		return false;
	}
	$('#departmentOwnerupdate').poshytip('destroy');// 销毁提醒框
	var reg = /^[0-9]{3,4}$/;
	if (!(reg.test($('#departmentOwnerupdate').val()))) {
		chexkwarn('departmentOwnerupdate', '工号只能为3-4位数字!');
		return false;
	}
	//检查工号是否存在//必须存在
	var flag = checkSameUpdate($('#departmentOwnerupdate').val());
	if(!flag){
		return false;
	}
	$('#departmentOwnerNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#departmentOwnerNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('departmentOwnerNameupdate', '主要负责人姓名不能为空!');
		return false;
	}
	if(!confirm('你确定修改吗？')){
		return false;
	}
	return true;
}

function sybp(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0,pos);
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/') + 1);
	//返回值:/http://ip地址端口/项目名
	//alert(localhostPath + projectName);
	return localhostPath + projectName;
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