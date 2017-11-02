
// 初次访问页面模态窗隐藏
$(function() {
	init_DataTables();
	$('#addModal').modal({
		keyboard : true,
		show : false
	});
	$('#statusModal').modal({
		keyboard : true,
		show : false
	});
	$('#allInfoModal').modal({
		keyboard : true,
		show : false
	});
	$('#editdModal').modal({
		keyboard : true,
		show : false
	});
});
/* 弹窗操作 开始 */
/* 添加窗口 */
function addModel() {
	clearErrorLable();
	$('#addForm')[0].reset();
	getStatusSelect(null,"addJobStatus");
	$('#addModal').modal('show');
}
/* 单个删除提示窗 */
function deleteModal(data) {
	$("#delete_jobId").val(data.jobId);
	$("#delete_jobName").val(data.jobName);
	$("#delete_jobGroupName").val(data.jobGroupName);
	$("#delete_jobTriggerName").val(data.jobTriggerName);
	$("#delete_jobTriggerGroupName").val(data.jobTriggerGroupName);
	$('#DeleteModal').modal('show');
}
//删除表单提交
/* 单个删除 */
function delOne() {
	$('#DeleteModal').modal('toggle');
	var jobId = $("#delete_jobId").val();
	var jobName = $("#delete_jobName").val();
	var jobGroupName = $("#delete_jobGroupName").val();
	var jobTriggerName = $("#delete_jobTriggerName").val();
	var jobTriggerGroupName = $("#delete_jobTriggerGroupName").val();
	$.post('/sys/quartz/deleteQuartzJob',{
		"jobId":jobId,
		"jobName":jobName,
		"jobGroupName":jobGroupName,
		"jobTriggerName":jobTriggerName,
		"jobTriggerGroupName":jobTriggerGroupName,
	}, function(result) {
		info(result, "删除");
	});
}
/* 编辑模态窗 */
function editdModal(data) {
	clearErrorLable();
	$("#updateJobId").val(data.jobId);
	$("#updateJobName").val(data.jobName);
	$("#updateJobGroupName").val(data.jobGroupName);
	$("#updateJobTriggerName").val(data.jobTriggerName);
	$("#updateJobTriggerGroupName").val(data.jobTriggerGroupName);
	$("#updateJobImplClass").val(data.jobImplClass);
	$("#updateCronExpression").val(data.cronExpression);
	getStatusSelect(data.jobStatus,"updateJobStatus");
	$("#updateJobDesc").val(data.jobDesc);
	$('#editdModal').modal('show');
}

//状态下拉窗
function getStatusSelect(selectData, htmlId) {
	var status = [[ '0','启用'] ,[ '1','禁用']];
	if(selectData == '启用'){
		selectData = '0';
	}
	if(selectData == '禁用'){
		selectData = '1';
	}
	getSelect(status,selectData,htmlId)
}
//修改任务定时器表达式表单提交
function editCorn() {
	$('#editCorndModal').modal('toggle');
	var jobId = $("#editCornId").val();
	var corn = $("#editCronExpression").val();
	$.post('/sys/quartz/updateCorn', {
		"jobId" : jobId,
		"corn" : corn
	}, function(result) {
		info(result, "修改表达式");
	});
}
/*检测任务名称和任务组名唯一*/
function checkJobNameAndGroup(action){
	$("#" + action + "JobGroupNameError").show();
	var jobName = $("#" + action + "JobName").val();
	var jobGroupName = $("#" + action + "JobGroupName").val();
	var jobId = $("#" + action + "JobId").val();
	$.post('/sys/quartz/checkJobNameAndGroup', {
		"jobId":jobId,
		"jobName":jobName,
		"jobGroupName":jobGroupName
	}, function(result) {
		if (result.success == true) {
			$("#" + action + "JobGroupNameError").css("color", "red");
			$("#" + action + "JobGroupNameError").text(result.successMessage);
		}else{
			$("#" + action + "JobGroupNameError").text("");
		}
	});
}
/*检测触发器名称和触发器组唯一*/
function checkTriggerNameAndGroup(action){
	$("#" + action + "JobTriggerGroupNameError").show();
	var jobId = $("#" + action + "JobId").val();
	var jobTriggerName = $("#" + action + "JobTriggerName").val();
	var jobTriggerGroupName = $("#" + action + "JobTriggerGroupName").val();
	$.post('/sys/quartz/checkTriggerNameAndGroup', {
		"jobId":jobId,
		"jobTriggerName":jobTriggerName,
		"jobTriggerGroupName":jobTriggerGroupName
	}, function(result) {
		if (result.success == true) {
			$("#" + action + "JobTriggerGroupNameError").css("color", "red");
			$("#" + action + "JobTriggerGroupNameError").text(result.successMessage);
		} else{
			$("#" + action + "JobTriggerGroupNameError").text("");
		}
	});
}

