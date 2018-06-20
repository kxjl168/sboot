$(function () {
    PersonnelInformationTable();
    $('#myModal').on('show.bs.modal', function (e) {
    });
    // modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
    $('#myModal').on('hide.bs.modal', function (e) {

        $('#replyForm input').val('');

        $("#replyForm").data('bootstrapValidator').resetForm(true);

    });

    $("#btnSubmit").click(function () {
        // 触发全部验证
        $("#replyForm").data("bootstrapValidator").validate();


        // flag = true/false
        var flag = $("#replyForm").data("bootstrapValidator").isValid();

        if (flag) {
            var data = $("#replyForm").serialize();
            $.ajax({
                type: "post",
                url: '/manager/question/replyQuestion',
                data: data,
                async: false,
                dataType: "json",
                success: function (data) {
                    // debugger;
                    if (data == 1) {
                        swal({
                            title: '回复成功',
                            type: 'success'
                        }).then(
                            function () {
                                $("#myModal").modal("hide");
                                doQuery();
                            }
                        )
                    } else {
                        swal({
                            title: '回复失败',
                            type: 'error'
                        }).then(
                            function () {
                                $("#myModal").modal("hide");
                            }
                        )
                    }
                }
            });
        }
    });

    initValidate();

});

function initValidate() {
    $("#replyForm").bootstrapValidator({
        feedbackIcons: {
            /* input状态样式通过，刷新，非法三种图片 */
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        // submitButtons : 'button[type="submit"]',// 提交按钮
        fields: {
            answer: {
                validators: {
                    notEmpty: {
                        message: '回复不能为空'
                    }
                }
            }
        }
    });
}


function PersonnelInformationTable() {
    // 初始化Table
    $('#table_list').bootstrapTable({
        url: '/manager/question/showQuestion', // 请求后台的URL（*）
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
                question: $("#question").val(),
                goodName: $("#goodName").val()
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
                    return '<a href="#" onclick=showGoodsDetail("' + row.commondityId + '")>' + value + '</a>';
                }
        }, {
                field: 'question',
                title: '用户咨询',
                align: 'center',
                valign: 'middle'
            },

            {
                field: 'createTime',
                title: '咨询时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return row.createTime.split(".")[0]
                }
            },

            {
                field: 'answer',
                title: '答复',
                align: 'center',
                valign: 'middle'
            },


            {
                field: 'state',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if (value == "1")
                        return "已回复";
                    else if (value == "0")
                        return "未回复";
                    return value;
                }
            }
            ,
            {
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

    html += '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">答复</i> </button>&nbsp;';

    if ($.inArray("delete_question_btn", permissionArray) > -1) {
        html += '<button id = "delete" type = "button" class = " btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>';
    }
    html += '</div>';

    return html;
}

window.PersonnelInformationEvents = {
    "click #update": function (e, value, row, index) {
        $("#myModal").modal("show");
        $("#questionId").attr("value", row.id)
    },

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
                    url: "/manager/question/delete",
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
    console.log($("#question").val())
    var opt = {
        url: "/manager/question/showQuestion",
    };
    $("#table_list").bootstrapTable('refresh', opt);
}
