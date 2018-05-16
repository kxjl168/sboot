//场景控制方法
function controlSceneMethod(sceneId) {
    $.ajax({
        type: "post",
        url: '/manager/scene/controlScene',
        async: true,
        data: {"sceneId": sceneId},
        success: function (response) {

        }
    });
}


//场景更新
function updateSceneMethod(sceneId) {
    var inputs = $("#CJDeviceList").find("div[class='lista']");
    var deviceArr = [];
    var gateWayId = "";
    //构造数据
    inputs.each(function () {
        var device = {};
        var id = $(this).attr("id");
        device.deviceId = id;
        device.deviceName = $("#deviceName" + id).val();
        device.address = $("#address" + id).val();
        device.paramType = $("#paramType" + id).val();
        gateWayId = $("#deviceGateWay" + id).val();
        if (device.paramType === "1") {
            var bol = $("#deviceSwitch" + id).is(":checked");
            if (bol) {
                device.data = 1;
            } else {
                device.data = 0;
            }
        } else if (device.paramType === "2") {
            var bol = $("#deviceSwitch" + id).is(":checked");
            if (bol) {
                device.data = 1;
            } else {
                device.data = 0;
            }
        }

        if (id !== undefined) {
            deviceArr.push(device);
        }
    });

    var sceneName = $("#sceneName").val();
    if (sceneName === "") {
        alert("场景名称不能为空");
        return;
    }

    var baseUrl;
    if (sceneId === "") {
        baseUrl = "/manager/scene/addScene";
    } else {
        baseUrl = "/manager/scene/updateSceneEdit";
    }


    $.ajax({
        type: "post",
        url: baseUrl,
        data: {
            deviceArr: JSON.stringify(deviceArr),
            sceneId: sceneId,
            sceneName: sceneName,
            userId: userId,
            gateWayId: gateWayId
        },
        dataType: "json",
        success: function (data) {
            if (data.bol) {
                alert("操作成功");
                self.location=document.referrer;
            } else {
                alert("操作失败");
            }
        }
    });

}


//添加
function DevLeftMove() {
    $("#DevCheck").children().eq(0).prop('checked', false);
    $("#DevCheck").removeClass("oncheck");
    $("#CJCheck").children().eq(0).prop('checked', false);
    $("#CJCheck").removeClass("oncheck");
    $("#AllDeviceList").find("div[class='lista']").each(function () {
        var bol = $(this).find("input").eq(0).is(":checked");
        if (bol) {
            $(this).find("input").eq(0).prop('checked', false);
            $(this).children().eq(0).removeClass("oncheck");
            if ($("#CJDeviceList").children().length <= 0) {
                $(this).appendTo($("#CJDeviceList"));
            } else {
                $(this).insertAfter($("#CJDeviceList").children().last());
            }

        }
    });
}

//删除
function DevRightMove() {
    $("#CJCheck").children().eq(0).prop('checked', false);
    $("#CJCheck").removeClass("oncheck");
    $("#DevCheck").children().eq(0).prop('checked', false);
    $("#DevCheck").removeClass("oncheck");
    $("#CJDeviceList").find("div[class='lista']").each(function () {
        var bol = $(this).find("input").eq(0).is(":checked");
        if (bol) {
            $(this).find("input").eq(0).prop('checked', false);
            $(this).children().eq(0).removeClass("oncheck");
            if ($("#AllDeviceList").children().length <= 0) {
                $(this).appendTo($("#AllDeviceList"));
            } else {
                $(this).insertAfter($("#AllDeviceList").children().last());
            }

        }
    });
}


//单个选中
function chooseDevice(obj) {
    var bol = obj.children("input").is(":checked");
    if (!bol) {
        obj.addClass("oncheck");
        obj.children("input").prop('checked', true);
    } else {
        obj.removeClass("oncheck");
        obj.children("input").prop('checked', false);
    }
}


//场景列表全部选中
function CJCheckAll(obj) {
    var bol = obj.children("input").is(":checked");
    if (!bol) {
        obj.addClass("oncheck");
        obj.children("input").prop('checked', true);
        $("#CJDeviceList").find("div[class='lista']").each(function () {
            $(this).children().eq(0).addClass("oncheck");
            $(this).find("input").eq(0).prop('checked', true);
        });
    } else {
        obj.removeClass("oncheck");
        obj.children("input").prop('checked', false);
        $("#CJDeviceList").find("div[class='lista']").each(function () {
            $(this).children().eq(0).removeClass("oncheck");
            $(this).find("input").eq(0).prop('checked', false);
        });
    }
}

//设备列表全部选中
function DevCheckAll(obj) {
    var bol = obj.children("input").is(":checked");
    if (!bol) {
        obj.addClass("oncheck");
        obj.children("input").prop('checked', true);
        $("#AllDeviceList").find("div[class='lista']").each(function () {
            $(this).children().eq(0).addClass("oncheck");
            $(this).find("input").eq(0).prop('checked', true);
        });
    } else {
        obj.removeClass("oncheck");
        obj.children("input").prop('checked', false);
        $("#AllDeviceList").find("div[class='lista']").each(function () {
            $(this).children().eq(0).removeClass("oncheck");
            $(this).find("input").eq(0).prop('checked', false);
        });
    }
}


//全部打开
function controlAllDeviceOpen(obj) {
    var bol = obj.children("input").is(":checked");
    if (!bol) {
        var device = obj.parent().next().find("i");
        device.each(function () {
            $(this).removeClass("opencolor");
            $(this).parent().next().next().children("input").attr("checked", false);
        });
    } else {
        var device = obj.parent().next().find("i");
        device.each(function () {
            $(this).addClass("opencolor");
            $(this).parent().next().next().children("input").attr("checked", true);
        });
    }
}

//全部打开
function controlDeviceOpen(obj) {
    var bol = obj.children("input").is(":checked");
    if (!bol) {
        obj.prev().prev().children().removeClass("opencolor");
    } else {
        obj.prev().prev().children().addClass("opencolor");
    }
}