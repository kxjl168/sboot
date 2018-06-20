
	<div class="row">
	<div class="form-group hide">
		<label for="name" class="col-lg-2 control-label">商品instanceID</label>

		<div class="col-lg-10">
		 <input type="hidden"
				name="id" class="form-control" id="commodity_id"
				placeholder="商品ID">
			<p class="help-block"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">商品名称</label>

		<div class="col-lg-10">
			<input type="text" name="name" class="form-control"
				id="commodity_name" placeholder="商品名称">
			<p class="help-block"></p>
		</div>
	</div>



	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">商品编号</label>

		<div class="col-lg-10">
			<input type="text" name="code" class="form-control"
				id="commodity_code" placeholder="商品编号">
			<p class="help-block"></p>
		</div>
	</div>









<!-- 	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">是否上架</label>

		<div class="col-lg-10">

			<select name="status" class="form-control" id="status">

				<option value="1" selected="selected">是</option>
				<option value="2">否</option>
			</select>
			<p class="help-block"></p>
		</div>
	</div> -->


<div class="form-group">
		<label for="name" class="col-lg-2 control-label">一级分类</label>

		<div class="col-lg-10">
			<select name="fstClassify" class="form-control"
				id="fstClassify"> <#if typelist1??> <#list
				typelist1 as item>
					<option value="${item.id}">${item.name}</option> 
				</#list> </#if>

			</select>
			<p class="help-block"></p>
		</div>
	</div>
	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">二级分类</label>

		<div class="col-lg-10">
			<select name="scdClassify" class="form-control"
				id="scdClassify"> 

			</select>
			<p class="help-block"></p>
		</div>
	</div>
	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">三级分类</label>

		<div class="col-lg-10">
			<select name="thdClassify" class="form-control"
				id="thdClassify">

			</select>
			<p class="help-block"></p>
		</div>
	</div>


<div class="form-group">

		<input value="1" type="hidden" id="imgs_count"> <label
			for="name" class="col-lg-2 control-label">商品图片</label>

		<div class="col-lg-10 " id="imgs">
			<div class=" col-xs-3  row margin-top-5 gdimgcontainer">
				<input readonly="readonly" class=" form-control gdimg hidevalidate"
					type="text" name="fileId" id="fullurl_fileId" placeholder=" ">


				<img id="fullurl" src="/img/default.jpg"
					class="img-responsive gdimg"></img>

			</div>

			<div id="imgadd" class=" col-xs-3  row margin-top-5 gdimgcontainer">

				<img src="/img/add.png" class="img-responsive "></img>

			</div>

			<p class="help-block"></p>
		</div>
	</div>


	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">商品品牌</label>

		<div class="col-lg-10">
			<select name="trademarkId" class="form-control"
				id="commodity_trademarkId"> <#if brandlist??> <#list
				brandlist as brand> <#if brand?index=0 >
				<option value="${brand.id}" selected="selected">${brand.brandName}</option> <#else>
				<option value="${brand.id}">${brand.brandName}</option> </#if>
				</#list> </#if>

			</select>
			<p class="help-block"></p>
		</div>
	</div>
	
	

	

</div>

</form>