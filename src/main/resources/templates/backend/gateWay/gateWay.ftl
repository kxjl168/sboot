<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>网关管理</title>

    <link rel="stylesheet" type="text/css" href="/vendor/loading/style.css">
    <link rel="stylesheet" type="text/css" href="/vendor/gateWay/css/gateWay.css">


</head>
<body>
<div id="over" class="over"></div>

<div id="layout" class="layout">
    <div id="ajaxloader">
        <div class="outer"></div>
        <div class="inner"></div>
    </div>
</div>





<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                </h4>
            </div>

            <form id="gateWayForm" class="form-horizontal" role="form">
                <input type="hidden" value="" id="id" name="id">
                <div class="modal-body">
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="form-group">
                                <label for="name" class="col-lg-3 control-label">网关名称</label>

                                <div class="col-lg-9">
                                    <input type="text" name="name" class="form-control" id="name"
                                           placeholder="设置网关名">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="authKey" class="col-lg-3 control-label">加密密钥</label>

                                <div class="col-lg-9">
                                    <input type="text" name="authKey" class="form-control " id="authKey"
                                           placeholder="设置加密密钥">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="enableEncrypt" class="col-lg-3 control-label">是否启用加密</label>

                                <div class="col-lg-9">
                                    <select name="enableEncrypt" class="form-control" id="enableEncrypt">
                                        <option value="1">
                                            是
                                        </option>
                                        <option value="0">
                                            否
                                        </option>
                                    </select>
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="manufacturerId" class="col-lg-3 control-label">设备厂商ID</label>

                                <div class="col-lg-9">
                                    <select name="manufacturerId" class="form-control" id="manufacturerId">
                                    <#list manuFacturers as manuFacturer>
                                        <option value="${manuFacturer.id!}">${manuFacturer.name!}</option>
                                    </#list>
                                    </select>
                                    <p class="help-block"></p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                    <button type="submit" class="btn btn-primary" id="submit">
                        提交更改
                    </button>
                </div>
            </form>


        </div>
    </div>
</div>


<div>
    <div class="row">
        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px;float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">网关管理 &nbsp;><span>&nbsp;网关列表</span></h1>
        </div>
    </div>

    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-heading">
                    <span class="header">网关列表</span>
                </div>
            </div>
            <div class="panel-body">
                <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                    <div class="row">
                        <div class="col-sm-6">
                            <div id="dataTables-example_filter" class="dataTables_filter">

                                <button type="button" class="btn btn-default" id="addGateWay">新增</button>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-sm-12">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                    <tr class="tableCenter">
                                        <th>序号</th>
                                        <th>网关名称</th>
                                        <th>网关KEY</th>
                                        <th>时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbody">
                                    <#list gateWayList as gateWay>
                                    <tr id="${gateWay.id}" class="tableCenter1">
                                        <td>${gateWay_index + 1}</td>
                                        <td>${gateWay.name}</td>
                                        <td>${gateWay.authKey}</td>
                                        <td>${gateWay.createDate?string('yyyy-MM-dd hh:mm:ss')}</td>
                                        <td>
                                            <#if gateWay.status=='1'>
                                                已连接
                                            <#else>
                                                未连接
                                            </#if>
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-info btn-xs"
                                               onclick="readgetwaydata('${gateWay.id}')">读取数据</a>
                                            <a href="javascript:void(0);" class="btn btn-info btn-xs btnModf"
                                               gwId="${gateWay.id}">修改</a>
                                        </td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                </table>


                            <#import "../../common/pagination.ftl" as pagination>
                            <@pagination.page action="/manager/gateWay/view.action"></@pagination.page>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var groupId = "${groupId}";
</script>
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/gateWay/js/gateWay.js"></script>


</body>

</html>