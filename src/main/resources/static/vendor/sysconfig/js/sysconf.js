

function upload() {
    var path = document.getElementById("filename").value;
    if ($.trim(path) == "") { alert("请选择要上传的文件"); return; }

    var result_msg = "";
    $.ajaxFileUpload({
        url: '/UpFile.ashx',  //这里是服务器处理的代码
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: 'fu_UploadFile', // 上传文件的id、name属性名
        dataType: 'json', //返回值类型，一般设置为json、application/json
        data: {}, //传递参数到服务器
        success: function (data, status) {
            if (data.Result) {
                alert("文件成功处理完成!" + data.FileName);
            } else {
                alert("文件成功处理出错！原因：" + data.ErrorMessage);
            }
        },
        error: function (data, status, e) {
            alert("错误：上传组件错误，请检察网络!");
        }
    });
}