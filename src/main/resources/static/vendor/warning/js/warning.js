function WarningTable() {
    //初始化Table
    $('#table_warning').bootstrapTable({
        url: '/manager/report/findAllInformation?tableName=VehicleInsurance',         //请求后台的URL（*）
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
        sortName: 'vehicleno',             //排序字段
        sortOrder: "desc",                  //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        showColumns: true,                  //是否显示所有的列
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'id',
                visible: false
            }, {
                field: 'vehicleno',
                title: '设备名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'insurcom',
                title: '通道类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'insurnum',
                title: '物理地址',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'insurtype',
                title: '预警设备状态',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'insurcount',
                title: '预警状态',
                align: 'center',
                valign: 'middle'
            }, {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: ModifyAndDeleteButton,
                events: WarningEvents
            }

        ]
    });
}

/**
 * @return {string}
 */
function ModifyAndDeleteButton(value, row, index) {
    return [
        '<div class="btn-group">' +
        '<button id = "update" type = "button" class = "btn btn-primary"><i class="glyphicon glyphicon-pencil">修改</i> </button>' +
        '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>' +
        '</div>'
    ].join("");
}


window.WarningEvents = {
    "click #updateRule": function (e, value, row, index) {
        $("#header").html("更新计费规则");

        var obj = JSON.parse(row.ruleContent);
        var cityId = row.cityId;
        var businessCategoryId = row.businessCategoryId;
        var carTypeId = row.carTypeId;
        var id = row.id;
        var ruleType = row.ruleType;
        //生成表单
        searchCitySelect(cityId);
        searchBusinessCategorySelect(businessCategoryId);
        searchCarTypeSelect(carTypeId);
        idInput(id);
        typeInput(ruleType);
        showInfo(obj);
        expressionInput();
        hideText(ruleType, "update")

    },

    "click #delete": function (e, value, row, index) {
        var msg = "您真的确定要删除吗？";
        var url = "/manager/charging/deleteChargeRule";
        if (true === confirm(msg)) {
            $.ajax({
                type: "post",
                url: url,
                data: {"id": row.vehicleno, "tableName": "VehicleInsurance"},
                success: function (response) {
                    if (response.bol) {
                        alert("删除成功！");
                    } else {
                        alert("删除失败！");
                    }
                }
            });
        }
        VehicleInsuranceRefresh();
    }

};


$(".selectpicker").selectpicker('refresh');
$(function () {
    $("#slider-range-min").slider({
        range: "min",
        value: 50,
        min: 0,
        max: 100,
        slide: function (event, ui) {
            $("#amount").val(ui.value + "%");
            if (ui.value === 0) {
                $(".db").css("opacity", "");
            } else {
                $(".db").css("opacity", ui.value / 100);
            }
        }
    });
    $("#amount").val($("#slider-range-min").slider("value") + "%");
});

function change2() {
    if ($(this).hasClass("oncheck")) {
        $(".allcheck").toggleClass("oncheck");
        $("#AllCheck").toggleClass("oncheck");
    } else {
        $(".allcheck").toggleClass("oncheck");
        $("#AllCheck").toggleClass("oncheck");
    }


}

$(".checknew").on("click", function () {
    $(this).hasClass("oncheck") ? $(this).removeClass("oncheck") : $(this).addClass("oncheck");
})