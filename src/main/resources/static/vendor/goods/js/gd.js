$(function() {
	PersonnelInformationTable();

	


	//多图片上传-
	$kfile.init({
		fileUploadname:"fileUploadURL", //上传文件的name
		httppath:$("#val2").val(),  //img -static目录前缀
		isimg:true,
		filesufix:'png,jpg,gif,jpeg,',
		maxFileSize:2*1024*1024,//2M
		mform:'#mform',
		notnull:true,
		notnullmsg:'至少需要一张图片',
		maximgupload : 4,//最多可上传图片数量
		uploadurl:'/upload/UploadFileXhr',//上传图片action url
		container:$("body").find('#imgs'), //图片容器
	});


	//多附件
	$kfile.init({
		fileUploadname:"fileUploadURL", //上传文件的name
		httppath:$("#val2").val(),  //img -static目录前缀
		maximgupload : 4,//最多可上传图片数量
		isimg:false,
		maxFileSize:2*1024*1024,//2M
		uploadurl:'/upload/UploadFileXhr',//上传图片action url
		container:$("body").find('#attach_files2'), //附件容器
	});
	
	
	initCKPlugin();
	
	

	$("#btnAdd").click(function() {

		$('#myTab li:eq(0) a').tab('show');
		$("#imgs_count").val(1);
		$("#id").val("");

		$("#s_context").val("");
		$("#detail_error").hide();
		
		//$imgs.initImg();
		$kfile.get("imgs").initFile();
		

		$kfile.get("attach_files2").initFile();
		
		//加载必填属性
		initAttr();
		
		initOption();
		
		$("#fstClassify"). get(0).selectedIndex = 0;
		$("#fstClassify").trigger("change");
		
		
		$("#action").html("新增");
		
		$("#myModal").modal();
	});
	
	

	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal').on('hide.bs.modal', function(e) {


		
				
						$('#mform input').val('');
						$('#fullurl').attr("src","/img/default.jpg");

						$("#mform").data('bootstrapValidator').resetForm(true);
						
						//删除动态添加的图片验证
						$imgs.updateNormalValidate("#mform", $("#mform").find("#fullurl1_fileId"),"",true);
						//重置属性验证
						 $("#mform_inner_attr").bootstrapValidator("destroy");
			

	});
	
	
	$("#btnAddAttrTest").click(function(){

	});
	
	

	
	
	

	$("#btnSubmit").click(function() {
		// 触发全部验证
		
		$("#mform").data("bootstrapValidator").validate();
		
		$("#mform_inner_attr").data("bootstrapValidator").validate();
		
		
		
	
	
		if(! $("#mform").data("bootstrapValidator").isValid())
			{
			$('#myTab li:eq(0) a').tab('show');
			return;
			}
		
		if(!$("#s_context").val())
		{
		$('#myTab li:eq(1) a').tab('show');
		$('#myTab li:eq(1) ').addClass("bv-tab-error");
		
		$("#detail_error").find('.help-block').html("详情不能为空！");
		$("#detail_error").show();
		
		return;
		}else
			{
			$('#myTab li:eq(1) ').removeClass("bv-tab-error");
			$("#detail_error").hide();
			}
		
		
		if(! $("#mform_inner_attr").data("bootstrapValidator").isValid())
			{
			$('#myTab li:eq(2) a').tab('show');
			return;
			}

		var url = "/manager/goods/save.action";

		
			var data = $("#mform").serialize();

			
			//图文详情
			data+="&introduction="+encodeURIComponent($("#s_context").val());
			
			//添加图片
			data+="&imgs="+$kfile.get("imgs").getAllFileIds();
			
			//添加附件
			data+="&attachs="+$kfile.get("attach_files2").getAllFileIds();
			
			
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
					var pid=$(item2).attr("pid");
					//空值选项不做处理
					if($.trim(val)!="")
					optionobjs.push({id:id,val:val,pid:pid});
					
				});
				
			});
			data+="&options="+JSON.stringify(optionobjs);
			

			
			/**/

			$.ajax({
				type : "post",
				url : '/manager/goods/saveOrUpdate',
				data : data,
				async : false,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.result) {
						$('#myModal').modal("hide");
						doquery();
						success("操作成功！");
					} else {
						error("操作失败!"+ response.message);
					}
				}
			});
		
	});

	initValidate();
	
	//initAttrSelect(id);
	//thdClassify
	
	$("#fstClassify").change(function(){
		changeTypelist($("#scdClassify"),2,$("#fstClassify").val(),function(){
			$("#scdClassify"). get(0).selectedIndex = 0;
			  $('#scdClassify').trigger('change');
		});
	});
	
	$("#scdClassify").change(function(){
		changeTypelist($("#thdClassify"),3,$("#scdClassify").val(),function(){
			$("#thdClassify"). get(0).selectedIndex = 0;
			  $('#thdClassify').trigger('change');
		});
	});
});





