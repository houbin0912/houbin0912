$(function() {
	getMenu();
	getUserInfo();
	offBack();
});
/*获取用户菜单*/
function getMenu(){
	$.post(
			"/sys/user/menu",
			function(result) {
				var html = '';
				for (var i = 0; i < result.length; i++) {
					html += '<li class="active"> <a onclick="menuShow('+ result[i].tags+ ')"><i class="fa fa-home"></i>'
							+ result[i].text
							+ '<span class="fa fa-chevron-down"></span></a> <ul id="'
							+ result[i].tags
							+ '" class="nav child_menu">';
					var twoMenu = result[i].nodes;
					for (var j = 0; j < twoMenu.length; j++) {
						if(twoMenu[j].tags == 0){
							continue;
						}else{
							html += '<li onclick="refresh('+"'"+ twoMenu[j].href +"'"+','+"'"+ twoMenu[j].text +"'"+',this)"><a>' + twoMenu[j].text + '</a></li>';
						}
					}
					html += '</ul></li>';
				}
				$("#menu").html(html);
	}, "json");
}
/*获取用户信息*/
function getUserInfo(){
	$.post('/sys/user/info',function(result){
		if(result != null){
			$("#userNameMenu").text(result.user.userName);
			$("#userNameTop").text(result.user.userName);
			$("#content").load(result.sysRole.homeUrl);
		}
	 });
}
/*关闭浏览器返回事件*/
function offBack(){
	if (window.history && window.history.pushState) {
   	 $(window).on('popstate',function (){
   		  window.history.pushState('forward', null, ''); 
          window.history.forward(1);
   	 });
   }
   window.history.pushState('forward', null, '');  //在IE中必须得有这两行
   window.history.forward(1);
}
function refresh(html,title,node){
	/*var history = window.history;*/
	//window.history.pushState({},0,'http://'+window.location.host+html);
	$(document).attr("title",title);
	$("#content").load(html + "");
	$(".nav li").each(function(index, elem) {
		$(this).removeClass('current-page');
	});
	$(node).addClass('current-page');
}
/*打开或关闭二级菜单	*/
function menuShow(showId) {
    $(".nav.child_menu").each(function(index, elem) {
    	if($(this).attr('id') == showId){
    		var display = $(this).css('display');
    		if(display == 'none' || display == ''){
    			$(this).slideDown();
    		}
    		if(display == 'block'){
    			$(this).slideUp();
    		}
    	}else{
    		$(this).slideUp();
    	}
    });
}
		