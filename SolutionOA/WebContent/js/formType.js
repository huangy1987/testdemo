//进入添加页面
function add(){
	//加载部门信息
	var map = {'type':3};
	var deps = quickAjaxPost(sybp() + '/info/findAlldepartment', false, map);
	deps = jQuery.parseJSON(deps);
	$('#departmentNameadd').empty();
 	$('#departmentNameadd').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<deps.length;i++){
		var id = deps[i].id;
		var name = deps[i].departmentName;
	   $('#departmentNameadd').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
	}
	document.getElementById('departmentNameadd').selectedIndex = 0;
	popquestion('add', '添加工单类型');
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
//添加工单类型前数据检查
function checkData(){
	//非空验证
	$('#departmentNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentNameadd').val() == '0') {
		chexkwarn('departmentNameadd', '部门名称不能为空！');
		return false;
	}
	
	$('#formNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#formNameadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn("formNameadd", '工单名称不能为空！');
		return false;
	}
	var url = sybp() +'/config/addformType';
	$('#addForm').attr('action',url);
	//赋值隐藏域
	$('#addForm').submit();
	
}
//进入工单类型更新界面
function update(id){
	//获取工单类型
	var map = {'id':id,'type':1};
	var formType = quickAjaxPost(sybp() + '/config/findAllformType', false, map);
	formType = jQuery.parseJSON(formType);
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
        if(formType.departmentID == t1.options[i].value){  
            t1.options[i].selected=true;
        }  
    }  	
    //给页面其他表单赋值
	$('#formNameupdate').val(formType.formName);
	$('#remarkupdate').val(formType.remark);
	$('#formTypeid').val(formType.id);
	popquestion('update', '修改工单类型');
}
//更新数据前检查数据
function checkUpdateData(){
	//非空判断
	$('#formNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#formNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn("formNameupdate", '工单名称不能为空！');
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