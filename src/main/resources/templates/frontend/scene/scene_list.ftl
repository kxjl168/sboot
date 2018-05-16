<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>场景管理</title>


    <link rel="stylesheet" type="text/css" href="/vendor/scene/css/scene.css">

</head>

<body>


<div>
    <div class="row">
        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">场景管理&nbsp;><span>&nbsp;场景列表</span></h1>

            <div  style="float: right;">
                <button type="button" class="btn btn-default" style="width: 80px;margin-top: 5px;"
                        onclick="window.location.href='/manager/scene/findOneScene?SceneId='">新增
                </button>
                &nbsp;&nbsp;
                <button type="button" class="btn btn-default"  style="width: 125px;margin-top: 5px;" onclick="$('#myModel').modal('show');">更改app背景图片
                </button>
            </div>

        </div>
    </div>
<#list sceneDeviceListList as scene>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                                            <span data-toggle="collapse" data-parent="#accordion"
                                                  href="#collapseOne${scene.combination.id}"
                                                  class="header"> ${scene.sceneName}</span>
                    <div style="float: right; margin-right: 15px; margin-top: -37px;">
                        <button type="button" class="btn btn-default"
                                onclick="window.location.href='/manager/scene/findOneScene?SceneId=${scene.combination.id}'">
                            修改
                        </button>
                        <button type="button" class="btn btn-default zxys"
                                onclick="controlSceneMethod('${scene.combination.id}')">执行
                        </button>
                    </div>
                </div>

                <div id="collapseOne${scene.combination.id}" class="panel-collapse collapse in">
                    <div class="panel-body ">
                        <div class="row" style="margin-bottom:15px;">
                            <#list scene.deviceList as device>
                                <div class="col-lg-3">
                                    <div class="thumbnail" <#--onclick="controlDevice('${device.id}','1')"-->>
                                        <span class="text-success"> ${device.name}</span>
                                        <a class="tubiao">
                                            <#if (device.data=="0")>
                                                <i class="iconfont ys">${device.devicePicture.pictureCode!"&#xe61e;"}</i>
                                            <#else>
                                                <i class="iconfont ys opencolor">${device.devicePicture.pictureCode!"&#xe61e;"}</i>
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




<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a class="btnclose" data-dismiss="modal" aria-hidden="true">
                    <i class="iconfont">&#xe62b;</i>
                </a>
                <h4 class="modal-title" id="myModalLabel">
                    更改app背景图片
                </h4>
            </div>
            <div class="modal-body">
                <div class="row" >
                    <form id="testform" action="/manager/scene/uploadSceneBg"  method="post" class="form-inline" enctype="multipart/form-data">

                        &nbsp;&nbsp;
                        <div class="form-group">
                            <input type="file" id="picFile" name="picFile" size="45">
                        </div>

                        <input type="submit" name="submit"  class="btn btn-default" value="提交" >
                    </form>
                </div>

            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-default cancle" data-dismiss="modal">取消
                </button>
                &nbsp;&nbsp;
                <!--<button type="button" class="btn btn-primary">-->
                <!--提交更改-->
                <!--</button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->



<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/scene/js/scene.js"></script>
<script>
$(function(){

    var uploadbg="<#if uploadappbg ??>${ uploadappbg }</#if>";
    if(uploadbg=="true"){
        alert("更改背景图成功！");
    }else if(uploadbg=="false"){
        alert("更改背景图失败！");
    }

    $("#testform").submit(function (event) {
        if($("#picFile").val()=="") {
            alert("没有选择文件！")
            event.preventDefault();
        }
    });
});
</script>
</body>
</html>