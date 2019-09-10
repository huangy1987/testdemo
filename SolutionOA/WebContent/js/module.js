/**
 * 弹出框
 * 
 * @return
 */
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

function add(){
	//location.href = "../page/admin/module_add.jsp";
	//加载模型数据
	countHelp = 1;//改变请求次数
	//弹出diolog
	popquestion('add','添加模块');
	
}

function showMenu(value){
	if(value == 1 || value == 0){
		$('#menu1').hide();
		$('#menu2').hide();
	}else if(value == 2){
		$('#menu1').show();
		$('#menu2').show();
	}
}

function setType(value){
	alert(value);
	if(value == 0){
		$( "select[id$=moduleType1]" ).selectedIndex = 0;
	}else{
		$( "select[id$=moduleType1]" ).selectedIndex = 2;
	}
}

function sybp(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0,pos);
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/') + 1);
	return localhostPath + projectName;
}

//进入修改模块对话框
function updateModule(id){
	var url=sybp()+'/admin/updatemodule?type=2&id=' + id;
	var r = quickAjaxPost(url, false, null);
	r = jQuery.parseJSON(r);
	//给id为update的div赋值	
	$('#moduleIndexupdate').val(r.moduleIndex);
	$('#moduleNameupdate').val(r.moduleName);
	$('#modulePathupdate').val(r.modulePath);
	$('#remarkupdate').val(r.remark);
	$('#idupdate').val(r.id);
	//时间格式化
	var cr = r.createTime;
	var et = r.endTime;		//2013-03-21T09:09:09.000
	var a = cr.substring(0,10);  
	var b = cr.substring(11,19); 
	cr = a + " " + b;
	$('#createTimeupdate').val(cr);
	
	a = et.substring(0,10);  
	b = et.substring(11,19);
	et = a + " " + b;
	$('#endTimeupdate').val(et);
	if(r.moduleParentID == 0){
		//为菜单
		jQuery("#moduleTypeupdate").val(1);  //设置Select的Value值为1的项选中
		//隐藏挂靠菜单
		$('#menu11').hide();
		$('#menu22').hide();
	}else{
		//为功能
		jQuery("#moduleTypeupdate").val(2); 
		//显示挂靠菜单
		$('#menu11').show();
		$('#menu22').show();
		jQuery("#moduleParentIDupdate").val(r.moduleParentID);
	}
//	document.getElementById('moduleIconupdate').selectedIndex = 1;
	var text = r.moduleIcon;
	//根据select的显示值来为select设置模型图标
	var count = $("#moduleIconupdate").get(0).options.length;
	for(var i=0;i<count;i++){
		if($("#moduleIconupdate").get(0).options[i].text == text){
			$("#moduleIconupdate").get(0).options[i].selected = true;          
			break;  
		}  
	}
	//记录模型索引初始值
	indexModuleOld = r.moduleIndex;
	//弹出对话框
	popquestion('update', '修改模块');
}
//根据选择的模型类型改变相应的挂靠菜单的显示
function showMenuupdate(value){
	if(value == 1){
		//隐藏挂靠菜单
		$('#menu11').hide();
		$('#menu22').hide();
	}else{
		//显示挂靠菜单
		$('#menu11').show();
		$('#menu22').show();
	}
}
//修改模型检查模型索引是否已经存在
function checkIndexupdate(value){
	if(value == indexModuleOld){//值未改变
		return;
	}
	countHelp = 1;
	if(value != "" ){
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
			$('#indexErrorupdate').text('索引已经存在');
			setTimeout(function(){
				 $('#indexErrorupdate').text('');
				 $('#moduleIndexupdate').val('');
				},1700);
		}
		if(res == 'false'){
			$('#indexErrorupdate').text('');
		} 
	}
}

//请求方法
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

function checkIsNullupdate(){
	$('#moduleIndexupdate').poshytip('destroy');// 销毁提醒框
	if ($('#moduleIndexupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('moduleIndexupdate', '模型索引不能为空!');
		return false;
	}
	
	$('#moduleIndexupdate').poshytip('destroy');// 销毁提醒框
	if (!(/^[0-9]{1,3}$/.test($('#moduleIndexupdate').val()))) {
		chexkwarn('moduleIndexupdate', '模型索引只能1-3位数字!');
		return false;
	}
	
	$('#moduleNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#moduleNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('moduleNameupdate', '模型名称不能为空!');
		return false;
	}
	$('#modulePathupdate').poshytip('destroy');// 销毁提醒框
	if ($('#modulePathupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('modulePathupdate', '模型路径不能为空!');
		return false;
	}
	
	$('#createTimeupdate').poshytip('destroy');// 销毁提醒框
	if ($('#createTimeupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('createTimeupdate', '创建时间不能为空!');
		return false;
	}
	$('#endTimeupdate').poshytip('destroy');// 销毁提醒框
	if ($('#endTimeupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('endTimeupdate', '结束时间不能为空!');
		return false;
	}
	return true;
}