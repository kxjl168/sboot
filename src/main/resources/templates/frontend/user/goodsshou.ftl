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

.gimg{
max-width: 150px;
}
</style>



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
					用户中心&nbsp;><span>&nbsp;选择服务类型</span>
				</h1>
			</div>
		</div>



		<input id="id" name="id" type="hidden" value=${order.id!"" } />

		<div class="row clist">

			<div class="panel panel-default">
				<div class="panel-heading hide"></div>
				<div class="panel-body">

					<div class="col-sm-12 ">



<div class="col-sm-2 ">
						<#if goods??>
						<div >
							<img class="gimg" src="${httppath }${goods.commodity.commondityImages[0].fileinfo.http_relative_path }" />
							
						</div>

						<span>  </span>
						</#if>
						</div>
						
						<div class="col-sm-8">
						${goods.commodity.name}
						</div> 
					</div>
					
					<div class="col-sm-12">
					<hr />
					</div>
					
					<div class="col-sm-12 ">
					<p class="col-sm-5">选择服务类型
	</p>
	</div>

					<div class="col-sm-12 ">


	
	
						<div
							class=" col-sm-5 mod-applytype-item applyTypeItem applyrouter-applyTypesLeftContainer">
							<a
								href="#"
								onclick="tuikuan(1);"
								title="仅退款"> <img
								src="/img/y.png" class="img-responsive"
								alt="仅退款">
								<div class="mod-applytype-col">
									<h3 class="mod-applytype-tit">仅退款</h3>
									<p class="mod-applytype-desc">未收到货(包含未签收),或卖家协商同意前提下</p>
								</div>
							</a>
							<div class="mod-applytype-hide"></div>
						</div>


<div
							class=" col-sm-5 mod-applytype-item applyTypeItem applyrouter-applyTypesLeftContainer">
							<a
								onclick="tuikuan(2);"
								href="#"
								title="换货"> <img
								src="/img/h.png" class="img-responsive"
								alt="换货">
								<div class="mod-applytype-col">
									<h3 class="mod-applytype-tit">换货</h3>
									<p class="mod-applytype-desc">已收到货，需要更换已收到的货</p>
								</div>
							</a>
							<div class="mod-applytype-hide"></div>
						</div>

						<div
							class=" col-sm-5 mod-applytype-item applyTypeItem applyrouter-applyTypesLeftContainer">
							<a
							onclick="tuikuan(3);"
								href="#"
								title="退货退款"> <img
								src="/img/t.png" class="img-responsive"
								alt="退货退款">
								<div class="mod-applytype-col">
									<h3 class="mod-applytype-tit">退货退款</h3>
									<p class="mod-applytype-desc">已收到货,需要退换已收到的货物</p>
								</div>
							</a>
							<div class="mod-applytype-hide"></div>
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
			
			<script src="/vendor/order/order_select.js"></script>
			
			
</body>
</html>