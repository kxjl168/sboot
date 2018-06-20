$(function() {
	PersonnelInformationTable("#table_list1", 1);
	PersonnelInformationTable("#table_list4", 4);
	PersonnelInformationTable("#table_list5", 5);
	PersonnelInformationTable("#table_list6", 6);
	PersonnelInformationTable("#table_list2", 2);
	PersonnelInformationTable("#table_list8", 8);
	PersonnelInformationTable("#table_list", "");

});

function PersonnelInformationTable(tableid, status) {
	// 初始化Table
	$(tableid)
			.bootstrapTable(
					{
						url : '/userCenter/order/showOrders', // 请求后台的URL（*）
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
						sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
						pageNumber : 1, // 初始化加载第一页，默认第一页
						pageSize : 10, // 每页的记录行数（*）
						pageList : [ 10, 25 ], // 可供选择的每页的行数（*）
						search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
						detailView : true,
						// showColumns: true, //是否显示所有的列
						uniqueId : "id", // 每一行的唯一标识，一般为主键列
						// queryParamsType : "limit",
						queryParams : function queryParams(params) { // 设置查询参数
							var param = {
								pageSize : params.limit, // 每页要显示的数据条数
								offset : params.offset, // 每页显示数据的开始行号
								sortName : params.sort, // 要排序的字段
								sortOrder : params.order, // 排序规则
								id : $("#orderId").val(),
								goodName : $("#goodName").val(),
								startTime : $("#startTime").val(),
								endTime : $("#endTime").val(),
								state : status,
							};
							return param;
						},
						columns : [ {
							field : 'id',
							title : '订单编号',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'state',
							title : '状态',
							align : 'center',
							valign : 'middle',
							formatter : function(value, row, index) {
								if (row.state == 1) {
									return "待支付"
								} else if (row.state == 2) {
									return "已取消"
								} else if (row.state == 3) {
									return "已付款"
								} else if (row.state == 4) {
									return "待发货"
								} else if (row.state == 5) {
									return "待收货"
								} else if (row.state == 6) {
									return "已完成"
								}
								else if (row.state == 6) {
									return "交易完成"
								}
								else if (row.state == 8) {
									return "已完成"
								}
							}
						}, {
							field : 'remarks',
							title : '订单备注',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'payTime',
							title : '付款时间',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'payType',
							title : '支付方式',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'payMoney',
							title : '总计',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'mUser.name',
							title : '会员名称',
							align : 'center',
							valign : 'middle',
							visible : false
						}, {
							field : 'createTime',
							title : '订单创建时间',
							align : 'center',
							valign : 'middle',
							formatter : function(value, row, index) {
								return row.createTime.split(".")[0]
							}
						}, {
							title : '操作',
							field : 'vehicleno',
							align : 'center',
							formatter : modifyAndDeleteButton,
							events : PersonnelInformationEvents
						} ],
						
						onExpandRow : function(index, row, $detail) {
							var subTable = $detail.html('<table></table>')
									.find('table');
							$(subTable)
									.bootstrapTable(
											{
												silent : true,
												pagination : false, // 是否显示分页（*）
												sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
												pageNumber : 1, // 初始化加载第一页，默认第一页
												pageSize : 10, // 每页的记录行数（*）
												pageList : [ 10, 25 ], // 可供选择的每页的行数（*）

												striped : false, // 是否显示行间隔色
												columns : [
														{
															checkbox : false
														},
														{
															field : 'simg',
															title : '商品图片',
															formatter : function(
																	value, row,
																	index) {
																var imgpath=row.commodityInstance.commodity.commondityImages[0].fileinfo.http_relative_path
															if(typeof imgpath!='undefined')
																 return "<div class='cimg'><img src='"+$("#val2").val()+imgpath+"' class='img-responsive' /></div>";
															}
														},
														{
															field : 'commodityInstance.commodity.name',
															title : '商品名称'
														},
														{
															field : 'number',
															title : '商品数量'
														},

														{
															field : 'option',
															title : '型号',

															formatter : function(
																	value, row,
																	index) {

																var html = "";

																html += ' <div class=" col-xs-12" style="margin: 5px;"> '

																$
																		.each(
																				row.commodityInstance.commondityInstanceOptions,
																				function(
																						i,
																						item) {
																					html += ' <span>	 '
																							+ ' 		<span class="" style="text-align: center;">'
																							+ item.commodity_option.option.name
																							+ ':</span> '
																							+ ' 		<span class="" style="text-align: center;">'
																							+ item.commodity_option.value
																							+ '  '
																							+ "</span>&nbsp;/";

																				})

																html += '  </div> ';

																return html;
															}
														},
														{
															field : 'commodityInstance.price',
															title : '商品单价'
														},
														{
															field : 'sumprice',
															title : '总价',
															formatter : function(
																	value, row,
																	index) {

																return row.commodityInstance.price
																		* row.number;
															}

														}

														,
														
														{
															title : '操作',
															field : 'iaction',
															align : 'center',
															formatter : modifyAndDeleteButton2,
															events : PersonnelInformationEvents2
														} 
														
														],
												data : row.details,
												formatLoadingMessage : function() {
													return "";
												},

											});
						},
						
					});
	
	$(tableid).on('post-body.bs.table', function (e, d) {
	    console.log("table ready");
	    $(tableid).bootstrapTable("expandAllRows");
	});
	
}

