//设置变量解决点击账户后又修改部门造成的表单数据(账户和姓名)未清空问题
flagClear = false;
//解决连续点击审核人清空账户和姓名
count = 0;
//进入添加页面
function add(){
	//加载部门信息
	var map = {'type':3};
	var deps = quickAjaxPost(sybp() + '/info/findAlldepartment', false, map);
	deps = jQuery.parseJSON(deps);
	$('#departmentIDadd').empty();
 	$('#departmentIDadd').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<deps.length;i++){
		var id = deps[i].id;
		var name = deps[i].departmentName;
	   $('#departmentIDadd').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
	}
	document.getElementById('departmentIDadd').selectedIndex = 0;
	
	//初始化部门职位树形结构
	loadModule($('#ztreeadd'),'add');
	$('#treeadd').hide();
	//清空表单
	$('#flowNumberadd').val('');//流程编号
	$('#approverPositionadd').val('');//职位
	$('#approverAccountadd').val('');//账户
	$('#approverRealNameadd').val('');//姓名
	$('#constraintsadd').val('');//约束
	document.getElementById('formTypeIDadd').selectedIndex = 0;
	document.getElementById('departmentIDadd').selectedIndex = 0;
	
	$('#treeadd').hide();
	popquestion('add', '添加工单审核流程');
}

//页面加载部门和账户
function loadModule(loadtree,type){
	var map = {'type':1};
	var url=sybp() + "/config/addapprovalProcess";
	var result = quickAjaxPost(url, false, map);
	var depPos = jQuery.parseJSON(result);
	// 循环问题，赋值节点属性
	if (depPos != null) {
		var zNodes = [];
		var depID = '';
		if(type == 'update' && $('#accIDupdate').val() != ''){
			for ( var i = 0; i < depPos.length; i++) {
				if($('#accIDupdate').val() == depPos[i].id && depPos[i].pid != 'p-0'){
					depID = depPos[i].pid.split('-')[1];
				}
			}
		}
		
		for ( var i = 0; i < depPos.length; i++) {
			if(depPos[i].id.split('-')[1] == depID && type == 'update' && depPos[i].pid == 'p-0'){
				zNodes.push( {
					"id" : depPos[i].id,
					"pId" : depPos[i].pid,
					"name" : depPos[i].name,
					"open" : true
				});
				continue;
			}
				zNodes.push( {
					"id" : depPos[i].id,
					"pId" : depPos[i].pid,
					"name" : depPos[i].name,
					"open" : false
				});
		}
	}
	if(type == 'add'){
		// 初始化树状结构
		zTreeObj = $.fn.zTree.init(loadtree, setting, zNodes);
	}
	if(type == 'update'){
		// 初始化树状结构
		zTreeObj = $.fn.zTree.init(loadtree, settingupdate, zNodes);
	}
};

// 设置树结构属性  setting为add设置
var zTreeObj, zNodes, setting = {
	view : {
		selectedMulti : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : ztreeClick
	// 点击节点调用方法
	}
};
//settingupdate为update设置
var settingupdate = {
		view : {
			selectedMulti : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : ztreeClickupdate
		// 点击节点调用方法
		}
	};

//树形结构点击事件
function ztreeClick(event, treeId, treeNode) {
	//获取选择值给表单赋值
	if (treeNode.pId == null || treeNode.pId.split('-')[1] == 0) {//点击部门
		//$('#typeID').val(treeNode.id);//分类编号
		$('#approverPositionadd').val('');
		$('#approverAccountadd').val('');
		$('#approverRealNameadd').val('');
		//alert(treeNode.name);alert(treeNode.pId);alert(treeNode.id);
		/* var t1 = document.getElementById('departmentIDadd');   
	     for(i=0;i<t1.length;i++){//给select赋值  
	    	 var k = treeNode.id.split('-')[1];
	         if(k == t1.options[i].value){  
	             t1.options[i].selected=true;
	             setFormType(k);//级联工单类型选项
	         }  
	     }  */
	} else {//点击名字
		$('#approverRealNameadd').val(treeNode.name);
		//选中相应的部门
		/*var t1 = document.getElementById('departmentIDadd');   
	     for(i=0;i<t1.length;i++){//给select赋值  
	    	 var k = treeNode.pId.split('-')[1];
	         if(k == t1.options[i].value){  
	             t1.options[i].selected=true;
	             setFormType(k);//级联工单类型选项
	         }  
	     }  */
		//根据账户id获取账户信息(职位和账户)
		var map ={'id':treeNode.id,'type':2};
		var url=sybp() + "/config/addapprovalProcess";
		var account = quickAjaxPost(url, false, map);
		var account = jQuery.parseJSON(account);
		$('#approverAccountadd').val(account.loginName);
		$('#approverPositionadd').val(account.positionName);
		flagClear = true;//修改清空标识
		count ++;
	}
}

