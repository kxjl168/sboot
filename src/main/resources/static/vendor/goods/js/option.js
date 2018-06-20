$(function() {
	PersonnelInformationTable();

	$("#telephone").change(function() {
		$("#name").val($(this).val());
	});

	
	$("#btnAdd").click(function() {

		

		$("#id").val("");
	//	$("#telephone").removeAttr("readonly")
		$("#myModal_option").modal();
	});

	$('#myModal_option').on('show.bs.modal', function(e) {
		// $('#mform_option input').val('');

		// action="new";

		/*
		 * $("#type").find("option[value=2]").attr( "selected", true);
		 * $("#available").find("option[value=1]").attr( "selected", true);
		 */
		// $(".help-block").html('');
		// $("#mform_option").data('bootstrapValidator').resetForm(true);
	});
	


});

function loadMenuTree(user_id) {

	var setting = {
		isSimpleData : true, // 数据是否采用简单 Array 格式，默认false
		treeNodeKey : "id", // 在isSimpleData格式下，当前节点id属性
		treeNodeParentKey : "pId", // 在isSimpleData格式下，当前节点的父节点id属性

		check : {
			autoCheckTrigger : false,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			},
			chkStyle : "checkbox",
			enable : true,
			nocheckInherit : false,
			radioType : "level"
		},
		view : {
			showLine : true, // 是否显示节点间的连线
			// addHoverDom : addHoverDom, // 增加节点 点击新增
			// removeHoverDom : removeHoverDom,
			selectedMulti : false
		},
		edit : {
			enable : false,
			editNameSelectAll : true,
		// renameTitle : renameTitle, // 编辑按钮说明文字
		// removeTitle : removeTitle, // 删除按钮说明文字
		// showRemoveBtn : showRemoveBtn, // 是否显示移除按钮
		// showRenameBtn : showRenameBtn
		// 是否显示编辑按钮
		},
		callback : {
		// beforeDrag : beforeDrag,
		// beforeEditName : beforeEditName, // 编辑节点
		// beforeRemove : beforeRemove, // 删除节点
		// beforeRename : beforeRename,
		// onRemove : onRemove,
		// onRename : onRename,
		// beforeDrop : beforeDrop,
		// beforeClick : beforeClick,
		// onCheck : onCheck
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPID : 0
			}
		}
	};

	// var role_id=$("#role_id").val();

	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			user_id : user_id
		},
		dataType : "json",
		url : "/privilege/role/getRoleTree.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			error('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
			// if (typeof (data1.name) == undefined) {
			// data1.name = '';
			// }
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});

	$("#selectAll").bind("click", selectAll);
	$("#treeDemo a").each(function() {

	});

}

function option_actiondone()
{
	doquery();
	}

function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");

	var ck = $("#selectAll").get(0).checked;
	// alert(ck);
	zTree.checkAllNodes(ck);
}


function PersonnelInformationTable() {
	// 初始化Table
	$('#table_list').bootstrapTable({
		url : '/manager/option/list', // 请求后台的URL（*）
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
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				name : $("#q_name").val(),
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		}, {
			field : 'name',
			title : '名称',
			align : 'center',
			valign : 'middle'
		},
		
		{
			field : 'sort',
			title : '排序',
			align : 'center',
			valign : 'middle'
		},
		
		
		{
			title : '操作',
			field : 'vehicleno',
			align : 'center',
			formatter : modifyAndDeleteButton,
			events : PersonnelInformationEvents
		}

		]
	});
}

function modifyAndDeleteButton(value, row, index) {
	return [ '<div class="">'
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">修改</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ].join("");
}

window.PersonnelInformationEvents = {
	"click #update" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url : '/manager/option/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				$("#id").val(response.id);
				
				$("#name").val(response.name);
				
				
				$("#sort").val(response.sort);

				

				$("#myModal_option").modal();
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/option/delete";
		cconfirm(msg,function() {
			$.ajax({
				type : "post",
				url : url,
				data : {
					"id" : row.id
				},
				success : function(response) {
					if (response.bol) {
						success("删除成功！");
						doquery();
					} else {
						error("删除失败！");
					}
				}
			});
		});
		
	}
};

function doquery() {
	var opt = {
		url : "/manager/option/list",
		//silent : true
	};
	$("#table_list").bootstrapTable('refresh', opt);
}
