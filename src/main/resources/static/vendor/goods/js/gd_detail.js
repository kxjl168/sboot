$(function() {
	PersonnelInformationTable();

	//initCKPlugin();

	
	$("#btnAdd").click(function() {

		$('#myTab li:eq(0) a').tab('show');
		$("#imgs_count").val(1);
		$("#id").val("");

		$("#s_context").val("");
		
		initImg();
		
		//加载必填属性
		initAttr();
		
		initOption();
		
		
		
		$("#myModal_detail").modal();
	});

	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal').on('hide.bs.modal', function(e) {


		
				
						$('#mform_detail input').val('');
					//	$('#fullurl').attr("src","/img/default.jpg");

						$("#mform_detail").data('bootstrapValidator').resetForm(true);
						
						
						// $("#mform_inner_attr").bootstrapValidator("destroy");
			

	});
	
	
	$("#btnAddAttrTest").click(function(){

	});
	
	

	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_file').on('show.bs.modal', function(e) {
		$("#mform_detail").data('bootstrapValidator').resetForm();

	});
	

	$("#btnSubmit_detail").click(function() {
		// 触发全部验证
		
		$("#mform_detail").data("bootstrapValidator").validate();
		
		//$("#mform_inner_attr").data("bootstrapValidator").validate();
		

	
		// flag = true/false
		var flag = $("#mform_detail").data("bootstrapValidator").isValid();
		//&& $("#mform_inner_attr").data("bootstrapValidator").isValid();
		
		/*if(! $("#mform_detail").data("bootstrapValidator").isValid())
			{
			$('#myTab li:eq(0) a').tab('show');
			return;
			}
		if(! $("#mform_inner_attr").data("bootstrapValidator").isValid())
			$('#myTab li:eq(1) a').tab('show');*/

		var url = "/manager/goodsDetail/save.action";

		if (flag) {
			var data = $("#mform_detail").serialize();

			/*//添加图片
			var imgobjs=new Array();
			var imgs= $(document.body).find(".gdimgid");
			$.each(imgs,function(index,item){
				imgobjs.push({id:$(item).val()});
			})
			
			data+="&commodity.introduction="+escape($("#s_context").val());
			
			data+="&imgs="+JSON.stringify(imgobjs);
			
			
			$('#mySelect2').find(':selected').data('custom-attribute');
			
			//添加附件
			
			
			
			//添加属性
			var attrobjs=new Array();
			var attr_trs= $(document.body).find(".attr_tr_new");
			$.each(attr_trs,function(index,item){
				
				var id=$(item).find(".attr_id").val();
				var val=$(item).find(".attr_type").val();
				attrobjs.push({id:id,val:val});
			});
			data+="&attrs="+JSON.stringify(attrobjs);
			
			
			
			
			//添加选项
			var optionobjs=new Array();
			var option_trs= $(document.body).find(".option_tr_new");
			$.each(option_trs,function(index,item){
				
				var id=$(item).find(".option_id").val();
				$.each( $(item).find(".option_type"),function(index2,item2){
					var val=$(item2).val();
					if($.trim(val)!="")
					optionobjs.push({id:id,val:val});
					
				});
				
			});
			data+="&options="+JSON.stringify(optionobjs);
			
			*/
			
			
			
			/**/

			$.ajax({
				type : "post",
				url : '/manager/goodsDetail/saveOrUpdate',
				data : data,
				async : false,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						$('#myModal_detail').modal("hide");
						
						success("操作成功！");
						doquery();
						
					} else {
						error("操作失败!"+ response.message);
					}
				}
			});
		}
	});

	initValidate();
	
	//initAttrSelect(id);

});







/**
 * 设置属性选中项
 * @param id
 * @param name
 * @returns
 */
function setselect(ele,id,name){
	// create the option and append to Select2
    var option = new Option(name, id, true, true);
    $(ele).append(option).trigger('change');

    // manually trigger the `select2:select` event
    $(ele).trigger({
        type: 'select2:select',
        params: {
            data: {text:name,id:id}
        }
    });
    
  

 };



function formatRepo (repo) {
  if (repo.loading) {
    return repo.text;
  }

  var markup = repo.text;

  if (repo.id==-1) {
    markup = '<button type="button "  style=" width: 100%;" class=" btn btn-danger btn-warning " data-dismiss="modal ">'+  repo.text  +'</button>';
  }

  return markup;
}


function initCKPlugin()
{
	/*var pluginname="savedata";
	var cmd_name="cmd_savedata";
	CKEDITOR.plugins.add( pluginname, {
	 
	    init: function( editor ) {
	        editor.addCommand( cmd_name, {
	            exec: function( editor ) {
	            	
	          	var $scope = angular.element(ngSection).scope();
	          	
	            	$scope.$apply(function() {
	            	
	            	$scope.save(null,null,function(){
						
						$scope.getList();
						msg("保存成功！");
					});
	            	});	
	            }
	        });
	        editor.ui.addButton( 'btn_savedata', {
	            label: '保存数据',
	            command:cmd_name,
	            toolbar: 'insert',
	            icon: basePath+'/images/save.jpg',
	        });
	    }
	});*/

	//CKEDITOR.morePluginnames=pluginname;
	$("#s_context").ckeditor();
}



