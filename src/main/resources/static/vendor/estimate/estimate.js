$(function () {
    PersonnelInformationTable();
});

function PersonnelInformationTable() {
    // 初始化Table
    $('#table_list').bootstrapTable({
        url: '/manager/estimate/showEstimate', // 请求后台的URL（*）
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
                goodName: $("#goodName").val(),
                userName: $("#userName").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            };
            return param;
        },
        columns: [{
            field: 'id',
            visible: false
        }, {
            field: 'userName',
            title: '用户名称',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'goodName',
            title: '商品名称',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<a href="#" onclick=showGoodsDetail("' + row.commodityId + '")>' + value + '</a>';
            }
        }, {
            field: 'value',
            title: '评价内容',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'createTime',
            title: '评价时间',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return row.createTime.split(".")[0]
            }
        }, {
            field: 'stars',
            title: '评分',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'orderId',
            title: '订单',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<a href="#" onclick=showOrdersDetail("' + row.orderId + '")>' + value + '</a>';
            }
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

$("#startTime").datetimepicker({
    language: "zh-CN",//中文
    format: 'yyyy-mm-dd',//显示日期格式
    autoclose: true,//当选择一个日期之后是否立即关闭此日期时间选择器。
    todayHighlight: true,//高亮当前日期
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    todayBtn:  1,//在日期底部添加一个跳转到今天的按钮
})

$("#endTime").datetimepicker({
    language: "zh-CN",//中文
    format: 'yyyy-mm-dd',//显示日期格式
    autoclose: true,//当选择一个日期之后是否立即关闭此日期时间选择器。
    todayHighlight: true,//高亮当前日期
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    todayBtn:  1,//在日期底部添加一个跳转到今天的按钮
})

function modifyAndDeleteButton(value, row, index) {
    var html = '<div class="">';
    if ($.inArray("150", permissionArray) > -1) {
        html += '<button id = "delete" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>';
    }
    html += '</div>';
    return html;
}

window.PersonnelInformationEvents = {
    "click #delete": function (e, value, row, index) {
        swal({
            title: '确定删除吗？',
            text: '你将无法恢复它！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText : "取消",
            confirmButtonText: '确定！'
        }).then(function(result){
            if (result.value) {
                $.ajax({
                    type: "post",
                    url: "/manager/estimate/delete",
                    data: {
                        "id": row.id
                    },
                    success: function (data) {
                        if (data == 1) {
                            swal({
                                title: '删除成功',
                                type: 'success'
                            }).then(
                                function () {
                                    doQuery();
                                }
                            )
                        } else {
                            swal({
                                title: '删除失败',
                                type: 'error'
                            })
                        }
                    }
                });
            }
        })
    }
};

function doQuery() {
    var opt = {
        url: "/manager/estimate/showEstimate",
    };
    $("#table_list").bootstrapTable('refresh', opt);
}
