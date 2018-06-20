var cur_file_id = "1";

function uploadimg(e) {
	$("#myModal_file").modal('show');
	cur_file_id = $(e.target || e).attr("id");

	removedropimg();
}

/**
 * 清除选择
 * 
 * @returns
 */
function removedropimg() {
	imgpre.src = "";
	hidepro();
	$(".imgtip").show();
	$("#fname").val("");

	//$("#imgs").find("#" + cur_file_id + "_fileId").val("");
	// $("#imgs").find("#" + cur_file_id).attr("src",
	// $("#val2").val() + "/img/default.jpg");

}

/**
 * 删除多图片框
 * 
 * @param id
 * @returns
 */
function removeimg(id) {
	$("#imgs").find("#fullurl" + id).parent().remove();

	var index = parseInt($("#imgs_count").val()) - 1;
	$("#imgs_count").val(index);

	if (index < maximgupload)
		$("#imgs").find("#imgadd").show();
}

$(function() {
	$("#uploadState").hide();
	$("#uploadSub").bind("click", function(e) {
		uploadfile();
	});

	/*
	 * $("#closeDiv").bind("click", function(e) { topWin[0].divdlg.close(); });
	 */

	$(".gdimg").click(function(e) {
		uploadimg(e);
	});

	$("#imgadd").click(function(e) {

		addImg();

	});

	$("#btnremoveimg").click(function() {
		removedropimg();
	})

});

var maximgupload = 4;
function initImg(callback, maxnum) {
	$("#imgs").html("");
	$("#imgs_count").val(0);

	// <div class=" col-xs-3 row margin-top-5 gdimgcontainer">
	// <input readonly="readonly" class=" form-control gdimg
	// hidevalidate"
	// type="text" name="fileId" id="fullurl_fileId"
	// placeholder=" ">
	//
	//
	// <img id="fullurl" src="/img/default.jpg"
	// class="img-responsive gdimg"></img>
	//
	// </div>
	//
	// <div id="imgadd" class=" col-xs-3 row margin-top-5
	// gdimgcontainer">
	//
	// <img src="/img/add.png" class="img-responsive "></img>
	//
	// </div>

	var hide = " ";

	maximgupload = maxnum || 4;

	var add = $(' <div id="imgadd" class=" '
			+ hide
			+ 'col-xs-3  row margin-top-5 gdimgcontainer"> '

			+ ' <img src="/img/add.png" onclick=addImg() class="img-responsive "></img> '

			+ ' </div> ');

	$("#imgs").append(add);

	addImg();

	updateNormalValidate("#mform", $("#mform").find("#fullurl1_fileId"),
			"至少需要一张图片");

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
function updateNormalValidate(ele, fieldname, text, isremove) {

	if (isremove)
		$(ele).bootstrapValidator("removeField", fieldname);
	else {
		$(ele).bootstrapValidator("addField", fieldname, {
			validators : {
				notEmpty : {
					message : text
				},

			}
		});
	}
}

function addImg(id, url) {
	var index = parseInt($("#imgs_count").val()) + 1;

	var hide = " hide ";
	if (index > 1)
		hide = "";

	id = id || "";
	url = url || "/img/default.jpg";

	if (id != "") {
		var eitem = null;
		var isexistnullimg = false;
		$.each($(".gdimg"), function(index, item) {
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
			+ ' type="text" name="fileId" id="fullurl'
			+ index
			+ '_fileId"  value="'
			+ id
			+ '"  placeholder=" "> '
			+ ' 	 <span class=" '
			+ hide
			+ 'close imgclose "  onclick="removeimg('
			+ index
			+ ')" >&times;</span> '

			+ ' <img id="fullurl'
			+ index
			+ '" onclick="uploadimg(this)" src="'
			+ url + '" class="img-responsive gdimg"></img> ' + ' </div> ');

	$("#imgs_count").val(index);

	target.insertBefore($("#imgs").find("#imgadd"));

	if (index == maximgupload)
		$("#imgs").find("#imgadd").hide();
}

var dropBox;

window.onload = function() {
	dropBox = document.getElementById("dropBox");
	dropBox.ondragenter = ignoreDrag;
	dropBox.ondragover = ignoreDrag;
	dropBox.ondrop = drop;
}

function ignoreDrag(e) {
	// 因为我们在处理拖放，所以应该确保没有其他元素会取得这个事件
	e.stopPropagation();
	e.preventDefault();
}

function drop(e) {
	// 取消事件传播及默认行为
	e.stopPropagation();
	e.preventDefault();

	// 取得拖进来的文件
	var data = e.dataTransfer;
	var files = data.files;

	// 将其传给真正的处理文件的函数
	processFiles(files);
}

function deletedropimg() {

}

function processFiles(files) {

	$("#fname").val(files[0].name);
	fileObj = files[0];
	$(".imgtip").hide();

	var output = document.getElementById("fileOutput");
	// 创建FileReader
	var reader = new FileReader();
	// 告诉它在准备好数据之后做什么
	reader.onload = function(e) {
		// 使用图像URL来绘制dropBox的背景
		imgpre.src = "" + e.target.result + "";
	};
	// 读取图片
	reader.readAsDataURL(fileObj);
}

var fileObj = null;
// 图片上传
var xhr;
// 上传文件方法
function uploadfile() {

	var fileName = $("#fname").val();
	if (fileName != null && "" != fileName) {
		var fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length);
		if ("png" == fileExtension || "jpeg" == fileExtension
				|| "ico" == fileExtension || "gif" == fileExtension
				|| "jpg" == fileExtension) {

			var url = "/upload/UploadFileXhr"; // 接收上传文件的后台地址

			var form = new FormData(); // FormData 对象
			form.append("fileUploadURL", fileObj); // 文件对象

			xhr = new XMLHttpRequest(); // XMLHttpRequest 对象
			xhr.open("post", url, true); // post方式，url为服务器请求地址，true
			// 该参数规定请求是否异步处理。
			xhr.onload = uploadComplete; // 请求完成
			xhr.onerror = uploadFailed; // 请求失败

			// xhr.responseType ="document"; "text" String字符串
			// "document" Document对象 希望返回 XML 格式数据时使用
			// "json" javascript 对象 存在兼容性问题，IE10/IE11不支持
			// "blob" Blob对象
			// xhr.responseType =

			xhr.upload.onprogress = progressFunction;// 【上传进度调用方法实现】
			xhr.upload.onloadstart = function() {// 上传开始执行方法
				ot = new Date().getTime(); // 设置上传开始时间
				oloaded = 0;// 设置上传开始时，以上传的文件大小为0
			};

			xhr.send(form); // 开始上传，发送form数据

			// ajaxLoading();
			showpro();

			if (sh == null) {
				sh = setInterval(lookupUploadUrl, 500);
			}
		} else {
			ajaxLoadEnd();
			error("请上传图片格式的文件！");
			// ajaxLoadEnd();
			// $("#uploadState").hide();
		}
	} else {
		ajaxLoadEnd();
		error("请选择上传文件！");
		// $("#uploadState").hide();
	}

}

function uploaddone(jsonStr) {

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
			$("#URL").val(uploadUrl);

			$("#imgs").find("#" + cur_file_id + "_fileId").val(obj.fileid);

			$("#imgs").find("#" + cur_file_id).attr("src",
					$("#val2").val() + uploadUrl);

			$("#myModal_file").modal("hide");

			info("文件上传成功！");
		}

		ajaxLoadEnd();

		return;
	}
}