/**
 * 根据级别，父id获取分类子信息
 * @param successCallback
 * @returns
 */
function changeTypelist(ele,level,pid,successCallback){

	 $(ele).empty();
	var data="level="+level+ "&pid="+pid;
	$.ajax({
		type : "post",
		url : "/manager/classify/listClass",
		data : data,
		async : false,
		dataType : "json",
		success : function(data) {

			var lst= data;
			
			var html="";
			if(lst.length)
			{
			$.each(lst,function(index,item){
				if(index==0)
				html+=	' <option value="'+item.id+'"  selected="selected" >'+item.name+'</option> ';
				else
					html+=	' <option value="'+item.id+'"  >'+item.name+'</option> ';
				
			});
				}
			else
				{
				
				}
			$(ele).html(html);
			
			
			if(typeof(successCallback)=='function')
				{
				successCallback();
				}
			
		}
	});
}





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
	

	
	$("#mform").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],// excluded:[":hidden",":disabled",":not(visible)"] ,//bootstrapValidator的默认配置
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			"name" : {
				validators : {
					notEmpty : {
						message : '商品名称不能为空'
					}
				}
			},
			"code" : {
				validators : {
					notEmpty : {
						message : '商品编号不能为空'
					}
				}
			},
			"fstClassify" : {
				validators : {
					notEmpty : {
						message : '一级分类不能为空'
					}
				}
			},
			"scdClassify" : {
				validators : {
					notEmpty : {
						message : '二级分类不能为空'
					}
				}
			},
			"thdClassify" : {
				validators : {
					notEmpty : {
						message : '三级分类不能为空'
					}
				}
			},
			
			
			
		/*	fullurl_fileId : {
				validators : {
					notEmpty : {
						message : '至少需要一张商品图片'
					}
				}
			},*/
			
			


		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}



function PersonnelInformationTable() {
	// 初始化Table
	$('#table_list').bootstrapTable({
		url : '/manager/goods/list', // 请求后台的URL（*）
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
		sortable : true, // 是否启用排序
		sortName : 'create_time', // 排序字段
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
			
			
			if(params.sort=="brand.brandName")
				params.sort="brand.brand_name" ;
			
			if(params.sort=="createTimeStr")
				params.sort="gd.create_time" ;
				
			
			
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				name : $("#q_name").val(),
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		}
		,{
			field : 'name',
			title : '商品名称',
			align : 'center',
			valign : 'middle',
			sortable : true, // 是否启用排序
		},
		{
			field : 'createTimeStr',
			title : '创建时间',
			align : 'center',
			valign : 'middle',
			sortable : true, // 是否启用排序
		},
		
		{
			field : 'code',
			title : '商品编号',
			align : 'center',
			valign : 'middle'
		}
		,{
			field : 'brand.brandName',
			title : '商品品牌',
			align : 'center',
			valign : 'middle',
			sortable : true, // 是否启用排序
		}
		/*,{
			field : 'price',
			title : '商品价格',
			align : 'center',
			valign : 'middle'
		}*/
		,{
			field : 'firstClass.name',
			title : '一级分类',
			align : 'center',
			valign : 'middle',
			sortable : true, // 是否启用排序
		}
		,{
			field : 'secondClass.name',
			title : '二级分类',
			align : 'center',
			valign : 'middle',
			sortable : true, // 是否启用排序
		}
		,{
			field : 'thirdClass.name',
			title : '三级分类',
			align : 'center',
			valign : 'middle',
			sortable : true, // 是否启用排序
		}
		, {
			field : 'brand.httpRelativePath',
			title : '品牌图标',
			align : 'center',
			valign : 'middle',
			visible : false,
			 formatter: function (value, row, index) {

				 return "<img class='rowimg img-responsive' src='"+$("#val2").val()+value+"' />";
	           }
		}
/*,
		
		{
			field : 'status',
			title : '状态',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

	             if(value=="1")
	          	   return "正常";
	             else if(value=="2")
	          	   return "下线";
	             else if(value=="3")
		          	   return "停用";


	               return str;
	           }
		}*/
		
		
/*	,	{
			field : 'sort',
			title : '排序',
			visible : false,
			align : 'center',
			valign : 'middle'
		}*/
		
		
		,{
			title : '操作',
			field : 'vehicleno',
			align : 'center',
			formatter : modifyAndDeleteButton,
			events : PersonnelInformationEvents
		}

		]
	});
}

