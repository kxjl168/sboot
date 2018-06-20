/**
 * 
 */

$(function(){

	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_attr').on('hide.bs.modal', function(e) {

		$('#mform_attri input').val('');
		// $(".help-block").html('');

		$("#mform_attri").data('bootstrapValidator').resetForm();

	});

	$("#btnSubmit_attr").click(function() {
		// 触发全部验证
		$("#mform_attri").data("bootstrapValidator").validate();

		
		// flag = true/false
		var flag = $("#mform_attri").data("bootstrapValidator").isValid();

		var url = "/manager/attri/save.action";

		if (flag) {
			var data = $("#mform_attri").serialize();

		
			$.ajax({
				type : "post",
				url : '/manager/attri/saveOrUpdate',
				data : data,
				async : false,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						$('#myModal_attr').modal("hide");
						
						success("操作成功!");
						
						//doquery();
						if(typeof(attr_actiondone)=="function")
						attr_actiondone(response);
					} else {
						error(response.message);
					}
				}
			});
		}
	});

	initValidate_attr();
	
});



function initValidate_attr() {
	$("#mform_attri").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			/*
			 * id : {// validators : { notEmpty : {// 非空 message : 'ID不能为空' } } },
			 */
		
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



