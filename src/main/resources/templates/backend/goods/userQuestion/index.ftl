<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品商品咨询管理</title>

    <link rel="stylesheet" type="text/css" href="/vendor/sweetalert/css/sweetalert2.min.css">

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
                商品管理&nbsp;><span>&nbsp;商品咨询列表</span>
            </h1>
        </div>
    </div>


    <div class="row">


        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">商品咨询列表</span>
                </div>
                <div class="panel-body">

                    <div id="dataTables-example_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row">
                            <div class="col-sm-9">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="goodLabel">商品名称：</span>
                                        <input id="goodName" type="text" class="form-control" aria-describedby="goodLabel"
                                                style="width: 240px">
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="questionLabel">问题内容：</span>
                                        <input id="question" type="text" class="form-control" aria-describedby="questionLabel"
                                               style="width: 240px">
                                    </div>
                                </div>
                                <button type="button" class="btn btn-default zxys" id="btnQry"
                                        onclick="doQuery()">查询
                                </button>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="col-sm-1">
                            </div>
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

    <!-- 模态框（Modal） -->
    <div class="modal fade" data-backdrop="static" id="myModal"
         tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        商品咨询答复 <span id="message" style="margin-left: 20px;"></span>
                    </h4>

                </div>

                <form id="replyForm" name="mform" class="form-horizontal" role="form"
                      action="" method="post">

                    <div class="modal-body">
                        <div class="row">

                            <div class="col-lg-12">


                                <div class="form-group hide">
                                    <input type="hidden" name="id" class="form-control" id="questionId"
                                           placeholder="商品咨询ID">
                                </div>

                                <div class="form-group">
                                    <label for="name" class="col-lg-3 control-label">答复内容</label>

                                    <div class="col-lg-9">
											<textarea name="answer" style="height: 140px;" class="form-control"
                                                      id="answer"
                                                      placeholder="商品咨询名称"></textarea>
                                        <p class="help-block"></p>
                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" id="close">关闭
                        </button>
                        <button type="button" class="btn btn-primary" id="btnSubmit">
                            提交更改
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>
    <script src="/vendor/question/question.js"></script>
</body>
</html>