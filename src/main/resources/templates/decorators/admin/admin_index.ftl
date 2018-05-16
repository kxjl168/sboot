<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>中通国脉IOT管理平台</title>
    <link rel="icon" href="/img/ztgm.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/img/ztgm.ico" type="image/x-icon"/>


    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/metisMenu/metisMenu.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/sbadmin/css/sb-admin-2.css">
    <link rel="stylesheet" type="text/css" href="/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/picture/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/foundation.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrapValidator/css/bootstrapValidator.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap-table/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/jquery/jquery-migrate-3.0.1.min.js"></script>

<#-- <script src="/js/html5shiv.js"></script>
 <script src="/js/respond.min.js"></script>-->
    <![endif]-->
    <sitemesh:write property="head"/>
</head>
<body>

<div id="wrapper">


    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="/manager/admin/index.action"><img src="/img/logo.png"/></a>

        <!--<a class="navbar-brand"
               href="/index">中通国脉IOT管理平台 <#include "../../common/version.ftl"/></a>-->
        </div>


        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i><#if principal??>${principal.userName}</#if>&nbsp;<i
                        class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <!--<li><a href=""><i
                            class="fa fa-user fa-fw"></i> 用户信息</a>
                    </li>
                    <li><a href=""><i
                            class="fa fa-gear fa-fw"></i> 权限转移</a>
                    </li>
                    <li><a href="/manager/group/showMemberList"><i
                            class="fa fa-gear fa-fw"></i> 权限添加</a>
                    </li>-->
                    <li class="divider"></li>
                    <li><a href="/logout.action"><i class="fa fa-sign-out fa-fw"></i>
                        退出登录</a>
                    </li>
                </ul>
            </li>
        </ul>





    <#include "admin_left_navi.ftl"/>
    </nav>
    <div id="page-wrapper">
        <sitemesh:write property="body"/>
    </div>
</div>


	

<script src="/vendor/jquery/jquery-ui.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendor/metisMenu/metisMenu.min.js"></script>
<script src="/vendor/sbadmin/js/sb-admin-2.js"></script>
<script src="/vendor/bootstrap/js/foundation.min.js"></script>
<script src="/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>
<script src="/vendor/bootstrap-table/js/bootstrap-table.js"></script>
<script src="/vendor/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>


<script src="/js/iot.js"></script>

</body>
</html>