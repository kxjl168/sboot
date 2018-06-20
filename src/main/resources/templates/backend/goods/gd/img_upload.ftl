<div class="modal fade " id="myModal_file" tabindex="-1 "
			data-backdrop="static" role="dialog " aria-labelledby="myModalLabel ">
			<div class="modal-dialog " >
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




					<div class="row modal-body  margin-top-10 ">

						<form method="post" id="fileform" name="fileform"
							target="fileUploadFrame" enctype="multipart/form-data">


							<div class="row col-xs-12">
								<div class="control-label padding-top-0  ">图标：</div>
							</div>
							<div class="row col-xs-11">

								<div class="col-xs-12 text-right ">
							

									<div class="dropBox" id="dropBox">
										<div class="dropBoxInner">
											<div class="imgtip">将图片拖放到此处...</div>
											<img id="imgpre" class="imgpre img-responsive">


											<div class="prodiv">
												<hr class="hrback">
												<hr class="hrpro">
											</div>
										</div>

									</div>





								</div>
								<div class="row">
									<div class=" col-xs-5  ">
								
								<div tabindex="500"  class="btn btn-primary btn-file">
								<i class="glyphicon glyphicon-folder-open"></i>&nbsp;  
								<span >选择 …</span>
								<input type="hidden" id="fname" />
								
								
								<input type="file"  onchange="processFiles(this.files)"	name="fileUploadURL" id="fileUploadURL" class="form-control"  data-preview-file-type="text">
								</div>
								</div>
								
								<button type="button" id="btnremoveimg" tabindex="500" title="清除选中文件" class="hide btn btn-default btn-secondary fileinput-remove fileinput-remove-button">
								<i class="glyphicon glyphicon-trash"></i>  <span class="hidden-xs">移除</span></button>
								
								<div class="col-xs-2  ">
									<button type="button " class="btn btn-primary" name="uploadSub"
										id="uploadSub">上传</button>
								</div>
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