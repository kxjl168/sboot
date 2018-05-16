//区域控制
function controlRegion(regionId) {
    var bol = $("#Switch" + regionId).is(":checked");
    if (!bol) {
        //界面直接控制设备全开
        var closeRegion = $("#collapseOne" + regionId).find("i");
        closeRegion.each(function () {
            $(this).removeClass("opencolor")
        });
        controlRegionMethod(regionId, "1");
    } else {
        //界面直接控制设备全关
        var openRegion = $("#collapseOne" + regionId).find("i");
        openRegion.each(function () {
            $(this).addClass("opencolor")
        });
        controlRegionMethod(regionId, "0");
    }
}

//区域控制方法
function controlRegionMethod(regionId, data) {
    $.ajax({
        type: "post",
        url: '/manager/region/controlRegion',
        async: true,
        data: {"regionId": regionId, "data": data},
        success: function (response) {

        }
    });
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
    var element = $(".DeviceId" + deviceId);
    element.each(function () {
        if (deviceStatus === "0") {
            $(this).removeClass("opencolor");
        } else {
            $(this).addClass("opencolor")
        }
    });
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
        var element = $(".DeviceId" + deviceId);
        element.each(function () {
            if (deviceStatus === "0") {
                $(this).removeClass("opencolor");
            } else {
                $(this).addClass("opencolor")
            }
        });
    };
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
};

//设备控制
function controlRegionDevice(deviceId) {

    var states = $(".DeviceId" + deviceId).attr("class");
    var paramType = $(".DeviceId" + deviceId).attr("paramType");
    var data = 0;
    if (paramType === "1") {
        if (states.indexOf("opencolor") >= 0) {
            data = 0;
        } else {
            data = 1;
        }
    } else if (paramType === "2") {
        if (states.indexOf("opencolor") >= 0) {
            data = 0;
        } else {
            data = 100;
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