
/**
 * 动态添加选项后选择该选项
 * 
 * @param response
 * @returns
 */
function option_actiondone(response){
	// initOptionSelect(curselect2_option,response.id);
	 if(typeof(response.id)!="undefined")
		 {
		 setselect(curselect2_option, response.id,response.name);
		 }
		 	 
}

$(function(){
	$("#btnAddOption").click(function(){
		addOption();
	});

	
});


function cleanOption(){
	$("#table_option_list tbody") .html("");
	$("#options_count").val(1);
}

/**
 * 填充选项下拉框 填充必选选项，
 * 
 * @param donecallback
 *            完成后回调
 * @returns
 */
function initOption(donecallback){
	cleanOption();
	// 获取必填选项
	$.ajax({
		type : "post",
		url : '/manager/option/list',
		data : {
			pageSize : 3 // 3项
		},
		async : false,
		dataType : "json",
		success : function(response) {
			
			
			// 选项
			$.each(response.rows,function(index,item){

					addOption(item.id,item.name,false ,"");
			});
			
			if(typeof(donecallback)=="function")
				donecallback();

		}
	});
	
}


/**
 * 上移
 * 
 * @returns
 */
function upoption(e){
	var pre=$(e).parent().parent().prev(); 
	if(pre)
		{
		$(e).parent().parent().insertBefore(pre);
		}
}

/**
 * 下移
 * 
 * @returns
 */
function downoption(e){
	var next=$(e).parent().parent().next(); 
	if(next)
		{
		$(e).parent().parent().insertAfter(next);
		}
}


/**
 * 添加选项值
 * 
 * @param e
 * @param ele
 * @returns
 */
function addOptionVal(e,ele)
{
	var val="";
	var innerrow=' 		<tr  > '
		+' 			<td style="text-align: center;"><input type="text" '
		+' 				 value="'+val+'" class="option_type form-control" '
		+' 				placeholder=""> '
		+' 				<p class="help-block"></p></td> '
		+' 			<td style="text-align: center;"> '
		+' 				<span type="button "  onclick="deleteoptionrow(this)" class=" btn btn-danger btn-warning " '
		+' 					>删除</span> '
		+' 			</td> '
		+' 		</tr> ';
	
	$(e).parent().parent().parent().find(".table_optioninner_list tbody"). append(innerrow);
}


/**
 * 添加选项表格行
 * 
 * @param id
 * @param name
 * @param val
 * @returns
 */
function addOption(id,name,must,val,pid){
	
	
	val=val||"";
	pid=pid||"";
	
	var hide="";
	if(must)
		hide=" hide ";
	

	
	var innerrow=' 		<tr  > '
	+' 			<td style="text-align: center;"><input type="text"  pid="'+pid+'"  ' 
	+' 				 value="'+val+'" class="option_type form-control" '
	+' 				placeholder=""> '
	+' 				<p class="help-block"></p></td> '
	+' 			<td style="text-align: center;"> '
	+' 				<span type="button "  onclick="deleteoptionrow(this)" class=" btn btn-danger btn-warning " '
	+' 					>删除</span> '
	+' 			</td> '
	+' 		</tr> ';
	
	if(typeof(id)!="undefined")
		{
		// 更新时加载使用
		// 重复的 只添加值.
		var eitem=null;
		 var isexist=false;
		 $.each( $(".option_id"),function(index,item){
			 if($(item).val()==id)
				 {
				 isexist=true;
				 eitem=item;
				 }
				 
		 });
		 if(isexist)
			 {
			 $(eitem).val(id);
			 var innertable=$(eitem).parent().find(".table_optioninner_list tbody");
			 
			 
			 innertable.append(innerrow);
			 
			 
			 return;
			 }
			 
		}
	
	
	var index= parseInt($("#options_count").val())+1;
	
	
	var target=$('	<tr name="option_tr" class="option_tr_new"> '
			+' <input type="hidden" name="option_id" class="option_id"  /> '
			+' <td style="text-align: center;"  class="vcenter">'
			+'<select value=" " class= "select_option select2-chosen"  id="selectoption'+index+'"></select>'
			+'</td> '
			+' <td style="text-align: center;"> '

			+' <div class=""> '

			+' <div class="row"> '
			+' 	<div class="col-xs-9 "></div> '
			+' 	<div class="col-xs-2 "> '
			+' 		<button type="button" onclick="addOptionVal(this)" class="btn btn-default " '
			+' 			id="btnAddAttr">添加选项值</button> '
			+' 	</div> '
			+' </div> '

			+' <div class="row"> '
			+' 	<table '
			+' 		class="table_optioninner_list tree table table-bordered table-hover table-striped table-condensed"> '

			+' 		<thead id="classifyTableHeader"> '
			+' 			<tr> '
			+' 				<th style="text-align: center;">选项值</th> '
			+' 				<th style="text-align: center;">操作</th> '

			+' 			</tr> '
			+' 		</thead> '

			+innerrow
			
			
			
			 // value="'+val+'"
			
			
			
			+' 	</table> '
			+' </div> '
			+' </div> '



			+'</td>'
			+'<td style="text-align: center;" class="vcenter">'
			+'	<span type="button "  onclick="deleteoptionrow(this)" class=" btn btn-danger btn-warning "'
			+'		>删除</span>'
			
			+'	<span type="button "  onclick="upoption(this)" class=" btn btn-info "'
			+'		>上移</span>'
			+'	<span type="button "  onclick="downoption(this)" class=" btn btn-info "'
			+'		>下移</span>'
			

			+'</td>'

			+'</tr>');
	
	
	
	

	
	$("#table_option_list").append(target);
	
	initOptionSelect($("#table_option_list").find("#selectoption"+index),id,name,must,val);
	$("#options_count").val(index);
}

