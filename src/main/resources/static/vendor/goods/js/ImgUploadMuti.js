/**
 * 多图片-文件上传组件
 * 
 * 依赖jquery,
 * jquery bootstrap,
 * 
 * 
 * 初始化
 * //多图片上传
	$imgs.init({
		fileUploadname:"fileUploadURL", //上传文件的name
		httppath:$("val2").val(),  //img -static目录前缀
		maximgupload : 4,//最多可上传图片数量
		uploadurl:'/upload/UploadFileXhr',//上传图片action url
		imgcontainer:$("body").find('#imgs'), //图片容器
	});


 //添加默认占位图片
	$imgs.initImg(callback);
	
	//赋值图片
	$imgs.addImg(item.fileId,$("#val2").val()+item.fileinfo.http_relative_path);
 * 
 *  //获取图片fileids
 *  $imgs.getAllImg()
 * 
 * 
 * @param window
 * @returns
 * @date 20180611
 * @ahuor zj
 */

;(function(window) {

	function FileUploadMuti(options){
	
		this.default={
				cur_file_id:1,
				num:0,
				maximgupload : 4,// 最多可上传图片数量
			
				
				uploadurl:'',// 上传图片url
				imgcontainer:$("body").find('#imgs'),
				mform:"#mform",
				fileUploadname:"fileUploadURL",
				httppath:'',
		// 
		};
		
		
	} ;
	
	/**
	 * 获取图片fileids
	 */
	FileUploadMuti.prototype.getAllImg=function(options){
		//添加图片
		var imgobjs=new Array();
		var imgs= $(document.body).find(".gdimgid");
		$.each(imgs,function(index,item){
			imgobjs.push({id:$(item).val()});
		})
		return JSON.stringify(imgobjs);
	}


	FileUploadMuti.prototype.init=function(options){
		
		

	    this.options = $.extend({}, this.default, options);
	    
	    this.imgcontainer=$("body").find(this.options.imgcontainer);
		
		var  me = this;
	     
	    	 
	    	 me.initDom();
	    	 me.initEvent();
	    	
	    
		
	};
	

	FileUploadMuti.prototype.initEvent=function(e){
			
			var me = this;
		   

		
			  $("body").find(".imgmodal #uploadSub").off("click").on("click", function(e) {
				 me.uploadfile();
				});

				/*
				 * $("#closeDiv").bind("click", function(e) {
				 * topWin[0].divdlg.close(); });
				 */

		

				$("body").find("#btnremoveimg").click(function() {
					// me.removedropimg();
				});

				window.onload = function() {
					me.dropBox = document.getElementById("dropBox");
					me.dropBox.ondragenter =me. ignoreDrag;
					me.dropBox.ondragover = me.ignoreDrag;
					me.dropBox.ondrop = me.drop;
				}
			
		};
	
	FileUploadMuti.prototype.initDom=function(e){
		
		var me = this;
	   
		
       var dom =$('<div class="imgmodal">'
    		   +' <div class="modal fade " id="myModal_file" tabindex="-1 " '
    		   +' data-backdrop="static" role="dialog " aria-labelledby="myModalLabel "> '
    		   +' <div class="modal-dialog " > '
    		   +' 	<div class="modal-content "> '
    		   +' 		<div class="modal-header "> '
    		   +' 		<button type="button " class="close hide " data-dismiss="modal " '
    		   +' 			aria-hidden="true ">&times;</button> '
    		   +' 		<h4 class="modal-title " id="myModalLabel ">选择文件</h4> '


    		   +' 	</div> '



    		   +' 	<input id="val2" value="'+me.options.httppath+'" '
    		   +' 		name="val2" type="hidden" /> '


				

    		   +' <div class="row modal-body  margin-top-10 "> '

    	


    		   +' 		<div class="row col-xs-12"> '
    		   +' 			<div class="control-label padding-top-0  ">图标：</div> '
    		   +' 		</div> '
    		   +' 			<div class="row col-xs-11"> '

    		   +' 				<div class="col-xs-12 text-right "> '
							

    		   +' 					<div class="dropBox" id="dropBox"> '
    		   +' 						<div class="dropBoxInner"> '
    		   +' 							<div class="imgtip">将图片拖放到此处...</div> '
    		   +' 							<img id="imgpre" class="imgpre img-responsive"> '


    		   +' 							<div class="prodiv"> '
    		   +' 								<hr class="hrback"> '
    		   +' 									<hr class="hrpro"> '
    		   +' 							</div> '
    		   +' 						</div> '

    		   +' 					</div> '





    		   +' 				</div> '
    		   +' 				<div class="row"> '
    		   +' 					<div class=" col-xs-5  "> '
								
    		   +' 				<div tabindex="500"  class="btn btn-primary btn-file"> '
    		   +' 				<i class="glyphicon glyphicon-folder-open"></i>&nbsp;   '
    		   +' 				<span >选择 …</span> '
    		   +' 				<input type="hidden" id="fname" /> '
								
								
    		   +' 				<input type="file"  	name="fileUploadURL" id="fileUploadURL" class="form-control"  data-preview-file-type="text"> '
    		   +' 				</div> '
    		   +' 				</div> '
								
    		   +' 				<button type="button" id="btnremoveimg" tabindex="500" title="清除选中文件" class="hide btn btn-default btn-secondary fileinput-remove fileinput-remove-button"> '
    		   +' 				<i class="glyphicon glyphicon-trash"></i>  <span class="hidden-xs">移除</span></button> '
								
    		   +' 				<div class="col-xs-2  "> '
    		   +' 					<button type="button " class="btn btn-primary" name="uploadSub" '
    		   +' 						id="uploadSub">上传</button> '
    		   +' 				</div> '
    		   +' 				</div> '

    		   +' 			</div> '


    		   +' 	</div> '

    		   +' 	<div class="modal-footer "> '
    		   +' 			<button type="button " class="btn btn-default btn-warning " '
    		   +' 			data-dismiss="modal">取消</button> '

    		   +' 	</div> '
    		   +' </div> '
    		   +' </div> '
    		   +' </div> '
    		   +' </div> ');
		
       var spig=$("#myModal_file");
       if(spig.length<=0)
       	{
    
       	$("body").append(dom);
       	}
       
       
    	   $("body").find(".imgmodal #fileUploadURL").off("change").on("change",function(e) {
   			me.processFiles(e.target.files);
   		});
		
	};
	
	
	/**
	 * 显示上传窗口
	 */
	FileUploadMuti.prototype.uploadimg=function(e){
		
		var me = this;
		
		$("body").find("#myModal_file").modal('show');
		me.options.cur_file_id = $(e.target || e).attr("id");

		me.removedropimg();
	};
			

	/**
	 * 清除弹出选择文件框内容
	 */
	FileUploadMuti.prototype.removedropimg=function(){
		var me =this;
		$("body").find("#imgpre").attr("src", "");
		me.hidepro();
		$("body").find(".imgtip").show();
	};
	
	/**
	 * 动态添加的图片框上的删除按钮 事件删除多图片框
	 */
	FileUploadMuti.prototype.removeimg=function(e){
		var me=this;
		
		var id=$(e.target).parent().find(".gdimgid").attr("index");
		me.imgcontainer.find("#fullurl" + id).parent().remove();

		var index = me.options.num - 1;
		
		 me.options.num=index;

		if (index <me.options. maximgupload)
			me.imgcontainer.find("#imgadd").show();
	}

			
	/**
	 * 初始化/新增或者编辑时使用
	 */
	FileUploadMuti.prototype.initImg=function(callback,maxnum){
		var me = this;
		
		me.imgcontainer.html("");
		me.options.num=0;

		

		var hide = " ";

		me.options.maximgupload =me.options.maximgupload|| maxnum ;

		var add = $(' <div id="imgadd" class=" '
				+ hide
				+ 'col-xs-3  row margin-top-5 gdimgcontainer"> '

				+ ' <img src="/img/add.png"  class="img-responsive "></img> '

				+ ' </div> ');

		me.imgcontainer.append(add);
		
		me.imgcontainer.find("#imgadd").off("click").on("click",function(){me.addImg();});
		
	


		me.addImg();

		me.updateNormalValidate(me.options.mform, $(me.options.mform).find(
				"#fullurl1_fileId"), "至少需要一张图片");

		if (typeof (callback) == "function")
			callback();
	}

			
			
				

				/**
				 * 动态添加图片验证
				 * 
				 * @param ele
				 * @param fieldname
				 * @param text
				 * @param isremove
				 * @returns
				 */
	FileUploadMuti.prototype.updateNormalValidate=function(ele, fieldnameOrEle, messagetext, isremove){
		var me = this;
		
		if (isremove)
			$(ele).bootstrapValidator("removeField", fieldnameOrEle);
		else {
			$(ele).bootstrapValidator("addField", fieldnameOrEle, {
				validators : {
					notEmpty : {
						message : messagetext
					},

				}
			});
		}
	};
	
	
	/**
	 * 添加图片/id为空则添加空占位图片 id不为空则优先填充占用图片 然后新增图片
	 */
	FileUploadMuti.prototype.addImg=function(id, url){
		var me = this;
		var index =me.options.num+ 1;

		var hide = " hide ";
		if (index > 1)
			hide = "";

		id = id || "";
		url = url || "/img/default.jpg";

		if (id != "") {
			var eitem = null;
			var isexistnullimg = false;
			$.each(me.imgcontainer.find(".gdimg"), function(index, item) {
				if ($(item).attr("src") == "/img/default.jpg") {
					isexistnullimg = true;
					eitem = item;
				}

			});
			if (isexistnullimg) {
				$(eitem).parents().find(".gdimgid").val(id);
				$(eitem).attr("src", url);

				return;
			}
		}

		var target = $(' <div class=" col-xs-3  row margin-top-5 gdimgcontainer newcontainer"> '
				+ '  <input required  readonly="readonly" '
				+ ' class=" form-control gdimgid  hidevalidate" '
				+ ' type="text" name="fileId" index="'+index+'"  id="fullurl'
				+ index
				+ '_fileId"  value="'
				+ id
				+ '"  placeholder=" "> '
				+ ' 	 <span class=" '
				+ hide
				+ 'close imgclose " '
				
				+ ' >&times;</span> '

				+ ' <img id="fullurl'
				+ index
				+ '"  src="'
				+ url
				+ '" class="img-responsive gdimg"></img> '
				+ ' </div> ');

		me.options.num=index;

		target.insertBefore(me.imgcontainer.find("#imgadd"));

		if (index == me.options.maximgupload)
			me.imgcontainer.find("#imgadd").hide();
		
		
		me.imgcontainer.find(".gdimg").off("click").on("click",function(e) {
			me.uploadimg(e);
		});
		
		me.imgcontainer.find(".imgclose").off("click").on("click",function(e) {
			me.removeimg(e);
		});
		
		
		
	}
			
			

			

	FileUploadMuti.prototype.ignoreDrag=function(e){
		var me = this;
		// 因为我们在处理拖放，所以应该确保没有其他元素会取得这个事件
		e.stopPropagation();
		e.preventDefault();
		
	}
	
	/**
	 * 拖拽drop
	 */
	FileUploadMuti.prototype.drop=function(e){
	
		// 取消事件传播及默认行为
		e.stopPropagation();
		e.preventDefault();

		// 取得拖进来的文件
		var data = e.dataTransfer;
		var files = data.files;

		// 将其传给真正的处理文件的函数
		$imgs.processFiles(files);
	}
		
	/**
	 * 文件预览
	 */
	FileUploadMuti.prototype.processFiles=function(files){
			var me = this;
			$("body").find("#fname").val(files[0].name);
			me.fileObj = files[0];
			$("body").find(".imgtip").hide();

			// var output = document.getElementById("fileOutput");
			// 创建FileReader
			var reader = new FileReader();
			// 告诉它在准备好数据之后做什么
			reader.onload = function(e) {
				// 使用图像URL来绘制dropBox的背景
				$("body").find(".imgpre").attr("src", "" + e.target.result + "");
				
				//$("body").find(".imgpre").src = me.options.httppath+ "/img/default.jpg";// + e.target.result + "";
			};
			// 读取图片
			reader.readAsDataURL(me.fileObj);
	}
	
	
			
	/**
	 * xhr上传文件
	 */
	FileUploadMuti.prototype.uploadfile=function(e){
		var me = this;

		var fileName = $("body").find("#fname").val();
		if (fileName != null && "" != fileName) {
			var fileExtension = fileName.substring(fileName
					.lastIndexOf(".") + 1, fileName.length);
			if ("png" == fileExtension || "jpeg" == fileExtension
					|| "ico" == fileExtension
					|| "gif" == fileExtension
					|| "jpg" == fileExtension) {

				var url = me.options.uploadurl||"/upload/UploadFileXhr"; // 接收上传文件的后台地址

				var form = new FormData(); // FormData 对象
				form.append(me.options.fileUploadname, me.fileObj); // 文件对象

				me.xhr = new XMLHttpRequest(); // XMLHttpRequest 对象
				me.xhr.open("post", url, true); // post方式，url为服务器请求地址，true
				// 该参数规定请求是否异步处理。
				me.xhr.onload = me.uploadComplete; // 请求完成
				me.xhr.onerror = me.uploadFailed; // 请求失败

				// xhr.responseType ="document"; "text" String字符串
				// "document" Document对象 希望返回 XML 格式数据时使用
				// "json" javascript 对象 存在兼容性问题，IE10/IE11不支持
				// "blob" Blob对象
				// xhr.responseType =

				me.xhr.upload.onprogress = me.progressFunction;// 【上传进度调用方法实现】
				me.xhr.upload.onloadstart = function() {// 上传开始执行方法
					ot = new Date().getTime(); // 设置上传开始时间
					oloaded = 0;// 设置上传开始时，以上传的文件大小为0
				};

				me.xhr.send(form); // 开始上传，发送form数据

				// ajaxLoading();
				me.showpro();

			
			} else {
				if(typeof(error)=="function")
				error("请上传图片格式的文件！");
				// ajaxLoadEnd();
				// $("#uploadState").hide();
			}
		} else {
			if(typeof(error)=="function")
			error("请选择上传文件！");
			// $("#uploadState").hide();
		}
	}		


	
	FileUploadMuti.prototype.uploaddone=function(jsonStr){
		var me = this;
		var obj = JSON.parse(jsonStr);

		// var obj =
		// document.getElementById("fileUploadFrame").contentWindow;
		var uploadUrl = null;
		var fileMd5 = null;
		var fileid = null;
		// if(obj.document.getElementById("uploadUrl")!=null)
		uploadUrl = obj.relativeURL;

		// if(obj.document.getElementById("returnMsg")!=null)
		returnMsg = obj.ResponseMsg;

		if (uploadUrl != null && uploadUrl != "") {
			if (uploadUrl == '202') {
				error("请选择上传文件！");
			} else if (uploadUrl == '203') {
				error("文件上传失败！");
			} else if (uploadUrl == '204') {
				error("请选择要上传的文件！");
			} else if (uploadUrl == '205') {
				// error("文件上传失败，文件大小超过30M！");
				// alert("文件上传失败，文件大小超过30M！");
				error(returnMsg);
			} else if (uploadUrl == '206') {
				error("文件已经存在！");
			} else {
				// $("#URL").val(uploadUrl);

				me.imgcontainer.find("#" + me.options.cur_file_id + "_fileId").val(
						obj.fileid);

				me.imgcontainer.find("#" + me.options.cur_file_id).attr("src",
						$("#val2").val() + uploadUrl);

				$("body").find("#myModal_file").modal("hide");

				if(typeof(info)=="function")
				info("文件上传成功！");
			}

		}
	}
	
	/**
	 * xhr complete
	 */
	FileUploadMuti.prototype.uploadComplete=function(evt){
		
		$imgs.uploaddone(evt.target.responseText);
	}
	
	FileUploadMuti.prototype.uploadFailed=function(jsonStr){
	
		if(typeof(error)=="function")
			error("上传失败！");
		else
			alert("上传失败！");
	}
	
	// 取消上传
	FileUploadMuti.prototype.cancleUploadFile=function(jsonStr){
		
		
		$imgs.xhr.abort();
	}
	
	/**
	 * 显示上传进度条
	 */
	FileUploadMuti.prototype.showpro=function(jsonStr){
		var me = this;
		$("body").find(".hrback").show();
		$("body").find(".hrpro").show();
	}
	

	// 隐藏上传进度条
	FileUploadMuti.prototype.hidepro=function(jsonStr){
		var me = this;
		$("body").find(".hrback").hide();
		$("body").find(".hrpro").hide();
	}
	
	// 进度条计算
	FileUploadMuti.prototype.progressFunction=function(evt){
		
		
		if (evt.lengthComputable) {
			// progressBar.max = evt.total;
			// progressBar.value = evt.loaded;
			var per = Math.round(evt.loaded / evt.total * 100);
			// "%";

			$("body").find(".hrpro").css("width", per + "%");

			if (per == 100)
				$imgs.hidepro();

		}
	}
	
	if(typeof(window.$imgs)!="function")
	window.$imgs =new FileUploadMuti();

})(window);

