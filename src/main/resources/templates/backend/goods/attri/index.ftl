<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商品属性管理</title>

<link rel="stylesheet" href="/js/ztree/zTreeStyle.css">

</head>

<body>





	<div>
		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					商品管理&nbsp;><span>&nbsp;属性列表</span>
				</h1>
			</div>
		</div>


		<div class="row">




			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">属性列表</span>
					</div>
					<div class="panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row ">
								<div class=" col-sm-9">
								
									<div class="col-sm-3">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="endTimeLabel">属性名称:</span>
                                        
                                             <input id="q_name" type="text" name="q_name"
												class="form-control qinput" placeholder=""
												aria-controls="dataTables-example">
                                    </div>
                                    </div>
                                </div>
									
									
									
									
									<button type="button" class="btn btn-default zxys" id="btnQry"
										onclick="doquery()">查询</button>

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

	
	  <#include "goodattri.ftl"/>





		<script src="/js/ztree/jquery.ztree.all.min.js"></script>
		<script src="/vendor/goods/js/attri.js"></script>
		<script src="/vendor/goods/js/attri_add.js"></script>
</body>
</html>