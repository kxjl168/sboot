<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品品牌管理</title>

    <link rel="stylesheet" href="/js/ztree/zTreeStyle.css">
<link rel="stylesheet" href="/css/FileUploadMuti.css">

</head>

<body>


<div>

	<input id="val2" value="<#if httppath??>${httppath}</#if>"
						name=""val2"" type="hidden" />


    <div class="row">

        <div class="col-lg-12 wzbj">
            <div style="padding-top: 9px; float: left; padding-right: 4px;">
                <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
            </div>
            <h1 class="page-header">
                商品管理&nbsp;><span>&nbsp;品牌列表</span>
            </h1>
        </div>
    </div>


    <div class="row">


        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="header">品牌列表</span>
                </div>
                <div class="panel-body">

                    <div id="dataTables-example_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row ">
                            <div class=" col-sm-9">
                            
                            
                            
                            <div class="col-sm-3">
                          <div class="input-group">
                                    <span for="name" class="input-group-addon   ">品牌名称:</span>

                                    <input id="q_name" type="text" name="q_name"
                                               class="form-control qinput" placeholder=""
                                               aria-controls="dataTables-example">
                                </div>
                            </div>
                            
                                
                            </div>

                            <div class="col-sm-3 ">
                                <button type="button" class="btn btn-default zxys" id="btnQry"
                                        onclick="doquery()">查询
                                </button>

                                <button type="button" class="hide btn btn-default zxys" id="btnQry"
                                        onclick="showerror()">error
                                </button>
                                <button type="button" class="hide  btn btn-default zxys" id="btnQry"
                                        onclick="showinfo()">info
                                </button>


                                <button type="button" class="btn btn-default" id="btnAdd">新增</button>
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
                        品牌编辑
                    </h4>

                </div>

                <form id="mform" name="mform" class="form-horizontal" role="form"
                      action="" method="post">

                    <div class="modal-body">
                        <div class="row">

                            <div class="col-lg-12">


                                <div class="form-group hide">
                                    <label for="name" class="col-lg-3 control-label">品牌ID</label>

                                    <div class="col-lg-9">
                                        <input type="hidden" name="id" class="form-control" id="id"
                                               placeholder="品牌ID">
                                        <p class="help-block"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="name" class="col-lg-3 control-label">品牌名称</label>

                                    <div class="col-lg-9">
                                        <input type="text" name="brandName" class="form-control" id="brandName"
                                               placeholder="品牌名称">
                                        <p class="help-block"></p>
                                    </div>
                                </div>


<!-- 
<div class="form-group">

		<input value="1" type="hidden" id="imgs_count"> <label
			for="name" class="col-lg-2 control-label">商品图片</label>

		<div class="col-lg-10 " id="imgs">
			<div class=" col-xs-3  row margin-top-5 gdimgcontainer">
				<input readonly="readonly" class=" form-control gdimg hidevalidate"
					type="text" name="fileId" id="fullurl_fileId" placeholder=" ">


				<img id="fullurl" src="/img/default.jpg"
					class="img-responsive gdimg"></img>

			</div>

			<div id="imgadd" class=" col-xs-3  row margin-top-5 gdimgcontainer">

				<img src="/img/add.png" class="img-responsive "></img>

			</div>

			<p class="help-block"></p>
		</div>
	</div>
	 -->
	
                         
                                
                                <div class="form-group">

		<input value="1" type="hidden" id="imgs_count"> <label
			for="name" class="col-lg-3 control-label">品牌图片</label>

		<div class="col-lg-9 " id="imgs">
			<div class=" col-xs-3  row margin-top-5 gdimgcontainer">
				<input readonly="readonly" class=" form-control gdimg hidevalidate"
					type="text" name="fileId" id="fullurl_fileId" placeholder=" ">


				<img id="fullurl" src="/img/default.jpg"
					class="img-responsive gdimg"></img>

			</div>

			<div id="imgadd" class=" col-xs-3  row margin-top-5 gdimgcontainer">

				<img src="/img/add.png" class="img-responsive "></img>

			</div>

			<p class="help-block"></p>
		</div>
	</div>
	 


                                <div class="form-group">
                                    <label for="name" class="col-lg-3 control-label">是否可用</label>

                                    <div class="col-lg-9">

                                        <select name="status" class="form-control" id="available">

                                            <option value="1" selected="selected">启用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                        <p class="help-block"></p>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="name" class="col-lg-3 control-label">排序号</label>

                                    <div class="col-lg-9">
                                        <input type="text" name="sort" class="form-control"
                                               id="sort" placeholder="排序号">
                                        <p class="help-block"></p>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="name" class="col-lg-3 control-label">备注</label>

                                    <div class="col-lg-9">
                                        <input type="text" name="remark" class="form-control"
                                               id="remark" placeholder="备注">
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






    <script src="/js/ztree/jquery.ztree.all.min.js"></script>
    <script src="/vendor/goods/js/brand.js"></script>
    <!-- <script src="/vendor/goods/js/upload.js"></script> -->
  	<script src="/vendor/goods/js/ImgUploadMuti.js"></script>
</body>
</html>