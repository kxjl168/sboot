<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>订单管理</title>

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
</style>

		<script src="/vendor/rate/rate.js"></script>
		
		<script src="/vendor/order/order_commet.js"></script>
		
	
	<script>

		

			
		</script>
</head>

<body>
	<input id="val2" value="<#if httppath??>${httppath}</#if>"
						name="val2" type="hidden" />
<input id="cuserId" value="<#if httppath??>${cuserId}</#if>"
						name="cuserId" type="hidden" />





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
						<span class="header">商品...</span> <#if goods??> <span> <#if
							commodity??> ${commodity.name!""} </#if> </span> </#if> <span>${order.id!"" }</span>


					</div>
					<div class="panel-body">

						<div id="detailBox">
							<div class="db-showpanel">
								<a href="javascript:void(0)"> <img align="absmiddle"
									src="//img.alicdn.com/bao/uploaded/i2/882810118/TB2iJ29mm8YBeNkSnb4XXaevFXa_!!882810118-0-item_pic.jpg_460x460.jpg"></a>
							</div>
							<div class="db-icbu">
								<ol class="ui-form-bd">
									<li class="ui-form-row"><label class="ui-form-label">
											<h3>【狂欢价】超霸碳性干电池7号20粒+5号20粒 五号七号玩具电池批发遥控器鼠标</h3>
									</label></li>
									<li class="ui-form-row superstar-price"><label
										class="ui-form-label">价格</label>
										<div class="ui-form-right">
											<strong>32.00</strong> <span></span> 元
										</div></li>
									<li class="ui-form-row"><label class="ui-form-label">配送</label>
										<div class="ui-form-right">快递:&nbsp; 0.00</div></li>
									<li class="ui-form-row evalate"><label
										class="ui-form-label">评价</label>
										<div class="ui-form-right">
											<em class="superstar-tb-star" id="superstar-ratestar"><em>
													<i style="width: 58.8px;"></i>
											</em> <span>4.9分</span></em> <span> (累计评价 <span
												class="superstar-ratetotal">262188</span> )
											</span>
										</div></li>
								</ol>

								<ol class="scinfo-bd">
									<li class="ui-form-row">
										<div class="ui-form-right">
											<div class="ui-form-valid">
												<div class="ui-msg ui-form-msg">
													<p class="ui-msg-con ui-msg-tip">
														现在查看的是 您所购买商品的信息 <br> 于2018年6月2日下单购买了此商品 <s
															class="ui-msg-icon"></s>
													</p>
												</div>
											</div>
										</div>
									</li>
								</ol>
							</div>
						</div>


					</div>
				</div>
			</div>
		</div>

<#if order.state==6>
		<div class="row cmet">
			<div class="">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">评价</span>
					</div>
					<div class="panel-body">

						<form id="mform_attri" name="mform_attri" class="form-horizontal"
							role="form" action="" method="post">

<#if order.details??>
<#list order.details as detail>

							<div class="modal-body commetdiv">
								<div class="row">
								
								
							<input type="hidden" class="gid" name="gid" value="${detail.commodityInstance.commodity.id }" />
							<input type="hidden"  class="iid" name="iid" value="${detail.commodityInstance.id }" />	
						<input type="hidden"  class="oid" name="oid" value="${order.id }" />
												<input type="hidden"  class="odid" name="odid" value="${detail.id }" />




<div class="col-lg-3">
<div class="cimg">
 
<#list detail.commodityInstance.commodity.commondityImages as img>
  
     <#assign n =0 /> 
     <#if n == 0>
    <img alt="" src="${httppath}/${img.fileinfo.http_relative_path}" class="img-responsive">
   

     <#break>
     </#if>
  
   </#list>
   
  <p>${detail.commodityInstance.commodity.name}</p>
</div>
</div>

									<div class="col-lg-8">


										<div class="form-group hide">
											<label for="name" class="col-lg-2 control-label">属性ID</label>

											<div class="col-lg-9">
												<input type="hidden" name="id" class="form-control" id="id"
													placeholder="属性ID">
												<p class="help-block"></p>
											</div>
										</div>

										<div class="form-group">
											<label for="name" class="col-lg-2 control-label">评价</label>

											<div class="col-lg-9">
												<textarea rows="10" cols="" name="value" 
													class="svalue form-control" id="value" placeholder="评价"></textarea>

												<p class="help-block"></p>
											</div>
										</div>
										
										<div class="form-group">
											<label for="name" class="col-lg-2 control-label">打分</label>

											<div class="col-lg-9">
												<div id="star${detail_index}" class="start fleft" data-score="3"></div>
												<span id="rtip${detail_index}" class="hide low"></span>
												<div class="fleft margin-left-5">
												<span id="rtip2${detail_index}" class="low"></span>
												</div>
											</div>
											
											
											<script>
											initRate("star",'${detail_index}');
											</script>
											
										</div>
										


									</div>

								</div>
								
							</div>
							
						<#if detail_index!=order.details?size-1><hr/></#if>
							
						
							
						</#list>
						
						
						<div class="row">
						
							<div class="col-lg-3">
							</div>
						<div class="col-lg-8">
						
									<div class="form-group">
										<label for="name" class="col-lg-2 control-label"></label>

										<div class="col-lg-9">
											<span>匿名</span><input type="checkbox" checked="checked" name="hidename"
												id="hidename" placeholder="匿名">
										</div>
									</div>

								</div>
							</div>
						
						</#if>
							
							<div class="modal-footer">



								<button type="button" class="btn btn-primary"
									id="btnSubmit_attr" onclick="commet()">提交评价</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
		</#if>
		
		<div class="row clist">
			<div class="table-responsive" style="">
													<table id="c_list"
														class="table table-bordered table-hover table-striped"></table>
												</div>
		
		</div>


		<script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>
		<script
			src="/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
		<script
			src="/vendor/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
			
		
	
</body>
</html>