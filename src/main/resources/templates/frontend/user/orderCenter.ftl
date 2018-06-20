<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商品评价</title>

<link rel="stylesheet" type="text/css"
	href="/vendor/sweetalert/css/sweetalert2.min.css">
<link rel="stylesheet" type="text/css"
	href="/vendor/bootstrap-select/css/bootstrap-select.css">
<link rel="stylesheet" type="text/css"
	href="/vendor/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<style>
.zxys {
	margin-left: 15px;
}
</style>
</head>

<body>
	<input id="val2" value="<#if httppath??>${httppath}</#if>"
						name="val2" type="hidden" />



	<div class=" w1190 juli">
		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					用户中心&nbsp;><span>&nbsp;商品评价</span>
				</h1>
			</div>
		</div>


		<div class="row">
			<div class="">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header"></span>
					</div>
					<div class="panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">


							<div class="row">
								<div class="col-sm-12">



									<ul class="nav nav-tabs" id="myTab">

	<li class="active"><a href="#identifier" data-toggle="tab">全部</a></li>
										<li ><a href="#identifier1"
											data-toggle="tab">待支付</a></li>
										<li class=""><a href="#identifier4" data-toggle="tab">待发货</a></li>
										<li class=""><a href="#identifier5" data-toggle="tab">待收货</a></li>
										<li class=""><a href="#identifier6" data-toggle="tab">已完成</a></li>
										<li class=""><a href="#identifier2" data-toggle="tab">已取消</a></li>
										<li class=""><a href="#identifier8" data-toggle="tab">已关闭</a></li>
									
									</ul>

									<div class="tab-content">
										<div class="row tab-pane " id="identifier1">

											<div class=" col-lg-12 margin-top-5">

												<div class="table-responsive" style="margin: 10px;">
													<table id="table_list1"
														class="table table-bordered table-hover table-striped"></table>
												</div>
											</div>
										</div>
										<div class="row tab-pane " id="identifier4">

											<div class=" col-lg-12 margin-top-5">



												<div class="table-responsive" style="margin: 10px;">
													<table id="table_list4"
														class="table table-bordered table-hover table-striped"></table>
												</div>



											</div>
										</div>

										<div class="row tab-pane " id="identifier5">

											<div class=" col-lg-12 margin-top-5">



												<div class="table-responsive" style="margin: 10px;">
													<table id="table_list5"
														class="table table-bordered table-hover table-striped"></table>
												</div>



											</div>
										</div>

										<div class="row tab-pane " id="identifier6">

											<div class=" col-lg-12 margin-top-5">



												<div class="table-responsive" style="margin: 10px;">
													<table id="table_list6"
														class="table table-bordered table-hover table-striped"></table>
												</div>



											</div>
										</div>

										<div class="row tab-pane " id="identifier2">

											<div class=" col-lg-12 margin-top-5">



												<div class="table-responsive" style="margin: 10px;">
													<table id="table_list2"
														class="table table-bordered table-hover table-striped"></table>
												</div>



											</div>
										</div>
										<div class="row tab-pane " id="identifier8">

											<div class=" col-lg-12 margin-top-5">



												<div class="table-responsive" style="margin: 10px;">
													<table id="table_list8"
														class="table table-bordered table-hover table-striped"></table>
												</div>



											</div>
										</div>

										<div class="row tab-pane active" id="identifier">

											<div class=" col-lg-12 margin-top-5">




												<div class="" style="margin: 10px;">

													<div class="row">

														<div class="row padding-bottom-5">
															<div class="col-sm-12 ">
																<div class="col-sm-5">
																	<div class="input-group">
																		<span class="input-group-addon" id="orderIdLabel">订单编号：</span>
																		<input id="orderId" type="text" class="form-control"
																			aria-describedby="orderIdLabel" style="width: 240px">
																	</div>
																</div>
																<div class="col-sm-5">
																	<div class="input-group">
																		<span class="input-group-addon" id="userNameLabel">商品名称：</span>
																		<input id="goodName" type="text" class="form-control"
																			aria-describedby="userNameLabel" style="width: 240px">
																	</div>
																</div>
															</div>


														</div>
														<div class="row padding-bottom-5">
															<div class="col-sm-12 ">
																<div class="col-sm-5">
																	<div class="input-group">
																		<span class="input-group-addon" id="startTimeLabel">开始时间：</span>
																		<input id="startTime" type="text" class="form-control"
																			aria-describedby="startTimeLabel"
																			style="width: 240px">
																	</div>
																</div>
																<div class="col-sm-5">
																	<div class="input-group">
																		<span class="input-group-addon" id="endTimeLabel">结束时间：</span>
																		<input id="endTime" type="text" class="form-control"
																			aria-describedby="endTimeLabel" style="width: 240px">
																	</div>
																</div>
															</div>
														</div>

														
													</div>
													
													<div class="row padding-bottom-5">
															<div class="col-sm-12 ">
															<div class="col-sm-9">
															</div>
															<div class="col-sm-3">
													<button type="button" class="btn btn-default zxys"
																id="btnQry" onclick="doQuery()">查询</button>		
															
													<button type="button" class="btn btn-default zxys"
																id="btnExport" onclick="doexport()">导出订单详情</button>		
															</div>
															
													
															</div>
													</div>
	


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
				</div>
			</div>
		</div>
		<script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>
		<script
			src="/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
		<script
			src="/vendor/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="/vendor/order/order_front.js"></script>
</body>
</html>