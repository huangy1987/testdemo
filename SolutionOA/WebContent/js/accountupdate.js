//进入更改账户界面
function updateAccount(id){
	//location.href= sybp() + '/admin/updateaccount?type=2&id=' + id;
	//根据账户id获取账户信息
	var mapar= {'id':id,'type':4};
	var url = sybp()+'/admin/findAllaccount';
	//根据部门id获取所有职位
	var account = quickAjaxPost(url, false, mapar);
	account = jQuery.parseJSON(account);
	//根据获取的账户信息设置页面
	$('#loginNameupdate').val(account.loginName);
	$('#realNameupdate').val(account.realName);
	$('#passWordupdate').val(account.passWord);
	$('#remarkupdate').val(account.remark);
	$('#idupdate').val(account.id);
	
	//获取select中的所有值
//	var t = document.getElementById("departmentIDupdate");   
//    var selectValue=t.options[t.selectedIndex].value;//获取select的值 
    
	 var t1 = document.getElementById("departmentIDupdate");   
     for(i=0;i<t1.length;i++){//给select赋值  
         if(account.departmentID == t1.options[i].value){  
             t1.options[i].selected=true;
         }  
     }  
	//document.getElementById('departmentIDupdate').selectedIndex = account.departmentID;//已经加载好的?
	$('#positionIDupdate').empty();
	$('#positionIDupdate').append('<option value=\''+ account.positionID + '\'>'+ account.positionName +'</option>');
	document.getElementById('positionIDupdate').selectedIndex = 0;//追加
	$('#accountRoleIDupdate').empty();
	document.getElementById('accountRoleIDupdate').selectedIndex = 0;
	$('#accountRoleIDupdate').append('<option value=\''+ account.accountRoleID + '\'>'+ account.roleName +'</option>');
	popquestion('update', '修改账户');
}

//根据部门级联显示职位
function setPos(value){
	var mapar= {'id':value};
	var url = sybp()+'/admin/findAllPositonByDep';
	//根据部门id获取所有职位
	var result = quickAjaxPost(url, false, mapar);
	result = jQuery.parseJSON(result);
	var va = '';
	var iva = '';
	var ops = '';
	for(var d=0;d<result.length;d++){
    		va= result[d].positionName;
    		iva = result[0].id;
		ops =ops + '<option value=\''+result[d].id+ '\'>'+va+'<\/option>';
   	}
	$('#positionIDupdate').empty();
	 document.getElementById('positionIDupdate').selectedIndex = iva -1;//设置select显示值
	//给select赋值选项
	  $('#positionIDupdate').append(ops);//alert(ops);
	  setRole(value);
}
//根据部门id级联 查询角色
function setRole(value){
	var mapar= {'id':value};
	var url = sybp()+'/admin/findAllRoleByDep';
	//根据部门id获取所有职位
	var result = quickAjaxPost(url, false, mapar);
	result = jQuery.parseJSON(result);
	var va = '';
	var iva = '';
	var ops = '';
	for(var d=0;d<result.length;d++){
    		va= result[d].roleName;
    		iva = result[0].id;
		ops =ops + '<option value=\''+result[d].id+ '\'>'+va+'<\/option>';
   	}
	$('#accountRoleIDupdate').empty();
	 document.getElementById('accountRoleIDupdate').selectedIndex = iva -1;//设置select显示值
	//设置下拉框显示值
	//给select赋值选项
	  $('#accountRoleIDupdate').append(ops);//alert(ops);
}


//根据职位级联显示部门 (未用)
function setDep(value){
	var mapar= {'id':value};
	var url = sybp()+'/admin/findAllDepsByPos';
	//根据部门id获取所有职位
	var result = quickAjaxPost(url, false, mapar);
	var iva = parseInt(result);
	 document.getElementById('departmentIDupdate').selectedIndex = iva - 1;//设置select显示值
}

//更新前数据校验
function checkData(){
	var flag = checkIsNULL();
	if(!flag){
		return false;
	}
	if(!confirm('你确定要修改该账户吗？')){
		return false;
	}
	return true;
}
//更新前非空验证
function checkIsNULL(){
	//姓名密码
	$('#realNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#realNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('realNameupdate', '姓名不能为空!');
		return false;
	}
	
	$('#passWordupdate').poshytip('destroy');// 销毁提醒框
	if ($('#passWordupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('passWordupdate', '密码不能为空!');
		return false;
	}
	return true;
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