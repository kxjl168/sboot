<!DOCTYPE html>
<!-- saved from url=(0078)https://blackrockdigital.github.io/startbootstrap-sb-admin-2/pages/tables.html -->
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>设备列表</title>

    <link rel="stylesheet" type="text/css" href="/vendor/device/css/device.css">


</head>

<body>


<div>

    <div class="row">

        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">设备管理&nbsp;><span>&nbsp;设备列表</span></h1>
        </div>

    </div>


    <div class="panel-body">
        <div class="row" style="margin-bottom:15px;">

        <#list deviceList?sort_by("name") as device>

            <div class="col-lg-3" style="margin-bottom: 15px;">
                <div class="thumbnail" onclick="controlDevice('${device.id}','1')">
                    <span class="text-success">${device.name}</span>
                    <a class="tubiao">
                        <#if (device.data=="1"&&device.paramType==1)>
                            <i class="iconfont ys opencolor" id="${device.id}"
                               paramType="${device.paramType}">${device.devicePicture.pictureCode1!"&#xe61e;"}</i>
                        <#elseif device.data!="0"&&device.paramType==2>
                            <div class="tiaojie">
                                <div class="scale slider-range-min">0</div>
                            </div>
                            <i class="iconfont ys opencolor" id="${device.id}"
                               paramType="${device.paramType}">${device.devicePicture.pictureCode!"&#xe61e;"}</i>
                        <#else>
                            <i class="iconfont ys" id="${device.id}"
                               paramType="${device.paramType}">${device.devicePicture.pictureCode!"&#xe61e;"}</i>
                        </#if>
                    </a>
                </div>
            </div>
        </#list>
        </div>
    </div>
</div>

<script type="text/javascript">
    var userId = "${userId}";
</script>
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/device/js/device.js"></script>


</body>
</html>