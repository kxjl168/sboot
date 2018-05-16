$(function () {
    //groupTable();

});

function subtransfer(){
    var phone=$("#phone").val();
    $.ajax({
        type: "post",
        url: "/manager/user/permissionTransfer",
        data: {
            phone: phone
        },
        dataType: "json",
        success: function (data) {
            alert(data.message);
        }
    });
}

function updateOrSave()
{

	$("#login_form").bootstrapValidator({
	    feedbackIcons: {
	        /*input状态样式通过，刷新，非法三种图片*/
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    submitButtons: 'button[type="submit"]',//提交按钮
	    fields: {
	        account: {//验证账户
	            validators: {
	                notEmpty: {//非空
	                    message: '用户名不能为空'
	                }
	            }
	        },
	        password: {
	            validators: {
	                notEmpty: {
	                    message: '密码不能为空'
	                }
	            }
	        }
	        /* ,
	         captchaCode: {
	             validators: {
	                 notEmpty: {
	                     message: '验证码不能为空'
	                 }
	             }
	         }*/
	    }
	});
}


function groupTable() {
    //初始化Table
    $('#table_permission').bootstrapTable({
        url: '/manager/group/memberList',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',
        searchOnEnterKey: true,
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortName: 'id',             //排序字段
        sortOrder: "desc",                  //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        // showColumns: true,                  //是否显示所有的列
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'id',
                visible: false
            }, {
                field: 'telephone',
                title: '手机号码',
                align: 'center',
                valign: 'middle'
            }, {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: DeleteButton,
                events: groupEvents
            }

        ]
    });


}

/**
 * @return {string}
 */
function DeleteButton(value, row, index) {
    return [
        '<div class="btn-group">' +
        '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>' +
        '</div>'
    ].join("");
}

window.groupEvents = {
    "click #delete": function (e, value, row, index) {
        var msg = "您真的确定要删除吗？";
        var url = "/manager/charging/deleteChargeRule";
        if (true === confirm(msg)) {
            $.ajax({
                type: "post",
                url: url,
                data: {"id": row.id, "tableName": "VehicleInsurance"},
                success: function (response) {
                    if (response.bol) {
                        alert("删除成功！");
                    } else {
                        alert("删除失败！");
                    }
                }
            });
        }
        groupRefresh();
    }
};


function groupRefresh() {
    var opt = {
        url: '/manager/group/memberList',
        silent: true
    };
    $("#table_permission").bootstrapTable('refresh', opt);
}