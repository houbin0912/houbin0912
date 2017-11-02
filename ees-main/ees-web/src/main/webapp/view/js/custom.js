/**
 * 指定的时间内只执行一次，性能调优
 */
(function($,sr){
    var debounce = function (func, threshold, execAsap) {
      var timeout;
        return function debounced () {
            var obj = this, args = arguments;
            function delayed () {
                if (!execAsap)
                    func.apply(obj, args); 
                timeout = null; 
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100); 
        };
    };

    // smartresize 
    jQuery.fn[sr] = function(fn){  
    	return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr); 
    };

})(jQuery,'smartresize');
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 获取页面内 主要的几个区域
 */

var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
    $BODY = $('body'),
    $MENU_TOGGLE = $('#menu_toggle'),
    $SIDEBAR_MENU = $('#sidebar-menu'),
    $SIDEBAR_FOOTER = $('.sidebar-footer'),
    $LEFT_COL = $('.left_col'),
    $RIGHT_COL = $('.right_col'),
    $NAV_MENU = $('.nav_menu'),
    $FOOTER = $('footer');
// Sidebar 左侧主菜单
function init_sidebar() {
	// TODO: This is some kind of easy fix, maybe we can improve this
	var setContentHeight = function () {
		// reset height
		$RIGHT_COL.css('min-height', $(window).height());
	
		var bodyHeight = $BODY.outerHeight(),
			footerHeight = $BODY.hasClass('footer_fixed') ? -10 : $FOOTER.height(),
			leftColHeight = $LEFT_COL.eq(1).height() + $SIDEBAR_FOOTER.height(),
			contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;
	
		// normalize content
		contentHeight -= $NAV_MENU.height() + footerHeight;
	
		$RIGHT_COL.css('min-height', contentHeight);
	};

    $SIDEBAR_MENU.find('a').on('click', function(ev) {
        var $li = $(this).parent();
        if ($li.is('.active')) {
            $li.removeClass('active active-sm');
            $('ul:first', $li).slideUp(function() {
                setContentHeight();
            });
        } else {
            // prevent closing menu if we are on child menu
            if (!$li.parent().is('.child_menu')) {
                $SIDEBAR_MENU.find('li').removeClass('active active-sm');
                $SIDEBAR_MENU.find('li ul').slideUp();
            }else
            {
				if ( $BODY.is( ".nav-sm" ) )
				{
					$SIDEBAR_MENU.find( "li" ).removeClass( "active active-sm" );
					$SIDEBAR_MENU.find( "li ul" ).slideUp();
				}
			}
            $li.addClass('active');

            $('ul:first', $li).slideDown(function() {
                setContentHeight();
            });
        }
    });

	// toggle small or large menu 
	$MENU_TOGGLE.on('click', function() {
		$(".child_menu").each(function(index, elem) {
        	$(this).css('display', 'none');
        });
		if ($BODY.hasClass('nav-md')) {
			$SIDEBAR_MENU.find('li.active ul').hide();
			$SIDEBAR_MENU.find('li.active').addClass('active-sm').removeClass('active');
		} else {
			$SIDEBAR_MENU.find('li.active-sm ul').show();
			$SIDEBAR_MENU.find('li.active-sm').addClass('active').removeClass('active-sm');
		}
		$BODY.toggleClass('nav-md nav-sm');
		setContentHeight();
	});

	// check active menu
	$SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');
	$SIDEBAR_MENU.find('a').filter(function () {
		return this.href == CURRENT_URL;
	}).parent('li').addClass('current-page').parents('ul').slideDown(function() {
		setContentHeight();
	}).parent().addClass('active');

	// recompute content when resizing
	$(window).smartresize(function(){  
		setContentHeight();
	});

	setContentHeight();

	// fixed sidebar
	if ($.fn.mCustomScrollbar) {
		$('.menu_fixed').mCustomScrollbar({
			autoHideScrollbar: true,
			theme: 'minimal',
			mouseWheel:{ preventDefault: true }
		});
	}
};

var randNum = function() {
  return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
};

// Panel toolbox 工具箱
$(document).ready(function() {
    $('.collapse-link').on('click', function() {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');
        
        // fix for some div with hardcoded fix class
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function(){
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200); 
            $BOX_PANEL.css('height', 'auto');  
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });

    $('.close-link').click(function () {
        var $BOX_PANEL = $(this).closest('.x_panel');

        $BOX_PANEL.remove();
    });
});

// Tooltip
$(document).ready(function() {
    $('[data-toggle="tooltip"]').tooltip({
        container: 'body'
    });
});

// Progressbar
if ($(".progress .progress-bar")[0]) {
    $('.progress .progress-bar').progressbar();
}

