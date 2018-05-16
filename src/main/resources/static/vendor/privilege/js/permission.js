var action="new";
$(function() {
	groupTable();

	initValidate();

	
	/*showPopover($("#message"),
			"xxxxx");*/
	
	
	$("#btnAdd").click(function() {
		
		action="new";
		
		$("#id").val("");
		$("#name").val("");
	
		$("#url").val("");
		$("#percode").val("");
		$("#parentid").val("");
		$("#sortstring").val("");
	
		
//		$("#type").find("option[value='" + data.type + "']").attr(
//				"selected", true);
//
//		$("#available").find("option[value='" + data.type + "']").attr(
//				"selected", true);


		$("#id").removeAttr("readonly")
		$("#myModal").modal();
	});

	
	$('#myModal').on('show.bs.modal', function(e) {
		//$('#mform input').val('');
		
		//action="new";

	/*	$("#type").find("option[value=2]").attr(
				"selected", true);
		$("#available").find("option[value=1]").attr(
				"selected", true);*/
		// $(".help-block").html('');
		//$("#mform").data('bootstrapValidator').resetForm(true);

	});
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal').on('hide.bs.modal', function(e) {

		$('#mform input').val('');
		// $(".help-block").html('');

		$("#mform").data('bootstrapValidator').resetForm(true);

	});

	$("#btnSubmit")
			.click(
					function() {

						// 触发全部验证
						$("#mform").data("bootstrapValidator").validate();

						// flag = true/false
						var flag = $("#mform").data("bootstrapValidator")
								.isValid();

						var url="/privilege/permission/saveOrUpdate.action";
						if(action=="new")
							url="/privilege/permission/save.action";
						else
							url="/privilege/permission/update.action";
						
						if (flag) {
							var data = $("#mform").serialize();

							$
									.ajax({
										type : "post",
										url : url,
										data : data,
										async : false,
										dataType : "json",
										success : function(response) {
											//debugger;
											if (response.result) {
												//window.location.href = "/privilege/permission/index";
												$("#myModal").modal("hide");
												doquery();
											} else {
												showPopover($("#message"),
														response.message);
											}
										}
									});
						}
					});
});

function initValidate() {
	$("#mform").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			id : {// 
				validators : {
					notEmpty : {// 非空
						message : 'ID不能为空'
					}
				}
			},
			name : {
				validators : {
					notEmpty : {
						message : '权限菜单名称不能为空'
					}
				}
			}

			,
			percode : {
				validators : {
				/*	notEmpty : {// 非空
						message : '权限代码不能为空'
					}*/
				}
			}

			,
			parentid : {
				validators : {
					notEmpty : {// 非空
						message : '父菜单ID不能为空'
					}
				}
			}

			,
			sortstring : {
				validators : {
					notEmpty : {// 非空
						message : '排序号不能为空'
					}
				}
			}

		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}

function subtransfer() {
	var phone = $("#phone").val();
	$.ajax({
		type : "post",
		url : "/manager/user/permissionTransfer",
		data : {
			phone : phone
		},
		dataType : "json",
		success : function(data) {
			alert(data.message);
		}
	});
}

function groupTable() {
	// 初始化Table
	$('#table_list').bootstrapTable({
		url : '/privilege/permission/list', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toolbar', // 工具按钮用哪个容器
		showHeader : true,
		searchAlign : 'left',
		buttonsAlign : 'left',

		searchOnEnterKey : true,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortName : 'id', // 排序字段
		sortOrder : "desc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

		// showColumns: true, //是否显示所有的列
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		// queryParamsType : "limit",
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sort : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				name : $("#q_name").val(),
			};
			return param;
		},
		columns : [ 
			{
			field : 'id',
			visible : true,
			title : '权限ID',
			align : 'center',
			valign : 'middle'
		},

		{
			field : 'name',
			title : '权限菜单名称',
			align : 'center',
			valign : 'middle'
		}

		, {
			field : 'type',
			title : '菜单类型',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

               if(value=="1")
            	   return "一级菜单";
               else if(value=="2")
            	   return "二级菜单";

                 return str;
             }
		
		}, {
			field : 'url',
			title : '菜单url',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'percode',
			title : '权限代码字符串',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'parentid',
			title : '父结点id',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'sortstring',
			title : '排序号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'available',
			title : '是否可用', // 1：可用，0不可用
			align : 'center',
			valign : 'middle'
				,
				 formatter: function (value, row, index) {

	               if(value=="1")
	            	   return "可用";
	               else if(value=="0")
	            	   return "不可用";

	                 return str;
	             }
		}

		, {
			title : '操作',
			field : 'xxx',
			align : 'center',
			formatter : DeleteButton,
			events : groupEvents
		}

		]
	});

}

/**
 * @return {string}
 */
function DeleteButton(value, row, index) {
	return [ '<div class="">'
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-edit">编辑</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ].join("");
}

window.groupEvents = {
	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/privilege/permission/delete";
		if (true === confirm(msg)) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					"id" : row.id,
				},
				success : function(response) {
					if (response.bol) {
						alert("删除成功！");
						doquery();
					} else {
						alert("删除失败！");
					}
				}
			});
		}
		groupRefresh();
	},

	"click #update" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/privilege/permission/load";

		/*// 触发全部验证
		$("#mform").data("bootstrapValidator").validate();

		// flag = true/false
		var flag = $("#mform").data("bootstrapValidator").isValid();*/

		/*if (flag) {*/
			$.ajax({
				type : "post",
				url : url,
				data : {
					id :row.id,
				},
				async : false,
				dataType : "json",
				success : function(data) {

					action="update";
					$("#id").val(data.id);
					$("#name").val(data.name);

					$("#type").find("option[value='" + data.type + "']").attr(
							"selected", true);

					$("#url").val(data.url);
					$("#percode").val(data.percode);
					$("#parentid").val(data.parentid);
					$("#sortstring").val(data.sortstring);

					$("#available").find(
							"option[value='" + data.available + "']").attr(
							"selected", true);

					$("#myModal").modal();

				}
			});
	/*	}*/

	},

};

function doquery() {
	var opt = {
		url : '/privilege/permission/list',
		silent : true,

	};
	$("#table_list").bootstrapTable('refresh', opt);
}