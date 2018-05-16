<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>场景修改</title>

    <link rel="stylesheet" type="text/css" href="/vendor/scene/css/sceneEdit.css">

</head>

<body>

<div>

    <div class="row">

        <div class="col-lg-12 wzbj">

            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">场景管理&nbsp;><span>&nbsp;${sceneUpdate}</span></h1>

        </div>
    </div>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">${sceneDevice.sceneName!""}</span>
                    <div style="float: right; margin-right: 15px; margin-top: 5px;"
                         onclick="updateSceneMethod('${sceneDevice.combination.id!""}')">
                        <button type="button" class="btn btn-default">保存</button>
                    </div>
                </div>
                <div class="namexg">
                    <label class="name01">场景名称修改
                    </label>
                    <input id="sceneName" style="" type="text" class="form-control cj" placeholder="请填写场景名称"
                           value="${sceneDevice.sceneName!""}">
                </div>
                <div class="row" style="margin-left: 15px;">


                    <div class="panel list col-lg-4">
                        <div class="panel-heading list2">
                            <span class="headerlist">场景设备列表</span>
                            <div id="CJCheck" class="checknew" onclick="CJCheckAll($(this))">
                                <input class="checkboxclass" type="checkbox">
                            </div>

                            <div class="switch round" onclick="controlAllDeviceOpen($(this))">
                                <input type="checkbox" id="controlAllLeft">
                                <label for="controlAllLeft"></label>
                            </div>
                        </div>

                        <div class="sb">
                        <#--<div class="namexg gjc">
                            <label class="name01">关键词
                            </label>
                            <input style="float: left;" type="text" class="form-control" placeholder="请输入关键词">
                            <img style="margin-left:-35px;margin-top:9px;" src="/img/search.png">
                        </div>-->

                            <div id="CJDeviceList">
                            <#list sceneDevice.deviceList as device>
                                <div class="lista" id="${device.id!""}">
                                    <div class="checknew" onclick="chooseDevice($(this))">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                    <a class="tubiao">
                                        <#if (device.data=="0")>
                                            <i class="iconfont ys">${device.devicePicture.pictureCode!"&#xe61e;"}</i>
                                        <#else>
                                            <i class="iconfont ys opencolor">${device.devicePicture.pictureCode!"&#xe61e;"}</i>
                                        </#if>
                                    <#--<i class="iconfont ys">${device.devicePicture.pictureCode}</i>-->
                                    </a>
                                    <span class="text-success">${device.name} </span>
                                    <div class="switch round" onclick="controlDeviceOpen($(this))">
                                        <input type="checkbox" id="deviceSwitch${device.id}">
                                        <label for="deviceSwitch${device.id}"></label>
                                    </div>
                                    <input id="address${device.id!""}" type="hidden"
                                           value="${device.address!""}">
                                    <input id="paramType${device.id!""}" type="hidden"
                                           value="${device.paramType!""}">
                                    <input id="deviceId${device.id!""}" type="hidden"
                                           value="${device.id!""}">
                                    <input id="deviceName${device.id!""}" type="hidden"
                                           value="${device.name!""}">
                                </div>
                            </#list>
                            </div>
                        </div>

                    </div>


                    <div class="col-lg-2 btnzj">
                        <div class="btncommon delect" onclick="DevRightMove()">
                            <button type="button" class="btn btn-default">
                                移除
                                <img style="padding-left: 15px;" src="/img/you.png">
                            </button>
                        </div>
                        <div class="btncommon add" onclick="DevLeftMove()">
                            <button type="button" class="btn btn-default">
                                <img style="padding-right: 15px;" src="/img/zuo.png">
                                添加
                            </button>
                        </div>
                    </div>


                    <div class="panel list col-lg-4">
                        <div class="panel-heading list2">
                            <span class="headerlist">全部设备列表</span>
                            <div id="DevCheck" class="checknew" onclick="DevCheckAll($(this))">
                                <input class="checkboxclass" type="checkbox">
                            </div>
                        </div>

                        <div class="sb">
                        <#--<div class="namexg gjc">
                            <label class="name01">关键词
                            </label>
                            <input style="float: left;" type="text" class="form-control" placeholder="请输入关键词">
                            <img style="margin-left:-35px;margin-top:9px;" src="/img/search.png">
                        </div>-->

                            <div id="AllDeviceList">
                            <#list deviceList as device>
                                <div class="lista" id="${device.id!""}">
                                    <div class="checknew" onclick="chooseDevice($(this))">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                    <a class="tubiao">
                                        <i class="iconfont ys">${device.devicePicture.pictureCode!""}</i>
                                    </a>
                                    <span class="text-success">${device.name}</span>
                                    <div class="switch round" onclick="controlDeviceOpen($(this))">
                                        <input type="checkbox" id="deviceSwitch${device.id}">
                                        <label for="deviceSwitch${device.id}"></label>
                                    </div>
                                    <input id="address${device.id!""}" type="hidden"
                                           value="${device.address!""}">
                                    <input id="paramType${device.id!""}" type="hidden"
                                           value="${device.paramType!""}">
                                    <input id="deviceId${device.id!""}" type="hidden"
                                           value="${device.id!""}">
                                    <input id="deviceName${device.id!""}" type="hidden"
                                           value="${device.name!""}">
                                    <input id="deviceGateWay${device.id!""}" type="hidden"
                                           value="${device.gatewayId!""}">
                                </div>
                            </#list>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var userId = "${userId!""}";
    </script>

    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/scene/js/scene.js"></script>


</body>
</html>