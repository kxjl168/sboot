<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>权限添加</title>

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
                        <span class="header">权限转移</span>
                        <!--<div style="float: right; margin-right: 15px; margin-top: 2px;">
                         <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">新增</button>

                        </div>-->

                    </div>


                    <div class="xinxiqy">
                        <div class="namexg">
                            <label class="name01">手机号码：
                            </label>
                            <input style="float: left;" type="text" id="phone" class="form-control" placeholder="请输入对方手机号码" value="">
                        </div>
                    </div>
                    <div class="btnnr">
                        <button type="button" class="btn btn-default" onclick="subtransfer()" data-toggle="modal" data-target="#myModal">确定</button>
                    </div>

                </div>

                <!-- /.col-lg-4 -->
            </div>



            <!-- /.col-lg-4 -->
        </div>
        <!-- /.row -->

    <!--
    <div class="row">
        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">权限管理&nbsp;><span>&nbsp;权限添加</span></h1>

        </div>
    </div>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">权限添加

                       <button type="button" class="btn btn-default" style="float: right;margin-top: 5px;margin-right: 8px;">新增
                        </button>
                    </span>

                </div>
                <div class="panel-body">

                    <div style="margin-left: 12px;margin-right: 10px;">
                        <div class="table-responsive">
                            <table id="table_permission" class="table table-bordered table-hover table-striped"></table>
                        </div>
                    </div>


                </div>
            </div>


        </div>
    </div>
    -->
</div>


<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/permission/js/permission.js"></script>


</body>
</html>