
//限制最大输入长度的函数
function limitMaxLength(inputId,errTxtId,maxLength){
	//$($.parseHTML(this.errTxtId, document, true)).css('display', null);
	var txtval = $('#'+inputId).val().length;
	var remainLength = parseInt(maxLength - txtval);
	if(remainLength > 0 ){
		$('#'+errTxtId).html('剩余可输入'+remainLength+'字');
	}else{
		$('#'+errTxtId).html('剩余可输入0字');
		//这里意思是当里面的文字小于等于0的时候，那么字数不能再增加，只能是400个字
		$('#'+inputId).val($('#'+inputId).val().substring(0,maxLength)); 
	}
}

//下拉窗拼接
function getSelect(data,selectData,htmlId){
	var html = "<option value=''>请选择</option>";
	for(var i = 0; i < data.length; i++){
		if(selectData == data[i][0]){
			 html += "<option value="+ data[i][0] +" selected >" + data[i][1] + "</option>";
		}else{
			 html += "<option value="+ data[i][0] +">" + data[i][1] + "</option>";
		}
	}
	$("#"+htmlId).html(html);
}
//性别下拉窗
function getSexSelect(selectData, htmlId) {
	var sex = [[ '0','男'] ,[ '1','女']];
	if(selectData == '男'){
		selectData = '0';
	}
	if(selectData == '女'){
		selectData = '1';
	}
	getSelect(sex,selectData,htmlId)
}

//返回信息回显
/* 操作提示信息 */
function info(result, info) {
	var table = getTable();
	var start = table.page.info().start;
	var page = table.page();
	if (result.success == true) {
		$("#infoAction").html(info + result.successMessage);
	} else if (result == "tips") {
		$("#infoAction").html(info);
	} else {
		$("#infoAction").html(info + result.errorMessage);
	}
	$('#infoModel').modal('show');
	tableReload(start, page);
}

/*校验字段 */
function checkIsNotNull(selectTabId,errorTabId){
	if ($('#'+selectTabId).val() == '') {
		$('#'+errorTabId).text("不能为空");
	}else{
		$('#'+errorTabId).text("");
	}
}
/*清楚提示的错误信息 */
function clearErrorLable(){
	$(".error").each(function(index, elem) {
		$(this).text("");
	});
	$(".errorRemark").each(function(index, elem) {
		$(this).text("");
	});
}