/* 提交表单 开始 */
// 校验新增页面表单
function dosubmitAdd() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			jobName : "required",
			jobGroupName : "required",
			jobTriggerName : "required",
			jobTriggerGroupName : "required",
			jobImplClass : "required",
			cronExpression : "required",
			jobStatus : "required",
		},
		messages : {
			jobName : "请输入任务名称",
			jobGroupName : "请输入任务组名",
			jobTriggerName : "请输入触发器名称",
			jobTriggerGroupName : "请输入触发器组",
			jobImplClass : "请输入父类包路径",
			cronExpression : "请输入定时表达式",
			jobStatus : "请输入任务状态"
		},
		submitHandler : function() {
			if($("#addJobGroupNameError").text() != ''){
				return ;
			}
			if($("#addJobTriggerGroupNameError").text() != ''){
				return ;
			}
			
			$('#addModal').modal('toggle');
			$.ajax({
				url : "/sys/quartz/addQuartzJob",
				type : "POST",
				data : $("#addForm").serialize(),
				cache : false,
				processData : false,
				success : function(result) {
					info(result, "添加");
				}
			});
		}
	});
}
// 修改页面表单
function dosubmitUpdate() {
	$("#updateForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			jobName : "required",
			jobGroupName : "required",
			jobTriggerName : "required",
			jobTriggerGroupName : "required",
			jobImplClass : "required",
			cronExpression : "required",
			jobStatus : "required",
		},
		messages : {
			jobName : "请输入任务名称",
			jobGroupName : "请输入任务组名",
			jobTriggerName : "请输入触发器名称",
			jobTriggerGroupName : "请输入触发器组",
			jobImplClass : "请输入父类包路径",
			cronExpression : "请输入定时表达式",
			jobStatus : "请输入任务状态"
		},
		submitHandler : function() {
			if($("#updateJobGroupNameError").text() != ''){
				return ;
			}
			if($("#updateJobTriggerGroupNameError").text() != ''){
				return ;
			}
			$('#editdModal').modal('toggle');
			$.ajax({
				url : "/sys/quartz/updateQuartzJob",
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

// 批量操作表单
/* 操作事件 */
function action(url, infoAction) {
	var ids = '';
	$("input:checkbox[name='chooseId']:checked").each(function(index, elem) {
		ids += $(this).val() + ",";
	});
	if (ids == '') {
		info("tips", "请选着至少一条数据");
	} else {
		$.post(url, {
			"userIds" : ids
		}, function(result) {
			info(result, infoAction);
		});
	}
}
/* 提交表单结束 */
/*修改状态提示窗*/
function updateJobStatusModel(status,infoAction,data){
	$('#statusModal').modal('show');
}
/*修改状态异步提交*/
function updateJobStatus(status,infoAction,data){
	$.post('/sys/quartz/updateJobStatus', {
		"jobId":data.jobId,
		"jobStatus":status,
/*		"cronExpression":data.cronExpression,
		"jobName":data.jobName,
		"jobGroupName":data.jobGroupName,
		"jobTriggerName":data.jobTriggerName,
		"jobTriggerGroupName":data.jobTriggerGroupName,*/
	}, function(result) {
		info(result,infoAction);
	});
}
/*查看详情*/
function allInfo(data){
    $("#InfoJobName").text(data.jobName);
    $("#InfoJobGroupName").text(data.jobGroupName);
    $("#InfoJobTriggerName").text(data.jobTriggerName);
    $("#InfoJobTriggerGroupName").text(data.jobTriggerGroupName);
    $("#InfoCronExpression").text(data.cronExpression);
    $("#InfoJobStatus").text(data.jobStatus);
    $("#InfoJobDesc").text(data.jobDesc);
    $("#InfoCreatedates").text(data.createdates);
    $("#InfoLastupdatetimes").text(data.lastupdatetimes);
	$('#allInfoModal').modal('show');
}

/* DATA TABLES */
var table;
function init_DataTables() {
	if( typeof ($.fn.DataTable) === 'undefined'){ return; }
	console.log('init_DataTables');
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/quartz/list",
	        type:"POST",
	        data:{
	        	jobTriggerName:$("#queryJobTriggerName").val()
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
		    { "data": "jobId"},
            { "data": "jobName"},
            { "data": "jobGroupName" },
            { "data": "jobTriggerName" },
            { "data": "jobTriggerGroupName" },
            { "data": "cronExpression" },
            { "data": "jobStatus" },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 7,
                render: function (data, type, row, meta) {
                	var id = '"' + row.jobId + '"';
                	var json = JSON.stringify(row);
                	 var html = '';
                	if(row.jobStatus == "启用"){
                		html += "<a href='javascript:void(0);'  onclick='updateJobStatus("+'"' + 1 + '"'+","+'"禁用"'+","+ json + ")' style='margin: 1px' class='btn-sm btn-primary'>禁用</a>"
                	}else{
                		html += "<a href='javascript:void(0);'  onclick='updateJobStatus("+'"' + 0 + '"'+","+'"启用"'+","+ json + ")' style='margin: 1px' class='btn-sm btn-primary'>启用</a>"
                		html += "<a href='javascript:void(0);'   onclick='editdModal("+ json + ")'  style='margin: 1px' class='btn-sm btn-primary'>修改</a>"
                		html += "<a href='javascript:void(0);'   onclick='deleteModal("+ json + ")'  style='margin: 1px' class='btn-sm btn-primary'>删除</a>"
                	}
                	html += "<a href='javascript:void(0);'  onclick='allInfo("+ json + ")' style='margin: 1px' class='btn-sm btn-primary'>详情</a>"
                	return html;
                }
            },					            
            {
                targets: 0,
                render: function (data, type, row, meta) {
                	var id = row.jobId ;
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
	var jobName = $("#queryJobName").val();
	var jobGroupName = $("#queryJobGroupName").val();
	var jobTriggerName = $("#queryJobTriggerName").val();
	var jobTriggerGroupName = $("#queryJobTriggerGroupName").val();
	table.page.info().start = start;
    var param = {
        "jobTriggerName": jobTriggerName,
        "jobName": jobName,
        "jobGroupName": jobGroupName,
        "jobTriggerGroupName": jobTriggerGroupName
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}
			
function getTable(){
	return table;
}