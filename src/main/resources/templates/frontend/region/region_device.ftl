<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>区域管理</title>

    <link rel="stylesheet" type="text/css" href="/vendor/region/css/region.css">

</head>

<body>


<div>
    <div class="row">
        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">区域管理&nbsp;><span>&nbsp;区域列表</span></h1>
        </div>
    </div>
<#list DeviceStatus?sort_by("regionName") as region>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                                            <span data-toggle="collapse" data-parent="#accordion"
                                                  href="#collapseOne${region.region.id}"
                                                  class="header"> ${region.regionName}</span>
                    <div style="float: right; margin-right: 15px; margin-top: -32px;">
                        <span class="opentext">总开关:</span>
                        <div class="switch round" onclick="controlRegion('${region.region.id}')">
                            <input id="Switch${region.region.id}" type="checkbox">
                            <label for="Switch${region.region.id}">

                            </label>
                        </div>
                    </div>
                </div>

                <div id="collapseOne${region.region.id}" class="panel-collapse collapse in">
                    <div class="panel-body ">
                        <div class="row" style="margin-bottom:15px;">
                            <#list region.deviceList?sort_by("name") as device>
                                <div class="col-lg-3">
                                    <div class="thumbnail" onclick="controlRegionDevice('${device.id}')">
                                        <span class="text-success"> ${device.name}</span>
                                        <a class="tubiao">
                                            <#if (device.data=="1"&&device.paramType==1)>
                                                <i class="iconfont ys opencolor DeviceId${device.id}"
                                                   paramType="${device.paramType}">${device.devicePicture.pictureCode!""}</i>
                                            <#elseif device.data!="0"&&device.paramType==2>
                                                <i class="iconfont ys opencolor DeviceId${device.id}"
                                                   paramType="${device.paramType}">${device.devicePicture.pictureCode!""}</i>
                                            <#else>
                                                <i class="iconfont ys DeviceId${device.id}"
                                                   paramType="${device.paramType}">${device.devicePicture.pictureCode!""}</i>
                                            </#if>
                                        </a>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#list>
</div>

<script type="text/javascript">
    var userId = "${userId}";
</script>
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/region/js/region.js"></script>


</body>
</html>