function modifyAndDeleteButton(value, row, index) {
	return  '<div class="">'
		+ '<button id = "detail" type = "button" class = "btn btn-success"><i class="glyphicon glyphicon-pencil">上下架管理</i> </button>&nbsp;'
		
		
	
		
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">修改</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ;
}

window.PersonnelInformationEvents = {
	"click #update" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url : '/manager/goods/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
				$("#detail_error").hide();
				
				$("#id").val(response.id);
				$("#commodity_id").val(response.id);
				
				$("#commodity_name").val(response.name);
				
				$("#commodity_code").val(response.code);
				
				$(".newcontainer").remove();
				$("#imgs_count").val(1);
				/*$.each(response.commodity.commondityImages,function(index,item){
					if(index==0)
						{
					
						$("#fullurl").attr("src",$("#val2").val()+item.fileinfo.http_relative_path);
						$("#fullurl_fileId").val(item.fileId);
						}
						
					else
					addImg(item.fileId,$("#val2").val()+item.fileinfo.http_relative_path);
				})*/
				
			/*	$imgs.initImg(function(){
					$.each(response.commondityImages,function(index,item){
						$imgs.addImg(item.fileId,$("#val2").val()+item.fileinfo.http_relative_path);
					});
				})
				*/
				$kfile.get("imgs").initFile(function(){
					$.each(response.commondityImages,function(index,item){
						$kfile.get("imgs").addFile(item.fileId,$("#val2").val()+item.fileinfo.http_relative_path);
					});
				})
			
		
				
				$("#fstClassify").val(response.fstClassify);
				$("#fstClassify").trigger("change");
				setTimeout(() => {
					$("#scdClassify").val(response.scdClassify);
					$("#scdClassify").trigger("change");
					setTimeout(() => {
						$("#thdClassify").val(response.thdClassify);
						
					}, 400);
				}, 400);
				
				
				
				
				$("#s_context").val(response.introduction);
				
			
			$("#commodity_trademarkId").val(response.trademarkId);	
						
			$('#myTab li:eq(0) a').tab('show');
			
			
			
			$("#table_attr_list tbody") .html("");
			
			
			
			//获取必填属性
			
			initAttr(function(){
				//自身属性
				//属性
				$.each(response.commodityAttributes,function(index,item){
						addAttr(item.attributeDefId,item.attri.name ,item.attri.mandatory,  item.value);
				});
			});
			

				cleanOption();
				//自身属性
				//属性
				$.each(response.commodityOptions,function(index,item){
						addOption(item.optionDefId,item.option.name ,false,  item.value,item.id);
				});
			
				
				$kfile.get("attach_files2").initFile(function(){
					$.each(response.commondityAttachs,function(index,item){
						$kfile.get("attach_files2").addFile(item.fileId,$("#val2").val()+item.fileinfo.http_relative_path,item.fileinfo.old_name);
					});
				})
			
			
			
				
				
				$("#action").html("编辑");
				$("#myModal").modal();
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/goods/delete";
		cconfirm(msg,function(){
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
		
	},
	"click #detail" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/goods/delete";
		window. location="/manager/goodsDetail/index?goodsid="+row.id;
		
	}
};

function doquery() {
	var opt = {
		url : "/manager/goods/list",
		silent : true
	};
	$("#table_list").bootstrapTable('refresh', opt);
	
	//success("test");
}

function showerror() {
	
	error("test");
}

function showinfo() {
	
//	info("说明文件~~~~");
	
	//msg("msg~~~");
	
	
	/*cconfirm("确定删除选项吗？",function(){
		
		success("删除成功！");
		
	},function(){
		error("删除失败！");
	});*/
	
	
	if(aconfirm("确认"))
		{

		success("删除成功！");
		}
	else
		{
		info("取消");
		}
}
