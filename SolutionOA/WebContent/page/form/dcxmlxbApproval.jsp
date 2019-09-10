<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.solution.entity.Account,com.solution.entity.ApprovalProcessRecord,com.solution.entity.ApprovalProcess" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagebar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dcxmlxb.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/artdialog/skins/aero.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/module/poshytip/src/tip-violet/tip-violet.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/module/ztree/css/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dcxmlxb.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/artdialog/artDialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/poshytip/src/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
	var recordIDall = 0;//当前记录id
	var remarkflag = 0;//记录remark的id序号
	//var recordIDApprover = 0;//申请人记录id
	//进入查看审核页面
	function approval(recordID,formTypeID,formID,formNumber,apptime,flowNumber,remark,depName,posName,name){
		//记录当前记录id
		recordIDall = recordID;
		//如果初始状态为等待修改状态为正在审核
		//var status = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb?type=1&id=1&recordID='+recordID, false, null);
		//获取查看审核页面信息
		var dcxmlxb = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb?type=2&formNumber='+formNumber, false, null);
		var dcxmlxb = jQuery.parseJSON(dcxmlxb);
		$('#titletime').text('时间:'+dcxmlxb.createTime.split('T')[0]);
		$('#formid').text('编号:'+dcxmlxb.formNumber);
		$('#projectName').text(dcxmlxb.projectName);
		$('#projectPorperty').text(dcxmlxb.projectPorperty);
		$('#servicePrice').text(dcxmlxb.servicePrice);
		$('#staffNumber').text(dcxmlxb.staffNumber);
		$('#serviceMonths').text(dcxmlxb.serviceMonths);
		$('#singlePrice').text(dcxmlxb.singlePrice);
		$('#royaltyRate').text(dcxmlxb.royaltyRate);
		$('#callTime').text(dcxmlxb.callTime.split('T')[0] + ' ' + dcxmlxb.callTime.split('T')[1]);
		$('#intentionCustomer').text(dcxmlxb.intentionCustomer);
		$('#remarks').text(dcxmlxb.remarks);
		$('#sendEmail').text(dcxmlxb.sendEmail);
		$('#contact').text(dcxmlxb.contact);
		$('#consultant').text(dcxmlxb.consultant);
		$('#pName').text(dcxmlxb.projectName);
		//进入审核查看详情界面去掉之前添加的tr
		for(var i=0;i<10;i++){
			//清空数据
			$('#tr'+i).remove();
			$('#tr'+i+''+i).remove();
		}
		//设置审核流程信息
		//获取工单流程中的审核记录
		var result = quickAjaxPost(sybp() + '/dcxmlxb/findApproval?id='+formTypeID +'&formID='+formID, false, null);
		console.info(result);
		result = jQuery.parseJSON(result);
		var flag = false;//默认工单未审核
		for(var i = 0;i < result.length;i++){
			//console.info(result[i].approverDepName +  result[i].approverPosition + result[i].approverRealName);
			//页面追加tr
			var table1 = $('#approvalTab'); 
			var firstTr = table1.find('tbody>tr:last'); 
			var row = $("<tr id='tr" + i + "'></tr>");
			var td = $("<td align='center'></td>"); 
			td.append("<span id='fw" + i + "'>" + result[i].flowNumber + "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("<span id='depName" + i + "'>" + result[i].approverDepName + "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("<span id='posName" + i + "'>" + result[i].approverPosition + "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("<span id='realName" + i + "'>" + result[i].approverRealName +  "</span>");
			row.append(td);
				td = $("<td align='center'></td>");
			//判断状态	
			var st = result[i].approverStatus;
			console.info('approverStatus:'+st);
			if(st == 0){
				td.append("<span id='status" + i +  "'>等待</span>");
			}
			if(st == 1){
				td.append("<span id='status" + i +  "'>正在审核</span>");
			}
			if(st == 2){
				td.append("<span id='status" + i +  "'>通过</span>");
			}
			if(st == 3){
				td.append("<span id='status" + i +  "'>拒绝</span>");
			}
			row.append(td);
				td = $("<td align='center'></td>");
				if(st == 2 || st == 3){
					td.append("<span id='aTime" + i + "'>" + result[i].approverTime.split('T')[0] + " " + result[i].approverTime.split('T')[1] +  "</span>");
				}
			row.append(td);
				td = $("<td align='center'></td>");
				td.append("&nbsp;");
			row.append(td); 
			table1.append(row); 
			row = $("<tr id='tr" +　i　+ "" + i + "'></tr>"); 
				td = $("<td align='center'></td>");
				td.append("备注");
			row.append(td); 
				td = $("<td colspan='6'></td>");
				//判断是否审核，若为审核，此处表单可编辑？
				//获取当前用户真实姓名 注意:账户中真是名字和部门中负责人真实名字要一致(录入数据必须保证正确)
				var realName = quickAjaxPost(sybp() + '/dcxmlxb/godcxmlxb?type=5', false, null);//console.info(realName +  result[i].approverRealName);
				if(realName == result[i].approverRealName){
					if(st == '2' || st == '3'){
						//int sum = result.length;
						flag = true;
						//保证同一个人在不同审核流程中都有审核
						for(var j = 0;j < result.length;j++){
							if(j != i && result[i].flowNumber != result[j].flowNumber 
									&& result[i].id != result[j].id
									&& result[i].approverRealName == result[j].approverRealName 
									&& result[j].approverStatus == 1 || result[j].approverStatus == 0
									&& result[i].id == result[j].id){
								flag = false;console.info('不同审核流程有同一个审核人');
							}
						}
					}
					
					if(st == '1'){
						remarkflag = i;//记录当前用户审核时添加的备注的序列号
						td.append("<textarea name='remark" + i + "' id='remark" + i + "' cols='90' rows='2' onfocus='clearThis(this)' style='color:red;'>正在审核</textarea>");
	
					}
					if(st == '2' || st == '3' || st == '0'){
						td.append("<textarea readonly='readonly' name='remark" + i + "' id='remark" + i + "' cols='90' rows='2' style='color:green;font-weight:bold'>" +result[i].remark +"</textarea>");
					}
				}else{
					td.append("<textarea readonly='readonly' name='remark" + i + "' id='remark" + i + "' cols='90' rows='2' style='color:gray;font-weight:bold'>" +result[i].remark +"</textarea>");
				}
			row.append(td); 
			table1.append(row); 
		}
		//申请人
		$('#departmentName').text(depName);
		$('#positionName').text(posName);
		$('#realName').text(name);
		$('#finished').hide();$('#unfinish').hide();
		if(flag){//已审核
			$('#finished').show();$('#unfinish').hide();
		}else{//未审核
			$('#finished').hide();$('#unfinish').show();
		}
		popquestion('tab', '发起审核页面');
	}
	function clearThis(text){
		$(text).val('');
	}
	//审核确认
	function applyForApproval(value){
		if(value == 2){//通过
			if(!checkData()){
				return;
			}
		}

		if(value == 3){//拒绝
			if(!checkData()){
				return;
			}
		}
		
		//验证数据
		//alert('add');
		//获取备注
		var remark = $('#remark' + remarkflag).val().replace(/^ +| +$/g, '');
		//记录审核结果
		var map = {'remark':remark,'type':3,'recordID':recordIDall,'value':value};
		var result = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb', false, map);
		window.location.reload();
	}
	//修改审核
	/*function updateApproval(){
		//获取备注
		var remark = $('#causeRefuse').val().replace(/^ +| +$/g, '');
		//记录审核结果
		var value = 99;
		var map = {'remark':remark,'type':3,'recordID':recordIDall,'value':value};
		var result = quickAjaxPost(sybp() + '/dcxmlxb/approvalDcxmlxb', false, map);
		window.location.reload();
	}
	*/
	function checkData(){
		//非空验证
    	var flag1 = confirm('确定提交审核结果吗？');
    	if(!flag1){
    		return false;
    	}
		//数据有效性验证
		return true;
	}
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
	    	}, 1400);

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
</script>
<title>各种待审核信息</title>
</head>
<body>
<!-- list -->
<div class="tab-content default-tab" id="list">
各种待审核信息
<table style="font-size: 12px;">
	<thead>
		<tr>
		<td class="td3" style="width: 5%;">申请人部门</td>
			<td class="td3" style="width: 5%;">工单名称</td>
			<td class="td3" style="width: 5%;">工单编号</td>
			<td class="td3" style="width: 5%;">申请人职位</td>
			<td class="td3" style="width: 5%;">申请人姓名</td>
			<td class="td3" style="width: 5%;">申请时间</td>
			<td class="td3" style="width: 5%;">审核状态</td>
			<td class="td3" style="width: 5%;">审核时间</td>
			<td class="td3" style="width: 5%;">审核</td>
		</tr>
	</thead>
	<tbody>
		<%
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Account acc =(Account) session.getAttribute("accountInfo");
		String name = acc.getLoginName();
			List<ApprovalProcessRecord> list = (List<ApprovalProcessRecord>)request.getAttribute("approvalProcessRecordList");
		    if(list != null && list.size() > 0){
		    	int n = 0;
		       for(ApprovalProcessRecord kt:list){
		    	   if(kt.getApproverStatus() != 0){//1正在等待的审核人看不到工单申请
		    		   n = 0;
		    		   System.out.println("循环数据"+kt.getFormTypeID() + "...."+ kt.getFormID());
		    		   if(name.equals(kt.getApproverAccount())){
		    			   System.out.println("当前用户审核记录");
			    		  //不同审核流程同一个人审核 设置
			    		  boolean f = false;//默认不同审核流程同一个人审核全部通过
			    	   	  boolean t = true;//同一个人所有审核流程都通过
			    	   	  boolean m = false;//默认同一个人在一个审核流程
				    	   	for(int j=0;j<list.size();j++){//记录同一个人在不同审核流程
					    	   	 if(list.get(j).getFormTypeID() == kt.getFormTypeID() 
					    	   			   && list.get(j).getId() != kt.getId()
			    						   && list.get(j).getFormID() == kt.getFormID()
			    						   && list.get(j).getApproverAccount().equals(kt.getApproverAccount())
			    						   && list.get(j).getFlowNumber() != kt.getFlowNumber()
			    						   ){
					    	   		 m = true;
					    	   		System.out.println("记录同一个人在不同审核流程---------------");
					    	   		 break;
					    	   	 }
				    	   	}
				    	   	for(int j=0;j<list.size();j++){
				    	   	 if(list.get(j).getFormTypeID() == kt.getFormTypeID() 
		    						   && list.get(j).getFormID() == kt.getFormID()
		    						   && list.get(j).getApproverAccount().equals(kt.getApproverAccount())
		    						    && list.get(j).getFlowNumber() != kt.getFlowNumber()
		    						   ){
		    					  // m = false;
		    					   if((kt.getApproverStatus() != 2 || list.get(j).getApproverStatus() != 2 )){
		    						   t = false;System.out.println("t:"+t);
		    						   System.out.println("没有全部通过");
		    					   }else{
		    						   n++;
		    					   }
		    					   if(list.get(j).getApproverStatus() == 2 && kt.getApproverStatus() == 2){
		    						//   n ++;
		    					   }
		    				   }
				    	   	}
		    			   for(int j=0;j<list.size();j++){
		    				   System.out.println(list.get(j).getFormTypeID() + "---" + list.get(j).getFormID());
		    				   if(list.get(j).getFormTypeID() == kt.getFormTypeID() 
		    						   && list.get(j).getFormID() == kt.getFormID()
		    						   && list.get(j).getApproverAccount().equals(kt.getApproverAccount())
		    						   && list.get(j).getFlowNumber() != kt.getFlowNumber()
		    						 		){//同一个人不同审核流程
		    					//   m = false;
		    					   if(list.get(j).getApproverStatus() == 1 && kt.getApproverStatus() == 2){//不同审核流程同一个人审核 存在不全都是通过状态的 只显示最终状态
		    						   f = true;System.out.println("f:"+f);
		    					   }
		    				   }
		    				  
		    			   }
		    			   System.out.println(t+"1;"+f);
		    			   if(t && m){//不同审核流程同一个人审核全部通过只显示一条
		    				   System.out.println("全部通过");
		    				   System.out.println(1);
		    				   System.out.println("n:"+n);
		    			   		if(n > 1){
		    			   			continue;//?这里出问题了
		    			   		}
		    			   		
		    			   }
		    			   System.out.println(3);
		    			   if(f){
		    				   System.out.println(2);
		    				   continue;
		    			   }
		    			   System.out.println(t+"2;"+f);
		    		   }
		    		   System.out.println("1111111111");
		    		   System.out.println(kt.getFormTypeID() +":"+kt.getFormID());
			    	   Date at = kt.getApproverTime();
			    	   String attime = sdf.format(at);
			    	   Date applyTime = kt.getProposerTime();
			    	   String apptime = sdf.format(applyTime);
		%>
		<tr>
			<td><%=kt.getProposerDepartment()%></td>
			<td><%=kt.getFormName()%></td>
			<td><%=kt.getFormBornID()%></td>
			<td><%=kt.getProposerPosition()%></td>
			<td><%=kt.getProposerName()%></td>
			<td><%=apptime%></td>
			<td>
				<% 	if(kt.getApproverStatus() == 1){
				%>
					未审核
				<% }else if(kt.getApproverStatus() == 2 || kt.getApproverStatus() == 3){
				%>	
					已审核
				<%}%>	
			</td>
			<td>
				<%if(kt.getApproverStatus() == 1){
					%>	—:—:—
				<%}else{ %>
					<%=attime%>
				<%} %>
			</td>
			<td>
				<% 	if(kt.getApproverStatus() == 1){
					%>
						<img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="approval(<%=kt.getId()%>,<%=kt.getFormTypeID() %>,<%=kt.getFormID() %>,'<%=kt.getFormBornID()%>','<%=apptime%>',<%=kt.getFlowNumber() %>,'<%=kt.getRemark() %>','<%=kt.getProposerDepartment()%>','<%=kt.getProposerPosition()%>','<%=kt.getProposerName()%>');" title="审核" />
					<% }else if(kt.getApproverStatus() == 2 || kt.getApproverStatus() == 3){
					%>	
						<img src="${pageContext.request.contextPath}/images/edit.png" id="create" onclick="approval(<%=kt.getId()%>,<%=kt.getFormTypeID() %>,<%=kt.getFormID() %>,'<%=kt.getFormBornID()%>','<%=apptime%>',<%=kt.getFlowNumber() %>,'<%=kt.getRemark() %>','<%=kt.getProposerDepartment()%>','<%=kt.getProposerPosition()%>','<%=kt.getProposerName()%>');" title="查询详情" />
				<%}%>	
			</td>
		</tr>
		<%	
		   		}//1正在等待的审核人看不到工单申请
		
					}
		  		}else{
		%>
		<tr>
			<td colspan="7">&lt;<span id="" style="color: red;">页面没有数据</span>&gt;</td>		
		
		</tr> 
		<% }		    %>
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


