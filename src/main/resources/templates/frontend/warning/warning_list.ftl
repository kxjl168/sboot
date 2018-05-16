<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>预警界面</title>

    <link rel="stylesheet" type="text/css" href="/vendor/warning/css/warning.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap-table/css/bootstrap-table.min.css">

</head>

<body>

<div>


    <div class="row">

        <div class="col-lg-12 wzbj">

            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">预警管理&nbsp;><span>&nbsp;预警列表</span></h1>

        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">预警列表</span>
                    <div style="float: right; margin-right: 15px; margin-top: 3px;">
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">新增
                        </button>
                        <button type="button" class="btn btn-default zxys">批量删除</button>
                    </div>

                </div>
                <div class="row">

                    <div class="col-sm-6">
                        <div class="namexg gjc">
                            <label class="name01">关键词
                            </label>
                            <input style="float: left;" type="text" class="form-control" placeholder="请输入关键词">
                            <img style="margin-left:-35px;margin-top:9px;" src="/img/search.png">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="table-responsive">
                            <table id="table_warning" class="table table-bordered table-hover table-striped"></table>
                        </div>


                        <table width="100%"
                               class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
                               id="dataTables-example" role="grid" aria-describedby="dataTables-example_info"
                               style="width: 100%;">
                            <thead>
                            <tr role="row">
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-sort="ascending"
                                    aria-label="Rendering engine: activate to sort column descending" style="width:1%">
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-sort="ascending"
                                    aria-label="Rendering engine: activate to sort column descending" style="width:3%;">
                                    序号
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-sort="ascending"
                                    aria-label="Rendering engine: activate to sort column descending"
                                    style="width:20%;">设备名称
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-label="Browser: activate to sort column ascending" style="width: 15%;">通道类型
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-label="Platform(s): activate to sort column ascending" style="width: 15%">物理地址
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-label="Engine version: activate to sort column ascending" style="width:12%">
                                    预警设备状态
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-label="Engine version: activate to sort column ascending"
                                    style="width:10%;text-align: center;">预警状态
                                </th>
                                <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                                    aria-label="CSS grade: activate to sort column ascending"
                                    style="width:14%; text-align: center;">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeA odd" role="row">
                                <td>
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </td>
                                <td>1</td>
                                <td>空调</td>
                                <td>1</td>
                                <td>1.0.1</td>
                                <td>打开</td>
                                <td class="td-middle">已关闭</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#"><i class="iconfont ys2">&#xe639;</i><span>修改</span></a>
                                    <a class="sc" href="#"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                            <tr class="gradeA odd" role="row">
                                <td>
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </td>
                                <td>2</td>
                                <td>客厅灯</td>
                                <td>1</td>
                                <td>1.0.1</td>

                                <td>打开</td>
                                <td class="td-middle">已启用</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#"><i class="iconfont ys2">&#xe639;</i><span>修改</span></a>
                                    <a class="sc" href="#"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                            <tr class="gradeA odd" role="row">
                                <td>
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </td>
                                <td>3</td>
                                <td>窗帘</td>
                                <td>1</td>
                                <td>1.0.1</td>

                                <td>打开</td>
                                <td class="td-middle">已启用</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#"><i class="iconfont ys2">&#xe639;</i><span>修改</span></a>
                                    <a class="sc" href="#"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                            <tr class="gradeA odd" role="row">
                                <td>
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </td>
                                <td>4</td>
                                <td>窗帘</td>
                                <td>1</td>
                                <td>1.0.1</td>

                                <td>调节值：50%</td>
                                <td class="td-middle">已启用</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#"><i class="iconfont ys2">&#xe639;</i><span>修改</span></a>
                                    <a class="sc" href="#"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                            <tr class="gradeA odd" role="row">
                                <td>
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </td>
                                <td>5</td>
                                <td>窗帘</td>
                                <td>1</td>
                                <td>1.0.1</td>

                                <td>打开</td>
                                <td class="td-middle">已启用</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#"><i class="iconfont ys2">&#xe639;</i><span>修改</span></a>
                                    <a class="sc" href="#"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                            <tr class="gradeA odd" role="row">
                                <td>
                                    <div class="checknew">
                                        <input class="checkboxclass" type="checkbox">
                                    </div>
                                </td>
                                <td>6</td>
                                <td>窗帘</td>
                                <td>1</td>
                                <td>1.0.1</td>
                                <td>关闭</td>
                                <td class="td-middle">已启用</td>
                                <td class="td-middle anniu">
                                    <a class="xg" href="#"><i class="iconfont ys2">&#xe639;</i><span>修改</span></a>
                                    <a class="sc" href="#"><i class="iconfont ys3">&#xe612;</i><span>删除</span></a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="dataTables_info" id="dataTables-example_info" role="status" aria-live="polite">
                            Showing 1 to 10 of 57 entries
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <ul class="pagination">
                                <li class="paginate_button previous disabled" aria-controls="dataTables-example"
                                    tabindex="0" id="dataTables-example_previous">
                                    <a href="#">上一页</a>
                                </li>
                                <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0">
                                    <a href="#">1</a>
                                </li>
                                <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                    <a href="#">2</a>
                                </li>
                                <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                    <a href="#">3</a>
                                </li>
                                <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                    <a href="#">4</a>
                                </li>
                                <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                    <a href="#">5</a>
                                </li>
                                <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                    <a href="#">6</a>
                                </li>
                                <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0"
                                    id="dataTables-example_next">
                                    <a href="#">下一页</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>


        </div>

    </div>


