//进入添加页面
function add(){
	//加载所有部门对象
	var map = {'type':3};
	var deps = quickAjaxPost(sybp() + '/info/findAlldepartment', false, map);
	deps = jQuery.parseJSON(deps);
	$('#departmentNameadd').empty();
 	$('#departmentNameadd').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
 	var setvalue ='';
	for(var i=0;i<deps.length;i++){
		var id = deps[i].id;
		var name = deps[i].departmentName;
	   $('#departmentNameadd').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
	}
	document.getElementById('departmentNameadd').selectedIndex = 0;
	
	popquestion('add', '添加职位信息');
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

//添加部门检查工号是否存在
function checkSame(loginName){//alert(3);
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
	$('#departmentOwneradd').poshytip('destroy');
	chexkwarn("departmentOwneradd", '工号不存在,请先添加相应账户！');
	return false;
}
//添加职位捕获回车提交事件
function keycheck(){
	if(event.keyCode == 13){
		checkIsNULL();
	}
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
		$('#' + elementid).poshytip('destroy');
	}, 1700);

}
//添加职位信息非空验证
function PositionSubmitCheck(){
	$('#departmentNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentNameadd').val() == '0') {
		chexkwarn('departmentNameadd', '部门名称不能为空！');
		return;
	}
	
	$('#positionNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#positionNameadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn("positionNameadd", '职位名称不能为空！');
		return;
	}
	var url = sybp() +'/info/addposition';
	$('#addForm').attr('action',url);
	$('#addForm').submit();
}


//进入更新职位界面
function updatePosition(id){
	//根据职位id获取部门对象
	var map = {'id':id,'type':1};
	var pos = quickAjaxPost(sybp() + '/info/findAllposition', false, map);
	pos = jQuery.parseJSON(pos);
	
	//加载所有部门对象
	var map = {'type':3};
	var deps = quickAjaxPost(sybp() + '/info/findAlldepartment', false, map);
	deps = jQuery.parseJSON(deps);
	$('#departmentNameupdate').empty();
	for(var i=0;i<deps.length;i++){
		var id = deps[i].id;
		var name = deps[i].departmentName;
	   $('#departmentNameupdate').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
	}
	
	//遍历部门名称下拉框并赋值
	//var t1 = $('#departmentNameupdate');  
	var t1 = document.getElementById('departmentNameupdate');
    for(i=0;i<t1.length;i++){//给select赋值  
        if(pos.departmentID == t1.options[i].value){  
            t1.options[i].selected=true;
        }  
    }  	
    //给页面其他表单赋值
	$('#positionNameupdate').val(pos.positionName);
	$('#positionIDupdate').val(pos.id);
	
	popquestion('update', '修改职位信息');
}

//修改职位非空验证
function checkUpdateData(){
	$('#positionNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#positionNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn("positionNameupdate", '职位名称不能为空！');
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