// Switchery
$(document).ready(function() {
    if ($(".js-switch")[0]) {
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        elems.forEach(function (html) {
            var switchery = new Switchery(html, {
                color: '#26B99A'
            });
        });
    }
});
// iCheck
$(document).ready(function() {
    if ($("input.flat")[0]) {
        $(document).ready(function () {
            $('input.flat').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });
        });
    }
});
// Table
$('table input').on('ifChecked', function () {
    checkState = '';
    $(this).parent().parent().parent().addClass('selected');
    countChecked();
});
$('table input').on('ifUnchecked', function () {
    checkState = '';
    $(this).parent().parent().parent().removeClass('selected');
    countChecked();
});

var checkState = '';
$('.bulk_action input').on('ifChecked', function () {
    checkState = '';
    $(this).parent().parent().parent().addClass('selected');
    countChecked();
});
$('.bulk_action input').on('ifUnchecked', function () {
    checkState = '';
    $(this).parent().parent().parent().removeClass('selected');
    countChecked();
});
$('.bulk_action input#check-all').on('ifChecked', function () {
    checkState = 'all';
    countChecked();
});
$('.bulk_action input#check-all').on('ifUnchecked', function () {
    checkState = 'none';
    countChecked();
});

function countChecked() {
    if (checkState === 'all') {
        $(".bulk_action input[name='table_records']").iCheck('check');
    }
    if (checkState === 'none') {
        $(".bulk_action input[name='table_records']").iCheck('uncheck');
    }

    var checkCount = $(".bulk_action input[name='table_records']:checked").length;

    if (checkCount) {
        $('.column-title').hide();
        $('.bulk-actions').show();
        $('.action-cnt').html(checkCount + ' Records Selected');
    } else {
        $('.column-title').show();
        $('.bulk-actions').hide();
    }
}
// Accordion  折叠面板
$(document).ready(function() {
    $(".expand").on("click", function () {
        $(this).next().slideToggle(200);
        $expand = $(this).find(">:first-child");

        if ($expand.text() == "+") {
            $expand.text("-");
        } else {
            $expand.text("+");
        }
    });
});
// NProgress
if (typeof NProgress != 'undefined') {
    $(document).ready(function () {
        NProgress.start();
    });

    $(window).load(function () {
        NProgress.done();
    });
}
	
//hover and retain popover when on popover content
var originalLeave = $.fn.popover.Constructor.prototype.leave;
	$.fn.popover.Constructor.prototype.leave = function(obj) {
	  var self = obj instanceof this.constructor ?
	    obj : $(obj.currentTarget)[this.type](this.getDelegateOptions()).data('bs.' + this.type);
	  var container, timeout;
	
	  originalLeave.call(this, obj);
	
	  if (obj.currentTarget) {
	      container = $(obj.currentTarget).siblings('.popover');
	      timeout = self.timeout;
	      container.one('mouseenter', function() {
	      //We entered the actual popover – call off the dogs
	      clearTimeout(timeout);
	      //Let's monitor popover content instead
	      container.one('mouseleave', function() {
	        $.fn.popover.Constructor.prototype.leave.call(self, self);
	      });
	    });
	  }
};

$('body').popover({
  selector: '[data-popover]',
  trigger: 'click hover',
  delay: {
    show: 50,
    hide: 400
  }
});

function gd(year, month, day) {
	return new Date(year, month - 1, day).getTime();
}	
 /* AUTOSIZE */
