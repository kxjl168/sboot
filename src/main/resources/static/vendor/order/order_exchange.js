$(function() {
	
	

	initlist();
});


function initlist(){
	 // 初始化Table
    $('#table_list').bootstrapTable({
        url: '/userCenter/shou/list',// 请求后台的URL（*）
        method: 'post', // 请求方式（*）
        contentType: 'application/x-www-form-urlencoded',
        toolbar: '#toolbar', // 工具按钮用哪个容器
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',

        searchOnEnterKey: true,
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

        // showColumns: true, //是否显示所有的列
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        // queryParamsType : "limit",
        queryParams: function queryParams(params) { // 设置查询参数
            var param = {
                pageSize: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                sort: params.sort, // 要排序的字段
            	name : $("#goodName").val(),
				startTime : $("#startTime").val(),
				endTime : $("#endTime").val(),
              
            };
            return param;
        },
        columns: [
        	 {
                 field: 'id',
                 title: '退换货号',
                 align: 'center',
                 valign: 'middle'
             
             },
        	{
            field: 'orderDetail.orderId',
            title: '订单号',
            align: 'center',
            valign: 'middle'
        
        }, {
            field: 'orderDetail.commodityInstance.commodity.name',
            title: '商品名称',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'state',
            title: '状态',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
            	//    '退换货状态（0：审核中, 1:待退货 ，2：已退货，3：待退款, 4，已退款 ，5,已重新发货， 6: 已取消 ，7：已驳回，8:完成, 9：失败）',
            	
            	var t=value;
        		if(value==0)
        			t='审核中';
        		else if(value==1)
        			t='待退货';
        		else if(value==2)
        			t='已退货';
        		else if(value==3)
        			t='待退款';
        		else if(value==4)
        			t='已退款';
        		else if(value==5)
        			t='已重新发货';
        		else if(value==6)
        			t='已取消';
        		else if(value==7)
        			t='已驳回';
        		else if(value==8)
        			t='完成';
        		else if(value==9)
        			t='失败';
        		  return t;
            }
      
        
        
        },
        {
            field: 'createTimeStr',
            title: '申请时间',
            align: 'center',
            valign: 'middle'
        }, {
            title: '操作',
            field: 'vehicleno',
            align: 'center',
            formatter: modifyAndDeleteButton,
            events: PersonnelInformationEvents
        }
        ]
    });
}


function modifyAndDeleteButton(value, row, index) {
	var html = '<div class="">';
	// if ($.inArray("150", permissionArray) > -1) {

	
	if (row.state == 0) {
		// return "待支付"
		html += '<button id = "cancel" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-go">取消</i> </button>&nbsp;';


	}
	html += '<button id = "detail" type = "button" class = " btn btn-info"><i class="glyphicon glyphicon-go">查看</i> </button>&nbsp;';
		
	
	
	html += '</div>';
	return html;
}

window.PersonnelInformationEvents = {
	"click #cancel" : function(e, value, row, index) {
		cconfirm("确认取消退款退货？", function() {
			$.ajax({
				type : "post",
				url : '/userCenter/shou/update',
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
	"click #detail" : function(e, value, row, index) {
		//商品交易详情
		info("详情...");
	},
};



