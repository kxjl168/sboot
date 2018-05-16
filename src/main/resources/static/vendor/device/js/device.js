$(function () {

    // 初始化多个滑块
    $(".slider-range-min").each(function () {
        // 从标记读取初始值并删除
        var value = parseInt($(this).text(), 10);
        $(this).empty().slider({
            range: "min",
            value: value,
            min: 0,
            max: 100,
            animate: true,
            slide: function (event, ui) {
                if (ui.value === 0) {
                    $(this).parents("a").find("i").removeClass("opencolor");
                    $(this).parents("a").find("i").css("opacity", "");
                    controlDevice($(this).parents("a").find("i").attr("id"), "2");
                } else {
                    if (ui.value <= 0.2) {
                        $(this).parents("a").find("i").removeClass("opencolor");
                        $(this).parents("a").find("i").css("opacity", "");
                        controlDevice($(this).parents("a").find("i").attr("id"), "2");
                    } else {
                        $(this).parents("a").find("i").addClass("opencolor");
                        $(this).parents("a").find("i").css("opacity", ui.value / 100);
                        controlDevice($(this).parents("a").find("i").attr("id"), "2");
                    }
                }
            }
        });
    });


});

function change(obj) {
    var color = obj.children().attr("class");
    if (color.indexOf("opencolor") >= 0) {
        obj.children().removeClass("opencolor");
    } else {
        obj.children().addClass("opencolor");
    }
}


var hostport = document.location.host;

var websocketServerUrl = "ws://" + hostport + "/websocket/" + userId; //服务器地址

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
    var message = event.data.split(':');
    var deviceId = message[0];
    var deviceStatus = message[1];
    var deviceParamType = message[2];
    if (deviceParamType === "1") {
        //开关量
        if (deviceStatus === "1") {
            $("#" + deviceId).addClass("opencolor");
        } else {
            $("#" + deviceId).removeClass("opencolor");
        }
    } else if (deviceParamType === "2") {
        //模拟量
        if (deviceStatus === "0") {
            var state = $("#" + deviceId);
            state.removeClass("opencolor");
            state.css("opacity", ""); //设置透明度
        } else {
            var state = $("#" + deviceId);
            state.addClass("opencolor");
            state.css("opacity", Number(deviceStatus) / 100);
        }
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
        var message = event.data.split(':');
        var deviceId = message[0];
        var deviceStatus = message[1];
        var deviceParamType = message[2];
        if (deviceParamType === "1") {
            //开关量
            if (deviceStatus === "1") {
                $("#" + deviceId).addClass("opencolor");
            } else {
                $("#" + deviceId).removeClass("opencolor");
            }
        } else if (deviceParamType === "2") {
            //模拟量
            if (deviceStatus === "0") {
                var state = $("#" + deviceId);
                state.removeClass("opencolor");
                state.css("opacity", ""); //设置透明度
            } else {
                var state = $("#" + deviceId);
                state.addClass("opencolor");
                state.css("opacity", Number(deviceStatus) / 100);
            }
        }
    };
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
};


//设备控制
function controlDevice(deviceId, mode) {
    var states = $("#" + deviceId).attr("class");
    var paramType = $("#" + deviceId).attr("paramType");
    var data = 0;
    if (paramType === "1") {
        if (states.indexOf("opencolor") >= 0) {
            data = 0;
        } else {
            data = 1;
        }
    } else if (paramType === "2") {
        if (mode === "2") {
            var brightness = $("#" + deviceId).css("opacity");
            data = parseInt(Number(brightness) * 100);
            if (data <= 10) {
                data = 0;
            }
        } else {
            if (states.indexOf("opencolor") >= 0) {
                data = 0;
            } else {
                data = 100;
            }
        }

    }
    $.ajax({
        type: "post",
        url: '/manager/device/controlDevice',
        async: true,
        data: {"deviceId": deviceId, "data": data},
        success: function (response) {

        }
    });
}