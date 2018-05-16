var chooseDeviceId;
var choosePictureId;

function showPictureModal(obj) {

    $('#pictureModal').modal('show');
    chooseDeviceId = obj.id;
}

$(".mb").click(function()
{
    $(this).addClass("mb2");
    $(this).children(".xzstyle").show();
    $(this).siblings().removeClass("mb2");
    $(this).siblings().children(".xzstyle").hide();

    choosePictureId = $(this).attr("id")
})


$('#pictureModal').on('hidden.bs.modal', function () {
    $(".mb").each(function () {
        $(this).removeClass("mb2")
        $(this).children(".xzstyle").hide();
    })
})

function submitPictureChange () {
    $.ajax({
        url: "/manager/picture/submitPictureChange",
        type: "POST",
        cache: false,
        data: {
            "deviceId" : chooseDeviceId,
            "pictureId" : choosePictureId
        },
        success:function (data) {
            if (data == -1) {
                swal(
                    'ERROR',
                    '图片更新失败',
                    'error'
                );
            } else {
                swal({
                    title : 'OK',
                    text : '图片更新成功',
                    type : 'success'
                }).then(function () {
                    location.reload();
                });
            }
        }
    })
}