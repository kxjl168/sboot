<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>订单管理</title>

    <link rel="stylesheet" type="text/css" href="/vendor/sweetalert/css/sweetalert2.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap-select/css/bootstrap-select.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <style>
        .zxys {
            margin-left: 15px;
        }
    </style>
</head>

<body>


<div>
    <div class="row">

        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px; float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">
                订单销售管理&nbsp;><span>&nbsp;订单列表</span>
            </h1>
        </div>
    </div>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">订单列表</span>
                </div>
                <div class="panel-body">

                    <div id="dataTables-example_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="orderIdLabel">订单编号：</span>
                                        <input id="orderId" type="text" class="form-control" aria-describedby="orderIdLabel"
                                               style="width: 240px">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="userNameLabel">用户名称：</span>
                                        <input id="userName" type="text" class="form-control" aria-describedby="userNameLabel"
                                               style="width: 240px">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="startTimeLabel">开始时间：</span>
                                        <input id="startTime" type="text" class="form-control" aria-describedby="startTimeLabel"
                                               style="width: 240px">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="endTimeLabel">结束时间：</span>
                                        <input id="endTime" type="text" class="form-control" aria-describedby="endTimeLabel"
                                               style="width: 240px">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="float: right; padding: 15px 50px 0px">
                            <button type="button" class="btn btn-default zxys" id="btnQry"
                                    onclick="doQuery()">查询
                            </button>
                        </div>

                        <div class="row">
                            <div class="col-sm-12">

                                <div class="table-responsive" style="margin: 10px;">
                                    <table id="table_list"
                                           class="table table-bordered table-hover table-striped"></table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>
    <script src="/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="/vendor/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/vendor/order/order.js"></script>
</body>
</html>