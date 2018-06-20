<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>售后</title>

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

.db-showpanel {
	width: 462px;
	height: 462px;
	float: left;
	border: 1px solid #e7e7e7;
}

.db-icbu {
	width: 526px;
	height: 462px;
	margin-left: 463px;
	border-top: 1px solid #e7e7e7;
}

.mod-applytype-item a {
	display: block;
	padding: 35px 0 0 40px;
	height: 100%;
	border: 1px solid #e8e8e8;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-ms-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	transition: all .3s ease;
	margin-bottom: 20px;
}

.mod-applytype-item a:hover {
	border: 1px solid red;
}

.mod-applytype-tit {
	margin-bottom: 7px;
	font-size: 18px;
	color: #3c3c3c;
	font-weight: bold;
}

.mod-applytype-desc {
	font-size: 16px;
	color: #9c9c9c;
	line-height: 1.2em;
}

.mod-applytype-desc, .mod-applytype-tit {
	font-family: "\5FAE\8F6F\96C5\9ED1";
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.mod-applytype-item img {
	float: left;
	width: 50px;
	margin-right: 20px;
	max-width: 100px;
}

.mod-applytype-item a, .mod-applytype-item a:hover {
	outline: 0;
	text-decoration: none;
}

.gimg {
	max-width: 150px;
}
</style>

<script src="/vendor/rate/rate.js"></script>

<script src="/vendor/order/order_commet.js"></script>


<script>
	
</script>
</head>

<body>
	<input id="val2" value="<#if httppath??>${httppath}</#if>" name="val2"
		type="hidden" />
	<input id="cuserId" value="<#if httppath??>${cuserId}</#if>"
		name="cuserId" type="hidden" />





	<div class=" w1190 juli">
		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					用户中心&nbsp;><span>&nbsp;退换货</span>
				</h1>
			</div>
		</div>



		<input id="id" name="id" type="hidden" value=${order.id!"" } />

		<div class="row clist">

			<div class="panel panel-default">
				<div class="panel-heading hide"></div>
				<div class="panel-body">






					<div class="col-sm-12 ">



						<div class="form-group">
							<label for="name" class="col-lg-2 control-label">退换商品:</label>

							<div class="col-lg-9">
								<div class="col-sm-2 ">
									<#if goods??>
									<div>
										<img class="gimg"
											src="${httppath }${goods.commodity.commondityImages[0].fileinfo.http_relative_path }" />

									</div>

									<span> </span> </#if>
								</div>

								<div class="col-sm-8">${goods.commodity.name}</div>
							</div>
						</div>


						<div class="form-group">
							<label for="name" class="col-lg-2 control-label">服务类型:</label>

							<div class="col-sm-9">
								<div class="col-sm-12 margin-bottom-5">
									<input type="radio" name="type" value="1"> 退款
								</div>
								<div class="col-sm-12 margin-bottom-5">
									<input type="radio" name="type" value="2"> 退款退货
								</div>
								<div class="col-sm-12 margin-bottom-5">
									<input type="radio" name="type" value="3"> 换货
								</div>

							</div>
						</div>





						<div class="form-group">
							<label for="name" class="col-lg-2 control-label">退换说明:</label>

							<div class="col-lg-9">
								<textarea rows="10" cols="" name="value"
									class="svalue form-control" id="value" placeholder="退换货.退款说明"></textarea>

								<p class="help-block"></p>
							</div>
						</div>
						

<div class="form-group">
							<label for="name" class="col-lg-2 control-label"></label>

							<div class="col-lg-9">
								
<button type="button" class="btn btn-danger width-100" id="btnSubmit_attr"
						onclick="exchange()">提交</button>



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
		
			
			<script src="/vendor/order/order_doexchange.js"></script>
			
</body>
</html>