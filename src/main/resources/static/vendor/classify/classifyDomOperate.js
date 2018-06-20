function insertConstantDom(action, level) {
    $(".modal-body > .row > .col-lg-12").append(
        "<div class=\"form-group\" style='display: none'>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"text\" class=\"form-control\" id=\"classifyId\">\n" +
        "    </div>\n" +
        "</div>\n" +
        "<div class=\"form-group\" style='display: none'>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"text\" class=\"form-control\" id=\"topClassifyId\">\n" +
        "    </div>\n" +
        "</div>\n" +
        "<div class=\"form-group\" id=\"classifyName\">\n" +
        "    <label for=\"name\" class=\"col-lg-3 control-label\">类别名称</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"text\" class=\"form-control\" id=\"name\"\n" +
        "               placeholder=\"类别名称\">\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n" +
        "<div class=\"form-group\" id='isEnabled'>\n" +
        "    <label for=\"name\" class=\"col-lg-3 control-label\">是否可用</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <label class=\"radio-inline\"><input id='enabled' type=\"radio\" name=\"isEnabled\" value='1' checked>是</label>\n" +
        "        <label class=\"radio-inline\"><input id='disabled' type=\"radio\" name=\"isEnabled\" value='0'>否</label>\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"sort\" class=\"col-lg-3 control-label\">显示顺序</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"text\" class=\"form-control\" id=\"sort\"\n" +
        "               placeholder=\"显示顺序\">\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n" +
        "<div class=\"form-group\" id=\"topClassifyDiv\">\n" +
        "    <label for=\"topClassify\" class=\"col-lg-3 control-label\">所属类别</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"text\" class=\"form-control\" id=\"topClassify\"\n" +
        "               placeholder=\"所属类别\" disabled>\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"description\" class=\"col-lg-3 control-label\">类别描述</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <textarea class=\"form-control\" id=\"description\"\n" +
        "               placeholder=\"类别描述\"></textarea>\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n"
    )
    $(".modal-footer").append(
        "<button type=\"button\" class=\"btn btn-primary\" id=\"btnSubmit\" data-dismiss=\"modal\">\n" +
        "    提交\n" +
        "</button>"
    )
    bindClickEvent (action, level)
}

function editShowDomCommonInfo(obj, level) {
    var dom = $(obj).parent().parent().prevAll()
    var id = $(dom[12]).text()
    //显示一二三级分类共有属性
    $("#classifyId").attr("value", id);
    $("#name").attr("value", $(dom[11]).text())
    $("#description").text($(dom[10]).text())
    if ($(dom[5]).text() == '0') {
        $("#enabled").removeAttr("checked")
        $("#disabled").attr("checked", true)
    }
    $("#sort").attr("value", $(dom[4]).text());

    //显示一二三级分类不共有属性
    if (level == 1) {
        addFirstClassifyDom(id)
        if ($(dom[6]).text() == '0') {
            $("#display").removeAttr("checked");
            $("#blank").attr("checked", true);
        }
    } else if (level == 2 || level == 3) {
        //二三级分类可以修改上级分类
        $("#topClassifyDiv > div > input").remove()
        $("#topClassifyDiv > div > p").before(
            "<select class=\" form-control\" id='topClassify'>\n" +
            "</select>")
        $.ajax({
            type: "post",
            url: "/manager/classify/getAllTopClassify",
            cache: false,
            data : {"level" : level},
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var optionDom = "<option id='option_" + data[i].id + "' value='"+ data[i].id + "'>" + data[i].name + "</option>";
                    var trDom = $(dom).parent()
                    var topClassifyId = $(trDom).attr("class").split(" ")[1].split("_")[1]
                    $("#topClassify").append(optionDom)
                    if (topClassifyId == data[i].id) {
                        $("#option_" + data[i].id).attr("selected", "selected")
                    }
                }
            }
        })
    }
}

//初始化fileinput控件（第一次初始化）
function initFileInput(ctrlName, uploadUrl, maxFileSize, id) {
    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        uploadExtraData: {"id" : id, "type" : ctrlName},
        allowedFileExtensions : ['jpg', 'png'],//接收的文件后缀
        showUpload: true, //是否显示上传按钮
        showCaption: false,//是否显示标题
        showClose: false,
        dropZoneTitle : "将文件拖拽至此处上传",
        browseClass: "btn btn-primary", //按钮样式
        previewFileIcon: "<i class='glyphicon glyphicon-file'></i>",
        maxFileCount: 1,
        maxFileSize: maxFileSize,
        // enctype : 'multipart/form-data',
        msgFilesTooMany: "选择上传的文件数量{n} 超过允许的最大数值{m}！",
        msgSizeTooLarge : "选择上传的文件大小为{size}KB 超过允许的最大数值{maxSize}KB！",
        fileActionSettings: {
            showUpload: true
        }
    });
}

function addFirstClassifyDom() {
    //是否显示
    $("#isEnabled").after(
        "<div class=\"form-group\">\n" +
        "    <label for=\"name\" class=\"col-lg-3 control-label\">是否显示</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <label class=\"radio-inline\"><input id='display' type=\"radio\" name=\"isDisplay\" value='1' checked>是</label>\n" +
        "        <label class=\"radio-inline\"><input id='blank' type=\"radio\" name=\"isDisplay\" value='0'>否</label>\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n"
    )
    //一级分类不可修改上级分类
    $("#topClassifyDiv").remove()
}

function uploadFileDom() {
    $(".modal-body > .row > .col-lg-12").append(
        //图标
        "<div class=\"form-group\">\n" +
        "    <label for=\"name\" class=\"col-lg-3 control-label\">图标文件上传</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"file\" class=\"form-control\" id=\"iconUpload\" data-preview-file-type=\"text\">\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n" +
        //普通图片文件
        "<div class=\"form-group\">\n" +
        "    <label for=\"name\" class=\"col-lg-3 control-label\">普通图片上传</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"file\" class=\"file\" id=\"pictureUpload\" data-preview-file-type=\"text\">\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n" +
        //大图片文件
        "<div class=\"form-group\">\n" +
        "    <label for=\"name\" class=\"col-lg-3 control-label\">大图片上传</label>\n" +
        "    <div class=\"col-lg-9\">\n" +
        "        <input type=\"file\" class=\"form-control\" id=\"bigPictureUpload\" data-preview-file-type=\"text\">\n" +
        "        <p class=\"help-block\"></p>\n" +
        "    </div>\n" +
        "</div>\n"
    )
}