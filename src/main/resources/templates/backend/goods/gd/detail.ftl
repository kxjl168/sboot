<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商品上下架管理</title>

<link rel="stylesheet" href="/js/ztree/zTreeStyle.css">
<link rel="stylesheet" href="/vendor/select2/css/select2.min.css">
<link rel="stylesheet" href="/vendor/select2/css/select2.bootstrap.css">


</head>

<body>




	<div>
		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					商品管理&nbsp;><span>&nbsp;商品上下架</span>
				</h1>
			</div>
		</div>


		<div class="row">




			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">商品详情列表</span>
					</div>
					<div class="panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row ">
								<div class=" col-sm-9">
								
									<div class=" col-sm-3">
									<div class="input-group">
										<span for="name" class="input-group-addon">商品详情名称:</span>

										
											<input id="q_name" type="text" name="q_name" 
												class="form-control qinput " placeholder=""
												aria-controls="dataTables-example">
										
									</div>
									</div>
								</div>

								<div class="col-sm-3 ">
									<button type="button" class="btn btn-default zxys" id="btnQry"
										onclick="doquery()">查询</button>

									<button type="button" class="hide btn btn-default zxys"
										id="btnQry" onclick="showerror()">error</button>
									<button type="button" class="hide  btn btn-default zxys"
										id="btnQry" onclick="showinfo()">info</button>


									<button type="button" class="hide btn btn-default" id="btnAdd">新增</button>
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

		<div class="modal fade" data-backdrop="static" id="myModal_detail"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							商品上架 <span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform_detail" name="mform_attri" class="form-horizontal" role="form"
						action="" method="post">

						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">


									<div class="form-group hide">
		<label for="name" class="col-lg-2 control-label">商品instanceID</label>

		<div class="col-lg-10">
		 <input type="hidden"
				name="id" class="form-control" id="id"
				placeholder="商品ID">
				
			<input type="text" name="status" value="1" class="form-control" id="status"
												placeholder="状态">
			<p class="help-block"></p>
		</div>
	</div>
	
	
	

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">价格</label>

										<div class="col-lg-9">
											<input type="text" name="price" class="form-control" id="price"
												placeholder="价格">
											<p class="help-block"></p>
										</div>
									</div>


								





								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="close">关闭</button>
							<button type="button" class="btn btn-primary" id="btnSubmit_detail">
								提交更改</button>
						</div>
					</form>


				</div>
			</div>
		</div>





		<div class="modal fade " id="myModal_file" tabindex="-1 "
			data-backdrop="static" role="dialog " aria-labelledby="myModalLabel ">
			<div class="modal-dialog " style="width: 350px;">
				<div class="modal-content ">
					<div class="modal-header ">
						<button type="button " class="close hide " data-dismiss="modal "
							aria-hidden="true ">&times;</button>
						<h4 class="modal-title " id="myModalLabel ">选择文件</h4>


					</div>



					<input id="val2" value="<#if httppath??>${httppath}</#if>"
						name="val2" type="hidden" />


					<iframe id="fileUploadFrame" name="fileUploadFrame" src=""
						frameborder="0" hspace="0" height="0" width="0"></iframe>




					<div class="modal-body  margin-top-10 ">

						<form method="post" id="fileform" name="fileform"
							target="fileUploadFrame" enctype="multipart/form-data">


							<div class="row col-xs-12">
								<div class="control-label padding-top-0  ">图标：</div>
							</div>
							<div class="row col-xs-11">

								<div class="col-xs-10 text-right ">
									<input required class=" form-control" type="file"
										name="fileUploadURL" id="fileUploadURL"
										ng-model="fileUploadURL" placeholder=" ">
								</div>
								<div class="col-xs-2 text-right ">
									<button type="button " class="btn btn-primary" name="uploadSub"
										id="uploadSub">上传</button>
								</div>

							</div>



						</form>

					</div>

					<div class="modal-footer ">
						<button type="button " class="btn btn-default btn-warning "
							data-dismiss="modal">取消</button>

					</div>
				</div>
			</div>
		</div>


		<div class="modal fade " id="myModal_confirm" tabindex="-1 "
			role="dialog " aria-labelledby="myModalLabel " aria-hidden="true ">
			<div class="modal-dialog " style="width: 250px;">
				<div class="modal-content ">
					<div class="modal-header ">
						<button type="button " class="close " data-dismiss="modal"
							aria-hidden="true ">&times;</button>
						<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
					</div>



					<div class="modal-body container margin-top-10 ">
						<div class="row ">

							<p class="col-xs-10 " id="confirm_txt">确认执行操作吗？</p>

						</div>
					</div>
					<div class="modal-footer ">
						<button type="button " class="btn btn-default btn-warning "
							data-dismiss="modal ">取消</button>
						<button id="btnconfirm" type="button " class="btn btn-primary ">
							确定</button>
					</div>
				</div>
			</div>
		</div>

	

		<!-- <script src="/js/ztree/jquery.ztree.all.min.js"></script> -->
		<!-- <script src="/vendor/ckeditor4.8/ckeditor.js"></script>
		<script src="/vendor/ckeditor4.8/adapters/jquery.js"></script> -->

		<script src="/vendor/select2/js/select2.full.js"></script>
		<script src="/vendor/select2/js/i18n/zh-CN.js"></script>

		<script src="/vendor/goods/js/gd_detail.js"></script>
			
<!-- 
				<script src="/vendor/goods/js/attri_add.js"></script>
		<script src="/vendor/goods/js/option_add.js"></script>
		<script src="/vendor/goods/js/upload_muti.js"></script>
		<script src="/vendor/goods/js/gd_attr.js"></script>
		<script src="/vendor/goods/js/gd_option.js"></script>

	 -->
		
		
</body>
</html>