//树形结构点击事件
function ztreeClickupdate(event, treeId, treeNode) {
	//获取选择值给表单赋值
	if (treeNode.pId == null || treeNode.pId.split('-')[1] == 0) {//点击部门
		$('#approverPositionupdate').val('');
		$('#approverAccountupdate').val('');
		$('#approverRealNameupdate').val('');
	} else {//点击名字
		//根据账户id获取账户信息(职位和账户)
		var map ={'id':treeNode.id,'type':2};
		var url=sybp() + "/config/addapprovalProcess";
		var account = quickAjaxPost(url, false, map);
		var account = jQuery.parseJSON(account);
		$('#approverAccountupdate').val(account.loginName);
		$('#approverPositionupdate').val(account.positionName);
		$('#approverRealNameupdate').val(account.realName);//$('#approverRealNameupdate').val(treeNode.name);
		flagClear = true;//修改清空标识
		count ++;
	}
}

//根据部门级联显示工单类型,并查询显示相应部门下职位账户姓名
function setFormType(depID){
	if(depID == 0){
		$('#formTypeIDadd').empty();
	 	$('#formTypeIDadd').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	 	$('#approverPositionadd').val('');
	 	$('#approverAccountadd').val('');
	 	$('#approverRealNameadd').val('');
		return;
	}
	//根据部门显示相应工单类型
	//加载工单类型信息
	var map = {'type':2};
	var fromTypeList = quickAjaxPost(sybp() + '/config/findAllformType', false, map);
	fromTypeList = jQuery.parseJSON(fromTypeList);
	$('#formTypeIDadd').empty();
 	$('#formTypeIDadd').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<fromTypeList.length;i++){
		if(fromTypeList[i].departmentID == depID){
			var id = fromTypeList[i].id;
			var name = fromTypeList[i].formName;
		   $('#formTypeIDadd').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
		}
	}
	document.getElementById('formTypeIDadd').selectedIndex = 0;
	//判断是否为第一级流程
	if($('#flowNumberadd').val() == 1){
		$('#approverPositionadd').val(AutoSetPosition($('#departmentIDadd').val()));
	}
	//设置变量解决点击账户后又修改部门造成的表单数据(账户和姓名)未清空问题
	if(flagClear && count == 0){
		clearACC();
		flagClear = false;
	}
}
//根据工单类型赋值审核人职位
function changePosition(value){
	if(value == 1){//公共工单
		//清空职位值
		$('#approverPositionadd').val('');
		//显示审核人选择树结构
		$('#treeadd').show();
	}
	if(value == 2){//部门工单
		if($('#flowNumberadd').val() != 0){
			checkValue($('#flowNumberadd').val());
		}
		setPosValue($('#departmentIDadd').val());
	}
}
//根据职位id级联查询该职位的账户,并设置账户和姓名
function setAccount(posID){
	var map = {'id':posID};
	var dep = quickAjaxPost(sybp() + '/admin/findAllDepObjByPos', false, map);//根据职位id获取部门对象
	dep = jQuery.parseJSON(dep);
	$('#approverAccountadd').val(dep.departmentOwner);//账户
	$('#approverRealNameadd').val(dep.departmentOwnerName);//姓名
}
//根据部门id设置账户和姓名
function setAccByDepID(depID){
	
}
function setPos(depID){
	//根据部门id查询显示相应部门下职位
	var map = {'id':depID};
	var positions = quickAjaxPost(sybp() + '/admin/findAllPositonByDep', false, map);
	positions = jQuery.parseJSON(positions);
	$('#approverPositionadd').empty();
// 	$('#approverPositionadd').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<positions.length;i++){
		var id = positions[i].id;
		var name = positions[i].positionName;
	   $('#approverPositionadd').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
	}
	document.getElementById('approverPositionadd').selectedIndex = 0;
}
//根据部门id设置职位
function setPosValue(depID){
	var map = {'id':depID};
	var positions = quickAjaxPost(sybp() + '/admin/findAllPositonByDep', false, map);
	positions = jQuery.parseJSON(positions);
	for(var i=0;i<positions.length;i++){
		var id = positions[i].id;
		var name = positions[i].positionName;
		if(depID == positions[i].departmentID && (name == '部门经理' || name == '主管' || name == '总经理')){
			$('#approverPositionadd').val(name);
			//设置隐藏域？
		}
	}
}
//一级流程自动设置职位
function AutoSetPosition(depID){
	var position = quickAjaxPost(sybp() + '/admin/findAllAutoSetPositonOneLevel?id='+depID, false, null);
	return position;
}

