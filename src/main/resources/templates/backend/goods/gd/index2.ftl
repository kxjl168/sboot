<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商品管理</title>

<link rel="stylesheet" href="/js/ztree/zTreeStyle.css">
<link rel="stylesheet" href="/vendor/select2/css/select2.min.css">
<link rel="stylesheet" href="/vendor/select2/css/select2.bootstrap.css">


<style type="text/css">


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
					商品管理&nbsp;><span>&nbsp;商品列表</span>
				</h1>
			</div>
		</div>


		<div class="row">




			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">商品列表</span>
					</div>
					<div class="panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row ">
								<div class=" col-sm-9">
									<div class="form-group">
										<label for="name" class="lb_text col-lg-5 control-label">商品名称:</label>

										<div class="col-lg-7">
											<input id="q_name" type="text" name="q_name"
												class="form-control " placeholder=""
												aria-controls="dataTables-example">
										</div>
									</div>
								</div>

								<div class="col-sm-3 ">
									<button type="button" class="btn btn-default zxys" id="btnQry"
										onclick="doquery()">查询</button>

									<button type="button" class="hide btn btn-default zxys"
										id="btnQry" onclick="showerror()">error</button>
									<button type="button" class="  btn btn-default zxys"
										id="btnQry" onclick="showinfo()">info</button>


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
			data-keyboard=false tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="display: none;">
			<div class="modal-dialog" style="width: 1100px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">商品<span id="action"></span></h4>

					</div>



					<div class="modal-body nopadding margin-top-5">
						<div class="row">

							<ul class="nav nav-tabs" id="myTab">
								<li class="active"><a href="#identifier1" data-toggle="tab">常规</a></li>
								<li  class=""><a href="#identifier5" data-toggle="tab">详情</a></li>
								<li class=""><a href="#identifier2" data-toggle="tab">属性</a></li>
								<li class=""><a href="#identifier3" data-toggle="tab">选项</a></li>
								<li class=""><a href="#identifier4" data-toggle="tab">附件</a></li>
							</ul>

							<div class="tab-content">
								<div class="row tab-pane active" id="identifier1">

									<div class=" col-lg-12 margin-top-5">

										<form id="mform" name="mform" class="form-horizontal"
											role="form" action="" method="post">
										
											<#include "gd_normal.ftl"/>
										</form>
									</div>
								</div>
								<div class="row tab-pane " id="identifier5">

									<div class=" col-lg-12 margin-top-5">


										<div class="row">
										<form  class="form-horizontal"
											role="form" action="" method="post">
											<div class="form-group">
												<label for="name" class="col-lg-2 control-label">商品详情</label>

												<div class="col-lg-10">
													<textarea id="s_context" name="s_context" placeholder=" "></textarea>
													<p class="help-block"></p>
												</div>
											</div>
											</form>
										</div>



									</div>
								</div>

								<div class="row tab-pane  fade in" id="identifier2">
									<div class="  col-lg-12 margin-top-5">

										<form id="mform_inner_attr" class="form-horizontal" role="form"
											action="" method="post">
											<#include "gd_attr.ftl"/>
									 	</form>

									</div>


								</div>

								<div class="tab-pane  fade in" id="identifier3">
									<div class="  col-lg-12 margin-top-5">

										<form id="mform_inner_option" class="form-horizontal" role="form"
											action="" method="post">
											<#include "gd_option.ftl"/>
									 	</form>

									</div>

								</div>

								<div class="tab-pane  fade in" id="identifier4">
									<div class="col-lg-12 margin-top-5">附件</div>

								</div>



							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="close">关闭</button>
						<button type="button" class="btn btn-primary" id="btnSubmit">
							提交更改</button>
					</div>


				</div>
			</div>
		</div>




	<#include "img_upload.ftl"/> 



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

		<#include "../attri/goodattri.ftl"/> 
		<#include "../option/goodoption.ftl"/>




		<!-- <script src="/js/ztree/jquery.ztree.all.min.js"></script> -->
		<script src="/vendor/ckeditor4.8/ckeditor.js"></script>
		<script src="/vendor/ckeditor4.8/adapters/jquery.js"></script>

		<script src="/vendor/select2/js/select2.full.js"></script>
		<script src="/vendor/select2/js/i18n/zh-CN.js"></script>

		<script src="/vendor/goods/js/gd.js"></script>
			<script src="/vendor/goods/js/attri_add.js"></script>
		<script src="/vendor/goods/js/option_add.js"></script>
		<script src="/vendor/goods/js/upload_muti.js"></script>
		<script src="/vendor/goods/js/gd_attr.js"></script>
		<script src="/vendor/goods/js/gd_option.js"></script>


	
		
		
</body>
</html>