</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a class="btnclose" data-dismiss="modal" aria-hidden="true">
                    <i class="iconfont">&#xe62b;</i>
                </a>
                <h4 class="modal-title" id="myModalLabel">
                    预警列表新增
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div style="padding-left: 47px;" class="namexg gjc nameadd">
                        <label class="name01">设备名称
                        </label>
                        <select id="basic" class="selectpicker show-tick form-control" data-live-search="true">
                            <option>--请选择--</option>
                            <option>客厅灯</option>
                            <option>空调</option>
                            <option>电脑</option>
                            <option>调节灯</option>

                        </select>
                    </div>
                    <div class="namexg gjc nameadd" style="padding-left: 20px;">
                        <div>
                            <label class="name01">预警设备状态
                            </label>
                        </div>
                        <div id="tj" class="tiaojie">
                            <div class="scale" id="slider-range-min"></div>
                            <p><input style="background: rgba(0,0,0,0);" type="text" class="shuzhi" id="amount"></p>
                        </div>
                    </div>

                    <div style="padding-left: 20px;" class="namexg gjc nameadd">
                        <label class="name01">预警设备状态
                        </label>
                        <div class="switch round">
                            <input class="btn-z" type="checkbox">
                            <label for="mySwitch3" style="margin-top: 5px;">

                            </label>
                        </div>
                    </div>
                    <div style="padding-left: 47px;" class="namexg gjc nameadd">
                        <label class="name01">预警状态
                        </label>
                        <div class="radio-inline">
                            <label class="danxuancheck">
                                <input class="checkboxclass" type="radio" value="">
                                已启用
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label class="danxuancheck2">
                                <input class="checkboxclass" type="radio" value="">
                                已关闭
                            </label>
                        </div>
                    </div>
                    <div style="padding-left: 47px;" class="namexg gjc nameadd">
                        <label class="name01">手机号码
                        </label>
                        <input style="float: left;" type="text" class="form-control" placeholder="请输入手机号码">
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定
                </button>
                <button type="button" class="btn btn-default cancle" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>


    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap-table/js/bootstrap-table.js"></script>
    <script src="/vendor/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
    <script src="/vendor/warning/js/warning.js"></script>


</body>
</html>