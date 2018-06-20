function exchange() {
	$.ajax({
		type : "post",
		url : '/userCenter/shou/save',
		data : {
			type : $("input[name='type']:checked").val(),
			orderDetailId : $("#id").val(),
			remark : $("#id").val(),
			state : 0,
		},
		dataType : "json",
		success : function(response) {
			// debugger;
			if (response.result) {
				// $('#myModal').modal("hide");
				// success("操作成功！");

				window.location.href = "/userCenter/shou/exchange";

			} else {
				error(response.message);
			}
		},
		error : function(e) {
			info(e);
		}
	});

}

$(function() {

	var type= GetQueryString('type');
	 $("input:radio[name=type][value='"+type+"']").attr("checked",true);  
	 
});
