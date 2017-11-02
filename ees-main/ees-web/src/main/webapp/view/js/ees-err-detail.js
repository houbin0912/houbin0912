/* 初次访问页面模态窗隐藏 */


// 查询表单
function query(){
	tableReload(0,0);
}



var table;
function init_DataTables() {
	if( typeof ($.fn.DataTable) === 'undefined'){ return; }
	table = $("#datatable-buttons").DataTable({
	  ajax:{
	        url:"/sys/errdatail/list",
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
		    { "data": "name"},
            { "data": "email"},
            { "data": null },
            { "data": null },
            { "data": null },
            { "data": null },
            { "data": null }
        ],
        columnDefs:[
        	{
                targets: 6,
                render: function (data, type, row, meta) {
                	var id = '"' + 1 + '"';//row.user_id ;
                	var json = JSON.stringify(row);
                    var html = "<a href='javascript:void(0);'  onclick='edit("+ json + ")' style='margin: 1px' class='btn-sm btn-primary'>编辑</a>"
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
	var name = $("#query_name").val();
    var email = $("#query_email").val();
    table.page.info().start = start;
    var param = {
        "name": name,
        "email": email
    };
    table.settings()[0].ajax.data = param;
    table.ajax.reload().page(page).draw(false);
}

function getTable(){
	return table;
}