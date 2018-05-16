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
    <link rel="stylesheet" type="text/css" href="/vendor/sweetalert/css/sweetalert2.min.css">

    <style>
        /*图标配对图*/
        .tubiao img {
            padding-top: 18px;
        }

        /*弹出框*/
        .modal-header {
            padding: 8px;
            background: #1cb09a;
            border-top-left-radius: 4px;
            border-top-right-radius: 4px;
        }

        .modal-content {
            border: 1px solid rgba(0, 0, 0, .05);
        }

        .modal-title {
            color: #ffffff;
            font-size: 18px;
            padding: 4px;
        }

        .modal-header .close {
            margin: 4px 0px 0px 0px;
        }

        .modal-dialog {
            width: 540px;
            margin: 200px auto;
        }

        label {
            cursor: default;
        }

        .btnclose {
            float: right;
            line-height: 1;
            filter: alpha(opacity=50);
            opacity: .5;
            margin-top: 5px;
        }

        .btnclose i {
            color: #FFFFFF;
            font-size: 18px;
        }

        .btnclose:hover {
            opacity: 1.0;
        }

        .modal-footer {
            text-align: center;
        }

        .modal-footer .btn + .btn {
            margin-left: 15px;
        }

        .btn-default {
            width: 80px;

            color: #FFFFFF;
            background-color: #1cb09a;
            font-size: 14px;
            border-color: #1cb09a;
        }

        .btn-default:hover {
            color: #FFFFFF;
            background-color: #008c77;
            border-color: #008c77;
        }

        .btn-default:active {
            color: #FFFFFF;
            background-color: #008c77;
            border-color: #008c77;
        }

        .btn-default:active:focus {
            color: #FFFFFF;
            background-color: #008c77;
            border-color: #008c77;
        }

        .delect > .btn-default {
            background-color: #fb6e56;
            border-color: #fb6e56;
        }

        .delect > .btn-default:hover {
            background-color: #dd4228;
            border-color: #dd4228;
        }

        .cancle {
            background: #FFFFFF;
            border: 1px solid #cccccc;
            color: #666666;
        }

        /*弹出框图标内容*/
        .mb {
            border: 1px solid #d3d3d3;
            height: 150px;
            text-align: center;
        }

        .mb2 {
            border: 2px solid #00d1a4;
        }

        .pdtb {
            font-size: 80px;
            color: #999999;
        }

        .text-success01 {
            color: #333333;
            display: block;
            margin-top: -10px;
            font-size: 14px;
        }

        .tubiao01 {
            display: block;

        }

        .modal-body {
            padding: 10px;
        }

        .xzstyle {
            width: 32px;
            height: 32px;
            float: right;
            margin-top: 0px;
            position: relative;
            top: -144px;
            left: 28px;
            z-index: 9999;
            display: none;
        }
    </style>

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

        <#list deviceList as device>

            <div class="col-lg-3" style="margin-bottom: 15px;" onclick="showPictureModal(this)" id="${device.id}">
                <div class="thumbnail">
                    <span class="text-success">${device.name}</span>
                    <a class="tubiao">
                        <#if (device.devicePicture.id=="1")>
                            <i class="iconfont ys">&#xe61f;</i>
                        <#else>
                            <i class="iconfont ys">${device.devicePicture.pictureCode}</i>
                        </#if>
                    </a>
                </div>
            </div>
        </#list>
            <!-- /#wrapper -->
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="pictureModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <a class="btnclose" data-dismiss="modal" aria-hidden="true">
                                <i class="iconfont">&#xe62b;</i>
                            </a>
                            <h4 class="modal-title" id="myModalLabel">
                                选择图标
                            </h4>
                        </div>
                        <div class="modal-body" style="height: 500px; overflow-y: scroll">
                            <div class="row">
                            <#list devicePictureList as picture>
                                <div class="col-md-3 mb" id="${picture.id}">
                                    <a class="tubiao01">
                                        <i class="iconfont pdtb">${picture.pictureCode}</i>
                                    </a>
                                    <span class="text-success01">${picture.pictureName}</span>
                                    <div class="xzstyle"><img src="/img/xuanzhong.png"/></div>
                                </div>
                            </#list>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"
                                    onclick="submitPictureChange()">确定
                            </button>
                            <button type="button" class="btn btn-default cancle" data-dismiss="modal">取消
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        </div>
    </div>

    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>
    <script src="/vendor/picturePair/picturePair.js"></script>


</body>
</html>