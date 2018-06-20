<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品分类管理</title>

    <link rel="stylesheet" href="/js/ztree/zTreeStyle.css">
    <link rel="stylesheet" href="/vendor/treegrid/css/jquery.treegrid.css">
    <link rel="stylesheet" href="/vendor/bootstrap-fileInput/css/fileinput.min.css">
    <link rel="stylesheet" type="text/css" href="/vendor/sweetalert/css/sweetalert2.min.css">
    <#--<link rel="stylesheet" href="/vendor/bootstrap-table/css/bootstrap-table.min.css">-->
    <#--<link rel="stylesheet" href="/vendor/bootstrap-table/css/bootstrap-table.css">-->
<#--<link href="/vendor/select2/css/select2.css" rel="stylesheet" />-->
</head>

<body>


<div>
    <div class="row">

        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px; float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">
                商品管理&nbsp;><span>&nbsp;分类列表</span>
            </h1>
        </div>
    </div>


    <div class="row">


        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">分类列表</span>
                </div>
                <div class="panel-body">

                    <div id="dataTables-example_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row ">
                            <div class=" col-sm-9">
                                <div class="form-group">
                                    <button class="btn btn-danger btn-lg" id="btnAdd" onclick="addSummitClassify ()">
                                        <span class="glyphicon glyphicon-plus"></span>
                                        新增一级分类
                                    </button>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-sm-12">

                                <div class="table-responsive" style="margin: 10px;">
                                    <table id="table_list"
                                           class="tree table table-bordered table-hover table-striped table-condensed"
                                           <#--data-toggle="table"
                                           data-pagination="true"
                                           data-search="true"-->>
                                        <thead id="classifyTableHeader">
                                        <tr>
                                            <th style="text-align:center;">分类级别</th>
                                            <th style="text-align:center;">分类名称</th>
                                            <th style="text-align:center;">分类描述</th>
                                            <th style="text-align:center;">图标路径</th>
                                            <th style="text-align:center;">图片路径</th>
                                            <th style="text-align:center;">大图片路径</th>
                                            <th style="text-align:center;">类别状态</th>
                                            <th style="text-align:center;">是否启用</th>
                                            <th style="text-align:center;">显示顺序</th>
                                            <th style="text-align:center;">创建人员</th>
                                            <th style="text-align:center;">创建时间</th>
                                            <th style="text-align:center;">更新人员</th>
                                            <th style="text-align:center;">更新时间</th>
                                            <th style="text-align:center;">可用操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#list commodityClassifyList as commodityFirstClassify>
                                        <tr class="treegrid-first_${commodityFirstClassify.id}"
                                            style="background-color: #41bdff">
                                            <td style="text-align:center;">一级分类</td>
                                            <td style="text-align:center;display: none">${commodityFirstClassify.id!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.name!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.description!""}</td>
                                            <td style="text-align:center;"><img
                                                    src="${"/file/" + (commodityFirstClassify.iconFile.http_relative_path)!""}">
                                            </td>
                                            <td style="text-align:center;"><img
                                                    src="${"/file/" + (commodityFirstClassify.pictureFile.http_relative_path)!""}">
                                            </td>
                                            <td style="text-align:center;"><img
                                                    src="${"/file/" + (commodityFirstClassify.bigPictureFile.http_relative_path)!""}">
                                            </td>
                                            <td style="text-align:center;">${commodityFirstClassify.status!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.enabled!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.sort!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.creator!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.createTime!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.updater!""}</td>
                                            <td style="text-align:center;">${commodityFirstClassify.updateTime!""}</td>
                                            <td style="text-align:center;">
                                                <div class="btn-group" role="group" aria-label="...">
                                                    <button type="button"
                                                            class="btn btn-default firstClassifyAdd_${commodityFirstClassify.id}"
                                                            onclick="ClassifyAdd(this, 2)">
                                                        <span class="glyphicon glyphicon-plus"/>新增
                                                    </button>
                                                    <button type="button"
                                                            class="btn btn-info firstClassifyEdit_${commodityFirstClassify.id}"
                                                            onclick="ClassifyEdit(this, 1)">
                                                        <span class="glyphicon glyphicon-pencil"/>修改基本信息
                                                    </button>
                                                    <button type="button"
                                                            class="btn btn-info firstClassifyEdit_${commodityFirstClassify.id}"
                                                            onclick="fileModalShow(this)">
                                                        <span class="glyphicon glyphicon-pencil"/>修改图片信息
                                                    </button>
                                                    <button type="button"
                                                            class="btn btn-danger firstClassifyDelete_${commodityFirstClassify.id}"
                                                            onclick="ClassifyDelete(this, 1)">
                                                        <span class="glyphicon glyphicon-trash"/>删除
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                            <#list commodityFirstClassify.commoditySecondClassifyList as commoditySecondClassify>
                                            <tr class="treegrid-second_${commoditySecondClassify.id} treegrid-parent-first_${commodityFirstClassify.id}"
                                                style="background-color: #80d3ff">
                                                <td style="text-align:center;">二级分类</td>
                                                <td style="text-align:center; display: none">${commoditySecondClassify.id!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.name!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.description!""}</td>
                                                <td style="text-align:center;"></td>
                                                <td style="text-align:center;"></td>
                                                <td style="text-align:center;"></td>
                                                <td style="text-align:center;"></td>
                                                <td style="text-align:center;">${commoditySecondClassify.enabled!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.sort!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.creator!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.createTime!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.updater!""}</td>
                                                <td style="text-align:center;">${commoditySecondClassify.updateTime!""}</td>
                                                <td style="text-align:center;">
                                                    <div class="btn-group" role="group" aria-label="...">
                                                        <button type="button"
                                                                class="btn btn-default secondClassifyAdd_${commoditySecondClassify.id}"
                                                                onclick="ClassifyAdd(this, 3)">
                                                            <span class="glyphicon glyphicon-plus"/>新增
                                                        </button>
                                                        <button type="button"
                                                                class="btn btn-info secondClassifyEdit_${commoditySecondClassify.id}"
                                                                onclick="ClassifyEdit(this, 2)">
                                                            <span class="glyphicon glyphicon-pencil"/>修改
                                                        </button>
                                                        <button type="button"
                                                                class="btn btn-danger secondClassifyDelete_${commoditySecondClassify.id}"
                                                                onclick="ClassifyDelete(this, 2)">
                                                            <span class="glyphicon glyphicon-trash"/>删除
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                                <#list commoditySecondClassify.commodityThirdClassifyList as commodityThirdClassify>
                                                <tr class="treegrid-third_${commodityThirdClassify.id} treegrid-parent-second_${commoditySecondClassify.id}"
                                                    style="background-color: #bbe7ff">
                                                    <td style="text-align:center;">三级分类</td>
                                                    <td style="text-align:center;display: none">${commodityThirdClassify.id!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.name!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.description!""}</td>
                                                    <td style="text-align:center;"></td>
                                                    <td style="text-align:center;"></td>
                                                    <td style="text-align:center;"></td>
                                                    <td style="text-align:center;"></td>
                                                    <td style="text-align:center;">${commodityThirdClassify.enabled!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.sort!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.creator!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.createTime!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.updater!""}</td>
                                                    <td style="text-align:center;">${commodityThirdClassify.updateTime!""}</td>
                                                    <td style="text-align:center;">
                                                        <div class="btn-group" role="group" aria-label="...">
                                                            <button type="button"
                                                                    class="btn btn-info thirdClassifyEdit_${commodityThirdClassify.id}"
                                                                    onclick="ClassifyEdit(this, 3)">
                                                                <span class="glyphicon glyphicon-pencil"/>修改
                                                            </button>
                                                            <button type="button"
                                                                    class="btn btn-danger thirdClassifyDelete_${commodityThirdClassify.id}"
                                                                    onclick="ClassifyDelete(this, 3)">
                                                                <span class="glyphicon glyphicon-trash"/>删除
                                                            </button>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </#list>
                                            </#list>
                                        </#list>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


            </div>
        </div>
    </div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" data-backdrop="static" id="myModal"
         tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" id="myModalHeader">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;
                    </button>
                    <h4 class="modal-title">
                    </h4>
                </div>

                <form id="mform" name="mform" class="form-horizontal" role="form"
                      action="" method="post">

                    <div class="modal-body">
                        <div class="row">

                            <div class="col-lg-12">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" id="close">关闭
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <script src="/js/ztree/jquery.ztree.all.min.js"></script>
    <script src="/vendor/treegrid/js/jquery.treegrid.js"></script>
    <script src="/vendor/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
    <script src="/vendor/bootstrap-fileInput/js/fileinput.min.js"></script>
    <script src="/vendor/bootstrap-fileInput/js/zh.js"></script>
    <script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>
    <script src="/vendor/classify/classifyShowInfo.js"></script>
    <script src="/vendor/classify/classifyDomOperate.js"></script>
    <script src="/vendor/classify/classifyEvent.js"></script>
</body>
</html>