$("#startTime").datetimepicker({
	language : "zh-CN",// 中文
	format : 'yyyy-mm-dd',// 显示日期格式
	autoclose : true,// 当选择一个日期之后是否立即关闭此日期时间选择器。
	todayHighlight : true,// 高亮当前日期
	minView : "month", // 选择日期后，不会再跳转去选择时分秒
	todayBtn : 1,// 在日期底部添加一个跳转到今天的按钮
})

$("#endTime").datetimepicker({
	language : "zh-CN",// 中文
	format : 'yyyy-mm-dd',// 显示日期格式
	autoclose : true,// 当选择一个日期之后是否立即关闭此日期时间选择器。
	todayHighlight : true,// 高亮当前日期
	minView : "month", // 选择日期后，不会再跳转去选择时分秒
	todayBtn : 1,// 在日期底部添加一个跳转到今天的按钮
})



function modifyAndDeleteButton2(value, row, index) {
	var html = '<div class="">';
	// if ($.inArray("150", permissionArray) > -1) {

	
	if (row.state == 0) {
		// return "待支付"
		html += '<a id = "souhou" href="#"  class = ""><i class="glyphicon glyphicon-go">申请售后</i> </a>&nbsp;';

	} else
		{
		var t='';
		if(typeof row.orderExchange!='undefined')
			{
		if(row.orderExchange.type==1)
			t+='退款退货:';
		else if(row.orderExchange.type==2)
			t+='换货:';
		else if(row.orderExchange.type==3)
			t+='退款:';
		
		//'退换货状态（0：审核中, 1:待退货 ，2：已退货，3：待退款, 4，已退款 ，5,已重新发货， 6: 已取消 ，7：已驳回，8:完成, 9：失败）',
		if(row.orderExchange.state==0)
			t+='审核中';
		else if(row.orderExchange.state==1)
			t+='待退货';
		else if(row.orderExchange.state==2)
			t+='已退货';
		else if(row.orderExchange.state==3)
			t+='待退款';
		else if(row.orderExchange.state==4)
			t+='已退款';
		else if(row.orderExchange.state==5)
			t+='已重新发货';
		else if(row.orderExchange.state==6)
			t+='已取消';
		else if(row.orderExchange.state==7)
			t+='已驳回';
		else if(row.orderExchange.state==8)
			t+='完成';
		else if(row.orderExchange.state==9)
			t+='失败';
		
		
		
		html += t+'&nbsp;';
			}
	
	} 
	html += '</div>';
	return html;
}

