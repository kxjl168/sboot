var option;//1:新增  2:更新
$(function () {
    //读取列表数据
    //refreshData();

    //注册事件
    $(".btnModf").each(function (index, element) {
        $(element).bind("click", function (e) {
            var gwId = $(this).attr("gwId");
            $.ajax({
                url: "/manager/gateWay/gateWayDetail.action",
                async: false,
                data: {id: gwId},
                dataType: "json",
                success: function (data) {
                    $("#id").val(data.id);
                    $("#name").val(data.name);
                    $("#authKey").val(data.authKey);
                    $("#enableEncrypt").val(data.enableEncrypt);
                    $("#manufacturerId").val(data.manufacturerId);

                    option = 2;
                    $("#myModalLabel").html("编辑网关");
                    $("#myModal").modal();
                }
            });
        });
    });

    $(".btnDele").each(function (index, element) {
        $(element).bind("click", function (e) {
            var gwId = $(this).attr("gwId");
            if (confirm("确定删除吗?")) {
                $.ajax({
                    url: "/manager/gateWay/deleteGateWay.action",
                    async: false,
                    data: {id: gwId},
                    dataType: "json",
                    success: function (data) {
                        refreshData();
                    }
                });
            }
        });
    });

    $("#gateWayForm").bootstrapValidator({
        feedbackIcons: {
            /*input状态样式通过，刷新，非法三种图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitButtons: 'button[type="submit"]',//提交按钮
        fields: {
            name: {//验证网关名
                validators: {
                    notEmpty: {//非空
                        message: '网关名不能为空'
                    },
                    stringLength: {//长度
                        min: 1,
                        max: 36,
                        message: '网关名长度不能超过36位'
                    },
                    regexp: {//字符正则匹配
                        regexp: /^[a-zA-Z0-9_.\u4e00-\u9fa5]+$/i,
                        message: '网关名由汉字、数字、字母、下划线或者小数点组成'
                    }
                }
            },
            authKey: {
                validators: {
                    notEmpty: {
                        message: '加密密钥为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 19,
                        message: '加密密钥长度须在6到18之间'
                    }
                }
            }
        }
    });


    $("#addGateWay").click(function () {
        option = 1;
        $("#gateWayForm")[0].reset();
        $("#authKey").val(MathRand());
        $("#myModalLabel").html("新增网关");
        $("#myModal").modal();
    });


    $("#submit").click(function () {
        var data = $("#gateWayForm").serialize();
        var url = "";
        if (option === 1) {
            url = "/manager/gateWay/insertGateWay.action";
        }
        if (option === 2) {
            url = "/manager/gateWay/updateGateWay.action";
        }

        $.ajax({
            url: url,
            async: false,
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.result) {
                    $("#close").click();
                    refreshData();
                } else {
                    alert("网关名已存在")
                }
            }
        });
    });

});

function refreshData() {
    window.location.href = "/manager/gateWay/view.action";
}


/**
 * @return {string}
 */
function MathRand() {
    var Num = "";
    for (var i = 0; i < 4; i++) {
        for (var j = 0; j < 4; j++) {
            Num += Math.floor(Math.random() * 10);
        }
        if (i < 3) {
            Num += "-";
        }

    }
    return Num;
}


//读取网关参数
function readgetwaydata(getWayId) {
    var status = $("#" + getWayId).children('td:eq(4)').text();
    if (status.indexOf("未连接") >= 0) {
        alert("网关未连接,无法读取数据");
        return false;
    }
    $("#over").css("display", "block");
    $("#layout").css("display", "block");
    $.ajax({
        type: "post",
        url: '/manager/gateWay/readgetwaydata',
        async: false,
        data: {"getWayId": getWayId},
        success: function (response) {

        }
    });
}


var hostport = document.location.host;

var websocketServerUrl = "ws://" + hostport + "/websocket/" + groupId; //服务器地址

var websocket = null;

var RSwebsocket = null;

websocket = new WebSocket(websocketServerUrl);//创建WebSocket对象


//连接发生错误的回调方法
websocket.onerror = function () {

};

//连接成功建立的回调方法
websocket.onopen = function (event) {

};

//接收到消息的回调方法
websocket.onmessage = function (event) {
    var message = event.data;
    if (message.indexOf("complete") >= 0) {
        alert("读取信息完成");
        $("#over").css("display", "none");
        $("#layout").css("display", "none");
    }
};

//连接关闭的回调方法
websocket.onclose = function () {
    disConnect();
};

//每隔5秒去调用一次
function disConnect() {
    setTimeout(function () {
        reconnect();
    }, 5000);
}

//把刚才干的事情重写一遍
function reconnect() {
    RSwebsocket = new WebSocket(websocketServerUrl);
    RSwebsocket.onmessage = function () {
        var message = event.data;
        if (message.indexOf("complete") >= 0) {
            alert("读取信息完成");
            $("#over").css("display", "none");
            $("#layout").css("display", "none");
        }
    };
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
};
