<!-- 模态框（Modal） -->
		<div class="modal fade" data-backdrop="static" id="myModal_option"
			tabindex="-1" role="dialog" aria-labelledby="myModal_optionLabel"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModal_optionLabel">
							选项编辑 <span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform_option" name="mform_option" class="form-horizontal" role="form"
						action="" method="post">

						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">


									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label">选项ID</label>

										<div class="col-lg-9">
											<input type="hidden" name="id" class="form-control" id="id"
												placeholder="选项ID" >
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">选项名称</label>

										<div class="col-lg-9">
											<input type="text" name="name" class="form-control" id="name"
												placeholder="选项名称">
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
							<button type="button" class="btn btn-primary" id="btnSubmit_option">
								提交更改</button>
						</div>
					</form>


				</div>
			</div>
		</div>