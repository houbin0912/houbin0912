
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
	$('#detailsModal').modal({
		keyboard : true,
		show : false
	});
});
/* 弹窗操作 开始 */
/* 添加窗口 */
function addModel() {
	clearErrorLable();
	$('#addForm')[0].reset();
	getOrgSelect(null, "addOrgSelect");
	getSexSelect(null, "addSexSelect");
	$("#addInfo").text("提交");
	$('#addModal').modal('show');
}
// 修改窗口
/* 编辑回显 */
function edit(data) {
	clearErrorLable();
	$("#updateUserName").val(data.userName);
	$("#updateLoginName").val(data.loginName);
	$("#updateLoginName").attr("disabled", "disabled");
	$("#updateUserId").val(data.userId);
    $("#updateMail").val(data.email);
    $("#updatePhoneNumber").val(data.phoneNumber);
    isCheck("#updateReceiveEmail",data.receiveEmail);
    isCheck("#updateReceiveMessage",data.receiveMessage);
	$("#updateRemark").val(data.remark);
	getOrgSelect(data.orgId, "updateOrgSelect");
	getRoleSelect(data.orgId, data.roleId, "updateRoleSelect");
	getSexSelect(data.sex, "updateSexSelect");
	$('#updateModal').modal('show');
}

function isCheck(id,data){
	if(data == "接受"){
		$(id).attr("checked","checked");
	}else{
		$(id).removeAttr("checked");
	}
}

function details(data){
	$("#detailsUserName").text(data.userName);
	$("#detailsLoginName").text(data.loginName);
	$("#detailsUserId").text(data.userId);
    $("#detailsMail").text(data.email);
    $("#detailsSex").text(data.sex);
    $("#detailsOrgName").text(data.orgName);
    $("#detailsRoleName").text(data.roleName);
    $("#detailsStatus").text(data.status);
    $("#detailsReceiveEmail").text(data.receiveEmail);
    $("#detailsPhoneNumber").text(data.phoneNumber);
    $("#detailsReceiveMessage").text(data.receiveMessage);
	$("#detailsRemark").text(data.remark);
	$("#detailsCreatedates").text(data.createdates);
	$("#detailsLastupdatetimes").text(data.lastupdatetimes);
	$('#detailsModal').modal('show');
}
/* 单个删除 */
function del(id) {
	$("#delete_id").val(id);
	$('#DeleteModal').modal('show');
}
/* 编辑密码模态窗 */
function editPasswordModel(data) {
	$("#editPasswordId").val(data);
	$('#editPasswordModal').modal('show');
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
// 角色信息下拉窗
function getRoleSelect(orgId, roleId, selectTabId) {
	$.post('/sys/role/selectRoles', {
		"orgId" : orgId
	}, function(result) {
		var data = [];
		for (var i = 0; i < result.length; i++) {
			data[i] = [ result[i].roleId, result[i].roleName ];
		}
		getSelect(data, roleId, selectTabId);
	});
}

/* 下拉窗 结束 */

/* 提交表单 开始 */
// 校验新增页面表单

jQuery.validator.addMethod("mailCheck", function(value, element) {
    var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    return this.optional(element) || ( reg.test(value));
},"请输入正确格式的邮箱");

jQuery.validator.addMethod("phoneNumberCheck", function(value, element) {
    var length=value.length;
    var reg = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
    return this.optional(element) || (length==11 && reg.test(value));
},"请输入正确格式的电话号码");


function dosubmitAdd() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			userName : "required",
			loginName : "required",
			sex : "required",
			orgId : "required",
			roleId : "required",
            email:{
                required:true,
                mailCheck:true
            },
            phoneNumber : {
                required:true,
                phoneNumberCheck:true

            }
		},
		messages : {
			userName : "请输入用户名",
			sex : "请选择性别",
			orgId : "请选择机构",
			roleId : "请选择角色",
            email : "请输入一个有效的Email地址",
            phoneNumber: "请输入一个有效的联系电话"
		},
		submitHandler : function() {
			$('#addModal').modal('toggle');
			$.ajax({
				url : "/sys/user/add",
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
			userName : "required",
			sex : "required",
			orgId : "required",
			roleId : "required",
            email:{
                required:true,
                mailCheck:true
            },
            phoneNumber : {
                required:true,
                phoneNumberCheck:true

            }
		},
		messages : {
			userName : "请输入用户名",
			sex : "请选择性别",
			orgId : "请选择机构",
			roleId : "请选择角色",
            email : "请输入一个有效的Email地址",
            phoneNumber: "请输入一个有效的联系电话"
		},
		submitHandler : function() {
			$('#updateModal').modal('toggle');
			$.ajax({
				url : "/sys/user/update",
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
// 删除表单提交
/* 单个删除 */
function delOne() {
	$('#DeleteModal').modal('toggle');
	var ids = $("#delete_id").val();
	$.post('/sys/user/deleteByIds', {
		"userIds" : ids
	}, function(result) {
		info(result, "删除");
	});
}
// 修改密码表单提交
function editPassword() {
	$('#editPasswordModal').modal('toggle');
	var userId = $("#editPasswordId").val();
	var password = $("#editNewPassword").val();
	$.post('/sys/user/updatePwd', {
		"userId" : userId,
		"password" : password
	}, function(result) {
		info(result, "修改密码");
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
	var ids = [];
	$("input:checkbox[name='chooseId']:checked").each(function(index, elem) {
		ids[index]= $(this).val();
	});
	if (ids.length == 0) {
		info("tips", "请选着至少一条数据");
	} else {
		$.post(url, {
			"userIds" : ids.join(',')
		}, function(result) {
			info(result, infoAction);
		});
	}
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
	$("#addLoginNameError").show();
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
	if( typeof ($.fn.DataTable) === 'undefined'){ 
		return; 
	}
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/user/list",
	        type:"POST"
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
		    { "data": "userId"},
            { "data": "userName"},
            { "data": "loginName" },
            { "data": "email" },
            { "data": "phoneNumber" },
            { "data": "orgName" },
            { "data": "roleName" },
            { "data": "status" },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 8,
                render: function (data, type, row, meta) {
                	var id = '"' + row.userId + '"';
                	var json = JSON.stringify(row);
                    var html = "<a href='javascript:void(0);' style='margin: 1px' class='btn-primary btn-sm' onclick='edit("+ json + ")' >编辑</a>"
                    html += "<a href='javascript:void(0);' style='margin: 1px' class='btn-primary btn-sm' onclick='details("+ json + ")' >详情</a>"
                    html += "<a href='javascript:void(0);' style='margin: 1px'  class='btn-sm btn-primary'  onclick='del("+ id + ")' >删除</a>"
                	html += "<a href='javascript:void(0);' style='margin: 1px' class='btn-sm btn-primary' onclick='editPasswordModel("+ id + ")'  >修改密码</a>"
                	return html;
                }
            },					            
            {
                targets: 0,
                render: function (data, type, row, meta) {
                	var id = row.userId ;
                	return '<input type="checkbox" name="chooseId" value="'+ id + '"/>';
                }
            },
            { "orderable": false, "targets": 8 }
        ],
	  dom: '<"right">rt<"bottom"lp><"clear">',
	  responsive: true,
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
	var userName = $("#query_user_name").val();
	var loginName = $("#query_login_name").val();
	var orgName = $("#query_belong_org").val();
	var roleName = $("#query_role_name").val();
	table.page.info().start = start;
    var param = {
        "userName": userName,
        "roleName": roleName,
        "orgName": orgName,
        "loginName": loginName,
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}
			
function getTable(){
	return table;
}