<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>人员管理</title>

<!-- <link rel="stylesheet" type="text/css"
	href="/vendor/user/css/user.css">
 -->
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
					人员管理&nbsp;><span>&nbsp;人员列表</span>
				</h1>
			</div>
		</div>


		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">人员列表</span>
					</div>
					<div class="panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row ">
								<div class=" col-sm-9">
									<div class="form-group">
										<label for="name" class="lb_text col-lg-5 control-label">用户名称:</label>

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
	</div>



	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						用户编辑 <span id="message" data-original-title="xxx" style="margin-left: 20px;"></span>
					</h4>

				</div>

				<form id="mform" class="form-horizontal" role="form"
					action="" method="post">
					<input type="hidden" value="" id="id" name="id"> <input
						type="hidden" value="" id="userRoleId" name="userRoleId">
					<div class="modal-body">
						<div class="row">

							<div class="col-lg-12">
								<div class="form-group" hidden>
									<label  class="col-lg-3 control-label">用户名</label>

									<div class="col-lg-9">
										<input type="text" name="name" class="form-control" id="name"
											placeholder="用户名" readonly="readonly">
										<p class="help-block"></p>
									</div>
								</div>

								<div class="form-group">
									<label for="telephone" class="col-lg-3 control-label">手机号</label>

									<div class="col-lg-9">
										<input type="text" name="telephone" class="form-control"
											id="telephone" placeholder="手机号" readonly="readonly"
											maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label for="telephone" class="col-lg-3 control-label">称呼</label>

									<div class="col-lg-9">
										<input type="text" name="nickname" class="form-control"
											id="nickname" placeholder="称呼" 
											maxlength="11" >
										<p class="help-block"></p>
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-lg-3 control-label">密码</label>

									<div class="col-lg-9">
										<input type="text" name="password" class="form-control"
											id="password" placeholder="设置密码">
										<p class="help-block"></p>
									</div>
								</div>

								<div class="form-group">
									<label for="role" class="col-lg-3 control-label">角色</label>

									<div class="col-lg-9">

										<div class=" text-right ">

											<span class="row col-xs-5 pull-right"> <span>全选</span>
												<input id="selectAll" name="app" type="checkbox" attr=""
												value="" class="r_hide">

											</span>

											<div id="treeDemo" style="width: 90%; height: 80%"
												class="ztree"></div>
										</div>



										<p class="help-block"></p>
									</div>
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
				</form>


			</div>
		</div>
	</div>



	<script src="/js/ztree/jquery.ztree.all.min.js"></script>
	<script src="/vendor/user/js/user.js"></script>
</body>
</html>