function clearACC(){
	//流程编号为>=2如果账户和姓名有值就清空账户和姓名
	$('#approverAccountadd').val('');//账户
	$('#approverRealNameadd').val('');//姓名
}
//判断流程编号
function checkValue(fn){
	if(fn == 0){//请选择
		$('#approverPositionadd').val('');//职位
		$('#approverAccountadd').val('');//账户
		$('#approverRealNameadd').val('');//姓名
		$('#constraintsadd').val('');//约束
		document.getElementById('formTypeIDadd').selectedIndex = 0;
		document.getElementById('departmentIDadd').selectedIndex = 0;
		$('#treeadd').hide();
	}
	if(fn == 1){//一级流程
		$('#approverPositionadd').val(AutoSetPosition($('#departmentIDadd').val()));
		$('#approverAccountadd').val('');//账户
		$('#approverRealNameadd').val('');//姓名
		$('#constraintsadd').val('');//约束
//		document.getElementById('formTypeIDadd').selectedIndex = 0;
//		document.getElementById('departmentIDadd').selectedIndex = 0;
		
		
	}
	if(fn >= 2){//二级流程
		$('#treeadd').show();
	}
}

//判断流程编号
function checkValueupdate(fn){
	if(fn == 0){//请选择
		$('#approverPositionupdate').val('');//职位
		$('#approverAccountupdate').val('');//账户
		$('#approverRealNameupdate').val('');//姓名
		$('#constraintsupdate').val('');//约束
		document.getElementById('formTypeIDupdate').selectedIndex = 0;
		document.getElementById('departmentIDupdate').selectedIndex = 0;
		$('#treeupdate').hide();
	}
	if(fn == 1){//一级流程
		$('#approverPositionupdate').val(AutoSetPosition($('#departmentIDupdate').val()));
		$('#approverAccountupdate').val('');//账户
		$('#approverRealNameupdate').val('');//姓名
		$('#constraintsupdate').val('');//约束
//		document.getElementById('formTypeIDadd').selectedIndex = 0;
//		document.getElementById('departmentIDadd').selectedIndex = 0;
		
		
	}
	if(fn >= 2){//二级流程
		//$('#approverPositionadd').val('');
		$('#treeupdate').show();
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
//添加工单审核流程前数据检查
function checkData(){
	if ($('#departmentIDadd').val() == '0') {
		chexkwarn('departmentIDadd', '请选择部门名称！');
		return;
	}
	
	if ($('#formTypeIDadd').val() == '0') {
		chexkwarn('formTypeIDadd', '工单名称不能为空!请确认是否存在此工单！');
		return;
	}
	var flowNumber = $('#flowNumberadd').val();
	var formType = $('#formTypeIDadd').val();
	//非空验证
	if (flowNumber == '0') {
		chexkwarn('flowNumberadd', '审核流程编号不能为空！');
		return;
	}
	//根据流水号和工单类型验证是否已经存在该审核流程
//	var url = sybp() +'/config/addapprovalProcess'; 
//	var map = {'type':3,'flowNumber':flowNumber,'formTypeID':formType};
//	var result = quickAjaxPost(url, false, map);
//	if(result == 'true'){
//		$('#formTypeIDadd').poshytip('destroy');
//		chexkwarn('formTypeIDadd', '该工单对应审核流程已经存在！');
//		return;
//	}
	
	//根据流程编号
	if(flowNumber == '1'){
		var flag = checkIsNull_1();//一级流程非空验证
		if(!flag){
			return;
		}
		//清空账户和姓名
		$('#approverAccountadd').val('');//账户
		$('#approverRealNameadd').val('');//姓名
	}
	
	if(flowNumber >= '2'){
		var flag = checkIsNull_2();//二级流程非空验证
		if(!flag){
			return;
		}
	}
	
	//提交数据添加审核流程
	var url = sybp() +'/config/addapprovalProcess';
	$('#addForm').attr('action',url);
	$('#addForm').submit();
	
}
//一级审核非空验证
function checkIsNull_1(){
	$('#departmentIDadd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentIDadd').val() == '0') {
		chexkwarn('departmentIDadd', '请选择部门名称！');
		return false;
	}
	
	if ($('#formTypeIDadd').val() == '0') {
		chexkwarn('formTypeIDadd', '工单名称不能为空!请确认是否存在此工单！');
		return false;
	}
	return true;
}
//一级审核非空验证
function checkIsNullupdate_1(){
	if ($('#approverPositionupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('approverPositionupdate', '职位不能为空！');
		return false;
	}
	
	return true;
}


//二级审核非空验证
function checkIsNull_2(){
	if ($('#departmentIDadd').val() == '0') {
		chexkwarn('departmentIDadd', '请选择部门名称！');
		return false;
	}
	
	if ($('#formTypeIDadd').val() == '0') {
		chexkwarn('formTypeIDadd', '工单名称不能为空！请确认是否存在此工单！');
		return false;
	}
	
	if ($('#approverAccountadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('approverAccountadd', '审核人信息不能为空！请选择审核人');
		return false;
	}
	if ($('#approverRealNameadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('approverRealNameadd', '审核人信息不能为空！请选择审核人');
		return false;
	}
	return true;
}
//二级审核非空验证
function checkIsNullupdate_2(){
	if ($('#approverAccountupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('approverAccountupdate', '审核人信息不能为空！请选择审核人');
		return false;
	}
	if ($('#approverRealNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('approverRealNameupdate', '审核人信息不能为空！请选择审核人');
		return false;
	}
	return true;
}

//设置记录原始值重名验证判断
var flowNumberold = '';var formTypeIDold = '';
//进入工单审核流程更新界面
function update(id){
	//根据审核流程id获取审核流程对象
	var map = {'id':id,'type':1};
	var approvalProcess = quickAjaxPost(sybp() + '/config/findAllapprovalProcess', false, map);
	approvalProcess = jQuery.parseJSON(approvalProcess);
	//赋值隐藏域
	$('#idupdate').val(approvalProcess.id);
	//加载部门信息，填充部门选项
	var map = {'type':3};
	var deps = quickAjaxPost(sybp() + '/info/findAlldepartment', false, map);
	deps = jQuery.parseJSON(deps);
	$('#departmentIDupdate').empty();
 	$('#departmentIDupdate').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<deps.length;i++){
		var id = deps[i].id;
		var name = deps[i].departmentName;
	   $('#departmentIDupdate').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
	}
	//给部门表单赋值
	 var t1 = document.getElementById("departmentIDupdate");   
	 var depID;
    for(i=0;i<t1.length;i++){//给select赋值  
        if(approvalProcess.departmentName == t1.options[i].innerText){  
            t1.options[i].selected=true;
            depID = t1.options[i].value;
        }  
    }  
	//根据部门选项值获取所有工单类型
    var map = {'type':2};
	var fromTypeList = quickAjaxPost(sybp() + '/config/findAllformType', false, map);
	fromTypeList = jQuery.parseJSON(fromTypeList);
	$('#formTypeIDupdate').empty();
 	$('#formTypeIDupdate').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<fromTypeList.length;i++){
		if(fromTypeList[i].departmentID == depID){
			var id = fromTypeList[i].id;
			var name = fromTypeList[i].formName;
		   $('#formTypeIDupdate').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
		}
	}
	$('#approverPositionupdate').val(approvalProcess.approverPosition);//职位
	$('#approverAccountupdate').val(approvalProcess.approverAccount);//账户
	$('#approverRealNameupdate').val(approvalProcess.approverRealName);//姓名
	$('#constraintsupdate').val(approvalProcess.constraints);//约束
	$('#formTypeIDupdate').val(approvalProcess.formTypeID);
	//根据账户查找账户id赋值给隐藏域账户id
	var map = {'type':1,'loginName':approvalProcess.approverAccount};
	var accID = quickAjaxPost(sybp() + '/config/updateapprovalProcess', false, map);
	$('#accIDupdate').val(accID);
	//初始化部门职位树形结构
	loadModule($('#ztreeupdate'),'update');
	//赋值其他表单
	$('#flowNumberupdate').val(approvalProcess.flowNumber);//流程编号
	//如果是一级流程不显示树
	if($('#flowNumberupdate').val() == 1){
		$('#treeupdate').hide();
	}
	$('#treeupdate').show();
	//记录原始值
	flowNumberold = approvalProcess.flowNumber;
	formTypeIDold = approvalProcess.formTypeID;
	popquestion('update', '修改工单审核流程');
}
//更新界面部门表单联动
function setFormTypeupdate(depValue){
	setformTypeUpdate(depValue);
}
//设置
function setformTypeUpdate(depValue){
	if(depValue == 0){
		$('#formTypeIDupdate').empty();
	 	$('#formTypeIDupdate').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	 	$('#approverPositionupdate').val('');
	 	$('#approverAccountupdate').val('');
	 	$('#approverRealNameupdate').val('');
		return;
	}
	//根据部门显示相应工单类型
	//加载工单类型信息
	var map = {'type':2};
	var fromTypeList = quickAjaxPost(sybp() + '/config/findAllformType', false, map);
	fromTypeList = jQuery.parseJSON(fromTypeList);
	$('#formTypeIDupdate').empty();
 	$('#formTypeIDupdate').append('<option value=\'0\' selected=\'selected\'>请选择<\/option>');
	for(var i=0;i<fromTypeList.length;i++){
		if(fromTypeList[i].departmentID == depValue){
			var id = fromTypeList[i].id;
			var name = fromTypeList[i].formName;
		   $('#formTypeIDupdate').append('<option value=\'' + id+ '\'>' + name + '<\/option>');
		}
	}
	document.getElementById('formTypeIDupdate').selectedIndex = 0;
	//判断是否为第一级流程
	if($('#flowNumberupdate').val() == 1){
		$('#approverPositionupdate').val(AutoSetPosition($('#departmentIDupdate').val()));
	}
	//设置变量解决点击账户后又修改部门造成的表单数据(账户和姓名)未清空问题
	if(flagClear && count == 0){
		clearACC();
		flagClear = false;
	}
}

//更新数据前检查数据
function checkUpdateData(){
	if ($('#departmentIDupdate').val() == '0') {
		chexkwarn('departmentIDupdate', '请选择部门名称！');
		return;
	}
	
	if ($('#formTypeIDupdate').val() == '0') {
		chexkwarn('formTypeIDupdate', '工单名称不能为空!请确认是否存在此工单！');
		return;
	}
	var flowNumber = $('#flowNumberupdate').val();
	var formType = $('#formTypeIDupdate').val();
	//非空验证
	if (flowNumber == '0') {
		chexkwarn('flowNumberupdate', '审核流程编号不能为空！');
		return;
	}
	
	//根据流水号和工单类型验证是否已经存在该审核流程
	//判断是否是原值
//	if(flowNumberold != flowNumber || formType != formTypeIDold){
//		var url = sybp() +'/config/addapprovalProcess'; 
//		var map = {'type':3,'flowNumber':flowNumber,'formTypeID':formType};
//		var result = quickAjaxPost(url, false, map);
//		if(result == 'true'){
//			chexkwarn('formTypeIDupdate', '该工单对应审核流程已经存在！');
//			return;
//		}
//	}
	//根据流程编号
	if(flowNumber == '1'){
		var flag = checkIsNullupdate_1();//一级流程非空验证
		if(!flag){
			return;
		}
		//清空账户和姓名
		$('#approverAccountupdate').val('');//账户
		$('#approverRealNameupdate').val('');//姓名
	}
	if(flowNumber >= '2'){
		var flag = checkIsNullupdate_2();//二级流程非空验证
		if(!flag){
			return;
		}
	}
	
	if(!confirm('确认更新该条审核流程吗？')){
		return;
	}
	//赋值隐藏域
	
	//提交数据添加审核流程
	var url = sybp() +'/config/updateapprovalProcess';
	$('#updateForm').attr('action',url);
	
	$('#updateForm').submit();
}

//项目路径
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
//dialog
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
//ajax发送请求
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