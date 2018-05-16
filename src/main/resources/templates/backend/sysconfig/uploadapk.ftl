<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>系统参数设置</title>

    <link rel="stylesheet" type="text/css" href="/vendor/user/css/user.css">

</head>
<body>

<div>



    <!-- /.row -->

    <div class="row">

        <div class="col-lg-12 wzbj">

            <div style="padding-top: 9px;float: left; padding-right: 4px;"><embed src="/img/zhuye.svg" type="image/svg+xml" ></embed></div>
            <h1 class="page-header">权限管理&nbsp;><span>&nbsp;权限转移</span></h1>

        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div style="height: 120px;" class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">App目前版本信息</span>
                    <!--<div style="float: right; margin-right: 15px; margin-top: 2px;">
                     <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">新增</button>
                    </div>-->
                </div>

                <div class="row">
                    <div class="col-lg-3">
                        版本号：${latestVersion.versionNo!}
                    </div>
                    <div class="col-lg-3">
                        文件名称：${latestVersion.fileName!}
                    </div>
                    <div class="col-lg-3">
                        更新信息：${latestVersion.message!}
                    </div>
                    <div class="col-lg-3">
                        是否强制更新：<#if latestVersion.forceUpdate ??> ${ latestVersion.forceUpdate?string('是', '否') } </#if>
                    </div>
                    <div class="col-lg-3">
                        上传时间： <#if latestVersion.createDate??>
                                        ${latestVersion.createDate?string("yyyy-MM-dd HH:mm") }
                                    <#else>

                                    </#if>
                    </div>
                </div>

                <div class="panel-heading">
                    <span class="header">上传新版本</span>
                </div>

                <div class="row" style="margin-top:10px;">
                    <form id="mform" action="/manager/uploadapk"  method="post"  enctype="multipart/form-data">
                        <div class="row">
                        <div class="form-group col-lg-4">
                            <label for="message" class="col-sm-4" style="width:100px;">更新信息</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="message"   name="message" placeholder="">
                            </div>
                        </div>
                        &nbsp;&nbsp;
                        <div class="checkbox col-lg-2">
                            <label>
                                <input type="checkbox" name="forceUpdate" value="true"> 强制更新
                            </label>
                        </div>
                        &nbsp;&nbsp;
                        <div class="form-group  col-lg-6">
                            <input type="file" id="filename" name="filename" size="45">
                            <p class="help-block">(安装包apk命名方式：xxx_vxxx.apk，_v后为年月日8位)</p>
                        </div>
                        </div>
                        <div class="row" style="text-align:center">
                            <input type="submit" name="submit" value="提交" >
                        </div>
                    </form>
                </div>

                <div class="row" style="display:none;">
                    <form id="testform" action="/interface/user/updHeaderPic"  method="post" class="form-inline" enctype="multipart/form-data">

                        &nbsp;&nbsp;
                        <div class="form-group">
                            <input type="file" id="picFile" name="picFile" size="45">
                            <input type="hidden" name="Token" value="e38ae3bcb28af0bf6be7f30fce8d2b3d"/>
                        </div>

                        <input type="submit" name="submit" value="提交" >
                    </form>
                </div>

            </div>
            <!-- /.col-lg-4 -->
        </div>

        <!-- /.col-lg-4 -->
    </div>
    <!-- /.row -->
    <div style="display:none;">

        <#if Request.SuccessTag?exists>
            ${Request.SuccessTag}
        </#if>
        <br/>
        <#if SuccessTag?exists>
            ${SuccessTag}
        </#if>

    </div>

</div>

<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/sysconfig/js/sysconf.js"></script>
<script>

    $(function () {

        var tag="<#if SuccessTag?exists>${SuccessTag}</#if>";
        if(tag=='Yes'){
            alert("上传文件成功！");
        }else if(tag=="No"){
            alert("上传失败！");
        }else if(tag=="NameError"){
            alert(" apk 命名不符合格式！");
        }

        $("#mform").submit(function (event) {
            if($("#filename").val()=="") {
                alert("没有选择文件！")
                event.preventDefault();
            }
        });

    });
</script>

</body>
</html>