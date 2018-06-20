/**
 * 
 */

var tipTimeout = null;
function showPopover(target, msg, type) {

	clearTimeout(tipTimeout);

	target.attr("data-original-title", msg);
	$('[data-toggle="tooltip"]').tooltip();
	var tp = target.tooltip('show');

	$($(document.body).find(".tooltip")[0]).removeClass("success").removeClass(
			"error");
	if (typeof (type) != "undefined")
		$($(document.body).find(".tooltip")[0]).addClass(type);

	$($(document.body).find(".tooltip")[0]).css("z-index", 1100);

	target.focus();

	// 2秒后消失提示框
	tipTimeout = setTimeout(function() {
		 target.attr("data-original-title", "");
		 target.tooltip('hide');
	}, 2000);
};

function cconfirm(msg, donecallback, cancelcallback) {
	swal({
		title : '确定执行操作吗？',
		text : msg,// '你将无法恢复它！',
		type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : '确定',
		cancelButtonText : '取消',
		confirmButtonClass : 'btn btn-success',
		cancelButtonClass : 'btn btn-danger',
		buttonsStyling : true,
		allowOutsideClick : false,// 如果设置为false，用户无法通过点击弹窗外部关闭弹窗。
		allowEscapeKey : false, // 如果设置为false，用户无法通过按下Escape键关闭弹窗。
		allowEnterKey : false,
	}).then(function(rst) {

		if (rst.value) {
			if (typeof (donecallback) == "function")
				donecallback();
		}

		if (rst.dismiss) {
			if (typeof (cancelcallback) == "function")
				cancelcallback();
		}

		/*
		 * swal( '已删除！', '你的文件已经被删除。'+rst.dismiss, 'success' );
		 */
	})
};

function success(msg) {
	/*
	 * swal({ title: "操作成功", text: msg, timer: 500, type:"success",
	 * showConfirmButton: false });
	 * 
	 * return;
	 */

	// swal("干得漂亮！", "你点击了按钮！","success")
	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}

	showPopover($(target), msg, "success");
};

function msg(msg) {
	/*
	 * swal({ title : "提示信息", text : msg, // timer: 1000, type : "info",
	 * showConfirmButton : true });
	 * 
	 * return;
	 */

	success(msg);
};

function info(msg) {

	/*
	 * swal({ title : "提示信息", text : msg, // timer: 1000, type : "info",
	 * showConfirmButton : true });
	 * 
	 * return;
	 */

	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}
	;

	showPopover($(target), msg);
};

function error(msg) {
	/*
	 * swal({ title: "操作异常", text: msg, timer: 1000, type:"error",
	 * showConfirmButton: false });
	 * 
	 * return;
	 */

	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}
	;

	showPopover($(target), msg, "error");
};
