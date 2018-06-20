
/**
 * 动态添加属性后选择该属性
 * @param response
 * @returns
 */
function attr_actiondone(response){
	// initAttrSelect(curselect2,response.id);
	 if(typeof(response.id)!="undefined")
		 {
		 setselect(curselect2, response.id,response.name);
		 }
		 	 
}

$(function(){
	$("#btnAddAttr").click(function(){
		addAttr();
	});
	
	
	

	
});




/**
 * 填充属性下拉框
 * 填充必选属性，
 * @param donecallback 完成后回调
 * @returns
 */
function initAttr(donecallback){
	$("#table_attr_list tbody") .html("");
	$("#attrs_count").val(1);
	
	
	
	
	$("#mform_inner_attr").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],// excluded:[":hidden",":disabled",":not(visible)"] ,//bootstrapValidator的默认配置
		// submitButtons : 'button[type="submit"]',// 提交按钮
	});
	
	
	//获取必填属性
	$.ajax({
		type : "post",
		url : '/manager/attri/list',
		data : {
			type : 1 //必选
		},
		async : false,
		dataType : "json",
		success : function(response) {
			
			
			//属性
			$.each(response.rows,function(index,item){

					addAttr(item.id,item.name,true ,"");
			});
			
			if(typeof(donecallback)=="function")
				donecallback();

		}
	});
	
}


/**
 * 添加属性表格行
 * @param id
 * @param name
 * @param val
 * @returns
 */
function addAttr(id,name,must,val){
	
	
	val=val||"";
	
	var hide="";
	if(must)
		hide=" hide ";
	
	if(typeof(id)!="undefined")
		{
		//更新时加载使用
		//重复的属性不再添加 
		var eitem=null;
		 var isexist=false;
		 $.each( $(".attr_id"),function(index,item){
			 if($(item).val()==id)
				 {
				 isexist=true;
				 eitem=item;
				 }
				 
		 });
		 if(isexist)
			 {
			 $(eitem).val(id);
			 $(eitem).parent().find(".attr_type").val(val);
			 
			 return;
			 }
			 
		}
	
	var index= parseInt($("#attrs_count").val())+1;
	
	var target=$('  <tr name="attr_tr" class="attr_tr_new "  > '

	+'    <input type="hidden" class="attr_id" name="attr_id"  /> '
											
	+'    <td style="text-align:center;" > '
	
	+'<select value=" " class= "select_attr select2-chosen"  id="select'+index+'"></select>'
	+						'</td> '
	+'    <td style="text-align:center;" class="vcenter"> '
                                        
	+'  	<div class=""> '
								
	+'  <div class="col-lg-9 form-group"> '
	//required data-bv-notempty-message="不能为空"
	+'  <input  type="text"    value="'+val+'"  name="attr_val_'+index+' " class="attr_type form-control" ' 
		+'	placeholder=""> '
	
	+'  <p class="help-block"></p> '
	+'  </div> '
	+'  </div> '
                						
	+'   </td> '
	+'   <td style="text-align:center;" > '
	+'   	<span  class="btn btn-danger '+hide+'btn-warning "  onclick="deleteattrrow(this)"  data-dismiss="modal ">删除 '
	+'  </span> '
			
	+'                          </td> '
                                 
	+'  </tr> ');
	

	
	$("#table_attr_list tbody").append(target);
	
	if(must)
	updateValidate($("#table_attr_list").find("#select"+index).parent().parent().find(".attr_type"),name);
	
	initAttrSelect($("#table_attr_list").find("#select"+index),id,name,must,val);
	$("#attrs_count").val(index);
}


function updateValidate(fieldname,text,isremove)
{

	if(isremove)
		$("#mform_inner_attr").bootstrapValidator("removeField",fieldname);
	else
		{
		$("#mform_inner_attr").bootstrapValidator("addField",fieldname, {  
        validators: {  
            notEmpty: {  
                message: text+'不能为空'  
            },  
            
        }  
    });  
		}
}

/**
 * 删除属性
 */
function deleteattrrow(e)
{
	
	cconfirm("确定删除?",function(){
	
	var val=$(e).parents().find(".attr_type");
	
	updateValidate($(val).name,"",true);
	
	$(e).parent().parent().remove();
	var index= parseInt($("#attrs_count").val())-1;
	$("#attrs_count").val(index);
	});
}



//当前新建
var curselect2=null; 
var attrqueryname="";
//属性选择
/**
 * 初始化添加的select控件
 * @param elemnt
 * @param selectid
 * @param selectname
 * @returns
 */
function initAttrSelect(elemnt,selectid,selectname,must ){
	


	$(elemnt).select2({
		dropdownParent : $("#myModal"),
		placeholder : "选择属性",
		minimumInputLength : 0,
		theme : "bootstrap",

		ajax : {
			type : "post",
			url : "/manager/attri/list",
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
					text : '新建属性'
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
			    // Add custom attributes to the <option> tag for the selected option
			    //$(data.element).attr('data-custom-attribute', data.customValue);
			    $(container).parents("tr").find(".attr_id").val(data.id);
			    return data.text;
		  },
		
	});
	
	

	$(elemnt).on('select2:selecting', function(e) {
		var data = e.params.args.data;
		if (data.id == -1) {

			e.preventDefault();
			curselect2 = elemnt;
			$("#myModal_attr").modal();

		}else
			{
			//判断是否已经有该属性
		
			//重复的属性不再添加 
			 var isexist=false;
			 $.each( $(".attr_id"),function(index,item){
				 if($(item).val()==data.id)
					 {
					 isexist=true;
					 
					 }
					 
			 });
			 if(isexist)
				 {
				 error("已经选择过该属性!");
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