	$(function() {
		$.post(
				"/sys/user/menu",
				function(result) {
					var html = '';
					for (var i = 0; i < result.length; i++) {
						html += '<li> <a onclick="menuShow('
								+ result[i].tags
								+ ')"><i class="fa fa-home"></i>'
								+ result[i].text
								+ '<span class="fa fa-chevron-down"></span></a> <ul id="'
								+ result[i].tags
								+ '" class="nav child_menu" style="display:';
						if (i == 0) {
							html += 'block;">';
						} else {
							html += 'none;">';
						}
						var twoMenu = result[i].nodes;
						for (var j = 0; j < twoMenu.length; j++) {
							html += '<li><a onclick="refresh('+"'"+ twoMenu[j].href +"'"+','+"'"+ twoMenu[j].text +"'"+')">' + twoMenu[j].text + '</a></li>';
						}
						html += '</ul></li>';
					}
					$("#menu").html(html);
		}, "json");
		
		$(function() {
			$.post('/sys/user/info',function(result){
				if(result != null){
					$("#userNameMenu").text(result.userName);
				}
			 })
			
		});
		
		$('ul li').click(function(){
			menuClass();
		    $(this).addClass('active');
		});
	});
	
	function refresh(html,title){
		/*var history = window.history;*/
		/*window.history.pushState({},0,'http://'+window.location.host+html);*/
		$(document).attr("title",title);
		$("#content").load(html + "");
	}
	
	function menuClass(){
		$("ul li").each(function(index, elem) {
			alert("1");
			 $(this).removeClass('active');
       });
		
	}
	function menuShow(showId) {
        $(".child_menu").each(function(index, elem) {
        	if($(this).attr('id') == showId){
        		var display = $('#' + showId).css('display');
        		if (display == 'block') {
        			$('#' + showId).css('display', 'none');
        		} 
        		if (display == 'none') {
        			$('#' + showId).css('display', 'block');
        		}
        	}else{
        		$(this).css('display', 'none');
        	}
        });
	}
