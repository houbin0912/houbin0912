//模态窗隐藏
$(function() {
	init_DataTables();
	//新增窗口
	$('#addModal').modal({
		keyboard : true,
		show : false
	});
	//修改窗口
	$('#updateModal').modal({
		keyboard : true,
		show : false
	});
	
});

/* 弹窗操作 开始 */
/* 新增窗口的弹窗 */
function addModel(){
	$('#addForm')[0].reset();
	clearErrorLable();
	$('#addModal').modal('show');
}

/* 修改窗口的弹窗 */
function edit(data){
	clearErrorLable();
    $("#updateOrgId").val(data.orgId);
    $("#updateOrgName").val(data.orgName);
    $("#updateOrgCode").val(data.orgCode); 
    $("#updateRemark").val(data.remark);  
	$('#updateModal').modal('show');
}

/* 删除的模态窗 */
function del(id){
	$("#delete_id").val(id);
	$('#DeleteModal').modal('show');
}

/* 弹窗操作 结束*/

/* 提交表单 开始 */
/* 新增表单提交 */
function dosubmitAdd() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules:{
			orgCode: {
				required: true,
				maxlength:3
			},
			orgName : "required",
		},
		messages:{
			orgCode :{ 
				required:"请输入机构名",
				maxlength:"长度不能于大3个字母"
			},
			orgName: "请输入机构代码",
		},
		submitHandler : function() {
			$('#addModal').modal('toggle');
			$.ajax({
				url : "/sys/org/add",
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


/* 修改表单提交 */
function dosubmitUpdate() {
	$("#updateForm").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("label"));
		},
		rules:{
			orgName : "required",
			orgCode : "required",
		},
		messages:{
			orgName : "请输入机构名",
			orgCode : "请输入机构代码",
		},
		submitHandler : function() {
			$('#updateModal').modal('toggle');
			$.ajax({
				url : "/sys/org/update",
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


/* 删除提交 */
function delOne(){
	$('#DeleteModal').modal('toggle');
	var ids = $("#delete_id").val();
	$.post('/sys/org/deleteByIds',{"orgIds":ids},function(result){
		info(result,"删除");
	 });
}

/* 查询提交  */
function query(){
	tableReload(0,0);
}

/* 批量操作提交 */
function action(url,infoAction){
	var ids = [];
	$("input:checkbox[name='chooseId']:checked").each(function(index, elem) {
		ids[index]= $(this).val();
	});
	if(ids.length == 0){
		info("tips","请选着至少一条数据");
	}else{
		$.post(url,{"orgIds":ids.join(',')},function(result){
			info(result,infoAction);
		 });
	}
}



/* 提交表单 结束*/
/* DATA TABLES */
var table;
function init_DataTables() {
	if( typeof ($.fn.DataTable) === 'undefined'){ return; }
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/org/list",
	        type:"POST",
	        data:{
	        	orgName:$("#query_org_name").val(),
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
		    { "data": "orgId"},
            { "data": "orgName"},
            { "data": "remark" },
            { "data": "orgCode" },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 4,
                render: function (data, type, row, meta) {
                	var id = '"' + row.orgId + '"';//row.user_id ;
                	var json = JSON.stringify(row);
                    var html = "<a href='javascript:void(0);'  onclick='edit("+ json + ")' style='margin: 1px' class='btn-sm btn-primary'> 编辑</a>"
                	html += "<a href='javascript:void(0);'   onclick='del("+ id + ")'  style='margin: 1px' class='btn-sm btn-primary'> 删除</a>"
                	return html;
                }
            },					            
            {
                targets: 0,
                render: function (data, type, row, meta) {
                	var id = row.orgId ;
                	return '<input type="checkbox" name="chooseId" value="'+ id + '"/>';
                }
            },
            { "orderable": false, "targets": 4 }
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
	var orgName = $("#query_org_name").val();
    var orgCode = $("#query_org_code").val();
    table.page.info().start = start;
    var param = {
        "orgName": orgName,
        "orgCode": orgCode
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}


function getTable(){
	return table;
}


