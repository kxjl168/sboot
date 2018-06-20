
<div class="row">
	<div class="col-xs-10 "><span class="stip "><span class="  glyphicon glyphicon-exclamation-sign"></span>&nbsp;未选择选项及空选项值不会保存.</span></div>
	<div class="col-xs-2 ">
		<button type="button" class="btn btn-default " id="btnAddOption">添加选项</button>
	</div>
</div>

<input value="1" type="hidden" id="options_count">


<div class="table-responsive" style="margin: 10px;">
	<table id="table_option_list"
		class="tree table table-bordered table-hover table-striped table-condensed">

		<thead id="classifyTableHeader">
			<tr>
				<th style="text-align: center; width: 250px;">选项</th>
				<th style="text-align: center;">选项内容</th>
				<th style="text-align: center;">操作</th>

			</tr>
		</thead>

<tbody>
		<tr name="option_tr" class="option_tr_new">
			<input type="hidden" name="option_id" class="option_id" value="1" />

			<td style="text-align: center;">测试选项</td>
			<td style="text-align: center;">




				<div class="">

					<div class="row">
						<div class="col-xs-9 "></div>
						<div class="col-xs-3 ">
							<button type="button" class="btn  btn-default"
								id="btnAddAttr">添加选项值</button>
						</div>
					</div>

					<div class="row">
						<table
							class="table_optioninner_list tree table table-bordered table-hover table-striped table-condensed">

							<thead id="classifyTableHeader">
								<tr>
									<th style="text-align: center;">选项值</th>
									<th style="text-align: center;">操作</th>

								</tr>
							</thead>


							<tr>
								<td style="text-align: center;"><input type="text"
									name="option_val_1" class="option_type form-control"
									placeholder="">
									<p class="help-block"></p></td>
								<td style="text-align: center;">
									<button type="button " class=" btn btn-danger btn-warning "
										data-dismiss="modal ">删除</button>
								</td>
							</tr>
						</table>
					</div>
				</div>



			</td>
			<td style="text-align: center;">
				<button type="button "   onclick="deleteoptionrow(this)" class=" btn btn-danger btn-warning "
					data-dismiss="modal ">删除</button>

			</td>

		</tr>
</tbody>


	</table>


</div>

