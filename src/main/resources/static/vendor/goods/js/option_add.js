/**
 * 
 */

$(function(){

	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_option').on('hide.bs.modal', function(e) {

		$('#mform_option input').val('');
		// $(".help-block").html('');

		$("#mform_option").data('bootstrapValidator').resetForm();

	});

	$("#btnSubmit_option").click(function() {
		// 触发全部验证
		$("#mform_option").data("bootstrapValidator").validate();

	
		// flag = true/false
		var flag = $("#mform_option").data("bootstrapValidator").isValid();

		var url = "/manager/option/save.action";

		if (flag) {
			var data = $("#mform_option").serialize();

			/**/

			$.ajax({
				type : "post",
				url : '/manager/option/saveOrUpdate',
				data : data,
				async : false,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						$('#myModal_option').modal("hide");
						
						success("操作成功!");
						
						if(typeof(option_actiondone)=="function")
						option_actiondone(response);
					} else {
						error( response.message);
					}
				}
			});
		}
	});

	

	initValidate_option();
	
});


function initValidate_option() {
	$("#mform_option").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			name : {
				validators : {
					notEmpty : {
						message : '名称不能为空'
					}
				}
			}


		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}





