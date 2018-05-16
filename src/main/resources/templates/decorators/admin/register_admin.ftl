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

    <link href="/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/vendor/metisMenu/metisMenu.css" rel="stylesheet">
    <link href="/vendor/sbadmin/css/sb-admin-2.css" rel="stylesheet">
    <!--bootstrapValidator的验证css-->
    <link href="/vendor/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">

    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/vendor/jquery/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--这里需要注意，在IE8中，不能直接使用本地下面的两个js，必须使用CDN的或者代理的，否则IE8下样式会报错-->
<#--<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
<#--<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    <sitemesh:write property="head"/>
</head>
<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="/index">中通国脉IOT管理平台 <#include "../../common/version.ftl"/></a>
        </div>
    </nav>
    <div id="container">
        <sitemesh:write property="body"/>
    </div>
</div>

<script type="text/javascript" src="/vendor/bootstrap/js/bootstrap.js"></script>

<script type="text/javascript" src="/vendor/metisMenu/metisMenu.js"></script>
<script type="text/javascript" src="/vendor/sbadmin/js/sb-admin-2.js"></script>
<!--bootstrapValidator的验证js-->
<script type="text/javascript" src="/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>

</body>
</html>