<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>中通国脉IOT管理平台</title>
    <link rel="icon" href="/img/tsmp.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/img/tsmp.ico" type="image/x-icon"/>

    <link href="/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/vendor/sbadmin/css/sb-admin-2.css" rel="stylesheet">
    <!--bootstrapValidator的验证css-->
    <link href="/vendor/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">

    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/vendor/jquery/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--这里需要注意，在IE8中，不能直接使用本地下面的两个js，必须使用CDN的或者代理的，否则IE8下样式会报错-->
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    <sitemesh:write property="head"/>
</head>
<body>

<div class="container">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">中通国脉IOT管理平台 <#include "../../common/version.ftl"/></a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
        <#if user_session??>
            <li class="dropdown">
                <a href="/user/app/all"><i class="fa fa-tags fa-fw"></i> 时间戳服务应用</a>
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i>${user_session.getDisplayName()!}&nbsp;<i
                        class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="/user/account/info"><i class="fa fa-user fa-fw"></i> 个人信息</a>
                    </li>
                    <li><a href="/user/account/safety"><i class="fa fa-lock  fa-fw"></i> 账户安全</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="/login/frontend/logout"><i class="fa fa-sign-out fa-fw"></i>
                        退出登录</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
        </#if>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->
    </nav>
    <div id="container">
        <sitemesh:write property="body"/>
    </div>
</div>


<script type="text/javascript" src="/vendor/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/vendor/sbadmin/js/sb-admin-2.js"></script>
<!--bootstrapValidator的验证js-->
<script type="text/javascript" src="/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>

</body>
</html>