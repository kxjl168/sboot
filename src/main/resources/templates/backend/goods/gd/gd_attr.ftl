
<div class="row">
	<div class="col-xs-10"></div>
	<div class="col-xs-2 ">
		<button type="button" class="btn btn-default " id="btnAddAttr">添加属性</button>
		<button type="button" class="hide btn btn-default zxys" id="btnAddAttrTest">test</button>
	</div>
</div>

<input value="1" type="hidden" id="attrs_count">


<div class="table-responsive" style="margin: 10px;">
	<table id="table_attr_list"
		class="tree table table-bordered table-hover table-striped table-condensed">

		<thead id="classifyTableHeader">
			<tr>
				<th style="text-align: center; width: 250px;">属性</th>
				<th style="text-align: center;">内容</th>
				<th style="text-align: center;">操作</th>

			</tr>
		</thead>


<tbody>
		<#if attrmustlist??> <#list attrmustlist as item>
		<tr name="attr_tr" class="attr_tr">



		</tr>

		</#list> </#if>


</tbody>


	</table>


</div>

