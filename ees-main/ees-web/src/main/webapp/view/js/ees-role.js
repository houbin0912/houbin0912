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
});


/* 弹窗操作 开始 */
/* 添加窗口 */
function addModel(){
	getOrgSelect(null,'addOrgSelect');
	clearErrorLable();
	$("#add_num_text").show();
	$('#addForm')[0].reset();
	$('#addModal').modal('show');
}
/* 编辑回显数据 */
function edit(data){
	clearErrorLable();
	$("#update_num_text").show();
    $("#updateRoleId").val(data.roleId);
    $("#updateRoleName").val(data.roleName);
    $("#updateRoleName").attr("disabled","disabled");
    $("#updateHomeUrl").val(data.homeUrl);
    $("#updateRemark").val(data.remark);  
    getOrgSelect(data.belongOrg,'updateOrgSelect');  
	$('#updateModal').modal('show');
}
/* 删除警告框 */
function del(id){
	$("#delete_id").val(id);
	$('#DeleteModal').modal('show');
}
/* 弹窗操作结束 */

/* 下拉窗 开始 */
//机构信息下拉窗
function getOrgSelect(orgId, selectTabId) {
	$.post('/sys/org/selectAllOrg', function(result) {
		var data = [];
		for (var i = 0; i < result.length; i++) {
			data[i] = [ result[i].orgId, result[i].orgName ];
		}
		getSelect(data, orgId, selectTabId);
	});
}
/* 下拉窗 结束 */