function init_autosize() {
	if(typeof $.fn.autosize !== 'undefined'){
	autosize($('.resizable_textarea'));
	}
};  
/* SELECT2 */
function init_select2() {
	 
	if( typeof (select2) === 'undefined'){ return; }
	console.log('init_toolbox');
	 
	$(".select2_single").select2({
	  placeholder: "Select a state",
	  allowClear: true
	});
	$(".select2_group").select2({});
	$(".select2_multiple").select2({
	  maximumSelectionLength: 4,
	  placeholder: "With Max Selection limit 4",
	  allowClear: true
	});
};
/* PNotify */
function init_PNotify() {
	if( typeof (PNotify) === 'undefined'){ return; }
	new PNotify({
	  title: "PNotify",
	  type: "info",
	  text: "Welcome. Try hovering over me. You can click things behind me, because I'm non-blocking.",
	  nonblock: {
		  nonblock: true
	  },
	  addclass: 'dark',
	  styling: 'bootstrap3',
	  hide: false,
	  before_close: function(PNotify) {
		PNotify.update({
		  title: PNotify.options.title + " - Enjoy your Stay",
		  before_close: null
		});
		PNotify.queueRemove();
		return false;
	  }
	});
}; 
/* CUSTOM NOTIFICATION */
function init_CustomNotification() {
	if( typeof (CustomTabs) === 'undefined'){ return; }
	var cnt = 10;
	TabbedNotification = function(options) {
	  var message = "<div id='ntf" + cnt + "' class='text alert-" + options.type + "' style='display:none'><h2><i class='fa fa-bell'></i> " + options.title +
		"</h2><div class='close'><a href='javascript:;' class='notification_close'><i class='fa fa-close'></i></a></div><p>" + options.text + "</p></div>";

	  if (!document.getElementById('custom_notifications')) {
		alert('doesnt exists');
	  } else {
		$('#custom_notifications ul.notifications').append("<li><a id='ntlink" + cnt + "' class='alert-" + options.type + "' href='#ntf" + cnt + "'><i class='fa fa-bell animated shake'></i></a></li>");
		$('#custom_notifications #notif-group').append(message);
		cnt++;
		CustomTabs(options);
	  }
	};

	CustomTabs = function(options) {
	  $('.tabbed_notifications > div').hide();
	  $('.tabbed_notifications > div:first-of-type').show();
	  $('#custom_notifications').removeClass('dsp_none');
	  $('.notifications a').click(function(e) {
		e.preventDefault();
		var $this = $(this),
		  tabbed_notifications = '#' + $this.parents('.notifications').data('tabbed_notifications'),
		  others = $this.closest('li').siblings().children('a'),
		  target = $this.attr('href');
		others.removeClass('active');
		$this.addClass('active');
		$(tabbed_notifications).children('div').hide();
		$(target).show();
	  });
	};
	CustomTabs();
	var tabid = idname = '';

	$(document).on('click', '.notification_close', function(e) {
	  idname = $(this).parent().parent().attr("id");
	  tabid = idname.substr(-2);
	  $('#ntf' + tabid).remove();
	  $('#ntlink' + tabid).parent().remove();
	  $('.notifications a').first().addClass('active');
	  $('#notif-group div').first().css('display', 'block');
	});
};
$(document).ready(function() {
	/*jQuery.Sparklines 是一个用于制作小图（迷你图，Sparklines图）支持线图、条形图、
		三态图（Tristate Charts）、离散型图（Discrete Charts）、饼图、箱线图（Box Plots）
		等多种图表，同时支持提示框、事件等交互功能。*/		
	//init_sparklines(); 删掉了
	
	/*Flot是纯Javascript实现的基于jQuery的图表插件，主要支持线状图和柱状图的绘制
	 * (通过插件也可以支持饼状图)。*/
	//init_flot_chart(); 删掉了
	
	//init_sidebar();  
	/*富文本编辑器插件wysiwyg.js 删掉了*/
	//init_wysiwyg();
	/*输入框input输入内容格式限制插件  删掉了*/
	//init_InputMask();
	/*地图插件 删掉了*/
	//init_JQVmap();
	/*图片剪裁插件  删掉了*/
	//init_cropper();
	/*knobKnob是一款实用jQuery和css3制作的旋钮控制按钮，knobKnob这款jQuery和css3
	 * 旋钮效果非常逼真，可用鼠标控制旋钮刻度的变化。 删掉了*/
	//init_knob();
	/*范围滑块 删掉了*/
	//init_IonRangeSlider();
	/*颜色选择器ColorPicker 删掉了*/
	//init_ColorPicker();
	/*标签插件 删掉了*/
	//init_TagsInput();
	/*表单验证 删掉了*/
	//init_parsley();
	/*日期插件 删掉了*/
	//init_daterangepicker();
	//init_daterangepicker_right();
	//init_daterangepicker_single_call();
	//init_daterangepicker_reservation();
	/*smartWizard插件常用在一些向导式的,按步骤的功能中,是的用户按照我们设定的步骤进行操作 删掉了*/
	//init_SmartWizard();
	/*此插件主要是用来统计新的访问、跳出率、服务器负载、使用的RAM等，功能很强大，带有HTML5的动画效果 删掉了*/
	//init_EasyPieChart();
	/*charts 基于 HTML5 的 JavaScript 图表库 删掉了*/
	//init_charts();
	/*开源免费 基于 HTML5 的 JavaScript 图表库  删掉了*/
	//init_echarts();
	/*图表插件morris.js  删掉了*/
	//init_morris_charts();
	/*天气动画  删掉了*/
	//init_skycons();
	/*选的下拉列表（包括级联选择框）、复选的下拉列表、(简单)树形下拉列表等方式，界面效果如下所示。*/
	init_select2();
	//init_validator();
/*	init_DataTables();*/
	/*环形图 删掉了*/
	//init_chart_doughnut();
	/*gauge 是一款精美时尚的jQuery动态仪表盘插件 删掉了*/
	//init_gauge();
	/*实现弹出提示和消息提示 */
	init_PNotify();
	/*星级评分插件  删掉了*/
	//init_starrr();
	/*日程管理插件fullcalendar 删掉了*/
	//init_calendar();
	/*compose.js主要用于实现集成的一个javascript库 删掉了*/
	//init_compose();
	/*自定义通知 */
	init_CustomNotification();
	/*自适应大小*/
	init_autosize();
	/*收索自动提示 删掉了*/
	//init_autocomplete();
});	
