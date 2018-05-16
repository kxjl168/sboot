<!DOCTYPE html>
<!-- saved from url=(0077)pages/forms.html -->
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/vendor/sbadmin/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="/vendor/jquery/jquery.dragsort-0.5.2.js"></script>

    <style type="text/css">
        table th{word-break: keep-all;text-align: center}
        table td{word-break: keep-all;text-align: center}
        table {width:auto;text-align: center}
    </style>
    <style type="text/css">
        #list1, #list2 {
            width: 240px;
            list-style-type: none;
            margin: 1px;
            border: solid 1px black;
        }
    </style>
</head>

<body>

<div id="wrapper">


    <div <#--id="page-wrapper"--> style="min-height: 507px;">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">区域修改</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        区域设备列表管理
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <h1>${regionName}</h1>

                                <ul id="list2">
                                <#list regionDevice as device>
                                    <li>
                                        <div>
                                            <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar"
                                                 class="img-circle"/>
                                        ${device.name}
                                        </div>
                                    </li>
                                </#list>
                                </ul>

                            </div>
                            <!-- /.col-lg-6 (nested) -->
                            <div class="col-lg-6">
                                <h1>设备列表</h1>

                                <ul id="list1">
                                <#list deviceList as device>
                                    <li>
                                        <div>
                                            <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar"
                                                 class="img-circle"/>
                                        ${device.name}
                                        </div>
                                    </li>
                                </#list>
                                </ul>

                            </div>
                            <!-- /.col-lg-6 (nested) -->
                        </div>
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="col-lg-4">
                </div>
                <div class="col-lg-4">
                    <a class="btn btn-default" href="  "> 保 存 </a>
                </div>
                <div class="col-lg-4">
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>


    <!-- /#page-wrapper -->
    <input name="list1SortOrder" type="hidden"/>
</div>
<!-- /#wrapper -->

<!-- Bootstrap Core JavaScript -->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/vendor/sbadmin/js/sb-admin-2.js"></script>

<script type="text/javascript">
    $("#list1,#list2").dragsort({
        dragSelector: "div",
        dragBetween: true,
        dragEnd: saveOrder,
        placeHolderTemplate: "<li class='placeHolder'><div></div></li>"
    });

    function saveOrder() {
        var data = $("#list1").find("li").map(function () {
            return $(this).children().html();
        }).get();
        $("input[name=list1SortOrder]").val(data.join("|"));
    };
</script>


</body>
</html>