window.PersonnelInformationEvents2 = {
	"click #souhou" : function(e, value, row, index) {
		//申请售后
		//info("shouhou...");
		 window.open("/userCenter/shou/select?id="+row.id);
	},
	"click #edetail" : function(e, value, row, index) {
		//商品交易详情
		info("详情...");
	},
};



function modifyAndDeleteButton(value, row, index) {
	var html = '<div class="">';
	// if ($.inArray("150", permissionArray) > -1) {

	if (row.state == 1) {
		// return "待支付"

		html += '<button id = "pay" type = "button" class = " btn btn-info"><i class="glyphicon glyphicon-go">去付款</i> </button>&nbsp;';
		html += '<button id = "cancel" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-trash">取消</i> </button>';

	} else if (row.state == 2) {
		// return "已取消"
		html += '<button id = "delete" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>';

	} else if (row.state == 3) {
		// return "已付款"
	} else if (row.state == 4) {
		// return "待发货"
		html += '<button id = "tuikuan" type = "button" class = " btn btn-info"><i class="glyphicon ">申请退款</i> </button>&nbsp;';
	} else if (row.state == 5) {
		// return "待收货"
		html += '<button id = "shouhuo" type = "button" class = " btn btn-info"><i class="glyphicon ">确认收货</i> </button>&nbsp;';
	} else if (row.state == 6) {
		// return "已完成"
		html += '<button id = "pingjia" type = "button" class = " btn btn-info"><i class="glyphicon glyphicon-edit">评价</i> </button>&nbsp;';
		html += '<button id = "delete" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>';

	
} else if (row.state == 8) {
	// return "已完成"
	
	html += '<button id = "delete" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>';

}

	html += '</div>';
	return html;
}

window.PersonnelInformationEvents = {
	"click #pay" : function(e, value, row, index) {
		info("pay...");
	},
	"click #cancel" : function(e, value, row, index) {
		cconfirm("确认取消订单？", function() {
			$.ajax({
				type : "post",
				url : '/userCenter/order/update',
				data : {
					id : row.id,
					state : 2,
				},
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						// $('#myModal').modal("hide");
						success("操作成功！");
						doQuery();

					} else {
						error(response.message);
					}
				}
			});
		});
	},
	"click #shouhuo" : function(e, value, row, index) {
		cconfirm("确认收货？请确保收到货后再进行此操作！", function() {
			$.ajax({
				type : "post",
				url : '/userCenter/order/update',
				data : {
					id : row.id,
					state : 6,
				},
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						// $('#myModal').modal("hide");
						success("操作成功！");
						doQuery();

					} else {
						error(response.message);
					}
				}
			});
		});
	},
	"click #delete" : function(e, value, row, index) {
		cconfirm("确认要删除该订单吗？！", function() {
			$.ajax({
				type : "post",
				url : '/userCenter/order/update',
				data : {
					id : row.id,
					state : 7,
				},
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						// $('#myModal').modal("hide");
						success("操作成功！");
						doQuery();

					} else {
						error(response.message);
					}
				}
			});
		});
	},

	"click #pingjia" : function(e, value, row, index) {
          window.open("/userCenter/commet/detail?id="+row.id);
	},
	"click #tuikuan" : function(e, value, row, index) {

	},

};

function doQuery() {
	var opt = {

		silent : true
	};
	$("#table_list").bootstrapTable('refresh', opt);
	$("#table_list1").bootstrapTable('refresh', opt);
	$("#table_list2").bootstrapTable('refresh', opt);
	$("#table_list3").bootstrapTable('refresh', opt);
	$("#table_list4").bootstrapTable('refresh', opt);
	$("#table_list5").bootstrapTable('refresh', opt);
	$("#table_list6").bootstrapTable('refresh', opt);
	$("#table_list8").bootstrapTable('refresh', opt);
}

function doexport() {
	window.open("/userCenter/order/exportOrders?id=" + $("#orderId").val()
			+ "&goodName=" + encodeURIComponent($("#goodName").val())
			+ "&startTime=" + encodeURIComponent($("#startTime").val())
			+ "&endTime=" + encodeURIComponent($("#endTime").val()));

}
