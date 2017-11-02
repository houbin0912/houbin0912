
// 初次访问页面模态窗隐藏
$(function() {
	init_DataTables();
	$('#addModal').modal({
		keyboard : true,
		show : false
	});
	$('#updateModal').modal({
		keyboard : true,
		show : false
	});
	
	$('#allInfoModal').modal({
		keyboard : true,
		show : false
	});
});
/*  详情回显 */
function allInfo(data) {
	$("#acctErrId").val(data.acctErrId);
	$("#channelSeqNo").text(data.channelSeqNo);
	$("#sysSeqNo").text(data.sysSeqNo);
	$("#proofType").text(data.proofType);
	$("#orgCode").text(data.orgCode);
	$("#inferReason").text(data.inferReason);
	$("#status").text(data.status);
	$("#createTime").text(data.createTimes);
	$("#reportTime").text(data.reportTimes);
	$("#processTime").text(data.processTimes);
	getOrgSelect(data.orgId, "updateOrgSelect");
	$("#errReason").text(data.errReason);
	$("#processResult").text(data.processResult);
	$('#allInfoModal').modal('show');
}
/*编辑*/
function edit(data) {
	clearErrorLable();
	$("#UpdateAcctErrId").val(data.acctErrId);
	$("#UpdateErrReason").val(data.errReason);
	$("#UpdateProcessResult").val(data.processResult);
	getAcctSelect(data.processResult,"UpdateProcessResult");
	$('#updateModal').modal('show');
}
/* 弹窗操作结束 */

/* 下拉窗 开始 */
// 机构信息下拉窗
function getOrgSelect(orgId, selectTabId) {
	$.post('/sys/org/selectAllOrg', function(result) {
		var data = [];
		for (var i = 0; i < result.length; i++) {
			data[i] = [ result[i].orgId, result[i].orgName ];
		}
		getSelect(data, orgId, selectTabId);
	});
}

//是否错账下拉窗
function getAcctSelect(selectData, htmlId) {
	var acct = [[ '0','是'] ,[ '1','不是']];
	if(selectData == '是'){
		selectData = '0';
	}
	if(selectData == '不是'){
		selectData = '1';
	}
	getSelect(acct,selectData,htmlId)
}

/* 下拉窗 结束 */
/* 提交表单 开始 */
// 修改页面表单
function dosubmitUpdate() {
	$("#updateForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			processResult : "required",
			errReason : "required"
		},
		messages : {
			processResult : "请填写是否错账",
			errReason : "请填写错账原因"
		},
		submitHandler : function() {
			$('#updateModal').modal('toggle');
			$.ajax({
				url : "/sys/acct/update",
				type : "POST",
				data : $("#updateForm").serialize(),
				cache : false,
				processData : false,
				success : function(result) {
					info(result, "修改");
				}
			});
		}
	});
}
// 查询表单提交
/* 查询筛选 */
function query() {
	tableReload(0, 0);// 方法在custom.js中
}
function artificialQuery(id){
	
}

/* 提交表单结束 */
/* 校验 */
/* 机构信息在改编后校验是否为空，并同步修改下面角色信息 */
function checkChangeOrg(orgId, errorTabId, selectTabId) {
	if (orgId == '' || orgId == null) {
		$('#' + errorTabId).text("不能为空");
		$("#" + selectTabId).html('');
	} else {
		getRoleSelect(orgId, '', selectTabId)
	}
}

/* 验证登录名是否唯一 */
function isExist() {
	var loginName = $("#addLoginName").val();
	if (loginName == '') {
		$("#addLoginNameError").css("color", "red");
		$("#addLoginNameError").text("内容不能为空");
	} else {
		$.post('/sys/user/check/loginName', {
			"loginName" : loginName
		}, function(result) {
			if (result.success == true) {
				$("#addLoginNameError").css("color", "red");
				$("#addLoginNameError").text(result.successMessage);
			} else {
				$("#addLoginNameError").css("color", "green");
				$("#addLoginNameError").text(result.errorMessage);
			}
		});
	}
}

/* DATA TABLES */
var table;
function init_DataTables() {
	if(typeof ($.fn.DataTable) === 'undefined'){
		return; 
	}
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/acct/list",
	        type:"POST",
	        data:{
	        	channelSeqNo:$("#query_channelSeqNo").val(),
	        }
	    },
	  iDisplayLength:3,
	  bPaginate:true,
	  processing : true,
	  serverSide : true,
	  scrollX : true,
	  ordering: false,
	  pageLength: 3,
	  pagingType: "full_numbers",
	  lengthMenu: [ 3, 8, 15, 20, 30, 50 ],
	  columns: [
		    { "data": "acctErrId"},
		    { "data": "channelSeqNo"},
            { "data": "sysSeqNo"},
            { "data": "proofType" },
            { "data": "orgCode" },
            { "data": "inferReason" },
            { "data": "status" },
            { "data": "processResult" },
            { "data": "errReason" },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 9,
                render: function (data, type, row, meta) {
                	var id = '"' + row.acctErrId + '"';
                	var json = JSON.stringify(row);
                    var html = "<a href='javascript:void(0);'  onclick='edit("+ json + ")' style='margin: 1px' class='btn-sm btn-primary'>编辑</a>"
                	html += "<a href='javascript:void(0);'   onclick='allInfo("+ json + ")'  style='margin: 1px' class='btn-sm btn-primary'>详情</a>"
                	html += "<a href='javascript:void(0);'   onclick='artificialQuery("+ id + ")' style='margin: 1px' class='btn-sm btn-primary'>人工查询</a>"
                	return html;
                }
            },					            
            {
                targets: 0,
                render: function (data, type, row, meta) {
                	var id = row.acctErrId ;
                	return '<input type="checkbox" name="chooseId" value="'+ id + '"/>';
                }
            },
        ],
	  dom: '<"right">rt<"bottom"lp><"clear">',
	  language: {
	        "sProcessing": "处理中...",
	        "sLengthMenu": "显示 _MENU_ 项结果",
	        "sZeroRecords": "没有匹配结果",
	        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
	        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
	        "sInfoPostFix": "",
	        "sSearch": "搜索:",
	        "sUrl": "",
	        "sEmptyTable": "表中数据为空",
	        "sLoadingRecords": "载入中...",
	        "sInfoThousands": ",",
	        "oPaginate": {
	            "sFirst": "首页",
	            "sPrevious": "上页",
	            "sNext": "下页",
	            "sLast": "末页"
	        },
	        "oAria": {
	            "sSortAscending": ": 以升序排列此列",
	            "sSortDescending": ": 以降序排列此列"
	        }
	    }
	});
};
			
function tableReload(start,page){
	var info = table.page.info();
	var channelSeqNo = $("#query_channelSeqNo").val();
    var sysSeqNo = $("#query_sysSeqNo").val();
	table.page.info().start = start;
    var param = {
        "channelSeqNo": channelSeqNo,
        "sysSeqNo": sysSeqNo,
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}
			
function getTable(){
	return table;
}