// 上传成功响应
function uploadComplete(evt) {
	// 服务断接收完文件返回的结果

	// $("#fileUploadFrame").html(evt);

	uploaddone(evt.target.responseText);

	/*
	 * showpro(); var per=1; setInterval(() => { $(".hrpro"
	 * ).css("width",per+"%"); per+=10; if(per>100) per=1; }, 40);
	 */

	//	
	//	
	//	
	//	
	//	
	//	
	//	
	// var data = JSON.parse(evt.target.responseText);
	// if(data.success) {
	// alert("上传成功！");
	// }else{
	// alert("上传失败！");
	// }
}
// 上传失败
function uploadFailed(evt) {
	alert("上传失败！");
}
// 取消上传
function cancleUploadFile() {
	xhr.abort();
}

function showpro() {
	$(".hrback").show();
	$(".hrpro").show();
}
function hidepro() {
	$(".hrback").hide();
	$(".hrpro").hide();
}
// 上传进度实现方法，上传过程中会频繁调用该方法
function progressFunction(evt) {

	// var progressBar = document.getElementById("progressBar");
	// var percentageDiv =
	// document.getElementById("percentage");
	// event.total是需要传输的总字节，event.loaded是已经传输的字节。如果event.lengthComputable不为真，则event.total等于0
	if (evt.lengthComputable) {
		// progressBar.max = evt.total;
		// progressBar.value = evt.loaded;
		var per = Math.round(evt.loaded / evt.total * 100);
		// "%";

		$(".hrpro").css("width", per + "%");

		if (per == 100)
			hidepro();

	}

	/*
	 * var time = document.getElementById("time"); var nt = new
	 * Date().getTime();// 获取当前时间 var pertime = (nt-ot)/1000; //
	 * 计算出上次调用该方法时到现在的时间差，单位为s ot = new Date().getTime(); // 重新赋值时间，用于下次计算 var
	 * perload = evt.loaded - oloaded; // 计算该分段上传的文件大小，单位b oloaded =
	 * evt.loaded;// 重新赋值已上传文件大小，用以下次计算 // 上传速度计算 var speed = perload/pertime;//
	 * 单位b/s var bspeed = speed; var units = 'b/s';// 单位名称 if(speed/1024>1){
	 * speed = speed/1024; units = 'k/s'; } if(speed/1024>1){ speed =
	 * speed/1024; units = 'M/s'; } speed = speed.toFixed(1); // 剩余时间 var
	 * resttime = ((evt.total-evt.loaded)/bspeed).toFixed(1); time.innerHTML =
	 * '，速度：'+speed+units+'，剩余时间：'+resttime+'s'; if(bspeed==0) time.innerHTML =
	 * '上传已取消';
	 */
}