/**
 * 删除选项
 */
function deleteoptionrow(e)
{
	cconfirm("确定删除?",function(){
	$(e).parent().parent().remove();
	var index= parseInt($("#options_count").val())-1;
	$("#options_count").val(index);
	
	
	
	
	$.each( $(e).parent().parent().find(".option_type"),function(index,item){
	
		
		
		if($(item).attr("pid")!="")
		{
		$.ajax({
			type : "post",
			url : '/manager/goods/deleteoption',
			data : {
				id:$(item).attr("pid")
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
			
			}
		});
		}
	});
	});
	

}



// 当前新建
var curselect2_option=null; 
var optionqueryname="";
// 选项选择
/**
 * 初始化添加的select控件
 * 
 * @param elemnt
 * @param selectid
 * @param selectname
 * @returns
 */
function initOptionSelect(elemnt,selectid,selectname,must ){
	


	$(elemnt).select2({
		dropdownParent : $("#myModal"),
		placeholder : "选择选项",
		minimumInputLength : 0,
		theme : "bootstrap",

		ajax : {
			type : "post",
			url : "/manager/option/list",
			dataType : "json",
			data : function(params) {

				var query = {
					name : params.term,
					type : 'public',
					offset : params.page * 10 || 0
				}

				// Query parameters will be ?search=[term]&type=public
				return query;
			},

			processResults : function(data) {
				var selectdatas = [];
				selectdatas.push({
					id : -1,
					text : '新建选项'
				});

				$.each(data.rows, function(index, item) {
					selectdatas.push({
						id : item.id,
						text : item.name
					});
				});
				
				

				return {
					results : selectdatas,
					pagination : {
						more : false
					}
				};
			},
		},

		templateResult : formatRepo,
		escapeMarkup : function(markup) {
			return markup;
		}, // let our custom formatter work
		templateSelection: function (data, container) {
			    // Add custom optionibutes to the <option> tag for the selected
				// option
			    // $(data.element).option('data-custom-optionibute',
				// data.customValue);
			    $(container).parents("tr").find(".option_id").val(data.id);
			    return data.text;
		  },
		
	});
	
	

	$(elemnt).on('select2:selecting', function(e) {
		var data = e.params.args.data;
		if (data.id == -1) {

			e.preventDefault();
			curselect2_option = elemnt;
			$("#myModal_option").modal();

		}else
			{
			// 判断是否已经有该选项
		
			// 重复的选项不再添加
			 var isexist=false;
			 $.each( $(".option_id"),function(index,item){
				 if($(item).val()==data.id)
					 {
					 isexist=true;
					 
					 }
					 
			 });
			 if(isexist)
				 {
				 error("已经选择过该选项!");
					e.preventDefault();
					$(elemnt).val(null).trigger('change');
				 }
				 
			
		
			
			}

	});
	
	
	 if(typeof(selectname)!="undefined" &&typeof(selectid)!="undefined" )
		 {
		 
		// $(elemnt).disable();
		 
		 setselect(elemnt, selectid,selectname);
		
		 if(must)
		 $(elemnt).select2({ disabled : false });
		 }

}