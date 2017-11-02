/* 初次访问页面模态窗隐藏 */
$(function() {
	init_DataTables() ;
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
function addModel() {
	$('#addForm')[0].reset();
	clearErrorLable();
	getUrlTypeSelect(null,'addUrlType');
	$('#addModal').modal('show');
}
/* 编辑回显数据 */
function edit(data) {
	clearErrorLable();
	$("#updateMenuId").val(data.menuId);
	$("#updateMenuName").val(data.menuName);
	$("#updateParentMenuId").val(data.parentMenuId);
	$("#updateParentMenuName").val(data.parentName);
	$("#updateMenuUrl").val(data.menuUrl);
	getUrlTypeSelect(data.urlType,'updateUrlType');
	$("#updateRemark").val(data.remark);
	$('#updateModal').modal('show');
}
/* 删除警告框 */
function del(id) {
	$("#delete_id").val(id);
	$('#DeleteModal').modal('show');
}
/* 弹窗操作结束 */

/* 提交表单 开始 */

//URL类型下拉窗
function getUrlTypeSelect(selectData, htmlId) {
	var urlType = [[ '0','菜单'] ,[ '1','页面'],[ '2','接口']];
	if(selectData == '菜单'){
		selectData = '0';
	}
	if(selectData == '页面'){
		selectData = '1';
	}
	if(selectData == '接口'){
		selectData = '2';
	}
	getSelect(urlType,selectData,htmlId)
}
// 新增表单
function dosubmitAdd() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			menuName : "required",
			parentMenuId : "required",
			urlType : "required",
		},
		messages : {
			menuName : "请输入菜单名称",
			parentMenuId : "请选择父菜单",
			urlType : "请选择URL类型",
		},
		submitHandler : function() {
			$('#addModal').modal('toggle');
			$.ajax({
				url : "/sys/menu/add",
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
// 修改表单
function dosubmitUpdate() {
	$("#updateForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules : {
			menuName : "required",
			parentMenuId : "required",
			menuUrl : "required",
			isRelativePath : "required",
			remark : {
				maxlength : 50
			}
		},
		messages : {
			menuName : "请输入菜单名称",
			parentMenuId : "请选择父菜单",
			menuUrl : "请输入菜单URL",
			isRelativePath : "请选择是否为相对路径",
			remark : {
				maxlength : $.format("不能超过  {0} 个字符!")
			}
		},
		submitHandler : function() {
			$('#updateModal').modal('toggle');
			$.ajax({
				url : "/sys/menu/update",
				type : "POST",
				data : $("#updateForm").serialize(),
				cache : false,
				processData : false,
				success : function(result) {
					info(result, "添加");
				}
			});
		}
	});
}
// 删除表单
function delOne(){
	$('#DeleteModal').modal('toggle');
	var ids = $("#delete_id").val();
	$.post('/sys/menu/deleteByIds',{"menuIds":ids},function(result){
		info(result,"删除");
	 });
}
// 查询表单
function query(){
	tableReload(0,0);
}
// 批量操作
function action(url,infoAction){
	var ids = [];
	$("input:checkbox[name='chooseId']:checked").each(function(index, elem) {
		ids[index]= $(this).val();
	});
	if(ids.length == 0){
		info("tips","请选着至少一条数据");
	}else{
		$.post(url,{"menuIds":ids.join(',')},function(result){
			info(result,infoAction);
		 });
	}
}
/* 提交表单 結束 */
/* 树形父菜单反选 */
function getPmm(textId,tagsId) {
	$('#pmm').modal('show');
	$.post('/sys/auth/menu', function(result) {
		$('#pmm-tree').treeview({
			data : result,
			multiSelect : false,
			levels : 1,
            onNodeSelected: function(event, node) {
            	$('#' + textId)[0].value = node.text;
            	$('#' + tagsId)[0].value = node.tags;
            	$('#pmm').modal('toggle');
            },
            onNodeUnselected: function (event, node) {
            	
            }
		}, "json");
	});
}

// 选中事件
function back() {
	$('#updateParentMenuName')[0].value = "默认一级菜单";
	$('#updateParentMenuId')[0].value = 0;
	$('#pmm').modal('toggle');
}
/* 树形父菜单反选结束 */
/* DATA TABLES */
var table;
function init_DataTables() {
	if( typeof ($.fn.DataTable) === 'undefined'){ return; }
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/menu/list",
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
		    { "data": "menuId"},
            { "data": "menuName"},
            { "data": "parentName" },
            { "data": "menuUrl" },
            { "data": "urlType" },
            { "data": "remark" },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 6,
                render: function (data, type, row, meta) {
                	var id = '"' + row.menuId + '"';//row.user_id ;
                	var json = JSON.stringify(row);
                    var html = "<a href='javascript:void(0);'  onclick='edit("+ json + ")' style='margin: 1px' class='btn-sm btn-primary'>编辑</a>"
                	html += "<a href='javascript:void(0);'   onclick='del("+ id + ")' style='margin: 1px' class='btn-sm btn-primary'>删除</a>"
                	return html;
                }
            },					            
            {
                targets: 0,
                render: function (data, type, row, meta) {
                	var id = row.menuId ;
                	//return "<div id='a' style='width:100px;overflow: hidden;text-overflow: ellipsis' title='" +id +"' />"+ id +"</div>"
                	return '<input type="checkbox" name="chooseId" value="'+ id + '"/>';
                }
            },
            { "orderable": false, "targets": 6 }
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
	var menuName = $("#query_menu_name").val();
    var parentName = $("#query_menu_parent").val();
    table.page.info().start = start;
    var param = {
        "menuName": menuName,
        "parentName": parentName
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}

function getTable(){
	return table;
}