function ClassifyAdd(obj, level) {
    var headLevel
    if (level == 2) {
        headLevel = "二"
    } else {
        headLevel = "三"
    }
    var dom = $(obj).parent().parent().prevAll();
    var topClassifyName = $(dom[11]).text();
    var topClassifyId = $(dom[12]).text();
    //向模态框插入dom元素
    $("#myModalHeader > h4").append("增加" + headLevel + "级分类")
    insertConstantDom("insert", level)
    $("#topClassify").attr("value",topClassifyName)
    $("#topClassifyId").attr("value",topClassifyId)
    $("#myModal").modal("show");
}

function addSummitClassify() {
    $("#myModalHeader > h4").append("增加一级分类")
    insertConstantDom("insert", 1)
    addFirstClassifyDom()
    $("#myModal").modal("show");
}

function ClassifyEdit(obj, level) {
    $("#myModalHeader > h4").append("分类信息修改")
    insertConstantDom("update", level)
    editShowDomCommonInfo(obj, level)
    $("#myModal").modal("show");
}

function ClassifyDelete(obj, level) {
    var dom = $(obj).parent().parent().prevAll()
    var id = $(dom[12]).text()
    $.ajax({
        type: "post",
        url: "/manager/classify/deleteClassify",
        cache: false,
        data : {"id" : id, "level" : level},
        success: function (data) {
            if (data == 1) {
                swal({
                    title: '删除成功',
                    type: 'success'
                }).then(
                    function () {
                        location.reload()
                    }
                )
            } else {
                swal({
                    title: '删除失败',
                    text: '分类可能已经被关联',
                    type: 'error'
                })
            }
        }
    })
}

$('#myModal').on('hidden.bs.modal', function () {
    $("#myModalHeader > h4").empty()
    if ($("#iconUpload").length > 0) {
        location.reload()
    }
    $(".modal-body > .row > .col-lg-12").empty()
    $("#btnSubmit").remove()
})

function fileModalShow(obj) {
    var dom = $(obj).parent().parent().prevAll()
    var id = $(dom[12]).text()
    $("#myModalHeader > h4").append("一级分类图片修改")
    uploadFileDom()
    initFileInput("iconUpload", "uploadClassifyFile", 512, id)
    initFileInput("pictureUpload", "uploadClassifyFile", 512, id)
    initFileInput("bigPictureUpload", "uploadClassifyFile", 512, id)
    $("#myModal").modal("show");
}

function bindClickEvent (action, level) {
    $("#btnSubmit").on("click", function () {

        var showResult;
        if (action == "update") {
            showResult = "更新"
        } else {
            showResult = "插入"
        }

        var topClassifyId = $("#topClassifyId").val()
        var classifyId = $("#classifyId").val()
        var classifyName = $("#name").val()
        var isEnabled = $("input[name='isEnabled']:checked").val()
        var status = $("input[name='isDisplay']:checked").val()
        var sort = $("#sort").val()
        var description = $("#description").val()
        var parameter = new Object();
        parameter.id = classifyId;
        parameter.name = classifyName;
        parameter.enabled = isEnabled;
        parameter.description = description;
        parameter.sort = sort;
        parameter.action = action;
        parameter.level = level;
        if (level == 1) {
            parameter.status = status
        } else {
            if (action == "update") {
                topClassifyId = $("#topClassify").val()
            }
            parameter.topClassify = topClassifyId
        }
        $.ajax({
            type: "post",
            url: "/manager/classify/classifyOperate",
            cache: false,
            data : parameter,
            success: function (data) {
                if (data == 1) {
                    swal({
                        title: '操作成功',
                        text: "分类" + showResult + "成功",
                        type: 'success'
                    }).then(
                        function () {
                            location.reload()
                        }
                    )
                } else {
                    swal({
                        title: '操作失败',
                        text: "分类" + showResult + "失败",
                        type: 'error'
                    })
                }
            }
        })
    })
}