<div class = "tab" id="tab" style="display: none;width: 835px;height: 775px;" >
<form id="approval">
<table class="table_border" id="approvalTab" style="font-size: 12px;background-color: #ccc;width: 800px;height: 500px;" border="1">
	<thead>
		<tr>
			<td colspan="7" align="center" style="font-size: 16px;"><span id="pName"></span>地产项目立项表</td>
		</tr>
		<tr>
			<td colspan="7" align="right" style="font-size: 14px;"><span id = "titletime" style="text-align: right"></span></td>
		</tr>
		<tr>
			<td colspan="3" align="left"><span id="formid"></span></td>
			<td colspan="4">&nbsp;</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td colspan="3" align="center">基础服务费部分</td>
            <td colspan="2" align="center">佣金部分</td>
		</tr>
        <tr>
        	<td width="72">项目名称</td>
			<td width="72">项目属性</td>
			<td width="172">基础服务费单价</td>
			<td width="172">坐席数</td>
			<td width="172">服务月数</td>
            <td width="172">单套结算单价(元/套)</td>
			<td width="172">单套房款佣金提成比例(%)</td>
        </tr>
         <tr>
        	<td><span id="projectName"></span></td>
            <td><span id="projectPorperty"></span></td>
            <td><span id="servicePrice"></span></td>
            <td><span id="staffNumber"></span></td>
            <td><span id="serviceMonths"></span></td>
            <td><span id="singlePrice"></span></td>
            <td><span id="royaltyRate"></span></td>
        </tr>
         <tr>
            <td width="72">起呼时间</td>
			<td width="172">意向客户数</td>
			<td width="172">数据筛选方向</td>
            <td width="172">意向客户发送地址:</td>
			<td width="172">联系人及电话</td>
			<td width="172">短信下发号码及联系人</td>
            <td>&nbsp;</td>
        </tr>
         <tr>
        	<td width="72"><span id="callTime"></span></td>
        	<td width="172"><span id="intentionCustomer"></span></td>
        	<td width="172"><span id="remarks"></span></td>
        	<td width="172"><span id="sendEmail"></span></td>
        	<td width="172"><span id="contact"></span></td>
        	<td width="172"><span id="consultant"></span></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
			<td align="center">申请人:</td>
			<td align="center"><span id="departmentName"></span></td>
			<td align="center"><span id="positionName"></span></td>
			<td align="center"><span id="realName"></span></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td align="center">审核流程:</td>
			<td align="center">部门</td>
			<td align="center">职位</td>
			<td align="center">姓名</td>
			<td align="center">审核状态</td>
			<td align="center">审核时间</td>
			<td>&nbsp;</td>
		</tr>
	</tbody>
	<tfoot>
		<tr id="finished">
			<td colspan="7" align="center">
			</td>
		</tr>
		<tr id="unfinish">
			<td colspan="7" align="center">
				<input type="button" value="通过" onclick="applyForApproval(2);" style="border: none;background-color:#ccc;font-size:14px; border-bottom-style:dashed;  height: 25px;font-weight: bolder"/>
				<input type="button" value="拒绝" onclick="applyForApproval(3);" style="border: none;background-color:#ccc;font-size:14px; border-bottom-style:dashed;  height: 25px;font-weight: bolder"/>
			</td>
		</tr>
	</tfoot>

</table>
	<input type="hidden" name="dcxmlxb.loginName" id="loginNameadd" value="" />
	<input type="hidden" name="dcxmlxb.formNumber" id="formNumberadd" value="" />
	<input type="hidden" name="approvalProcessRecord.formTypeID" id="formTypeIDadd" value="" />
	
</form>
</div>
</body>
</html>