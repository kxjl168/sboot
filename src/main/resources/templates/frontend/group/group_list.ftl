<!DOCTYPE html>
<!-- saved from url=(0078)https://blackrockdigital.github.io/startbootstrap-sb-admin-2/pages/tables.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <style>
        html, body{
            background: #e9e9e9;
        }

        a{
            text-decoration:none;
            underline: none;
        }
        a:hover
        {
            text-decoration:none;
            cursor:pointer;
        }
        body{
            font-family: "微软雅黑";
        }
        #wrapper{
            background: #323232;
        }
        /* 头部样式*/
        .navbar-static-top{
            background: #1cb09a;
        }
        .dropdown a{
            color: #FFFFFF;
        }

        .nav>li>a:hover,.nav>li>a:focus{
            background-color: #10a18b;
        }
        .nav .open>a, .nav .open>a:focus, .nav .open>a:hover{
            background-color: #10a18b;
        }
        .navbar-default{
            border-color: #008c77;
        }




        /* 左边样式*/
        .navbar-brand{
            padding:2px 0px 0px 10px;
        }
        .navbar-collapse{
            background: #323232;
            height: 1080px;
        }
        .navbar-collapse ul{
            margin-left:0;
        }
        .sidebar{
            margin-top: 56px;
        }

        .sidebar ul li{
            border-bottom:1px solid #323232;
        }
        #side-menu li a{
            color: #d2d2d2;
        }
        #side-menu li:hover a{
            color: #FFFFFF;
        }
        #side-menu li a{
            color: #FFFFFF;
        }
        .sidebar ul li a.active{
            background-color: #1cb09a;
        }
        .collapse{
            background: #242424;
        }


        /* 中间上部分位置样式*/
        .row{
            margin: 0px;
            max-width: 100%;
        }
        .panel{
            padding: 0;
        }
        #page-wrapper{
            background-color: #e9e9e9;
            padding: 0px;
        }
        h1{
            font-size: 14px;
            color: #888888;
            display: inline-block;
            text-align: center;
        }
        .page-header{
            margin: 14px 0;
            border-bottom:0;
            padding-bottom: 0;
            color: #333333;

        }
        .page-header span{
            color: #1cb09a;
        }


        .wzbj{
            width: 100%;
            height: 42px;
            background: #e4e3e3;
            margin-bottom: 12px;
            border-bottom:1px solid #dddddd;
        }
        embed{
            width: 22px;
            height: 22px;
            vertical-align: middle;
        }
        .thumbnail{
            height: 150px;
            padding: 0;
            margin-bottom: 0;
        }
        .text-success{
            color: #333333;
            float: left;
            /*margin-top: 95px;*/
            margin-left: 20px;
            line-height: 40px;
        }
        .ys{
            font-size: 38px;
            border-radius: 50%;
            line-height: 40px;
            border: 1px solid #DDDDDD;
            padding: 4px;
            background: #FFFFFF;
            color: #666666;
        }
        .ys2{
            font-size: 18px;
            border-radius: 8px;
            line-height: 40px;
        }
        .ys3{
            font-size: 18px;
            border-radius: 8px;
            line-height: 40px;
        }
        .tubiao{
            margin-left: 15px;
            margin-right: 15px;
            display: block;
            float: left;
        }
        .opencolor{
            color: #fb6e56;
            border: 1px solid #fb6e56;
        }
        .lista .caption {
            padding: 0;
        }
        .lista{
            height: 80px;
        }
        .lista>.switch{
            margin-right: 15px;
        }
        .row .row{
            margin: 0;
        }

        .icon{
            font-size: 30px;
            line-height: 40px;
            float: right;
            padding-left: 8px;
            color: #999999;
        }
        .opentext{
            margin-right: 8px;
            font-size: 14px;
            color: #666666;
        }


        .panel-heading{
            width: 100%;
            height: 42px;
            padding: 0;
        }
        .panel-default>.panel-heading{
            background-color: #d8d8d8;
        }
        .header{
            width: 800px;
            color: #333333;
            font-size: 16px;
            line-height: 40px;
            margin-left: 15px;
        }
        .panel-body {
            padding: 15px 0px 15px 0px;
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
        .btn-default:active{
            color: #FFFFFF;
            background-color: #008c77;
            border-color: #008c77;
        }
        .btn-default:active:focus{
            color: #FFFFFF;
            background-color: #008c77;
            border-color: #008c77;
        }
        .delect>.btn-default{
            background-color: #fb6e56;
            border-color: #fb6e56;
        }
        .delect>.btn-default:hover {
            background-color: #dd4228;
            border-color: #dd4228;
        }
        /* 表格样式*/
        .table>tbody>tr>td{
            height:40px;
        }
        .table>tbody>tr>td{
            padding: 0px 8px;
            vertical-align:middle;
        }
        .table-striped>tbody>tr:nth-of-type(odd) {
            background-color:#FFFFFF;
        }
        .table-striped>tbody>tr:nth-of-type(even) {
            background-color: #f9f9f9;
        }
        .table-hover>tbody>tr:hover {
            background-color: #dbf8f4;
        }
        .table>thead>tr>th {
            vertical-align: middle;
        }

        /* 修改删除按钮*/
        .td-middle{
            text-align: center;
        }
        .anniu a:hover span{
            text-decoration: underline;
        }
        .anniu span{
            margin-left: 4px;
        }
        .xg{
            color: #00a0e9;
            margin-right:15px;
        }
        .xg:hover{
            color:#007ab2;
        }
        .xg:visited,.xg:active,.xg:focus{
            text-decoration: none;
        }
        .sc{
            color:#fb6e56;
        }
        .sc:visited{
            color:#fb6e56;
            text-decoration: none;
        }
        .sc:hover{
            color:#e73517;
        }
        .sc:active,.sc:focus{

            text-decoration: none;
        }

        .namexg>.form-control:focus{
            box-shadow: none;
            border: 1px solid #13d1be;
            background: #ffffff;

        }
        .namexg>.form-control{
            width: 340px;
            height: 38px;
            border-radius: 4px;
            border: 1px solid #CCCCCC;
        }
        .namexg>.cj{
            width: 300px;
        }
        .namexg{
            margin-top: 15px;

        }
        .gjc{

            height: 50px;

        }
        .nameadd{
            margin-top: 6px;
        }
        .name01 {
            float: left;
            line-height: 40px;
            margin-right: 15px;
            margin-left: 15px;
        }
        /* 页码*/
        .pagination>li>a:hover{
            color: #FFFFFF;
            border-color: #1cb09a;
        }
        ul.pagination li:hover a{
            background: #1cb09a;
        }
        .pagination>.active>a,.pagination>.active>a:hover,.pagination>.active>a:focus{
            background-color: #1cb09a;
            border-color: #1cb09a;
        }
        .kg{
            font-size: 10px;
            position: absolute;
            top: 1px;
        }
        .OFF{
            color: #666666;
            left: -27px;
        }
        .rg{
            color: #666666;
            left: -27px;
        }
        .ON{
            color: #FFFFFF;
            left: -42px;
        }
        .switch{
            padding-top: 4px;
            margin-bottom: 0;
        }
        .switch.round label{
            cursor: default;
        }
        .switch label {
            height: 1.3rem;
            width: 3.0rem;
        }
        .switch label:after {
            height: 1rem;
            width: 1rem;
            left: .2rem;
            top: .13rem;


        }
        label.switch2{
            background: #1cb09a;
        }
        label.switch2:after {
            left: 1.8rem;
        }

        /* 复选框*/
        .checknew{
            float: left;
            background-image: url(img/xuanze1.png);
            background-repeat: no-repeat;
            cursor: pointer;
            height: 20px;
        }
        .checkboxclass {
            width: 20px;
            height: 20px;
            opacity: 0;
            cursor: pointer;
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
            filter: alpha(opacity=0);
        }
        .oncheck{
            background-image: url(img/xuanze2.png);
        }
        input[type="checkbox"]{
            margin: 0;
        }
        /* 调节灯*/
        .ui-slider-handle{
            width:10px;
            height:16px;
            border: 1px solid #12a690;
            background: #00cb9e;
            border-radius: 4px;
            position:absolute;
            left:-2px;
            top:-5px;
            cursor:default;
        }
        .scale{
            background-repeat: repeat-x;
            background-position: 0 100%;
            background-color: #E4E4E4;
            border-left: 1px #83BBD9 solid;
            width: 100px;
            height: 8px;
            position: relative;
            font-size: 0px;
            border-radius: 3px;
            float: left;
        }
        .scale div{
            background-repeat: repeat-x;
            background-color: #00cb9e;
            width: 0px;
            position: absolute;
            height:8px;
            left: 0;
            bottom: 0; }
        .tiaojie input[type="text"]{
            width: 60px;
            border: 0;
            box-shadow:inset 0 0px 0px rgba(0,0,0,0);
        }
        input[type="text"],input[type="number"]{
            box-shadow:inset 0 0px 0px rgba(0,0,0,0);
        }

        .shuzhi{
            color:#666666;
            font-weight:bold;
            float: left;
            position: relative;
            left: 112px;
            top: -23px;
        }
        .tiaojie{
            float: left;
            height: 15px;
            margin-top: 15px;
        }
        /*弹出框*/
        .modal-header{
            padding: 8px;
            background: #1cb09a;
            border-top-left-radius:4px;
            border-top-right-radius:4px;
        }
        .modal-content{
            border: 1px solid rgba(0,0,0,.05);
        }
        .modal-title{
            color: #ffffff;
            font-size:18px;
            padding:4px;
        }
        .modal-header .close{
            margin: 4px 0px 0px 0px;
        }
        .modal-dialog{
            width: 540px;
            margin: 200px auto;
        }
        label{
            cursor: default;
        }
        .btnclose{
            float: right;
            line-height: 1;
            filter: alpha(opacity=50);
            opacity: .5;
            margin-top: 5px;
        }
        .btnclose i{
            color: #FFFFFF;
            font-size: 18px;
        }
        .btnclose:hover{
            opacity: 1.0;
        }
        .modal-footer{
            text-align: center;
        }
        .modal-footer .btn+.btn{
            margin-left:15px;
        }

        .cancle{
            background: #FFFFFF;
            border:1px solid #cccccc;
            color: #666666;
        }
        /*单选*/
        .danxuancheck{
            background-image: url(img/danxuan1.png);
            background-repeat: no-repeat;
            cursor: pointer;
            height: 20px;
            padding-left: 28px;
        }
        .danxuancheck2{
            background-image: url(img/danxuan2.png);
            background-repeat: no-repeat;
            cursor: pointer;
            height: 20px;
            padding-left: 28px;
        }
        .radio-inline{
            padding-left: 0;
            margin-top: 9px;
        }
        .radio-inline+.radio-inline{
            margin-top: 9px;
        }
        .bootstrap-select>.btn-default{
            color: #666666;
            background-color: #ffffff;
            font-size: 14px;
            border-color: #cccccc;
        }
        .bootstrap-select.form-control:not([class*="col-"]){
            width: 340px;
            border: 0;
        }
        .bootstrap-select>.btn{
            height: 38px;

        }
        .dropdown-menu>.active>a{
            background-color: #1cb09a;
        }
        .dropdown-menu>li>a:hover{
            background-color: #dbf8f4;
        }
        .form-control:focus{
            background: #fafafa;
            border-color: #13d1be;
        }
        input[type="text"]:focus{
            background: #F5F5F5;
            border-color: #13d1be;
            background: url(img/qx.png) no-repeat 100% #F5F5F5;
            background-position:296px 10px;
            /*background: url(img/search.png) no-repeat 100% #F5F5F5;*/
            /*background-position:290px 4px;*/


        }
        .form-control:focus{
            box-shadow: inset 0 1px 1px rgba(0,0,0,0), 0 0 8px rgba(102,175,233,0);
        }

    </style>
</head>

<body>


    <!-- /.row -->

    <div class="row">

        <div class="col-lg-12 wzbj">

            <div style="padding-top: 9px;float: left; padding-right: 4px;"><embed src="/img/zhuye.svg" type="image/svg+xml" ></embed></div>
            <h1 class="page-header">权限组管理 &nbsp;><span>&nbsp;权限组设置</span></h1>

        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">权限组管理</span>
                    <div style="float: right; margin-right: 15px; margin-top: 3px;">
                        <button type="button" class="btn btn-default" onclick="openNewDialog()">新增</button>

                        <!-- data-toggle="modal" data-target="#myModal"  -->
                    </div>

                </div>
                <div class="row">

                    <div class="col-sm-6">
                        <div class="namexg gjc">
                            <label class="name01">关键词
                            </label>
                            <input style="float: left;" type="text" class="form-control" <#if searchKey??> value="${searchKey}" </#if>  id="searchKey" name="searchKey" placeholder="请输入关键词" >
                            <img style="margin-left:-35px;margin-top:9px;cursor:pointer" onclick="searchGroup()" src="/img/search.png">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">
                            <thead>
                            <tr role="row">

                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width:1%;">序号</th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width:20%;">权限组名称</th>

                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" style="width:6%; text-align: center;">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#if groups??>
                            <#list groups as group>

                            <tr class="gradeA odd" role="row">

                                <td>${group_index+1}</td>
                                <td>${group.name}</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#" onclick="openDialog('${group.name}','${group.id}')"><i class="iconfont ys2">&#xe639;</i><span>修改组名</span></a>
                                    <a class="xg" href="#" onclick="etrGroupSetting('${group.id}')"><i class="iconfont ys2">&#xe62d;</i><span>设置权限</span></a>
                                    <a class="sc" href="#" onclick="delGroup('${group.id}')"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                        </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">

                    <div class="col-sm-6">


                    <#import "../../common/pagination.ftl" as pagination>
                                    <@pagination.page action="/manager/group/etrGroupList.action" pageSizeParaName="ps" pageNumParaName="pn" qryItems=["telephone"]></@pagination.page>

                    </div>
                </div>

<span id="showmessage" style="display:none">
${principal.userId}
</span>
    <form id="etrSettingFm" action="/manager/group/etrGroupSetting" style="display: none">
        <input type="hidden" id="_groupId" name="groupId">
    </form>

    <!-- /.col-lg-4 -->
</div>




    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a class="btnclose" data-dismiss="modal" aria-hidden="true">
                    <i class="iconfont">&#xe62b;</i>
                </a>
                <h4 class="modal-title" id="myModalLabel">
                    <span id="modeltitle">新建权限组名称</span>
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div  style="padding-left: 47px;" class="namexg gjc nameadd">
                        <label class="name01"><span id="modeltitle2">新建组名</span>
                        </label>
                        <input style="float: left;" type="text" class="form-control" id="groupName" placeholder="请输入权限组名称">
                        <input type="hidden" id="groupId">
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="subbtn" onclick="subOper()">确定
                </button>
                <button type="button" class="btn btn-default cancle" data-dismiss="modal">取消
                </button>
                <!--<button type="button" class="btn btn-primary">-->
                <!--提交更改-->
                <!--</button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->



    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/js/iot.js"></script>
    <script src="/vendor/group/js/group-list.js"></script>
</body></html>