/* 提交表单 开始 */
//校验新增页面表单
function dosubmitAdd() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			roleName : "required",
			homeUrl : "required",
			belongOrg : "required",
			remark : {
				maxlength : 500
			}
		},
		messages : {
			homeUrl : "请输入角色首页",
			belongOrg : "请选择所属机构",
		},
		submitHandler : function() {
			$('#addModal').modal('toggle');
			$.ajax({
				url : "/sys/role/add",
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
//修改页面表单
function dosubmitUpdate() {
	$("#updateForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			roleId:"required",
			roleName : "required",
			homeUrl : "required",
			belongOrg : "required",
			remark : {
				maxlength : 500
			}
		},
		messages : {
			roleId:"请输入角色ID",
			roleName : "请输入角色名",
			homeUrl : "请选择角色的首页",
			belongOrg : "请选择所属机构",
		},
		submitHandler : function() {
			$('#updateModal').modal('toggle');
			$.ajax({
				url : "/sys/role/update",
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
/* 删除 */
function delOne(){
	$('#DeleteModal').modal('toggle');
	var ids = $("#delete_id").val();
	$.post('/sys/role/deleteByIds',{"roleIds":ids},function(result){
		info(result,"删除");
	 });
}
/* 筛选查询 */
function query(){
	tableReload(0,0);
}
//批量操作表单
/* 操作事件 */
function action(url,infoAction){
	var ids = getIds();
	if(ids.length == 0){
		info("tips","请选着至少一条数据");
	}else{
		$.post(url,{"roleIds":ids.join(',')},function(result){
			info(result,infoAction);
		});
	}
}
/* 提交表单结束 */

/* 校验 */
/* 验证角色名是否唯一 */
function isExist(htmlId,roleName){
	$("#"+htmlId).show();
	if(roleName == ''){
		$("#"+htmlId).css("color","red");
		$("#"+htmlId).text("内容不能为空");
	}else{
		$.post('/sys/role/check/roleName',{"roleName":roleName},function(result){
			if(result.success == true){
				$("#"+htmlId).css("color","red");
				$("#"+htmlId).text(result.successMessage);
			}else{
				$("#"+htmlId).css("color","green");
				$("#"+htmlId).text(result.errorMessage);
			}
		 });
	}
}
/* 校验 结束*/


/* 树形结构开始 */
/* 获取所有子节点 */
function getChildNodeIdArr(node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            ts.push(node.nodes[x].nodeId);
            if (node.nodes[x].nodes) {
                var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}
/* 选中所有子节点 */
function setParentNodeCheck(node) {
    var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        var checkedCount = 0;
        for (x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount ++;
            } else {
                break;
            }
        }
        if (checkedCount === parentNode.nodes.length) {
            $("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
            setParentNodeCheck(parentNode);
        }
    }
}
/* 赋权操作*/
function empower(id){
	/* 单个赋权需要回显 */
	if(id != null){
		$("#empowerOne").val(id);
		initTreeView(id);
		$('#empowerment').modal('show');
	}else{
		var ids = getIds();
		if(ids.length == 0){
			info("tips","请选着至少一条数据");
		}else{
			$("#empowerOne").val("");
			initTreeView();
			$('#empowerment').modal('show');
		}
	}
}
/* 初始化treeview和回显 */
function initTreeView(id){
	var $checkableTree;
	$.post('/sys/auth/menu',{"roleId":id},function(result){
		 $checkableTree = $('#treeview-checkable').treeview({
	          data: result,
	          showIcon: false,
	          showCheckbox: true,
	          levels:1,
	          onNodeChecked: function(event, node) {
	        	  var selectNodes = getChildNodeIdArr(node); //获取所有子节点
	        	  if (selectNodes) { //子节点不为空，则选中所有子节点
	        	  	 $('#treeview-checkable').treeview('checkNode', [selectNodes, { silent: true }]);
	        	  }
	        	  var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
	        	  setParentNodeCheck(node);
	          },
	          onNodeUnchecked: function (event, node) {
	        	  var selectNodes = getChildNodeIdArr(node); //获取所有子节点
	        	  if (selectNodes) { //子节点不为空，则取消选中所有子节点
	        	  	$('#treeview-checkable').treeview('uncheckNode', [selectNodes, { silent: true }]);
	        	  }
	          }
	    },"json");
	});
}

/* 获取选中的权限信息 */
function getNodes(){
	var id = $("#empowerOne").val();
	var nodes =  $('#treeview-checkable').treeview('getChecked');
  	var empower = '';
  	$.each(nodes, function(key, val ) {
  		empower += val.tags + ',';
  	});
	if(id != ''){
		$.post('/sys/auth/addOrUpdateAuth',{"anthList":empower,"roleId":id},function(result){
        	$('#empowerment').modal('toggle');
     		 info(result,"赋权");
     	});
	}else{
		var ids = getIds();
     	$.post('/sys/auth/initAuthList',{"empower":empower,"roleIds":ids.join(',')},function(result){
        	$('#empowerment').modal('toggle');
     		info(result,"批量赋权");
     	}); 
	}
}

/* 批量操作时，获取被选中的复选框 */
function getIds(){
	var ids = [];
	$("input:checkbox[name='chooseId']:checked").each(function(index, elem) {
		ids[index]= $(this).val();
	});
	return ids;
}
/* 树形结构结束 */
/* DATA TABLES */
var table;
function init_DataTables() {
	if( typeof ($.fn.DataTable) === 'undefined'){ 
		return; 
	}
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/role/list",
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
		    { "data": "roleId"},
            { "data": "roleName"},
            { "data": "homeUrl" },
            { "data": "orgName" },
            { "data": "remark" },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 5,
                render: function (data, type, row, meta) {
                	var id = '"' + row.roleId + '"';//row.user_id ;
                	var json = JSON.stringify(row);
                    var html = "<a href='javascript:void(0);'  onclick='edit("+ json + ")' style='margin: 1px' class='btn-sm btn-primary'> 编辑</a>"
                	html += "<a href='javascript:void(0);'   onclick='del("+ id + ")'  style='margin: 1px' class='btn-sm btn-primary'> 删除</a>"
                	html += "<a href='javascript:void(0);'   onclick='empower("+ id + ")' style='margin: 1px' class='btn-sm btn-primary'>赋权</a>"
                	return html;
                }
            },					            
            {
                targets: 0,
                render: function (data, type, row, meta) {
                	var id = row.roleId ;
                	//return "<div id='a' style='width:100px;overflow: hidden;text-overflow: ellipsis' title='" +id +"' />"+ id +"</div>"
                	return '<input type="checkbox" name="chooseId" value="'+ id + '"/>';
                }
            },
            { "orderable": false, "targets": 5}
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
	var roleName = $("#query_role_name").val();
    var orgName = $("#query_org_name").val();
	table.page.info().start = start;
	var param = {
        "roleName": roleName,
        "orgName": orgName
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}

function getTable(){
	return table;
}