function initValidate() {
	$("#mform_detail").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],// excluded:[":hidden",":disabled",":not(visible)"] ,//bootstrapValidator的默认配置
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			"price" : {
				validators : {
					notEmpty : {
						message : '价格不能为空'
					},
					numeric:{
						message:'价格必须为数字'
					}
				}
			},
			
			
			


		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}



function PersonnelInformationTable() {
	// 初始化Table
	$('#table_list').bootstrapTable({
		url : '/manager/goodsDetail/list', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toolbar', // 工具按钮用哪个容器
		showHeader : true,
		searchAlign : 'left',
		buttonsAlign : 'left',

		searchOnEnterKey : true,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortName : 'id', // 排序字段
		sortOrder : "desc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

		// showColumns: true, //是否显示所有的列
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		// queryParamsType : "limit",
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sort : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				name : $("#q_name").val(),
				id:GetQueryString("goodsid")
			};
			return param;
		},
		 rowStyle: function (row, index) {
             var style = {};           // ['active', 'success', 'info', 'warning', 'danger'];  
               //  style={css:{'color':'#ed5565'}};
             if(row.status=="2")
             style.classes="warning";
             else
            	 style.classes="success"; 
             return style;
         }  ,
		columns : [ {
			field : 'id',
			visible : false
		}
		,{
			field : 'commodity.name',
			title : '商品名称',
			align : 'center',
			valign : 'middle'
		}

		
		,{
			field : 'option',
			title : '商品选项',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

				 
				 var html="";
				 
				 html+=' <div class=" col-xs-12" style="margin: 5px;"> '
		
				 
				 $.each(row.commondityInstanceOptions,function(i,item){
					 html+=' <span>	 '
							+' 		<span class="" style="text-align: center;">'+ item.commodity_option.option.name+':</span> '
							+' 		<span class="" style="text-align: center;">'+item.commodity_option.value+'  '
							+"</span>&nbsp;/";
					
				 })

							html+='  </div> ';

							
				 
				 return html;
	           }
		}
		
	
		,{
			field : 'price',
			title : '商品价格',
			align : 'center',
			valign : 'middle'
		}

		,{
			field : 'status',
			title : '状态',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

	             if(value=="1")
	          	   return "正常";
	             else if(value=="2")
	          	   return "下架";
	             else if(value=="3")
		          	   return "停用";


	               return value;
	           }
		}
	
	,	
		{
			title : '操作',
			field : 'tableaction',
			align : 'center',
			events : PersonnelInformationEvents,
			formatter : modifyAndDeleteButton,
			
		}

		]
	});
}





function modifyAndDeleteButton(value, row, index) {
	var html= '<div >';
		if(row.status=="2")
			html+= '<button id = "up2"  type = "button" class = "up btn btn-success"><i class="glyphicon glyphicon-circle-arrow-up">上架</i> </button>&nbsp;';
		
		if(row.status=="1")
			html+=  '<button id = "down" type = "button" class = "down btn btn-warnning"><i class="glyphicon glyphicon-circle-arrow-down">下架</i> </button>&nbsp;';
				
		html+='<button id = "delete" type = "button" class = "delete btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>';
		
		return [html].join("");
}


window.PersonnelInformationEvents = {
	"click #up2" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url : '/manager/goodsDetail/load',
			data : {
				id : row.id
			},
			async : true,
			dataType : "json",
			success : function(response) {
				$("#id").val(response.id);
			
				$("#price").val(response.price);
		
				
				
				$("#myModal_detail").modal();
			}
		});

	},
	
	"click .down" : function(e, value, row, index) {
		var msg = "您确定要下架该商品吗？";
		var url = "/manager/goodsDetail/down";
		if (true === confirm(msg)) {
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data : {
					"id" : row.id
				},
				success : function(response) {
					if (response.result) {
						success("下架成功！");
						doquery();
					} else {
						error("下架失败！"+response.message);
					}
				}
			});
		}
	
	},
	"click .delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/goodsDetail/delete";
		cconfirm(msg,function() {
			$.ajax({
				type : "post",
				url : url,
				data : {
					"id" : row.id
				},
				success : function(response) {
					if (response.bol) {
						success("删除成功！");
						doquery();
					} else {
						error("删除失败！"+response.message);
					}
				}
			});
		});
		
	}
};


function doquery() {
	var opt = {
		url : "/manager/goodsDetail/list",
		silent : true
	};
	$("#table_list").bootstrapTable('refresh', opt);
	
	//success("test");
}
