
	<!-- 模态框（Modal） -->
		<div class="modal fade" data-backdrop="static" id="myModal_attr"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							属性编辑 <span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform_attri" name="mform_attri" class="form-horizontal" role="form"
						action="" method="post">

						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">


									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label">属性ID</label>

										<div class="col-lg-9">
											<input type="hidden" name="id" class="form-control" id="id"
												placeholder="属性ID" >
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">属性名称</label>

										<div class="col-lg-9">
											<input type="text" name="name" class="form-control" id="name"
												placeholder="属性名称">
											<p class="help-block"></p>
										</div>
									</div>


								<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否为规格</label>

										<div class="col-lg-9">
											<select name="isSpecification" class="form-control" id="isSpecification">

												<option value="1">是</option>
												<option value="0"  selected="selected">不是</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否必选</label>

										<div class="col-lg-9">
											<select name="mandatory" class="form-control" id="mandatory">

												<option value="0">非必选</option>
												<option value="1"  selected="selected">必选</option>
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










								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="close">关闭</button>
							<button type="button" class="btn btn-primary" id="btnSubmit_attr">
								提交更改</button>
						</div>
					</form>


				</div>
			</div>
		</div>